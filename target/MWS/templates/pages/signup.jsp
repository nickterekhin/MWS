<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="index">
<head>
    <title>SignUp Page</title>
</head>
<body>
<div layout:fragment="content">
    <div class="page-header">
        <h1>SignUp</h1>
    </div>
    <div class="page-content">
        <div class="column two">
            <div class="warn-box" th:if="${warn}">
                <ul id="warn-box">
                    <li th:each="error : ${errors}"><span th:text="${error}"></span></li>
                </ul>
            </div>
            <div th:if="${done}" class="success-message">
                <p th:text="${vmMessage}"></p>
            </div>
            <form name="signupForm" action="../pages/signup.jsp" th:action="@{/pages/signup}" method="POST" th:if="${not done}">
                <input type="hidden" value="1" name="send"/>
                <ul id="signup-form">
                    <li><label>First Name <em>*</em></label><input type="text" name="firstName" value="" th:value="${param.containsKey('firstName')}?${param.firstName[0]}" class="signup-input"/></li>
                    <li><label>Last Name <em>*</em></label><input type="text" name="lastName" value="" th:value="${param.containsKey('lastName')}?${param.lastName[0]}" class="signup-input"/></li>
                    <li><label>UserName <em>*</em></label><input type="text" name="userName" value="" th:value="${param.containsKey('userName')}?${param.userName[0]}" class="signup-input"/></li>
                    <li><label>Password <em>*</em></label><input type="password" name="passwd" value="" class="signup-input"/></li>
                    <li><input type="submit" class="btn-signup" name="btnSignUp" value="SignUp"/></li>
                </ul>
            </form>
        </div>
        <div class="column two">
            <h2>
                Information Text
            </h2>

            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquam at beatae deserunt eius enim harum
                itaque labore magni quae quibusdam? Corporis, cum dolore eligendi eos est nam odio ullam vitae?</p>

            <p>Alias architecto atque aut beatae consequatur corporis dolorem ea eaque eius excepturi expedita facilis
                laborum nesciunt nihil non numquam placeat possimus quae quam quas recusandae sint soluta voluptatem,
                voluptates voluptatum.</p>

            <p>Alias animi aspernatur cum debitis dolor ea, earum eligendi, facere fuga hic magnam minima nemo nostrum
                placeat quaerat quidem, quos sequi similique sit tempora totam unde voluptates. Alias, dolores
                quisquam.</p>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
</body>
</html>
