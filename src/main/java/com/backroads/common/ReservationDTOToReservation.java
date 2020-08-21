package com.backroads.common;

import com.backroads.dto.ReservationDTO;
import com.backroads.entity.Reservation;
import com.backroads.service.DestinationService;
import com.backroads.service.ReservationService;

import com.backroads.service.UserServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservationDTOToReservation implements Converter<ReservationDTO, Reservation> {
    @Autowired
    ReservationService reservationService;
    @Autowired
    UserServiceIterface userService;
    @Autowired
    DestinationService destinationService;
    @Override
    public Reservation convert(ReservationDTO source) {
        Reservation reservation;
        if(source.getId()==null){
            reservation=new Reservation();
        }else{
            reservation=reservationService.findOne(source.getId());
        }
        reservation.setUser(userService.findOne(source.getUserId()));
        reservation.setDestination(destinationService.findOne(source.getDestinationId()));
        return reservation;
    }
}
