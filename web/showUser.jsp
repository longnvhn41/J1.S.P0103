<%-- 
    Document   : showUser
    Created on : Sep 27, 2021, 11:01:21 PM
    Author     : Nguyen Van Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/showuser.css" rel="stylesheet" media="all">

        <title>User Profile</title>
    </head>
    <body>
        <div id="header">
            <ul id=nav>
                <li><a href="#">Happy Programming</a></li>
                    <c:if test="${sessionScope.user==null}">
                    <li><a href="homepage.jsp">Home Page</a></li>
                    <li><a href="UserController?service=logout">Logout</a></li>
                    </c:if>

                <c:if test="${sessionScope.user!=null}">
                    <li><a href="#" style="padding-right:0">Hello: ${sessionScope.user.account}                              
                        </a></li>

                    <!-- Phân quyền cho mentee-->
                    <c:if test="${sessionScope.user.role==1}">
                        <li><a href="homepage.jsp">Home Page </a></li>
                        </c:if>
                    <!-- Phân quyền cho mentor-->
                    <c:if test="${sessionScope.user.role==0}">
                        <li><a href="#">Request of student</a></li>
                        <li><a href="#">Request</a></li>
                        </c:if>
                    <!-- Phân quyền cho admin--> 
                    <c:if test="${sessionScope.user.role==2}">
                        <li><a href="#">Manager User</a></li>
                        <li><a href="#">Request</a></li>
                        <li><a href="#">Contact</a></li>                            
                        </c:if>

                    <li><a href="UserController?service=logout">Logout</a></li>
                    </c:if>
            </ul>
        </div>

        <div class="wrapper bg-white mt-sm-5">
            <form class="col-12">
                <h1 class="pb-4 border-bottom">USER PROFILE</h1>
                <div class="d-flex align-items-start py-3 border-bottom"> 
                    <li><a href="UserController?service=userProfile&user=${sessionScope.user.account}"
                           style="padding: 0 8px"><img src="${sessionScope.user.ava}" alt="Avatar" class="avatar"></a></li>
                </div> 
                <div class="row py-2">
                    <label class="col-md-6" for="id">ID</label> 
                    <input class="col-md-6" type="text"  value="${sessionScope.user.id}" >
                </div>
                <div class="row py-2">
                    <label class="col-md-6" for="name">Name</label> 
                    <input class="col-md-6" type="text"  value="${sessionScope.user.name}" > 
                </div>
                <div class="row py-2">
                    <label class="col-md-6" for="account">Account</label> 
                    <input class="col-md-6" type="text"  value="${sessionScope.user.account}">
                </div>
                <div class="row py-2">
                    <label class="col-md-6" for="password">Password</label> 
                    <input class="col-md-6" type="password" value="${sessionScope.u.password}%>" readonly />
                </div>
                <div class="row py-2">
                    <label class="col-md-6" for="email">Email</label>
                    <input class="col-md-6" type="email"  value="${sessionScope.user.email}">
                </div>
                <div class="row py-2">
                    <label class="col-md-6" for="phone">Phone</label> 
                    <input class="col-md-6" type="text"  value="${sessionScope.user.phone}">
                </div>
                <div class="row py-2">
                    <label class="col-md-6" for="dob">Date of birth</label> 
                    <input class="col-md-6" type="text"  value="${sessionScope.user.dob}" > 
                </div>   
                <div class="row py-2">
                    <label class="col-md-6" for="gender">Gender</label>
                    ${sessionScope.user.gender==1? "Male" : "Female"}
                </div>
                <div class="row py-2">
                    <label class="col-md-6" for="address">Address</label> 
                    <input class="col-md-6" type="text"  value="${sessionScope.user.address}"> 
                </div>
                <div class="row py-2">
                    <div><a href="UserController?service=update&id=${sessionScope.u.id}">Update</a><br>
                        <a href="changePass.jsp">Change password</a>
                    </div>         
                </div>
            </form>
        </div>
    </body>
</html>
