package com.backroads.controller;

import com.backroads.common.DestinationToDestinationDTO;
import com.backroads.common.SeasonToSeasonDTO;
import com.backroads.dto.DestinationDTO;
import com.backroads.dto.SeasonDTO;
import com.backroads.entity.Destination;
import com.backroads.entity.Season;
import com.backroads.repository.DestinationRepository;
import com.backroads.service.DestinationService;
import com.backroads.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/season")
@CrossOrigin("*")
public class SeasonController {
    @Autowired
    private SeasonService service;
    @Autowired
    private SeasonToSeasonDTO toDTO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SeasonDTO>> findAll() {
        List<Season> seasons=service.findAll();
        return new ResponseEntity<>(toDTO.convert(seasons),HttpStatus.OK);
    }
}
