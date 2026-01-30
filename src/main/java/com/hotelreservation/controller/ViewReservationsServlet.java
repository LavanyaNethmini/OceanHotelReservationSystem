//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hotelreservation.controller;

import com.hotelreservation.model.Reservation;
import com.hotelreservation.service.ReservationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/viewReservations"})
public class ViewReservationsServlet extends HttpServlet {
    private ReservationService reservationService;

    public void init() {
        this.reservationService = new ReservationService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = this.reservationService.getAllReservations();
        System.out.println("Reservations fetched: " + reservations.size());
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("viewReservations.jsp").forward(request, response);
    }
}
