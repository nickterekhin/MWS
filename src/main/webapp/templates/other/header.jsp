<!DOCTYPE html>
<html lang="en">
<head th:fragment="headTag">
    <link rel="stylesheet" href="../../js/ui/jquery-ui.css" th:href="@{/js/ui/jquery-ui.css}"/>
    <link rel="stylesheet" href="../../css/style.css" type="text/css" th:href="@{/css/style.css}"/>

    <script type="text/javascript" src="../../js/jquery-1.11.2.min.js" language="JavaScript" th:src="@{/js/jquery-1.11.2.min.js}"/>
    <script type="text/javascript" src="../../js/ui/jquery-ui.js" language="JavaScript" th:src="@{/js/ui/jquery-ui.js}"/>
    <script type="text/javascript" src="../../js/jquery.validate.js" language="JavaScript" th:src="@{/js/jquery.validate.js}"/>
    <script type="text/javascript" src="../../js/jquery.ui.datepicker.validation.js" language="JavaScript" th:src="@{/js/jquery.ui.datepicker.validation.js}"/>
    <script type="text/javascript" src="../../js/functions.js" language="JavaScript" th:src="@{/js/functions.js}"/>
    <script language="JavaScript" type="text/javascript">
        $(function(){
            $("div.main_container").css("height",$(window).height());
            $(window).resize(function(){
                $("div.main_container").css("height",$(window).height());
            });
        });
    </script>
</head>
</html>
