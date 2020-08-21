package com.backroads.service;


import com.backroads.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface LocationService {
    void delete(Location location);
    void save (Location location);
    Location findOne(Long id);
    List<Location> findAll();
    Location findByName(String name);
    Page<Location> searchByName(@Param("name") String name, int pageNum, int pageSize);
}
