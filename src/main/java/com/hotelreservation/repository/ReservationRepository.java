//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

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
        String sql = "INSERT INTO reservations (guest_id, room_id, check_in, check_out, status) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnectionManager.getInstance().getConnection();

            boolean var5;
            try {
                PreparedStatement ps = conn.prepareStatement(sql);

                try {
                    ps.setInt(1, reservation.getGuestId());
                    ps.setInt(2, reservation.getRoomId());
                    ps.setDate(3, Date.valueOf(reservation.getCheckIn()));
                    ps.setDate(4, Date.valueOf(reservation.getCheckOut()));
                    ps.setString(5, reservation.getStatus());
                    var5 = ps.executeUpdate() > 0;
                } catch (Throwable var9) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var10) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (conn != null) {
                conn.close();
            }

            return var5;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList();
        String sql = "SELECT * FROM reservations ORDER BY reservation_id DESC";

        try {
            Connection conn = DBConnectionManager.getInstance().getConnection();

            try {
                PreparedStatement ps = conn.prepareStatement(sql);

                try {
                    ResultSet rs = ps.executeQuery();

                    try {
                        while(rs.next()) {
                            Reservation r = new Reservation();
                            r.setReservationId(rs.getInt("reservation_id"));
                            r.setGuestId(rs.getInt("guest_id"));
                            r.setRoomId(rs.getInt("room_id"));
                            r.setCheckIn(rs.getDate("check_in").toLocalDate());
                            r.setCheckOut(rs.getDate("check_out").toLocalDate());
                            r.setStatus(rs.getString("status"));
                            list.add(r);
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var13) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
