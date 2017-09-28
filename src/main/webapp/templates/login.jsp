<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="/layouts/login_layout">
<head>
    <title>Login Page</title>
</head>
<body>



        <div layout:fragment="content" class="login-container">
            <div class="login-header">
                <h3>Login Panel</h3>
            </div>
            <div class="login-form">
                <form action="login" method="POST" name="loginForm">
                    <input type="hidden" name="send" value="1"/>
                    <ul class="login-items">
                        <li><label>UserName</label><input type="text" name="user_name" value=""/></li>
                        <li><label>Password</label><input type="password" name="user_passwd" value=""/></li>
                        <li><input type="submit" value="Login" name="btnSignUp" class="accept"/></li>
                    </ul>
                </form>
            </div>

        </div>


<div>
    <p layout:fragment="custom-footer">Login Page footer</p>
</div>
</body>
</html>
