package com.backroads.controller;

import com.backroads.common.ReservationDTOToReservation;
import com.backroads.common.ReservationToReservationDTO;
import com.backroads.dto.ReservationDTO;
import com.backroads.entity.Reservation;
import com.backroads.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/reservation")
@CrossOrigin("*")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationDTOToReservation toReservation;
    @Autowired
    ReservationToReservationDTO toDTO;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ReservationDTO> save(@Validated @RequestBody ReservationDTO reservationDTO){
        Reservation reservation = toReservation.convert(reservationDTO);
        reservationService.save(reservation);
        return new ResponseEntity<>(toDTO.convert(reservation), HttpStatus.CREATED);
    }
}
