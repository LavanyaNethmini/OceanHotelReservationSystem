//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hotelreservation.controller;

import com.hotelreservation.model.User;
import com.hotelreservation.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.authenticate(username, password);

        if (user != null) {
            HttpSession session = request.getSession();

            // ✅ Store full user (optional but useful)
            session.setAttribute("loggedUser", user);

            // ✅ Store primitives for easy access
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", user.getRole());

            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }


}
