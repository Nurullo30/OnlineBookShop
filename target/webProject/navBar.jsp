<%@ page import="com.company.constants.UserConstant" %>
<%@ page import="com.company.constants.CommonConstants"%>
<%@ page import="com.company.resources.Internationalization" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Bookstore</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <%
        boolean isUserLogged = session.getAttribute("userId") != null;
    %>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                 <%if (isUserLogged) {%>
                    <a class="nav-link" href="myProfile"><%=UserConstant.MY_PROFILE%></a>
                 <%}%>
            </li>
            <li class="nav-item">
                <%if (isUserLogged) {%>
                    <a class="nav-link" href="seeAllBooks?panel=seeAllBooks&page=1"><%=CommonConstants.BOOKS%></a>
                <%}%>
            </li>
            <li class="nav-item">
                <%if (isUserLogged) {%>
                    <a class="nav-link" href="basket.jsp">
                        <% List<BookDTO> productNum = (List<BookDTO>) session.getAttribute("basketBooks");%>
                        <%=UserConstant.MY_ORDERS%>
                        <% if (productNum != null && productNum.size() != 0) { %>
                        <span class="badge badge-danger"><%=productNum.size() %></span>
                        <%}%>
                    </a>
                <%}%>
            </li>
        </ul>

        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            <%if (!isUserLogged) {%>
                <a class="nav-link" href="login">Sign in</a>
                <a class="nav-link" href="Registration">Sign Up</a>
            <%}%>

            <%if (isUserLogged) {%>
                <a class="nav-link" href="logout">Log out</a>
            <%}%>
            <div class="btn-group">
                <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Language
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Eng</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Ru</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Uz</a>
                    <div class="dropdown-divider"></div>
                </div>
            </div>
        </form>
    </div>
</nav>