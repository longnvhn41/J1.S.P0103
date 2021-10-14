<%-- 
    Document   : verifyResetPass
    Created on : Oct 12, 2021, 11:21:21 AM
    Author     : GHC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        body{
            background-image: url(https://anhdephd.com/wp-content/uploads/2018/08/hinh-anh-dep.jpg);

        }
        .container{
            background-color: #fbe9e9;
            height: 150px;
            width: 320px;
            text-align: center;
            position: absolute;
            left: 50%;
            top: 50%;
            padding: 30px;
            border: 1px dashed;
            border-color: red;
            transform: translate(-50%, -90%);
            .h4{
                padding-bottom: 10px;
            }  
        }
    </style>
    <body>

        <div class="container">
            <form action="forgetPass?service=checkCode" method="POST">
                <h4 style="color: red">${error}</h4> 
                Check Code <input type="text" name="authcode"/><br>
                New password: <input type="password" value="${tempPass}" name="newPass"/><br>
                <input style="margin-top: 15px" type="submit" value="submit"/>
            </form>
        </div>


    </body>
</html>
