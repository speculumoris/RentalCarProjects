package com.saferent.service;

import com.saferent.domain.*;
import com.saferent.domain.enums.*;
import com.saferent.dto.ReservationDTO;
import com.saferent.dto.request.*;
import com.saferent.exception.*;
import com.saferent.exception.message.*;
import com.saferent.mapper.*;
import com.saferent.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public void createReservation(ReservationRequest reservationRequest, User user, Car car) {

        checkReservationTimeIsCorrect(reservationRequest.getPickUpTime(), reservationRequest.getDropOfTime());

        boolean carStatus = checkCarAvailability(car , reservationRequest.getPickUpTime(), reservationRequest.getDropOfTime());

        Reservation reservation = reservationMapper.reservationRequestToReservation(reservationRequest);

        if(carStatus){
            reservation.setStatus(ReservationStatus.CREATED);
        } else {
            throw new BadRequestException(ErrorMessage.CAR_NOT_AVAILABLE_MESSAGE);
        }
        reservation.setCar(car);
        reservation.setUser(user);

        Double totalPrice = getTotalPrice(car, reservationRequest.getPickUpTime(),reservationRequest.getDropOfTime());

        reservation.setTotalPrice(totalPrice);

        reservationRepository.save(reservation);

    }
    // !!! Istenen rezervasyon tarihleri doğru mu ???
    private void checkReservationTimeIsCorrect(LocalDateTime pickUpTime,
                                               LocalDateTime dropOfTime){
        LocalDateTime now = LocalDateTime.now();

        if(pickUpTime.isBefore(now)) {
            throw new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
        }

        //!!! baç. tarihi ve bitiş tarihi biribirine eşit mi
         boolean isEqual = pickUpTime.isEqual(dropOfTime)?true:false;
        // !!! baş. tarihi, bitiş tarihinin öncesinde mi
         boolean isBefore = pickUpTime.isBefore(dropOfTime)?true:false; // !!!

         if(isEqual || !isBefore){
             throw  new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
         }

    }

    // !!! Araç müsait mi ???
    public boolean checkCarAvailability(Car car, LocalDateTime pickUpTime,
                                        LocalDateTime dropOfTime) {

        List<Reservation> existReservations = getConflictReservations(car,pickUpTime,dropOfTime);

        return existReservations.isEmpty();

    }

    // !!! Fiyat Hesaplaması
    public Double getTotalPrice(Car car, LocalDateTime pickUpTime,
                                LocalDateTime dropOfTime){
        Long minutes =  ChronoUnit.MINUTES.between(pickUpTime,dropOfTime);
        double hours = Math.ceil(minutes/60.0);
         return car.getPricePerHour() * hours;

    }

    // !!! Reservasyonlar arası çakışma var mı ???
    private List<Reservation> getConflictReservations(Car car,LocalDateTime pickUpTime,
                                                      LocalDateTime dropOfTime ){
        if(pickUpTime.isAfter(dropOfTime)){
            throw  new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
        }

        ReservationStatus[] status = {ReservationStatus.CANCELED,ReservationStatus.DONE};

        List<Reservation> existReservation =
                reservationRepository.checkCarStatus(car.getId(),pickUpTime,dropOfTime,status);

        return existReservation;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.map(reservations);
    }

    public Page<ReservationDTO> getAllWithPage(Pageable pageable) {

       Page<Reservation>reservationPage= reservationRepository.findAll(pageable);
       return reservationPage.map(reservationMapper::reservationToReservationDTO);

    }

    public void updateReservation(Long reservationId, Car car, ReservationUpdateRequest reservationUpdateRequest) {

    }

    public Reservation getById(Long id) {
        Reservation reservation =  reservationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,id)));

        return reservation;
    }
}












