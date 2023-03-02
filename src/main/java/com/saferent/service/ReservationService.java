package com.saferent.service;

import com.saferent.domain.Car;
import com.saferent.domain.User;
import com.saferent.dto.request.ReservationRequest;
import com.saferent.exception.BadRequestException;
import com.saferent.exception.message.ErrorMessage;
import com.saferent.repository.*;
import org.springframework.stereotype.*;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(ReservationRequest reservationRequest, User user, Car car) {
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
}
