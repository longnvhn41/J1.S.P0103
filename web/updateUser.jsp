<%-- 
    Document   : updateUser
    Created on : Sep 28, 2021, 12:41:45 AM
    Author     : Nguyen Van Long
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/updateuser.css" rel="stylesheet" media="all">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User user = (User) request.getAttribute("us");
            String title = "Update";
        %>
        <div class="wrapper bg-white mt-sm-5">
        <div id="header">
                <ul id=nav>
                    <li><a href="#">Happy Programming</a></li>                       
                        <li><a href="homepage.jsp">Home Page</a></li>
                        <li><a href="UserController?service=logout">Logout</a></li>                       
                </ul>
        </div>      
    <form class="col-12" action="UserController" method="POST">  
        <div class="row py-2">
            <label class="col-md-6" for="id">ID</label> 
            <input class="col-md-6" type="text" name="id" value="<%=user.getId()%>" readonly >
        </div>
        <div class="row py-2">
             <label class="col-md-6" for="name">Name</label> 
             <input class="col-md-6" type="text" name="name" value="<%=user.getName()%>" > 
        </div>
        <div class="row py-2">
            <label class="col-md-6" for="account">Account</label> 
            <input class="col-md-6" type="text" name="acc"  value="<%=user.getAccount()%>"> 
        </div>
        <div class="row py-2">
            <label class="col-md-6" for="password">Password</label> 
            <input class="col-md-6" type="password" name="pass"  value="<%=user.getPassword()%>"/> 
        </div>
        <div class="row py-2">
            <label class="col-md-6" for="email">Email</label> 
            <input class="col-md-6" type="email" name="email"  value="<%=user.getEmail()%>"> 
        </div>
        <div class="row py-2">
             <label class="col-md-6" for="phone">Phone</label> 
             <input class="col-md-6" type="text" name="phone"  value="<%=user.getPhone()%>"> 
        </div>
        <div class="row py-2">
            <label class="col-md-6" for="dob">Date of birth</label> 
            <input class="col-md-6" type="text" name="dob"  value="<%=user.getDob()%>" > 
        </div>
        <div class="row py-2">
            <label class="col-md-6" for="sex">Gender</label>
            <input type="radio" name="sex" value="1"  ${sessionScope.user.gender==1?"checked":""}/> Male
            <input type="radio" name="sex" value="0"  ${sessionScope.user.gender==0?"checked":""} /> Female
        </div>
        <div class="row py-2">
            <label class="col-md-6" for="address">Address</label> 
            <input class="col-md-6" type="text" name="address" value="<%=user.getAddress()%>">
        </div>
        <input type="submit" value="Update" />
        <input type="reset" value="reset" />
        <input type="hidden" name="service" value="updated" /> 
    </form>
    </body>
</html>
