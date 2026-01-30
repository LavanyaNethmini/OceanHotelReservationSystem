<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 1/28/2026
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="css/style.css">

<div class="card">
    <h2>Hotel Login</h2>

    <form action="login" method="post">
        <label>Username</label>
        <input type="text" name="username" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit">Login</button>
    </form>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>
</div>

</html>

