package com.hotelreservation.controller;

import com.hotelreservation.factory.ReservationFactory;
import com.hotelreservation.model.Guest;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.service.ReservationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {

    private final ReservationService service = new ReservationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // =========================
        // 1️⃣ Guest data
        // =========================
        Guest guest = new Guest.Builder()
                .setName(req.getParameter("guestName"))
                .setAddress(req.getParameter("guestAddress"))
                .setEmail(req.getParameter("guestEmail"))
                .setPhone(req.getParameter("guestPhone"))
                .build();

        // =========================
        // 2️⃣ Reservation data
        // =========================
        int roomId = Integer.parseInt(req.getParameter("roomId"));
        LocalDate checkIn = LocalDate.parse(req.getParameter("checkIn"));
        LocalDate checkOut = LocalDate.parse(req.getParameter("checkOut"));


        // ✅ GET LOGGED-IN USER FROM SESSION
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int createdBy = (int) session.getAttribute("userId");

        Reservation reservation =
                ReservationFactory.createReservation(
                        roomId,
                        checkIn,
                        checkOut,
                        createdBy
                );

        // =========================
        // 3️⃣ Facade call
        // =========================
        boolean success =
                service.createReservationWithGuest(guest, reservation);

        if (success) {
            resp.sendRedirect("viewReservations");
        } else {
            req.setAttribute("error", "Reservation failed");
            req.getRequestDispatcher("reservation.jsp")
                    .forward(req, resp);
        }
    }
}
