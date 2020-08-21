package com.backroads.common;

import com.backroads.dto.LocationDTO;
import com.backroads.entity.Location;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationToLocationDTO implements Converter<Location, LocationDTO> {

    @Override
    public LocationDTO convert(Location source) {
        LocationDTO dto=new LocationDTO();
        dto.setId(source.getLocation_id());
        dto.setName(source.getName());
        dto.setInd_search(source.getInd_search());
        return dto;
    }
    public List<LocationDTO> convert(List<Location> list){
        List<LocationDTO> dtos=new ArrayList<>();
        for(Location l : list){
            dtos.add(convert(l));
        }
        return dtos;
    }
}
