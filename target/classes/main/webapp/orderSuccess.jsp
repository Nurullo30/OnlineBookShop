<%@ page import="java.util.Map" %>
<%@ page import="com.company.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
    <style>
        body {
            text-align: center;
            /* padding: 40px 0;
            background: #EBF0F5; */
        }
        #successText {
            color: #88B04B;
            font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
            font-weight: 900;
            font-size: 40px;
            margin-bottom: 10px;
        }
        .receivedPurchase {
            color: #404F5E;
            font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
            font-size:20px;
            margin: 0;
        }
        .checkmark {
            color: #9ABC66;
            font-size: 100px;
            line-height: 200px;
            margin:0 auto;
        }
        .card {
            background: white;
            padding: 60px;
            border-radius: 4px;
            box-shadow: 0 2px 3px #C8D0D8;
            display: inline-block;
            margin: 0 auto;
            width: 400px;
            text-align:center;
        }
    </style>
</head>
<body>
    <div id="header">
    <%@include file="navBar.jsp"%>
    </div>
    <div class="card" style="margin-top:100px;">
        <div style="border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;">
            <i class="checkmark">✓</i>
        </div>
        <span id="successText">Success</span>
        <p id="receivedPurchase">We received your purchase request;<br/> we'll be in touch shortly!</p>
    </div>
</body>
</html>






