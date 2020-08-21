package com.backroads.service;



import com.backroads.entity.Reservation;

import java.util.List;

public interface ReservationService {
    void delete(Reservation reservation);
    void save (Reservation reservation);
    Reservation findOne(Long id);
    List<Reservation> findAll();
}
