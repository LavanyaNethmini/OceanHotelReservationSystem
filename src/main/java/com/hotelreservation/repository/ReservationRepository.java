package com.hotelreservation.repository;

import com.hotelreservation.model.Reservation;
import com.hotelreservation.util.DBConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    public boolean saveReservation(Reservation reservation) {

        String sql =
                "INSERT INTO reservations " +
                        "(guest_id, room_id, check_in, check_out, status, created_by) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reservation.getGuestId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, Date.valueOf(reservation.getCheckIn()));
            ps.setDate(4, Date.valueOf(reservation.getCheckOut()));
            ps.setString(5, reservation.getStatus());
            ps.setInt(6, reservation.getCreatedBy());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reservation> getAllReservations() {

        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations ORDER BY reservation_id DESC";

        try (Connection conn = DBConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reservation reservation =
                        new Reservation.Builder()
                                .setReservationId(rs.getInt("reservation_id"))
                                .setGuestId(rs.getInt("guest_id"))
                                .setRoomId(rs.getInt("room_id"))
                                .setCheckIn(rs.getDate("check_in").toLocalDate())
                                .setCheckOut(rs.getDate("check_out").toLocalDate())
                                .setStatus(rs.getString("status"))
                                .setCreatedBy(rs.getInt("created_by"))
                                .build();

                list.add(reservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
