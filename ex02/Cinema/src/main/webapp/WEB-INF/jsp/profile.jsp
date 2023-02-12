<jsp:useBean id="user" scope="session" type="edu.school21.cinema.models.User"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: 20228428
  Date: 09.01.2023
  Time: 00:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
    <style>
        body {
            margin: 0;
            background: #F4F1F8;
        }
        /* внешние границы таблицы серого цвета толщиной 1px */
        table {
            width: 600px;
            border: 1px solid grey;
        }
        /* границы ячеек первого ряда таблицы */
        th {
            background-color: lightgray;
            padding: 10px;
            border: 1px solid grey;
        }
        /* границы ячеек тела таблицы */
        td {
            text-align: center;
            padding: 10px 10px 10px 10px;
            border: 1px solid grey;
        }
    </style>
</head>
<body>

    <p>${user.firstName} ${user.lastName}</p>
    <p>${user.email}</p>

    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>IP</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${user.data}" var="data">
                <tr>
                    <td>${data.date}</td>
                    <td>${data.time}</td>
                    <td>${data.ip}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <img src="images" alt="Not Found"/>
    <form method="post" action="images" enctype="multipart/form-data">
        Choose a file: <input type="file" name="multiPartServlet" />
        <input type="submit" value="Upload" />
    </form>
    <form name="profileForm" action="profile" method="POST">
        <input type="submit" value="Log out" name="exit"/>
    </form>

    <table>
        <thead>
        <tr>
            <th>File name</th>
            <th>Size</th>
            <th>MIME</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${user.images}" var="images">
            <tr>
                <td><a href="/Cinema-1.0-SNAPSHOT/images/${images.uniqueName}">${images.fileName}</a></td>
                <td>${images.size}</td>
                <td>${images.mime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
