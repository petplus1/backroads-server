package com.backroads.service.impl;

import com.backroads.entity.Reservation;
import com.backroads.repository.ReservationRepository;
import com.backroads.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation findOne(Long id) {
        return reservationRepository.getOne(id);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
