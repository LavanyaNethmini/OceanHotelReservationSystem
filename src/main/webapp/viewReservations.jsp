<%@ page import="java.util.List" %>
<%@ page import="com.hotelreservation.model.Reservation" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

<div class="navbar">
    <h1>Hotel Reservation System</h1>
    <div>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="<%= request.getContextPath() %>/reservation.jsp">Reservation</a>
        <a href="<%= request.getContextPath() %>/viewReservations">Reports</a>
        <a href="<%= request.getContextPath() %>/logout">Logout</a>
    </div>
</div>

<div class="container">
    <div class="card">
        <h2>Reservation Report</h2>

        <%
            List<Reservation> reservations =
                    (List<Reservation>) request.getAttribute("reservations");
        %>

        <table>
            <thead>
            <tr>
                <th>Guest ID</th>
                <th>Room ID</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (reservations != null && !reservations.isEmpty()) {
                    for (Reservation r : reservations) {
            %>
            <tr>
                <td><%= r.getGuestId() %></td>
                <td><%= r.getRoomId() %></td>
                <td><%= r.getCheckIn() %></td>
                <td><%= r.getCheckOut() %></td>
                <td><%= r.getStatus() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" class="no-data">No reservations found</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
