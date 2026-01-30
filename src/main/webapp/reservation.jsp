<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 1/28/2026
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="css/style.css">

<div class="card">
    <h2>New Reservation</h2>

    <form action="reserve" method="post">

        <label>Room ID</label>
        <input type="number" name="roomId" required>

        <label>Check-in Date</label>
        <input type="date" name="checkIn" required>

        <label>Check-out Date</label>
        <input type="date" name="checkOut" required>

        <button type="submit">Reserve</button>
    </form>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>

    <div class="link">
        <a href="dashboard.jsp">← Back to Dashboard</a>
    </div>
</div>

