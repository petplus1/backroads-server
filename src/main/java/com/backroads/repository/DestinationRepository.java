package com.backroads.repository;

import com.backroads.entity.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
        @Query("select d from Destination  d where trim(d.location.location_id) like :location_id and  trim(d.season.season_id) like :season_id")
        Page<Destination>search(@Param("location_id") String location_id,
                                @Param("season_id") String season_id,
                                Pageable pageable);

    @Query("select d from Destination  d where d.name like :name")
    Page<Destination> searchByName(@Param("name")String name, Pageable pageable);
}
