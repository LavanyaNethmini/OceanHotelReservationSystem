package com.hotelreservation.factory;

import com.hotelreservation.model.Reservation;

import java.time.LocalDate;

public class ReservationFactory {

    public static Reservation createReservation(
            int roomId,
            LocalDate checkIn,
            LocalDate checkOut,
            int createdBy
    ) {
        return new Reservation.Builder()
                .setRoomId(roomId)
                .setCheckIn(checkIn)
                .setCheckOut(checkOut)
                .setCreatedBy(createdBy)
                .setStatus("CONFIRMED")
                .build();
    }
}
