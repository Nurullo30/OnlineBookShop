<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.05.2022
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style><%@include file="css/Style.css"%></style>
    <style><%@include file="css/userPanel.css"%></style>
    <title>Registration</title>
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>"/>
<input type="hidden" id="firstname" value="<%= request.getAttribute("firstname")%>"/>
<input type="hidden" id="lastname" value="<%= request.getAttribute("lastname")%>"/>

<div class="main-block">
    <h1>Registration</h1>
    <form action="Registration" method="post">
        <label id="icon" for="name"><i class="fas fa-envelope"></i></label>
        <input type="text" name="firstname" id="name" placeholder="firstname" required/>

        <label id="icon" for="name"><i class="fas fa-envelope"></i></label>
        <input type="text" name="lastname" id="name" placeholder="lastname" required/>

        <label id="icon" for="name"><i class="fas fa-edsUser"></i></label>
        <input type="text" name="age" id="name" placeholder="Age" required/>

        <label id="icon" for="name"><i class="fas fa-edsUser"></i></label>
        <input type="text" name="login" id="name" placeholder="Login" required/>

        <label id="icon" for="name"> <i class="fas fa-unlock-alt"></i></label>
        <input type="password" name="password" id="name" placeholder="Password" required/>
        <hr>
            <div class="gender">
                <input type="radio" name="gender" value="male" id="male" name="gender" checked/>
                <label for="male" class="radio">Male</label>
                <input type="radio" name="gender" value="female" id="female" name="gender" />
                <label for="female" class="radio">Female</label>
            </div>
        <hr>
        <div class="btn-block">
            <p>By clicking Register, you agree on our <a href="https://www.w3docs.com/privacy-policy">Privacy Policy for W3Docs</a>.</p>
            <button type="submit" href="Registration">Submit</button>
        </div>

    </form>
    <a href="index.jsp"><p style="text-align:center; margin:10px; font-size:15px;text-decoration-line: none;">Home</p></a>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.js"></script>

    <script>
        $(document).ready(function(){
            var status = document.getElementById("status").value;
            var lastname = document.getElementById("lastname").value , firstname = document.getElementById("firstname").value ;
            if(status == "SUCCESSFUL"){
                swal("Your info has been changed!");
            } else if (status == "FAILED") {
                swal("Sorry try again! :(", "User with this login already exist" );
            }
        });
    </script>
</div>
</body>
</html>
