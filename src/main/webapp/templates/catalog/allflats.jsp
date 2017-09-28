<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="index">
<head>
    <title>Catalog Of The Appartments</title>
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
        <h1>Catalog Of Appartments</h1>
    </div>
    <div class="page-content">
        <div class="table-container">
            <div class="table-header">
                <div class="table-title">
                    <!--country(Flag),city,distric-->
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
                    <td th:text="${#dates.format(flat.getBuildDate().toDate(),'dd/mm/yyyy')}">dd/mm/yy</td>
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
                    <th>
                     <a th:href="@{/catalog/allflats(action=showflat,flatId=${flat.getFlatId()})}" title="Show Flat"><img  th:src="@{/images/eye.png}" alt="Show Flat"/></a>&nbsp;
                    </th>
                </tr>
                </tbody>
            </table>


        </div>

    </div>
</div>
</body>
</html>
