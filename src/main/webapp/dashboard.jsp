<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 1/28/2026
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.hotelreservation.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<link rel="stylesheet" href="css/style.css">

<!-- NAVBAR -->
<div class="navbar">
    <div class="logo">Hotel Reservation System</div>

    <ul>
        <li><a href="dashboard.jsp">Dashboard</a></li>
        <li><a href="reservation.jsp">Reservation</a></li>
        <li><a href="<%= request.getContextPath() %>/viewReservations">Reports</a></li>
        <li><a href="logout">Logout</a></li>
    </ul>
</div>

<!-- PAGE CONTENT -->
<div class="page-container">
    <div class="dashboard-card">
        <h2>Welcome, <%= user.getFullName() %></h2>
        <p><strong>Role:</strong> <%= user.getRole() %></p>

        <p style="margin-top:20px;">
            Use the menu above to manage reservations and reports.
        </p>
    </div>
</div>


