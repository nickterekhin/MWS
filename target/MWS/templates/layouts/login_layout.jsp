<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head th:include="other/header::headTag">
    <title>Login page</title>

</head>
<body class="login_body">
<div class="main_container"></div>
<div class="hdr">
    <div class="hdr_main">
        <div class="hdr_wrap">
            <div id="Logo">

            </div>
            <div id="user_panel">

            </div>
        </div>
    </div>

</div>

        <div layout:fragment="content">
            <p>Master Page Content</p>
        </div>

        <div class="ftr_content">
            <div id="ftr_content_inner" class="clearfix">
                <div class="ftr_clear"></div>
                <div class="ftr_divline" style="margin-bottom:5px;"></div>
                <p>Email: nick.terekhin@gmail.com</p>
                <div class="clearfix"></div>
                <div class="ftr_divline2" style="margin-top:5px;"></div>
                 <table>
                     <tr>
                         <td class="copyright">Copyright 2015. All right Reserved to "Nick Terkhin"</td>
                     </tr>
                 </table>
            </div>

        </div>

</body>
</html>
