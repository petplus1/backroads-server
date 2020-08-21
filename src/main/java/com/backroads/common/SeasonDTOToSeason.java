package com.backroads.common;

import com.backroads.dto.SeasonDTO;
import com.backroads.entity.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.backroads.service.SeasonService;

@Component
public class SeasonDTOToSeason implements Converter<SeasonDTO, Season> {
    @Autowired
    private SeasonService service;
    @Override
    public Season convert(SeasonDTO source) {
        Season season;
        if(source.getId()==null){
            season=new Season();
        }else{season=service.findOne(source.getId());}
        season.setName(source.getName());
        season.setInd_search(source.getInd_search());
        return season;
    }
}
