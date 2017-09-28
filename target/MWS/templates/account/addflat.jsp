<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="index">
<head>
    <title>Add Flat Page</title>
    <script type="text/javascript">
        $(document).ready(function(){

            $("#buildDate").datepicker({
                showOn: 'both',
                yearRange: '-15:+10',
                minDate: null,
                showStatus: false,
                showButtonPanel:true,
                buttonImage: '../../images/calendar20.png', /*theme/standard/images/edit.png*/
                defaultDate: 'actual date',
                buttonImageOnly: true,
                buttonText: '...',
                speed: 'fast',
                dateFormat:"dd.mm.yy"
            });

            $("#addFlatForm").validate({
                errorPlacement: $.datepicker.errorPlacement,
                rules:{
                    flatCountry:{required:true},
                    city:{required:true},
                    district:{required:true},
                    street:{required:true},
                    flatNumber:{required:true},
                    buildDate:{required:true,dpDate:true},
                    flatLevel:{required:true,digits:true},
                    flatRooms:{required:true,digits:true},
                    flatSquare:{required:true,number:true},
                    priceForRent:{required:true,number:true},
                    rentRate:{required:true},
                    priceForSale:{required:true,number:true},
                    flatDescript:{required:true}

                },
                messages:{},
            submitHandler: function() {
                document.forms['addFlatForm'].submit();
            },
            success: function(label)
            {
            label.html("&nbsp;").addClass("checked").remove();
            }

            });

            isRent();
        });

        function isRent()
        {
            var hasRent = $("#hasRent");
            if(hasRent.is(":checked"))
            {
                $("#isRentPrice").show();
                $("#isRentRate").show();
            }
            else
            {
                $("#isRentPrice").hide();
                $("#isRentRate").hide();
            }

            hasRent.click(function(){

                if($(this).is(":checked"))
                {
                    $("#isRentPrice").show();
                    $("#isRentRate").show();
                }
                else
                {
                    $("#isRentPrice").hide();
                    $("#isRentRate").hide();
                }
            });
        }
    </script>
