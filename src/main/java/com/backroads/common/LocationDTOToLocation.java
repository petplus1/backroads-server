package com.backroads.common;

import com.backroads.dto.LocationDTO;
import com.backroads.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.backroads.service.LocationService;

@Component
public class LocationDTOToLocation implements Converter<LocationDTO, Location> {
    @Autowired
    LocationService service;
    @Override
    public Location convert(LocationDTO source) {
        Location location;
        if(source.getId()==null){
            location=new Location();
        }else{
            location=service.findOne(source.getId());
        }
        location.setName(source.getName());
        location.setInd_search(source.getInd_search());
        return location;
    }
}
