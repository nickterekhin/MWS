<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="index">
<head>
  <title>Flat details</title>
    <script type="text/javascript" language="JavaScript1.2" src="../../js/plugin/gallery.js"></script>
    <script th:inline="javascript">
        $(document).ready(function(){
            gl = new Gallery();
        });
    </script>
</head>
<body>
<div layout:fragment="content">
  <div class="page-header">
    <h1 th:text="'Flat\'s Details'"></h1>


  </div>
  <div class="table-manage-container">
    <ul class="account-menu-container">
      <li><a th:href="@{/catalog/allflats}" class="button back_btn lnk_green">Back to catalog</a></li>
    </ul>
  </div>

  <div class="page-content">
   <div class="column two_wide">
     <h2 th:text="${flat.getCountryName()}+', '+${flat.getCityName()}"></h2>

       <div class="price-info">
           <ul class="view_price">
               <li th:if="${flat.getIsRent()}"><label>Rent Price:</label><span th:text="'$'+${flat.getPriceForRent()}+' '+${flat.getRentRate()}"></span></li>
               <li th:if="${flat.getPriceForSale()!=0}"><label>Sale Price:</label><span th:text="'$'+${flat.getPriceForSale()}"></span></li>
           </ul>
       </div>
     <div class="contact-info">
         <div class="flat-info">
             <ul class="view">
                 <li><label>District:</label><span th:text="${flat.getDistrictName()}"></span></li>
                 <li><label>Address:</label><span th:text="${flat.getStreet()}"></span></li>
                 <li><label>Flat Number</label><span th:text="${flat.getFlatnumber()}"></span></li>
                 <li><label>Build Date:</label><span th:text="${#dates.format(flat.getBuildDate().toDate(),'dd/MM/yyyy')}"></span></li>
                 <li><label>Level:</label><span th:text="${flat.getLevel()}"></span></li>
                 <li><label>Room Qty:</label><span th:text="${flat.getRoomsQty()}"></span></li>
                 <li><label>Square:</label><span th:text="${flat.getFlatSquare()}"></span></li>
             </ul>
             <ul class="view" th:if="${not #strings.isEmpty(flat.getFullDescription())}">
                 <li class="groups-header">Description</li>
                 <li><p th:text="${flat.getFullDescription()}"></p></li>
             </ul>
         </div>
         <div>

        <ul class="view" th:if="${not #lists.isEmpty(contacts)}">
            <li class="groups-header">Contact information</li>
            <li class="flat-user-avatar" th:if="${not us.getDef_avatar()}">
                <img th:src="@{'/upload/users/'+${us.getAvatar()}}"/>
            </li>
            <li th:each="c:${contacts}" th:class="${not us.getDef_avatar()}?user-avatar:null"><label th:text="${c.getContactTypeName()}"></label><span th:text="${c.getValue()}"></span></li>
        </ul>
             <div class="clearfix"></div>
         </div>
         <ul class="view_social">
             <li class="groups-header">Share with friends</li>
             <li class="soc-item"><img th:src="@{'/images/facebook.png'}"/></li>
             <li class="soc-item"><img th:src="@{'/images/google_plus.png'}"/></li>
             <li class="soc-item"><img th:src="@{'/images/linkedin.png'}"/></li>
             <li class="soc-item"><img th:src="@{'/images/twitter_1.png'}"/></li>
         </ul>
     </div>

   </div>
    <div class="column two_wide">
        <div>
          <div class="flat-picture" id="big_flat_picture">
              <div class="flat-picture-container">
            <img th:src="@{'/upload/flats/'+${flat.getGallery().get(0).getPhotoName()}}" th:if="${not #lists.isEmpty(flat.getGallery())}"/>
            <img th:src="@{'/images/default_homegr.jpg'}" th:if="${#lists.isEmpty(flat.getGallery())}" width="450px"/>
                  </div>
          </div>
            <div style="margin-bottom:10px;width:100%;padding:5px 0px" th:if="${not #lists.isEmpty(flat.getGallery())}">
              <ul id="gallery_items" class="gallery-items" >

                <li th:each="p,iStat:${flat.getGallery()}">
                    <div class="view-photo-item" th:id="${p.getFileName()}" th:onclick="'gl.ShowPicture(this.id,\''+${p.getPhotoName()}+'\',\'big_flat_picture\')'">
                        <img th:src="@{'/upload/flats/thumbs/'+${p.getPhotoName()}}"/>
                    </div>

                </li>

              </ul>
            </div>

        </div>
    </div>
    <div class="clearfix"></div>
  </div>
</div>
</body>
</html>
