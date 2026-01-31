package com.hotelreservation.service;

import com.hotelreservation.model.Guest;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.repository.GuestRepository;
import com.hotelreservation.repository.ReservationRepository;
import com.hotelreservation.repository.RoomRepository;

import java.util.List;

public class ReservationService {

    private final ReservationRepository reservationRepository =
            new ReservationRepository();

    private final GuestRepository guestRepository =
            new GuestRepository();

    private final RoomRepository roomRepository =
            new RoomRepository();

    // ============================
    // CREATE reservation + guest
    // ============================
    public boolean createReservationWithGuest(
            Guest guest,
            Reservation reservation
    ) {

        // 1️⃣ Validate room exists
        if (!roomRepository.existsById(reservation.getRoomId())) {
            throw new IllegalStateException("Room does not exist");
        }

        // 2️⃣ Check room availability
        boolean available =
                reservationRepository.isRoomAvailable(
                        reservation.getRoomId(),
                        reservation.getCheckIn(),
                        reservation.getCheckOut()
                );

        if (!available) {
            throw new IllegalStateException(
                    "Room is not available for selected dates"
            );
        }

        // 3️⃣ Handle guest (reuse by phone)
        int guestId;
        Guest existingGuest =
                guestRepository.findByPhone(guest.getPhone());

        if (existingGuest != null) {
            guestId = existingGuest.getGuestId();
        } else {
            guestId = guestRepository.saveGuest(guest);
            if (guestId <= 0) {
                return false;
            }
        }

        // 4️⃣ Build final reservation (Builder pattern)
        Reservation finalReservation =
                new Reservation.Builder()
                        .setGuestId(guestId)
                        .setRoomId(reservation.getRoomId())
                        .setCheckIn(reservation.getCheckIn())
                        .setCheckOut(reservation.getCheckOut())
                        .setStatus(reservation.getStatus())
                        .setCreatedBy(reservation.getCreatedBy())
                        .build();

        // 5️⃣ Save reservation
        return reservationRepository.saveReservation(finalReservation);
    }

    // ============================
    // VIEW reservations
    // ============================
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }
}
