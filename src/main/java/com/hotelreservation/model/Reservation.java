package com.hotelreservation.model;

import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private int guestId;
    private int roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
    private int createdBy; // staff/admin user id

    // Private constructor → forces Builder usage
    private Reservation(Builder builder) {
        this.reservationId = builder.reservationId;
        this.guestId = builder.guestId;
        this.roomId = builder.roomId;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.status = builder.status;
        this.createdBy = builder.createdBy;
    }

    // ===== GETTERS ONLY =====
    public int getReservationId() {
        return reservationId;
    }

    public int getGuestId() {
        return guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getStatus() {
        return status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    // ===== BUILDER =====
    public static class Builder {

        private int reservationId;
        private int guestId;
        private int roomId;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String status;
        private int createdBy;

        public Builder setReservationId(int reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public Builder setGuestId(int guestId) {
            this.guestId = guestId;
            return this;
        }

        public Builder setRoomId(int roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder setCheckIn(LocalDate checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public Builder setCheckOut(LocalDate checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setCreatedBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Reservation build() {

            // ===== Validation =====


            if (checkIn == null || checkOut == null || checkIn.isAfter(checkOut)) {
                throw new IllegalStateException("Invalid reservation dates");
            }

            if (status == null) {
                status = "CONFIRMED";
            }

            return new Reservation(this);
        }
    }
}
