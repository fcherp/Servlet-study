<%--
  Created by IntelliJ IDEA.
  User: fcher
  Date: 12/13/2024
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #50d096">

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Cities</a></li>
        </ul>
    </nav>
</header>
<% out.print(2*5); %>
<tbody>

</tbody>
</body>
</html>
