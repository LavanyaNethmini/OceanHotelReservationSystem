package com.hotelreservation.controller;

import com.hotelreservation.factory.ReservationFactory;
import com.hotelreservation.model.Guest;
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

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {

    private final ReservationService service = new ReservationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
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

            // =========================
            // 3️⃣ Logged-in user
            // =========================
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("loggedUser") == null) {
                resp.sendRedirect("login.jsp");
                return;
            }

            User loggedUser = (User) session.getAttribute("loggedUser");
            int createdBy = loggedUser.getUserId();

            Reservation reservation =
                    ReservationFactory.createReservation(
                            roomId,
                            checkIn,
                            checkOut,
                            createdBy
                    );

            // =========================
            // 4️⃣ Facade call
            // =========================
            service.createReservationWithGuest(guest, reservation);

            resp.sendRedirect("viewReservations");

        } catch (IllegalStateException ex) {
            // ✅ Business-rule error → show on UI
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("reservation.jsp")
                    .forward(req, resp);

        } catch (Exception ex) {
            // ❌ Unexpected error
            ex.printStackTrace();
            req.setAttribute("error", "Unexpected error. Please try again.");
            req.getRequestDispatcher("reservation.jsp")
                    .forward(req, resp);
        }
    }
}
