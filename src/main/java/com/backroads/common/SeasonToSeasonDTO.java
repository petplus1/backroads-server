package com.backroads.common;

import com.backroads.dto.SeasonDTO;
import com.backroads.entity.Season;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeasonToSeasonDTO implements Converter<Season, SeasonDTO> {
    @Override
    public SeasonDTO convert(Season source) {
        SeasonDTO dto=new SeasonDTO();
        dto.setId(source.getSeason_id());
        dto.setName(source.getName());
        dto.setInd_search(source.getInd_search());
        return dto;
    }
    public List<SeasonDTO> convert(List<Season> list){
        List<SeasonDTO> dtos=new ArrayList<>();
        for(Season s:list){
            dtos.add(convert(s));
        }
        return dtos;
    }
}
