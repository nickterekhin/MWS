<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="main_noframe">

<head>
  <title>Add Contact</title>

  <script type="application/javascript">
    $(document).ready(function(){
      $("#addContactForm").validate({
        rules:{
          contactValue:{required:true},
          contactPos:{required:true}
        },
        messages:{},
        submitHandler: function() {
          document.forms['addContactForm'].submit();
        },
        success: function(label)
        {
          label.html("&nbsp;").addClass("checked").remove();
        }

      });

    });
  </script>
</head>
<body>
<div layout:fragment="content">
  <div class="page-header">
    <h1 th:text="'Add Contact'"></h1>
  </div>

  <div class="page-content">
    <form id="addContactForm" name="addContactForm" action="../account/addcontact.jsp" th:action="@{/account/account(action=addcontact)}" method="POST" >
      <input type="hidden" value="1" name="send"/>
    <div class="column">
      <ul class="form">
        <li><label>Contact Type:</label>
        <select name="contactType" id="contactType">
          <option th:each="ct:${conTypes}" th:value="${ct.getId()}" th:attr="selected=${contact and contact.getContactType()==ct.getId()}?'selected':null" th:text="${ct.getContactType()}"></option>
        </select>
        </li>
        <li><label>Contact Value:</label><input type="text" name="contactValue" id="contactValue" th:value="${param.containsKey('contactValue')}?${param.contactValue[0]}:(${contact}?${contact.getValue()}:null)" /></li>
        <li><label>Position:</label><input type="text" name="contactPos" th:value="${param.containsKey('contactPos')}?${param.contactPos[0]}:(${contact}?${contact.getPosition()}:null)" id="contactPos"/></li>
        <li><input type="submit" class="btn-signup" name="btnSignUp" value="Add Contact"/> <input type="button" class="btn-cancel" value="Cancel" name="btnCancel" th:onclick="'cancelPopup();'"/></li>
         </ul>
    </div>
      </form>
  </div>
</div>
</body>
</html>

