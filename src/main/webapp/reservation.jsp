<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Reservation</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- ================= NAVBAR ================= -->
<div class="navbar">
    <div class="logo">Ocean View Resort</div>
    <ul>
        <li><a href="dashboard.jsp">Dashboard</a></li>
        <li><a href="viewReservations">Reservations</a></li>
        <li><a href="logout">Logout</a></li>
    </ul>
</div>

<!-- ================= PAGE CONTENT ================= -->
<div class="container">
    <div class="card">

        <h2>Create New Reservation</h2>

        <form action="reservation" method="post">

            <!-- ===== Guest Section ===== -->
            <h3>Guest Details</h3>

            <label>Contact Number</label>
            <input type="text"
                   id="guestPhone"
                   name="guestPhone"
                   placeholder="Enter contact number"
                   required>

            <p class="hint">
                If the phone number already exists, the guest details will be reused.
            </p>

            <label>Full Name</label>
            <input type="text"
                   id="guestName"
                   name="guestName"
                   placeholder="Enter guest full name"
                   required>

            <label>Address</label>
            <input type="text"
                   id="guestAddress"
                   name="guestAddress"
                   placeholder="Enter guest address"
                   required>

            <label>Email</label>
            <input type="email"
                   id="guestEmail"
                   name="guestEmail"
                   placeholder="Enter email address">

            <hr>

            <!-- ===== Reservation Section ===== -->
            <h3>Reservation Details</h3>

            <label>Room ID</label>
            <input type="number"
                   name="roomId"
                   placeholder="Room number"
                   required>

            <label>Check-in Date</label>
            <input type="date"
                   name="checkIn"
                   required>

            <label>Check-out Date</label>
            <input type="date"
                   name="checkOut"
                   required>

            <button type="submit">Confirm Reservation</button>
        </form>

        <%-- Context path for AJAX --%>
        <script>
            const contextPath = "<%= request.getContextPath() %>";
        </script>

        <script>
            document.getElementById("guestPhone").addEventListener("blur", function () {

                const phone = this.value.replace(/\s+/g, "");
                if (phone === "") return;

                fetch(contextPath + "/findGuestByPhone?phone=" + encodeURIComponent(phone))
                    .then(res => res.json())
                    .then(data => {
                        if (data.exists) {
                            document.getElementById("guestName").value = data.name;
                            document.getElementById("guestAddress").value = data.address;
                            document.getElementById("guestEmail").value = data.email;
                        }
                    })
                    .catch(err => console.error("Guest lookup failed:", err));
            });
        </script>

        <!-- ===== Error Message ===== -->
        <% if (request.getAttribute("error") != null) { %>
        <div class="error">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <div class="link">
            <a href="dashboard.jsp">← Back to Dashboard</a>
        </div>

    </div>
</div>

</body>
</html>