</head>
<body>
<div layout:fragment="content">
    <div class="page-header">
        <h1 th:text="${isEdit}?'Edit Flat':'Add Flat'"></h1>


    </div>
    <div class="table-manage-container">
        <ul class="account-menu-container">
            <li><a th:href="@{/account/myflats}" class="button back_btn lnk_green">To my Flats</a></li>

        </ul>
    </div>
    <div class="page-content" th:if="${done}">
            <div class="success-message">
                <p th:text="${vmMessage}"></p>
            </div>
    </div>
    <div class="page-content" th:if="${not done}">
        <div class="warn-box" th:if="${warn}">
            <ul id="warn-box">
                <li th:each="error : ${errors}"><span th:text="${error}"></span></li>
            </ul>
        </div>
        <form id="addFlatForm" name="addFlatForm" action="../account/addflat.jsp" th:action="(${isEdit}?@{/account/editflat(flatId=${flat.getFlatId()})}:@{/account/addflat})" method="POST" th:if="${not done}">
        <input type="hidden" value="1" name="send"/>
        <div class="column three">

            <div th:if="${done}" class="success-message">
                <p th:text="${vmMessage}"></p>

            </div>
                <ul class="form">
                    <li class="groups-header">Address information</li>
                    <li>
                        <label>
                            Country <em>*</em>
                        </label>
                        <select name="flatCountry" id="flatCountry">
                            <option value="">Select Country</option>
                            <option th:each="country : ${countries}" th:value="${country.getCountryId()}" th:selected="(${flat.getCountryId()}==${country.getCountryId()})" th:text="${country.getCountryName()}" th:if="${isEdit}">
                                CountryName
                            </option>
                            <option th:each="country : ${countries}" th:value="${country.getCountryId()}" th:text="${country.getCountryName()}" th:if="${not isEdit}" >
                                CountryName
                            </option>
                        </select>
                    </li>
                    <li><label>City <em>*</em></label>
                        <input type="text" name="city" value="" th:value="${param.containsKey('city')}?${param.city[0]}" id="city" th:if="${not isEdit}"/>
                        <input type="text" name="city" value="" th:value="${param.containsKey('city')}?${param.city[0]}:${flat.getCityName()}" id="city" th:if="${isEdit}"/>
                    </li>
                    <li><label>District <em>*</em></label>
                        <input type="text" name="district" value="" th:value="${param.containsKey('district')}?${param.district[0]}" id="district" th:if="${not isEdit}"/>
                        <input type="text" name="district" value="" th:value="${param.containsKey('district')}?${param.district[0]}:${flat.getDistrictName()}" id="district" th:if="${isEdit}"/>
                    </li>
                    <li><label>Address <em>*</em></label>
                        <input type="text" name="street" value="" th:value="${param.containsKey('street')}?${param.street[0]}" id="street" th:if="${not isEdit}"/>
                        <input type="text" name="street" value="" th:value="${param.containsKey('street')}?${param.street[0]}:${flat.getStreet()}" id="street" th:if="${isEdit}"/>
                    </li>
                    <li><label>Flat Number <em>*</em></label>
                        <input type="text" name="flatNumber" value="" th:value="${param.containsKey('flatNumber')}?${param.flatNumber[0]}"  id="flatNumber" th:if="${not isEdit}"/>
                        <input type="text" name="flatNumber" value="" th:value="${param.containsKey('flatNumber')}?${param.flatNumber[0]}:${flat.getFlatnumber()}"  id="flatNumber" th:if="${isEdit}"/>
                    </li>
                </ul>
        </div>
        <div class="column three">
            <ul class="form">
                <li class="groups-header">Explication</li>
                 <li><label>Build date (ex:dd.mm.YYYY) <em>*</em></label>
                     <input type="text" name="buildDate" value="" th:value="${param.containsKey('buildDate')}?${param.buildDate[0]}" id="buildDate" th:if="${not isEdit}"/>
                     <input type="text" name="buildDate" value="" th:value="${param.containsKey('buildDate')}?${param.buildDate[0]}:${#dates.format(flat.getRegDate().toDate(),'dd.MM.yyyy')}" id="buildDate" th:if="${isEdit}"/>
                 </li>
                <li><label>Level (number only)<em>*</em></label>
                    <input type="text" name="flatLevel" value="" th:value="${param.containsKey('flatLevel')}?${param.flatLevel[0]}" id="flatLevel" th:if="${not isEdit}"/>
                    <input type="text" name="flatLevel" value="" th:value="${param.containsKey('flatLevel')}?${param.flatLevel[0]}:${flat.getLevel()}" id="flatLevel" th:if="${isEdit}"/>
                </li>

                <li><label>Rooms (number only)<em>*</em></label>
                    <input type="text" name="flatRooms" value="" th:value="${param.containsKey('flatRooms')}?${param.flatRooms[0]}" id="flatRooms" th:if="${not isEdit}"/>
                    <input type="text" name="flatRooms" value="" th:value="${param.containsKey('flatRooms')}?${param.flatRooms[0]}:${flat.getRoomsQty()}" id="flatRooms" th:if="${isEdit}"/>
                </li>
                <li><label>Live Square (in meters)<em>*</em></label>
                    <input type="text" name="flatSquare" value="" th:value="${param.containsKey('flatSquare')}?${param.flatSquare[0]}" id="flatSquare" th:if="${not isEdit}"/>
                <input type="text" name="flatSquare" value="" th:value="${param.containsKey('flatSquare')}?${param.flatSquare[0]}:${flat.getFlatSquare()}" id="flatSquare" th:if="${isEdit}"/></li>
            </ul>

        </div>
        <div class="column three">
            <ul class="form">
            <li class="groups-header">Finance information</li>
                <li><label>Is Private <em>*</em></label><input type="checkbox" name="isPrivate" value="1" th:checked="${isEdit}?${flat.getIsPrivate()}:checked" /></li>
                <li><label>Price for Sale (in USD)<em>*</em></label>
                    <input type="text" name="priceForSale" value="" th:value="${param.containsKey('priceForSale')}?${param.priceForSale[0]}" id="priceForSale" th:if="${not isEdit}"/>
                    <input type="text" name="priceForSale" value="" th:value="${param.containsKey('priceForSale')}?${param.priceForSale[0]}:${flat.getPriceForSale()}" id="priceForSale" th:if="${isEdit}"/>
                </li>
                <li><label>Has Rent <em>*</em></label>
                    <input type="checkbox" name="isRent" value="1" id="hasRent" th:checked="${isEdit}?${flat.getIsRent()}"/>
                </li>
            <li id="isRentPrice" style="display:none"><label>Price for Rent (in USD) <em>*</em></label>
                <input type="text" name="priceForRent" value="" th:value="${param.containsKey('priceForRent')}?${param.priceForRent[0]}" id="priceForRent" th:if="${not isEdit}"/>
                <input type="text" name="priceForRent" value="" th:value="${param.containsKey('priceForRent')}?${param.priceForRent[0]}:${flat.getPriceForRent()}" id="priceForRent" th:if="${isEdit}"/>
            </li>
            <li id="isRentRate" style="display:none"><label>Rent Rate (ex: per/hr, per/day, per/Month) <em>*</em></label>
                <input type="text" name="rentRate" value="" th:value="${param.containsKey('rentRate')}?${param.rentRate[0]}" class="signup-input" id="rentRate" th:if="${not isEdit}"/>
                <input type="text" name="rentRate" value="" th:value="${param.containsKey('rentRate')}?${param.rentRate[0]}:${flat.getRentRate()}" class="signup-input" id="rentRate" th:if="${isEdit}"/>
            </li>


        </ul>
        </div>
        <div class="clearfix"></div>
        <div class="column">
            <ul class="form">
                <li>
                    <label>Description <em>*</em></label>
                    <textarea name="flatDescript" class="text-area" id="flatDescript" th:text="${param.containsKey('flatDescript')}?${param.flatDescript[0]}" th:if="${not isEdit}"></textarea>
                    <textarea name="flatDescript" class="text-area" id="flatDescript" th:text="${param.containsKey('flatDescript')}?${param.flatDescript[0]}:${flat.getFullDescription()}" th:if="${isEdit}"></textarea>
                </li>
                <li>
                    <input type="submit" class="btn-signup" name="btnSignUp" th:value="${isEdit}?'Update Flat':'Add Flat'"/>
                </li>
            </ul>

        </div>
        <div class="clearfix"></div>
        </form>
    </div>
</div>
</body>
</html>