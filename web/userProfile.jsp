<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Skill"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : userProfile
    Created on : Sep 24, 2021, 4:50:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mentor Register</title>
        <link href="css/userprofile_1.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="menter-register" id="menter-register">
                <div class="menter-register__heading">
                    <p class="heading-title">Mentor Register</p>
                </div>
                <form action="RequestController?service=becomeMentor&id=${sessionScope.user.id}" method="POST">
                    <div class="menter-register__body">

                        <div class="menter-register__item">
                            <label for="link-cv" class="menter-register__label">
                                <p class="red-star">*</p>
                                Link file CV:
                            </label>
                            <input type="text" id="link-cv" class="mentor-register__input"> 
                        </div>
                        <div class="menter-register__item">
                            <label for="link-github" class="menter-register__label">Link Github:</label>
                            <input type="text" id="link-github" class="mentor-register__input"> 
                        </div>
                        <div class="menter-register__item">
                            <label for="link-linkedin" class="menter-register__label">Linkedin:</label>
                            <input type="" id="link-linkedin" class="mentor-register__input"> 
                        </div>
                        <div class="menter-register__item">
                            <label for="skill" class="menter-register__label">
                                <p class="red-star">*</p>
                                Chọn kĩ năng:
                            </label>
                            <select name="nameSkill" class="mentor-register__input" multiple="true" size="6">
                                <c:forEach var="o" items="${list}">
                                    
                                    <option value="${o.id}">${o.name}</option>
                                </c:forEach>
                                
                            </select>
                        </div>
                        <div> 
                            <input style="margin-left: 350px" type="submit" value="Register" />
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </body>
</html>
