<%@ page import="java.util.Map" %>
<%@ page import="com.company.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="css/userPanel.css"%></style>
</head>
<body>
    <%@include file="navBar.jsp"%>

    <div id="checkout-form">
        <%
            BookDTO book = (BookDTO) request.getAttribute("book");
        %>
        <ul class="list-group">
            <li class="list-group-item">
                <span style="font-style: italic;color: #cbc9c9">Book name and author: </span>
                <%=book.getName()%>, <%=book.getAuthor()%>
            </li>
            <li class="list-group-item">
                <span style="font-style: italic;color: #cbc9c9">Book amount: </span>
                <%=book.getAmount()%>
            </li>
            <li class="list-group-item">
                <span style="font-style: italic;color: #cbc9c9">Book price:</span>
                <%=book.getPrice()%>
            </li>
            <li class="list-group-item">
                <span style="font-style: italic;color: #cbc9c9">Total price:</span>
                <%=book.getPrice()*book.getAmount()%>
            </li>
        </ul>
        <form action="processOrder?product=<%=book.getBookId()%>" method="post">
            <div class="form-group" style="margin-top: 30px;">
                <label for="inputAddress">Card number</label>
                <input type="text" class="form-control" id="inputAddress" placeholder="1234 5678 91011 1213 ">
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputCity">Month/Year</label>
                    <input type="text" class="form-control" id="inputCity">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputZip">CSV</label>
                    <input type="text" class="form-control" id="inputZip">
                </div>
                <div class="form-group col-md-4">
                    <label for="inputState">Card type</label>
                    <select id="inputState" class="form-control">
                        <option selected>Choose...</option>
                        <option>Uzcard</option>
                        <option>Humo</option>
                        <option>Visa</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Buy</button>
        </form>
    </div>
</body>
</html>
