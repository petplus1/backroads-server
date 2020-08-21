package com.backroads.controller;

import com.backroads.common.LocationDTOToLocation;
import com.backroads.common.LocationToLocationDTO;
import com.backroads.common.SeasonToSeasonDTO;
import com.backroads.dto.DestinationDTO;
import com.backroads.dto.LocationDTO;
import com.backroads.dto.SeasonDTO;
import com.backroads.entity.Destination;
import com.backroads.entity.Location;
import com.backroads.entity.Season;
import com.backroads.service.LocationService;
import com.backroads.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/location")
@CrossOrigin("*")
public class LocationController {
    @Autowired
    private LocationService service;
    @Autowired
    private LocationToLocationDTO toDTO;
    @Autowired
    private LocationDTOToLocation toLocation;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LocationDTO>> findAll() {
        List<Location> locations=service.findAll();
        return new ResponseEntity<>(toDTO.convert(locations), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<LocationDTO> delete(@PathVariable Long id){
        Location l=service.findOne(id);
        if(l==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.delete(l);
        return new ResponseEntity<>(toDTO.convert(l),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public ResponseEntity<List<LocationDTO>>findByName(@RequestParam() int pageNum, @RequestParam() int pageSize, @RequestParam() String name) {
        HttpHeaders headers = new HttpHeaders();
        StringBuilder sb =new StringBuilder();
        if(name.equals("*")){
            sb.append("%");
        }else{
            sb.append(name+"%");
        }
        Page<Location> destinations=service.searchByName(sb.toString(),pageNum,pageSize);
        headers.add("totalPages", Integer.toString(destinations.getTotalPages()));
        headers.add("access-control-expose-headers","totalPages");
        headers.add("totalElements", Long.toString(destinations.getTotalElements()));
        headers.add("access-control-expose-headers","totalElements");
        return new ResponseEntity<>(toDTO.convert(destinations.getContent()),headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LocationDTO> save(@RequestBody LocationDTO locationDTO){
        Location l=toLocation.convert(locationDTO);
        if(l==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(l);
        return new ResponseEntity<>(toDTO.convert(l),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<LocationDTO> edit(@PathVariable Long id,
                                            @Validated @RequestBody LocationDTO locationDTO){
        if(!id.equals(locationDTO.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Location l=toLocation.convert(locationDTO);
        service.save(l);
        return new ResponseEntity<>(toDTO.convert(l),HttpStatus.OK);
    }

}
