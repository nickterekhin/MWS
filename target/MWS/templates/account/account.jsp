<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="index">

<head>
  <title>Account</title>
  <script type="text/javascript" src="../../js/plugin/dataTables/js/jquery.dataTables.js" th:href="@{/js/plugin/dataTables/js/jquery.dataTables.js}"></script>
  <link rel="Stylesheet" href="../../js/plugin/dataTables/css/custom.dataTables.css" th:href="@{/js/plugin/dataTables/css/custom.dataTables.css}"/>
    <script language="javascript" src="../../js/jupload.js" th:src="@{/js/jupload.js}" type="text/javascript"></script>
    <script type="text/javascript" language="JavaScript1.2" src="../../js/plugin/gallery.js"></script>
  <script type="application/javascript">
    $(document).ready(function(){


            gl = new Gallery(0,"/ajax/avatar");



      $("#sortedTable").dataTable({
          "paging":false,
        "pagingType": "full_numbers",
        "language": { "infoFiltered": "",
          "search": "" }
      });
    });
  </script>
</head>
<body>
<div layout:fragment="content">
  <div class="page-header">
    <h1 th:text="'Account'"></h1>
  </div>

  <div class="page-content">
    <div class="column two_wide">
        <h2>User Info</h2>
        <!--<div class="table-manage-container">
            <ul class="account-menu-container">
                <li><a th:href="@{/account/addflat}" class="button add lnk_green">Add New Flat</a></li>
            </ul>
        </div>-->
        <div class="gall-menu" th:attr="id=${'gall_menu_'+session.user.getUid()}" >
            <div class="gall-pict-fline" th:attr="id=${'gall_out_'+session.user.getUid()}">
                <div class="gall-pict-inside" th:id="${'gall_'+session.user.getUid()}" th:onclick="'gl.uploadFile(this.id,'+${session.user.getUid()}+');'" th:onmouseover="'gl.chgColorOn(this.id,null);'" th:onmouseout="'gl.chgColorOff(this.id,null);'" >


                    <img src="" width="100" th:attr="src=@{'/upload/users/thumbs/'+${session.user.getAvatar()}}" th:id="${'imgall_'+session.user.getUid()}" th:if="${not session.user.getDef_avatar()}"/>

                </div>
                <input type="file" th:attr="name=${'fileToUpload_'+session.user.getUid()}" th:id="${'fileToUpload_'+session.user.getUid()}" size="1" class="gall-fileup"  th:onchange="'gl.ajaxFileUpload(\'/upload/users/\',\''+${session.user.getUid()}+'\',100,1,1);'" />
            </div>

            <div th:id="${'ddgall_'+session.user.getUid()}" class="gall_cntr_menu" th:if="${not session.user.getDef_avatar()}">
                <ul id="gall_menu_item">
                    <li class="photo_del" th:id="${'dp_'+session.user.getUid()}" th:onclick="'gl.DelPhoto('+${session.user.getUid()}+');'" th:title="'Delete Avatar'"></li>
                </ul>
            </div>

        </div>
        <div style="display:block;float:right;width:330px;padding:5px 0">
        <ul id="userInfo" class="view">
            <li>
                <label>User Group:</label>
                <span th:text="${session.UserGroup.getGroupName()}"></span>
            </li>
            <li>
                <label>Registered:</label>
                <span th:text="${#dates.format(session.user.getRegTime().toDate(),'dd/MM/yyyy')}"></span>
            </li>
            <li>
                <label>First Name:</label>
                <span th:text="${session.user.getFirstName()}"></span>
            </li>
            <li>
                <label>Last Name:</label>
                <span th:text="${session.user.getLastName()}"></span>
            </li>
        </ul>
        </div>
    </div>
    <div class="column two_wide">
        <h2>Contacts</h2>
        <div class="table-container">
            <div class="table-header">
                <div class="table-manage-container">
                    <ul class="account-menu-container">
                        <li><a href="javascript:void(0)" th:onclick="'popup(\''+@{/account/account(action=addcontact)}+'\',\'Add Contact\',400,320,1);'" class="button add lnk_green">Add New Contact</a></li>
                    </ul>
                </div>
                <div class="table-title">

                </div>
                <div class="table-filters" id="filter_catalog">

                </div>
            </div>

          <table id="sortedTable">
        <thead>
        <tr>
          <th>
            #
          </th>
          <th>
            Type
          </th>
          <th>
            Value
          </th>
          <th>
            Position
          </th>
          <th>
            Action
          </th>
        </tr>
        </thead>
        <tbody>
          <tr th:each="c,iStat:${cont}">
            <td th:text="${iStat.count}"></td>
            <td th:text="${c.getContactTypeName()}"></td>
            <td th:text="${c.getValue()}"></td>
            <td th:text="${c.getPosition()}"></td>
            <td>
              <a th:href="@{/account/account(cid=${c.getId()},action=openclosed,a=close)}" title="Close" th:if="${c.isActive()}"><img th:src="@{/images/opened.png}" alt="Opened"/></a>
              <a th:href="@{/account/account(cid=${c.getId()},action=openclosed,a=open)}" title="Open" th:if="${c.isActive()==false}"><img th:src="@{/images/closed.png}" alt="Closed"/></a>&nbsp;
              <a href="javascript:void(0)" th:onclick="'popup(\''+@{/account/account(action=editcontact,cid=${c.getId()})}+'\',\'Edit Contact\',800,600,1);'" title="Edit"><img th:src="@{/images/pencil.png}" alt="Edit"/></a>&nbsp;

              <a th:href="@{/account/account(cid=${c.getId()},action=delcontact)}" title="Delete"><img  th:src="@{/images/cross.png}" alt="Delete"/></a>&nbsp;
            </td>
          </tr>
        </tbody>
      </table>
        </div>
    </div>
    <div class="clearfix"></div>
  </div>
</div>
</body>
</html>
