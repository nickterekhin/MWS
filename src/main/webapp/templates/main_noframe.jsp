<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
  <title layout:title-pattern="$DECORATOR_TITLE - $CONTENT_TITLE">MWS Main Page</title>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="imagetoolbar" content="no" />
  <object th:include="other/header::headTag" th:remove="tag"/>
</head>
<body class="main_body">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top">
      <div layout:fragment="content" class="main_content">
        <p>Master Page Content No Frame</p>
      </div>
    </td>
  </tr>
  </table>
</body>
</html>
