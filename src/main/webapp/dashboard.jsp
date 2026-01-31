<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.hotelreservation.model.User" %>

<%
    // =========================
    // Session & Auth check
    // =========================
    String ctx = request.getContextPath();
    User loggedUser = (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect(ctx + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>

<!-- ================= NAVBAR ================= -->
<div class="navbar">
    <div class="logo">Ocean View Resort</div>

    <ul>
        <li><a href="<%= ctx %>/dashboard.jsp">Dashboard</a></li>
        <li><a href="<%= ctx %>/reservation.jsp">New Reservation</a></li>
        <li><a href="<%= ctx %>/viewReservations">Reservations</a></li>

        <%-- 🔐 ADMIN ONLY MENU --%>
        <% if ("ADMIN".equals(loggedUser.getRole())) { %>
        <li><a href="<%= ctx %>/users">Manage Users</a></li>
        <li><a href="<%= ctx %>/reports">Reports</a></li>
        <% } %>

        <li><a href="<%= ctx %>/logout">Logout</a></li>
    </ul>
</div>

<!-- ================= PAGE CONTENT ================= -->
<div class="page-container">
    <div class="card">

        <h2>Welcome, <%= loggedUser.getFullName() %></h2>

        <p>
            <strong>Role:</strong>
            <span style="color:#1e3c72; font-weight:bold;">
                <%= loggedUser.getRole() %>
            </span>
        </p>

        <p style="margin-top:20px;">
            Use the menu above to manage reservations
            <% if ("ADMIN".equals(loggedUser.getRole())) { %>
            , users, and reports.
            <% } else { %>
            and guest details.
            <% } %>
        </p>

    </div>
</div>

</body>
</html>
