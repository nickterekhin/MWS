<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="index">
<head>
    <title>My Flats</title>
    <script type="text/javascript" src="../../js/plugin/dataTables/js/jquery.dataTables.js" th:href="@{/js/plugin/dataTables/js/jquery.dataTables.js}"></script>
    <link rel="Stylesheet" href="../../js/plugin/dataTables/css/custom.dataTables.css" th:href="@{/js/plugin/dataTables/css/custom.dataTables.css}"/>
    <script type="application/javascript">
        $(document).ready(function(){

            $("#sortedTable").dataTable({
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
        <h1>My Flats</h1>
    </div>
    <div class="page-content">
        <div class="table-container">
            <div class="table-header">
                <div class="table-manage-container">
                    <ul class="account-menu-container">
                        <li><a th:href="@{/account/addflat}" class="button add lnk_green">Add New Flat</a></li>
                    </ul>
                </div>
                <div class="table-title">

                </div>
                <div class="table-filters" id="filter_catalog">

                </div>
            </div>
            <table id="sortedTable" class="table-catalog">
                <thead>
                <tr>
                    <th>#</th>
                    <th>
                        Published Date
                    </th>
                    <th>
                        Country
                    </th>
                    <th>
                        City
                    </th>
                    <th>
                        District
                    </th>
                    <th>
                        Header
                    </th>
                    <th>
                        Rooms Qty
                    </th>
                    <th>
                        Square
                    </th>
                    <th>
                        Level
                    </th>
                    <th>
                        IsPhoto
                    </th>
                    <th>Sales Price</th>
                    <th>Rent Price</th>
                    <th>

                    </th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="flat : ${flats}">
                    <td th:text="${flatStat.count}"></td>
                    <td th:text="${#dates.format(flat.getRegDate().toDate(),'dd/MM/yyyy')}">dd/mm/yy</td>
                    <td th:text="${flat.getCountryName()}">Ua</td>
                    <td th:text="${flat.getCityName()}">M</td>
                    <td th:text="${flat.getDistrictName()}">Ua</td>
                    <td th:text="${flat.getShortDescription()}">Ua</td>
                    <td th:text="${flat.getRoomsQty()}">Ua</td>
                    <td th:text="${flat.getFlatSquare()}">Ua</td>
                    <td th:text="${flat.getLevel()}">Ua</td>
                    <td th:text="${flat.getDef_foto()}">Ua</td>
                    <td th:text="'$'+${#numbers.formatDecimal(flat.getPriceForSale(),3,'POINT',2,'COMMA')}">##.#</td>
                    <td th:text="('$'+${#numbers.formatDecimal(flat.getPriceForRent(),3,'POINT',2,'COMMA')} +' '+ ${flat.getRentRate()})">##.#</td>
                    <td>
                        <a th:href="@{/account/myflats(flatId=${flat.getFlatId()},action=closed)}" title="Close" th:if="${flat.getIsActive()}"><img th:src="@{/images/opened.png}" alt="Opened"/></a>
                        <a th:href="@{/account/myflats(flatId=${flat.getFlatId()},action=open)}" title="Open" th:if="${flat.getIsActive()==false}"><img th:src="@{/images/closed.png}" alt="Closed"/></a>&nbsp;
                        <a th:href="@{/account/editflat(flatId=${flat.getFlatId()})}" title="Edit"><img th:src="@{/images/pencil.png}" alt="Edit"/></a>&nbsp;
                        <!--<a th:href="@{/account/addserv(flatId=${flat.getFlatId()})}" title="Add Services"><img th:src="@{/images/service_status.png}" alt="Add Services"/></a>&nbsp;-->
                        <a th:href="@{/account/gallery(flatId=${flat.getFlatId()})}" title="Add Photo"><img  th:src="@{/images/eye.png}" alt="Add Photo"/></a>&nbsp;
                        <a th:href="@{/account/myflats(flatId=${flat.getFlatId()},action=del)}" title="Delete"><img  th:src="@{/images/cross.png}" alt="Delete"/></a>&nbsp;

                    </td>
                </tr>
                </tbody>
            </table>


        </div>

    </div>
</div>
</body>
</html>
