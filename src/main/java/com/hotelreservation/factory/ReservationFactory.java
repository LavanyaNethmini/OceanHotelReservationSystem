package com.hotelreservation.factory;

import com.hotelreservation.model.Reservation;

import java.time.LocalDate;

public class ReservationFactory {

    /**
     * Creates a reservation for a guest by a staff/admin user.
     */
    public static Reservation createReservation(
            int guestId,
            int roomId,
            LocalDate checkIn,
            LocalDate checkOut,
            int createdBy) {

        return new Reservation.Builder()
                .setGuestId(guestId)
                .setRoomId(roomId)
                .setCheckIn(checkIn)
                .setCheckOut(checkOut)
                .setStatus("CONFIRMED")
                .setCreatedBy(createdBy)
                .build();
    }
}
