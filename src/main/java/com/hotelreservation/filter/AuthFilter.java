//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hotelreservation.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/*"})
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String uri = req.getRequestURI();
        if (!uri.endsWith("login.jsp") && !uri.endsWith("/login") && !uri.contains("/css/") && !uri.contains("/js/") && !uri.endsWith("index.jsp")) {
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("loggedUser") != null) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
