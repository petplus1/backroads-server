package com.backroads.common;

import com.backroads.dto.LocationDTO;
import com.backroads.dto.ReservationDTO;

import com.backroads.entity.Location;
import com.backroads.entity.Reservation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationToReservationDTO implements Converter<Reservation, ReservationDTO> {


    @Override
    public ReservationDTO convert(Reservation source) {
        ReservationDTO dto=new ReservationDTO();
        dto.setId(source.getId());
        dto.setUserId(source.getUser().getId());
        dto.setDestinationId(source.getDestination().getId());
        return dto;
    }
    public List<ReservationDTO> convert(List<Reservation> list){
        List<ReservationDTO> dtos=new ArrayList<>();
        for(Reservation l : list){
            dtos.add(convert(l));
        }
        return dtos;
    }
}
