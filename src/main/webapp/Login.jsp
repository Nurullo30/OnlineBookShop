<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style><%@include file="css/Style.css"%></style>
    <style><%@include file="css/userPanel.css"%></style>
    <title>Title</title>
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>"/>
<div class="main-block">

    <h1>Sign in</h1>
    <form action="login" method="post">
        <label id="icon" for="name"><i class="fas fa-envelope"></i></label>
        <input type="text" name="login" id="name" placeholder="login" required/>

        <label id="icon" for="name"><i class="fas fa-envelope"></i></label>
        <input type="text" name="password" id="name" placeholder="password" required/>
        <div class="btn-block">
            <a href="https://www.w3docs.com/privacy-policy">Forgot password</a>
            <button type="submit" href="login">Submit</button>
        </div>
    </form>
    <a href="index.jsp"><p style="text-align:center; margin:10px; font-size:15px;text-decoration-line: none;">Home</p></a>
</div>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.js"></script>

    <script>
        $(document).ready(function(){
            var status = document.getElementById("status").value;
            if(status == "failed"){
                swal("Sorry!" , "Wrong username or password :(", "error");
            }
        });
    </script>
</body>
</html>
