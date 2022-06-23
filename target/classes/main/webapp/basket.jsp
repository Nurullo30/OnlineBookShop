<%@ page import="java.util.Map" %>
<%@ page import="com.company.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.06.2022
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="css/userPanel.css"%></style>
</head>
<body>
        <div class="header-panel" style="margin-bottom: 20px;">
            <%@include file="navBar.jsp"%>
        </div>
        <% List<BookDTO> products = (List<BookDTO>) session.getAttribute("basketBooks");%>
        <% if (products != null && products.size() != 0){ %>
        <div id="total-block">
            <% double count = 0d;%>
            <% for (BookDTO book : products) {
                count += book.getAmount() * book.getPrice();
            }%>
            <span style="font-size:2.5rem">Total price: $<%=count%></span>
            <a href="checkout.jsp" class="btn btn-primary btn-lg" style="margin: 0 0 20px 20px">Checkout</a>
        </div>
        <table id="basket-table" style="border: 1px solid black; margin: 0 auto;">
            <tr>
                <td>Product Name</td>
                <td>Quantity</td>
                <td>Price</td>
            </tr>
            <% for (BookDTO book : products) {%>
            <tr>
                <td> <%= book.getName() %></td>
                <td>
                     <!-- increment--> <a href="amountControl?action=inc&id=<%=book.getBookId()%>" class="btn btn-primary">+</a>
                    <%= book.getAmount()%>
                    <!-- decrement--> <a href="amountControl?action=dec&id=<%=book.getBookId()%>" class="btn btn-primary">-</a>
                </td>
                <td> <%= book.getPrice() * book.getAmount() %></td>
                <td>
                    <a href="removeProduct?id=<%=book.getBookId()%>" class="btn btn-danger">Remove</a>
                </td>
            </tr>
            <% } %>
        </table>
       <%} else {%>
            <h3 style="text-align: center;">Your basket is empty!</h3>
        <%}%>
</body>
</html>
