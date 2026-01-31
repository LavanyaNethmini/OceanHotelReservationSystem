package com.hotelreservation.service;

import com.hotelreservation.factory.ReservationFactory;
import com.hotelreservation.model.Guest;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.repository.GuestRepository;
import com.hotelreservation.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {

    private final GuestRepository guestRepository =
            new GuestRepository();

    private final ReservationRepository reservationRepository =
            new ReservationRepository();

    /**
     * Creates a reservation by first saving the guest and then
     * creating the reservation linked to that guest.
     */
    public boolean createReservation(
            Guest guest,
            int roomId,
            LocalDate checkIn,
            LocalDate checkOut,
            int staffId) {

        // ===== Business Validation =====
        if (guest == null || staffId <= 0 || roomId <= 0) {
            return false;
        }

        if (checkIn == null || checkOut == null || checkIn.isAfter(checkOut)) {
            return false;
        }

        // ===== Persist Guest =====
        int guestId = guestRepository.saveGuest(guest);

        if (guestId <= 0) {
            return false;
        }

        // ===== Create Reservation via Factory =====
        Reservation reservation =
                ReservationFactory.createReservation(
                        guestId, roomId, checkIn, checkOut, staffId);

        // ===== Persist Reservation =====
        return reservationRepository.saveReservation(reservation);
    }

    /**
     * Retrieves all reservations.
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }
}
