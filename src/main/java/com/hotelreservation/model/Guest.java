package com.hotelreservation.model;

public class Guest {

    private int guestId;
    private String name;
    private String address;
    private String email;
    private String phone;

    // Private constructor (forces use of Builder)
    private Guest(Builder builder) {
        this.guestId = builder.guestId;
        this.name = builder.name;
        this.address = builder.address;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    // Getters only (immutability after creation)
    public int getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    // ===== BUILDER =====
    public static class Builder {

        private int guestId;
        private String name;
        private String address;
        private String email;
        private String phone;

        public Builder setGuestId(int guestId) {
            this.guestId = guestId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Guest build() {
            if (name == null || phone == null) {
                throw new IllegalStateException("Guest name and phone are required");
            }
            return new Guest(this);
        }
    }
}
