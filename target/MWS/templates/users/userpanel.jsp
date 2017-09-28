<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<body>
    <div th:fragment="userPanel">
        <div th:if="${session.isLogged}">
        <ul class="acc_menu">
            <li><label>User:</label><span th:text="${session.user.getLastName()}+' '+|${session.user.getFirstName()}|">LastName FirstName</span></li>

        </ul>
            <div class="clearfix"></div>
            <ul class="acc_menu">
                <li><a href="account/account" th:href="@{/account/account}" class="logout_link">Account</a></li>
                <li><a href="account/myflats" th:href="@{/account/myflats}" class="logout_link">Flats</a></li>
                <li><a href="logout" th:href="@{/logout}" class="logout_link">Logout</a></li>
            </ul>
        </div>
        <form name="reg" action="../login.jsp" th:attr="action=@{/login}" method="post" th:if="${!session.isLogged}">
            <input type="hidden" name="send" value="1"/>
            <table class="login_panel">
                <tr>
                    <td>
                        <label class="email">UserName</label>
                    </td>
                    <td>
                        <label class="passwd">Password</label>
                    </td>

                </tr>
                <tr>
                   <td><input type="text" name="user_name" value="" /></td>
                    <td><input type="password" name="user_passwd" value=""/><input type="submit" value="Login" name="btnLogin" class="accept"/></td>
                    <td th:if="${!session.isLogged}"><a href="../pages/signup.jsp" class="button btn_blue" th:href="@{/pages/signup}">SignUp</a></td>
                </tr>

            </table>
        </form>
    </div>
</body>
</html>
