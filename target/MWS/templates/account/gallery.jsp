<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="index">
<head>
    <title>Gallery</title>
    <script language="javascript" src="../../js/jupload.js" th:src="@{/js/jupload.js}" type="text/javascript"></script>
    <script type="text/javascript" language="JavaScript1.2" src="../../js/plugin/gallery.js"></script>
    <script th:inline="javascript">

        /*<![CDATA[*/

        $(document).ready(function(){
            gl = new Gallery(/*[[${param.flatId[0]}]]*/);
        });
        /*]]>*/

    </script>
</head>
<body>
<div layout:fragment="content">
<div class="page-content">
    <div id="gall_error" ></div>
    <div class="gallery-list">
        <form name="addprof" action="" method="POST" class="add-prof" enctype="multipart/form-data">

            <div class="gall-menu" th:attr="id=${'gall_menu_'+iStat.count}" th:each="i:${#numbers.sequence(0,num_gall,1)}">
                <div class="gall-pict-fline" th:attr="id=${'gall_out_'+iStat.count}">
                    <div class="gall-pict-inside" th:id="${'gall_'+iStat.count}" th:onclick="'gl.uploadFile(this.id,'+${iStat.count}+');'" th:onmouseover="'gl.chgColorOn(this.id,null);'" th:onmouseout="'gl.chgColorOff(this.id,null);'" >


                    <img src="" width="100" th:attr="src=@{'/upload/flats/thumbs/'+${photo[i].getPhotoName()}}" th:id="${'imgall_'+iStat.count}" th:if="${photo[i]}"/>

                    </div>
                    <input type="file" th:attr="name=${'fileToUpload_'+iStat.count}" th:id="${'fileToUpload_'+iStat.count}" size="1" class="gall-fileup"  th:onchange="'gl.ajaxFileUpload(\'/upload/flats/\',\''+${iStat.count}+'\',300,1,\'\');'" />
                </div>

                <div th:id="${'dgall_'+iStat.count}" class="gall_isinfo" th:onmouseover="'gl.ShowInfo('+${iStat.count}+');'" th:title="${photo[i].getDescription()}" th:if="${photo[i] and photo[i].getDescription()!=null}"></div>

                <div th:id="${'ddgall_'+iStat.count}" class="gall_cntr_menu" th:if="${photo[i]}">
                    <ul id="gall_menu_item">
                        <li class="photo_descript"  th:id="${'ad_'+iStat.count}" th:onclick="'gl.AddDescription('+${iStat.count}+');'" th:title="'Add Description to Photo'"></li>
                        <li class="photo_del" th:id="${'dp_'+iStat.count}" th:onclick="'gl.DelPhoto('+${iStat.count}+');'" th:title="'Delete Photo'"></li>
                    </ul>
                </div>

            </div>

            <div class="clearfix"></div>
        </form>
    </div>
</div>
    <div id="galldesr_dlg" title="Add description to photo">
        <div id="galldescr_dlg_body">
            <form id="form_galdescr">
                <textarea class="descript-tophoto" id="ph_descr"></textarea>
            </form>
        </div>
    </div>
</div>
</body>
</html>
