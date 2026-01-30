//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hotelreservation.service;

import com.hotelreservation.model.Reservation;
import com.hotelreservation.repository.ReservationRepository;
import java.util.List;

public class ReservationService {
    private ReservationRepository reservationRepository = new ReservationRepository();

    public boolean createReservation(Reservation reservation) {
        return this.reservationRepository.saveReservation(reservation);
    }

    public List<Reservation> getAllReservations() {
        return this.reservationRepository.getAllReservations();
    }
}
