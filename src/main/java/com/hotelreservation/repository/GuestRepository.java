package com.hotelreservation.repository;

import com.hotelreservation.model.Guest;
import com.hotelreservation.util.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GuestRepository {

    public int saveGuest(Guest guest) {

        String sql =
                "INSERT INTO guests (name, address, email, phone) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getAddress());
            ps.setString(3, guest.getEmail());
            ps.setString(4, guest.getPhone());

            ps.executeUpdate();

            // Get generated guest_id
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // indicates failure
    }
}
