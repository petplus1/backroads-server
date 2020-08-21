package com.backroads.common;

import com.backroads.dto.DestinationDTO;
import com.backroads.entity.Destination;
import com.backroads.service.LocationService;
import com.backroads.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.backroads.service.DestinationService;

@Component
public class DestinationDTOToDestination implements Converter<DestinationDTO, Destination> {
    @Autowired
    private DestinationService service;
    @Autowired
    private LocationService locationService;
    @Autowired
    private SeasonService seasonService;
    @Override
    public Destination convert(DestinationDTO source) {
        Destination destination;
        if(source.getId()==null){
            destination=new Destination();
        }else{
            destination=service.findOne(source.getId());
        }
        destination.setName(source.getName());
        destination.setCountry(source.getCountry());
        destination.setDescription(source.getDescription());
        destination.setPicture(source.getPicture());
        destination.setPrice(source.getPrice());
        destination.setD_from(source.getFrom());
        destination.setD_to(source.getTo());
        destination.setLocation(locationService.findOne(source.getLocation().getId()));
        destination.setSeason(seasonService.findOne(source.getSeason().getId()));
        return destination;
    }
}
