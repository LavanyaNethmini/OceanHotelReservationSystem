package com.hotelreservation.controller;

import com.hotelreservation.service.ReservationService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/viewReservations")
public class ViewReservationsServlet extends HttpServlet {

    private final ReservationService service = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("reservations", service.getAllReservations());
        req.getRequestDispatcher("viewReservations.jsp")
                .forward(req, resp);
    }
}
