package com.backroads.service;

import com.backroads.entity.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinationService {
    void save(Destination destination);
    void delete(Destination destination);
    Destination findOne(Long id);
    Page<Destination> search(@Param("location_id") String location_id,@Param("season_id") String season_id,@Param("order_by") String order_by,int pageNum,int pageSize);
    Page<Destination> searchByName(@Param("name") String name,int pageNum,int pageSize);
    List<Destination> findAll();
}
