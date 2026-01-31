package com.hotelreservation.repository;

import com.hotelreservation.model.Guest;
import com.hotelreservation.util.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GuestRepository {

    // =========================
    // FIND guest by phone
    // =========================
    public Guest findByPhone(String phone) {

        String sql = "SELECT name, address, email, phone FROM guests WHERE phone = ?";

        try (Connection conn = DBConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Guest.Builder()
                            .setName(rs.getString("name"))
                            .setAddress(rs.getString("address"))
                            .setEmail(rs.getString("email"))
                            .setPhone(rs.getString("phone"))
                            .build();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // guest not found
    }

    // =========================
    // SAVE guest
    // =========================
    public int saveGuest(Guest guest) {

        String sql =
                "INSERT INTO guests (name, address, email, phone) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getAddress());
            ps.setString(3, guest.getEmail());
            ps.setString(4, guest.getPhone());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // guest_id
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
