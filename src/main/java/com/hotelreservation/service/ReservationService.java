package com.hotelreservation.service;

import com.hotelreservation.model.Reservation;
import com.hotelreservation.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {

    private final ReservationRepository repository =
            new ReservationRepository();

    public boolean createReservation(Reservation reservation) {

        if (reservation == null) return false;

        if (reservation.getGuestId() <= 0 ||
                reservation.getRoomId() <= 0) return false;

        LocalDate in = reservation.getCheckIn();
        LocalDate out = reservation.getCheckOut();

        if (in == null || out == null || in.isAfter(out)) return false;

        return repository.saveReservation(reservation);
    }

    public List<Reservation> getAllReservations() {
        return repository.getAllReservations();
    }
}
