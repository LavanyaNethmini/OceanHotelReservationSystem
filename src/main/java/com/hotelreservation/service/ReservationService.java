package com.hotelreservation.service;

import com.hotelreservation.model.Guest;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.repository.GuestRepository;
import com.hotelreservation.repository.ReservationRepository;

import java.util.List;

public class ReservationService {

    private final ReservationRepository reservationRepository =
            new ReservationRepository();

    private final GuestRepository guestRepository =
            new GuestRepository();

    // ============================
// CREATE reservation + guest
// ============================
    public boolean createReservationWithGuest(
            Guest guest,
            Reservation reservation
    ) {

        int guestId;

        // 1️⃣ Check if guest already exists (by phone)
        Guest existingGuest =
                guestRepository.findByPhone(guest.getPhone());

        if (existingGuest != null) {
            // Reuse existing guest
            guestId = existingGuest.getGuestId();
        } else {
            // Save new guest
            guestId = guestRepository.saveGuest(guest);
            if (guestId <= 0) {
                return false;
            }
        }

        // 2️⃣ Build reservation with correct guestId
        Reservation finalReservation =
                new Reservation.Builder()
                        .setGuestId(guestId)
                        .setRoomId(reservation.getRoomId())
                        .setCheckIn(reservation.getCheckIn())
                        .setCheckOut(reservation.getCheckOut())
                        .setStatus(reservation.getStatus())
                        .setCreatedBy(reservation.getCreatedBy())
                        .build();

        // 3️⃣ Save reservation
        return reservationRepository.saveReservation(finalReservation);
    }

    // ============================
    // VIEW reservations (FIX 🔥)
    // ============================
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }
}
