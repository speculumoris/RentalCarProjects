package com.saferent.mapper;

import com.saferent.domain.*;
import com.saferent.dto.request.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation reservationRequestToReservation(ReservationRequest reservationRequest);
}
