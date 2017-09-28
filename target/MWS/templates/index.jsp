<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title layout:title-pattern="$DECORATOR_TITLE - $CONTENT_TITLE">MWS Main Page</title>
    <object th:include="other/header::headTag" th:remove="tag"/>
</head>
<body class="main_body">
<div class="main_container"></div>
    <div class="hdr">
        <div class="hdr_main">
            <div class="hdr_wrap">
                <div id="Logo">

                </div>
                <div id="user_panel" th:include="users/userpanel::userPanel">

                </div>
                <div id="navigat">
                    <ul id="nav">
                        <li><a href="index.jsp" th:href="@{/home}">Home</a></li>
                        <li><a href="catalog/allflats.jsp" th:href="@{/catalog/allflats}">Catalog</a></li>
                        <li><a href="pages/description.jsp" th:href="@{/pages/description}">Description</a></li>
                        <li><a href="#">Contacts</a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>

    </div>
    <div id="center" align="center">
        <div class="main_content_container">
            <div layout:fragment="content" class="main_content">
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
        </div>
    </div>
</body>
</html>
