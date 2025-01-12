<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="org.example.web.MainServlet"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>City database test</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Style.css?v=1.1"  type="text/css">
</head>
<body>
<div class="background"></div>

    <nav class="navbar">
        <div class="title">
            <a class="navbar-brand">City database test</a>
        </div>
        <div class="navbar-link">
        <ul class="nav-ul">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link"><p class="link-text">List of cities</p></a></li>
        </ul>
        </div>
    </nav>
<br>

<div class="container" id="container-form">
    <div class="card">
        <div class="card-body">
            <c:if test="${city != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${city == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${city != null}">
                                Edit data about the city
                            </c:if>
                            <c:if test="${city == null}">
                                Add a new city
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${city != null}">
                        <input type="hidden" name="id" value="<c:out value='${city.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>City name:</label> <input type="text"
                                                        value="<c:out value='${city.cityName}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Population:</label> <input type="text"
                                                         value="<c:out value='${city.population}' />" class="form-control"
                                                         name="population">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Country:</label> <input type="text"
                                                           value="<c:out value='${city.country}' />" class="form-control"
                                                           name="country">
                    </fieldset>

                    <button type="submit" class="btn">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>