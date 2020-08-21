package com.backroads.repository;


import com.backroads.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);
    @Query("select l from Location  l where l.name like :name")
    Page<Location> searchByName(@Param("name")String name, Pageable pageable);
}
