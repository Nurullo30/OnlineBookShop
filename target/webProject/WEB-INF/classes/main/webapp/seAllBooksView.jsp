<%@ page import="com.company.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.service.BookStoreImpl" %>
<%@ page import="com.company.DAO.BookDAO" %>
<%@ page import="com.company.factory.ClassFactory" %>
<%@ page import="com.company.constants.BeanConstants" %>
<%@ page import="java.awt.print.Book" %>
<%@ page import="com.company.service.BookStoreService" %>
<%@ page import="com.company.constants.CommonConstants" %>
<%@ page import="com.company.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style><%@include file="css/userPanel.css"%></style>

<% boolean hasPermission = request.getAttribute("status") != null ? (boolean) request.getAttribute("status").equals("success") : false;%>

<%
    List<BookDTO> books = null;
    int pageNo = 0;
    int totalBookNum = 0;
    int count = 0;
    int totalNoOfPages = 0;
    String url = null;

    if(hasPermission){
        books = (List)request.getAttribute("books");
        url =("seeAllBooks?panel=" + (String) request.getAttribute("panel")) + "&" + "page=";
        pageNo = (Integer) request.getAttribute("pageNum");
        totalBookNum = (Integer)request.getAttribute("totalBookNum");
        count = (Integer) request.getAttribute("bookNumber");
        totalNoOfPages = (int) Math.ceil(totalBookNum * 1.0 / 4);
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="header-panel" style="margin-bottom: 20px;">
    <%@include file="navBar.jsp"%>
</div>
    <div id="main-block">

        <div id="left-block">
            <table id ="filterBy-table" width="100%" style="margin-left: auto;">
                <tr>
                    <td>
                        <b> Filter by</b>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="seeAllBooks?panel=seeAllBooks&page=1">All Books</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="seeAllBooks?panel=newBooks&page=1"> New books </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Genre</p>
                        <form action="seeAllBooks?panel=genre&page=1" method="post">
                            <select name="genreVal">
                                <option disabled><a href=""> Выберите героя</a></option>
                                <option value="Education"><a href="">Education</a></option>
                                <option value="Fantastic"><a href="">Fantastic</a></option>
                                <option value="Mystery"><a href="">Mystery</a></option>
                                <option value="Mystery"><a href="">Thriller</a></option>
                                <option value="Mystery"><a href="">Horror</a></option>
                                <option value="Drama"><a href="">Historical</a></option>
                                <option value="Horror|Thriller"><a href="">Historical</a></option>
                            </select>
                            <p><input type="submit" value="search" href="seeAllBooks?panel=genre&page=1"></p>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p> <a href="#"> Price </a></p>

                        <input type="text" placeholder="Minimum price"/>

                        <p><input type="text" placeholder="Maximum price"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Search</p>
                        <input type="text" placeholder="search by phrase">
                    </td>
                </tr>
            </table>
        </div>

        <div class="row">
            <% if(hasPermission){
                for (BookDTO book:books) {%>
            <div class="col-md-3" style="margin-bottom: 20px;">
                <div class="card w-100" style="width: 18rem;" >
                        <img class="card-img-top" src="images/<%=book.getImgName()%>.jpg" alt="Card image cap" style="height: 250px;">
                        <div class="card-body" style="text-align: center">
                            <h3 class="card-title"><%= book.getName()%></h3>
                            <p>Author: <%= book.getAuthor() %></p>
                            <p>Genre: <%= book.getGenre() %> </p>
                            <p>Amount: <%= book.getAmount() %> </p>
                            <p>Price: <%= book.getPrice()%> </p>
                            <a href="myBasket?product=<%=book.getBookId()%>" class="btn btn-dark"><%=UserConstant.ADD_TO_BASKET%></a>
                            <a href="buySingleItem?product=<%=book.getBookId()%>" class="btn btn-primary"><%=UserConstant.BUY_BOOK%></a>
                        </div>
                </div>
            </div>
                <%}
            }%>
        </div>
        <%if(hasPermission && totalNoOfPages > 1){%>
            <div id="pagination-block" style="margin: 0 auto;width:100;">
                <nav aria-label="...">
                    <ul class="pagination">
                        <% String previousEnabled = pageNo > 1 ? "enabled" : "disabled"; %>
                        <li class="page-item <%=previousEnabled%>">
                            <% int previousNum = pageNo;%>
                            <%if(previousNum > 1)
                                previousNum = previousNum - 1;%>
                            <a class="page-link" href="<%=url+String.valueOf(previousNum)%>" tabindex="-1">Previous</a>
                        </li>

                        <%if (totalNoOfPages > 1){
                            for (int i = 0; i < totalNoOfPages; i++) {%>
                                <% String pageNumActive = (i+1) == pageNo ? "active" : "";%>
                                <li class="page-item <%=pageNumActive%>">
                                    <a class="page-link" href="<%=url + String.valueOf(i+1)%>"><%=i+1%></a>
                                </li>
                            <%}
                        }%>

                        <% int nextNum = pageNo; %>
                        <% if (totalBookNum > count && hasPermission){
                            nextNum = nextNum + 1;%>
                        <%}%>
                        <% String nextEnabled = totalBookNum > count ? "enabled" : "disabled"; %>
                        <li class="page-item <%=nextEnabled%>">
                            <a class="page-link" href="<%=url+String.valueOf(nextNum)%>">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        <%}%>
    </div>
</body>
</html>
