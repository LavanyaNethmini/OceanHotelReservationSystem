//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hotelreservation.controller;

import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.User;
import com.hotelreservation.service.ReservationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet({"/reserve"})
public class ReservationServlet extends HttpServlet {
    private ReservationService reservationService;

    public void init() {
        this.reservationService = new ReservationService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loggedUser") != null) {
            User user = (User)session.getAttribute("loggedUser");
            String roomIdStr = request.getParameter("roomId");
            if (roomIdStr != null && !roomIdStr.isEmpty()) {
                int roomId = Integer.parseInt(roomIdStr);
                LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"));
                LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"));
                Reservation reservation = new Reservation();
                reservation.setGuestId(user.getUserId());
                reservation.setRoomId(roomId);
                reservation.setCheckIn(checkIn);
                reservation.setCheckOut(checkOut);
                reservation.setStatus("CREATED");
                boolean success = this.reservationService.createReservation(reservation);
                System.out.println("Reservation success = " + success);
                if (success) {
                    response.sendRedirect("dashboard.jsp");
                } else {
                    request.setAttribute("error", "Reservation failed");
                    request.getRequestDispatcher("reservation.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("error", "Room ID is required");
                request.getRequestDispatcher("reservation.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
