package com.backroads.service;

import com.backroads.entity.Season;

import java.util.List;

public interface SeasonService {
    List<Season> findAll();
    void save(Season season);
    Season findOne(Long id);
    void delete(Season season);
}
