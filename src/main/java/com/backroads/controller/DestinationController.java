package com.backroads.controller;

import com.backroads.Backroads;
import com.backroads.common.DestinationDTOToDestination;
import com.backroads.common.DestinationToDestinationDTO;
import com.backroads.common.RandomPhotos;
import com.backroads.dto.DestinationDTO;
import com.backroads.entity.Destination;
import com.backroads.entity.Location;
import com.backroads.repository.DestinationRepository;
import com.backroads.repository.LocationRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.backroads.service.DestinationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value="api/destination")
@CrossOrigin("*")

public class DestinationController {
    @Autowired
    private DestinationService service;
    @Autowired
    private DestinationToDestinationDTO toDTO;
    @Autowired
    private DestinationDTOToDestination toDestination;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DestinationDTO>>findAll(@RequestParam() int pageNum, @RequestParam() int pageSize, @RequestParam() String sortLocation, @RequestParam() String sortSeason,@RequestParam() String order_by) {
        HttpHeaders headers = new HttpHeaders();
        if(sortLocation.equals("*")){
            sortLocation="%";
        }
        if(sortSeason.equals("*")){
            sortSeason="%";
        }
        Page<Destination> destinations=service.search(sortLocation,sortSeason,order_by,pageNum,pageSize);
        headers.add("totalPages", Integer.toString(destinations.getTotalPages()));
        headers.add("access-control-expose-headers","totalPages");
        headers.add("totalElements", Long.toString(destinations.getTotalElements()));
        headers.add("access-control-expose-headers","totalElements");
        return new ResponseEntity<>(toDTO.convert(destinations.getContent()),headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public ResponseEntity<List<DestinationDTO>>findByName(@RequestParam() int pageNum, @RequestParam() int pageSize, @RequestParam() String name) {
        HttpHeaders headers = new HttpHeaders();
        StringBuilder sb =new StringBuilder();
        if(name.equals("*")){
            sb.append("%");
        }else{
            sb.append(name+"%");
        }
        Page<Destination> destinations=service.searchByName(sb.toString(),pageNum,pageSize);
        headers.add("totalPages", Integer.toString(destinations.getTotalPages()));
        headers.add("access-control-expose-headers","totalPages");
        headers.add("totalElements", Long.toString(destinations.getTotalElements()));
        headers.add("access-control-expose-headers","totalElements");
        return new ResponseEntity<>(toDTO.convert(destinations.getContent()),headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<DestinationDTO>findById(@PathVariable Long id){

        Destination destination=service.findOne(id);
        if(destination==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(toDTO.convert(destination), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/photos")
    public ResponseEntity<List<RandomPhotos>>random(){
        List<RandomPhotos>randomPhotos=new ArrayList<>();
        List<Destination> destinations = service.findAll();
        List<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < 8) {

            int random = randomGenerator .nextInt(destinations.size())+1;
            if (!numbers.contains(random)) {
                numbers.add(random);
                Destination destination=service.findOne((long)random);
                randomPhotos.add(new RandomPhotos(destination.getId(),destination.getPicture()));
            }
        }
        return new ResponseEntity<>(randomPhotos,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<DestinationDTO>findByName(@PathVariable Long id) {
        Destination destination=service.findOne(id);
        if(destination==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.delete(destination);
        return new ResponseEntity<>(toDTO.convert(destination), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<DestinationDTO>findByName(@PathVariable Long id , @Validated @RequestBody DestinationDTO destinationDTO) {
       if(!id.equals(destinationDTO.getId())){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       Destination destination = toDestination.convert(destinationDTO);
       service.delete(destination);
        return new ResponseEntity<>(toDTO.convert(destination), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DestinationDTO>findByName(@Validated @RequestBody DestinationDTO destinationDTO) {
        Destination destination=toDestination.convert(destinationDTO);
        if(destination==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(destination);
        return new ResponseEntity<>(toDTO.convert(destination), HttpStatus.OK);
    }
}
