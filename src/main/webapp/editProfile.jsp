<%@ page import="com.company.DTO.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.company.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.company.constants.Constants" %>
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

<% String changeStatus = (String)request.getAttribute("status"); %>

<% if (changeStatus != null && changeStatus.equals(CommonConstants.SUCCESSFUL)){%>
    <h1 style="text-align: center"> Your data has been changed!</h1>
<%}%>

<% UserDTO userDTO = (UserDTO) request.getAttribute("userData"); %>
<%if (changeStatus != null && changeStatus.equals(Constants.FAILED)){%>
    <h3 style="text-align: center"> We could not change data, sorry! Try again</h3>
<%}%>
<table id="myProfile-table">
    <form action="editProfile" method="post">
    <tr>
        <td>
            <p>Name</p>
        </td>
        <td>
            <% String userName = userDTO != null ? userDTO.getName() : ""; %>
            <input type="text" name="name" placeholder="<%=userName%>">
        </td>
    </tr>
    <tr>
        <td>
            <p>Surname</p>
        </td>
        <td>
            <%String userSurname = userDTO != null ? userDTO.getSurname() : "";%>
            <input type="text" name="surname" placeholder="<%=userSurname%>">
        </td>
    </tr>
    <tr>
        <td>
            <p>Age</p>
        </td>
        <td>
            <% String userAge = userDTO != null ? userDTO.getAge() : "";%>
            <input type="number" name="age" placeholder="<%=userAge%>">
        </td>
    </tr>
    <tr>
        <td>
            <p>Gender</p>
        </td>
        <td>
            <div class="form-check" style="float:left">
                <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" value="male">
                <label class="form-check-label" for="exampleRadios1">Male </label>
            </div>
            <div class="form-check" style="float: left;margin-left: 20px;">
                <input class="form-check-input" type="radio" name="gender" id="exampleRadios2" value="Female">
                <label class="form-check-label" for="exampleRadios2">Female </label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input class="btn btn-dark" type="submit" value="Submit"/>
        </td>
    </tr>
    </form>
</table>


</body>
</html>
