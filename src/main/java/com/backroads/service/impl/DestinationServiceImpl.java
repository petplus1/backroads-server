package com.backroads.service.impl;

import com.backroads.entity.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.backroads.repository.DestinationRepository;
import com.backroads.service.DestinationService;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository repository;
    @Override
    public void save(Destination destination) {
        repository.save(destination);
    }
    @Override
    public void delete(Destination destination) {
        repository.delete(destination);
    }
    @Override
    public Destination findOne(Long id) {
        return repository.getOne(id);
    }
    @Override
    public Page<Destination> searchByName(String name,int pageNum,int pageSize){
        return repository.searchByName(name,new PageRequest(pageNum,pageSize,Sort.by("name")));
    }

    @Override
    public Page<Destination> search(String location_id, String season_id, String order_by, int pageNum, int pageSize) {
        return repository.search(location_id,season_id,new PageRequest(pageNum,pageSize,Sort.by(order_by)));
    }

    @Override
    public List<Destination> findAll() {
        return repository.findAll();
    }


}
