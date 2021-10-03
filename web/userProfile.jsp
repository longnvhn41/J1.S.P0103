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
                            <label for="skill" class="menter-register__label">
                                <p class="red-star">*</p>
                                Chọn kĩ năng:
                            </label>
                            <input type="text" id="skill" class="mentor-register__input" name="skill" placeholder="Chọn kĩ năng">
                        </div>
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
                        <div> 
                            <input style="margin-left: 350px" type="submit" value="Register" />
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </body>
</html>
