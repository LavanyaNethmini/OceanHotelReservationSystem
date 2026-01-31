package com.hotelreservation.filter;

import com.hotelreservation.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebFilter("/*")
public class AuthFilter implements Filter {

    // URL → allowed roles
    private static final Map<String, List<String>> ROLE_MAP = new HashMap<>();

    static {
        ROLE_MAP.put("/dashboard.jsp", Arrays.asList("ADMIN", "STAFF"));
        ROLE_MAP.put("/reservation", Arrays.asList("ADMIN", "STAFF"));
        ROLE_MAP.put("/viewReservations", Arrays.asList("ADMIN", "STAFF"));

        ROLE_MAP.put("/users", Arrays.asList("ADMIN"));
        ROLE_MAP.put("/reports", Arrays.asList("ADMIN"));
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();
        String ctx = req.getContextPath();

        // ✅ ALLOW PUBLIC PAGES & STATIC FILES
        if (path.equals("/login.jsp")
                || path.equals("/login")
                || path.equals("/logout")
                || path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.startsWith("/images/")) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        User user = (session != null)
                ? (User) session.getAttribute("loggedUser")
                : null;

        // ❌ Not logged in
        if (user == null) {
            resp.sendRedirect(ctx + "/login.jsp");
            return;
        }

        // 🔐 Role-based access check
        if (ROLE_MAP.containsKey(path)) {
            List<String> allowedRoles = ROLE_MAP.get(path);

            if (!allowedRoles.contains(user.getRole())) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return;
            }
        }

        // ✅ Authorized
        chain.doFilter(request, response);
    }
}
