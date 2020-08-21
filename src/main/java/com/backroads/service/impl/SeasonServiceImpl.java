package com.backroads.service.impl;

import com.backroads.entity.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backroads.repository.SeasonRepository;
import com.backroads.service.SeasonService;

import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    private SeasonRepository repository;
    @Override
    public List<Season> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Season season) {
        repository.save(season);
    }

    @Override
    public Season findOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void delete(Season season) {
        repository.delete(season);
    }


}
