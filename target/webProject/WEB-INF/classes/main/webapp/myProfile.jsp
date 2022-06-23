<%@ page import="com.company.service.userPanel.UserService" %>
<%@ page import="com.company.factory.ClassFactory" %>
<%@ page import="com.company.constants.BeanConstants" %>
<%@ page import="com.company.service.userPanel.UserImpl" %>
<%@ page import="com.company.DTO.UserDTO" %>
<%@ page import="com.company.Exceptions.UserNotFoundException" %>
<%@ page import="com.company.entities.Book" %>
<%@ page import="com.company.DAO.BookDAO" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.05.2022
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.Map" %>
<%@ page import="com.company.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String sessionId = (String) session.getAttribute("userId");
    if (sessionId == null){
        response.sendRedirect("GuestView.jsp");
    }
%>
<html>
<head>
    <style><%@include file="css/userPanel.css"%></style>
    <title>Title</title>
</head>
<body>
<div class="header-panel" style="margin-bottom: 20px;">
    <%@include file="navBar.jsp"%>
</div>
<% UserDTO userDTO = (UserDTO) request.getAttribute("userData");
    if (userDTO != null){ %>

<table id="myProfile-table">
    <tr>
        <td>
            <p>UID</p>
        </td>
        <td>
            <p><%=userDTO.getUID()%></p>
        </td>
    </tr>
    <tr>
        <td>
            <p>Name</p>
        </td>
        <td>
            <p><%=userDTO.getName()%></p>
        </td>
    </tr>
    <tr>
        <td>
            <p>Surname</p>
        </td>
        <td>
            <p><%=userDTO.getSurname()%></p>
        </td>
    </tr>
    <tr>
        <td>
            <p>Age</p>
        </td>
        <td>
            <p><%=userDTO.getAge()%></p>
        </td>
    </tr>
    <tr>
        <td>
            <p>Gender</p>
        </td>
        <td>
            <p><%=userDTO.getGender()%></p>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <a href="editProfile">Change Details</a>
        </td>
    </tr>

</table>
<%} else {%>
 <h1> No information found </h1>
<%}%>
</body>
</html>
