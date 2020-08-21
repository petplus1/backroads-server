package com.backroads.common;

import com.backroads.dto.DestinationDTO;
import com.backroads.entity.Destination;
import com.backroads.service.LocationService;
import com.backroads.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DestinationToDestinationDTO implements Converter<Destination, DestinationDTO> {
    @Autowired
    private LocationService serviceLocation;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private SeasonToSeasonDTO toSeasonDTO;
    @Autowired LocationToLocationDTO toLocationDTO;
    @Override
    public DestinationDTO convert(Destination source) {
        DestinationDTO dto =new DestinationDTO();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setCountry(source.getCountry());
        dto.setDescription(source.getDescription());
        dto.setPicture(source.getPicture());
        dto.setPrice(source.getPrice()  );
        dto.setFrom(source.getD_from());
        dto.setTo(source.getD_to());
        dto.setSeason(toSeasonDTO.convert(seasonService.findOne(source.getSeason().getSeason_id())));
        dto.setLocation(toLocationDTO.convert(serviceLocation.findOne(source.getLocation().getLocation_id())));
        return dto;
    }
    public List<DestinationDTO> convert(List<Destination> list){
        List<DestinationDTO> dtos=new ArrayList<>();
        for(Destination d :list){
            dtos.add(convert(d));
        }
        return dtos;
    }
}
