<%-- 
    Document   : activeOrders
    Created on : Apr 28, 2013, 3:32:19 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Active Orders</title>
        <style type="text/css">
            th, td{
                border:1px solid black;           
            }
            
            table{
                border-collapse: collapse;
                padding-top: 10px;
            }
            
            th, td{
                padding: 10px;
            }
            
            #filterBox{
                padding:10px;
                border:1px solid black;
            }
        </style>
        <script type="text/javascript" src="/POSProject/resources/jquery-1.8.3.min.js"></script>
        <script type="text/javascript">
            
            var _idSortToggle = 'ascending';
            
            function filter(param){
                if(param == 'all'){
                    $('.del').show();
                    $('.co').show();
                    $('.di').show();
                    $('.delCoNull').show();
                    $('.coDiNull').show();
                    $('.diNull').show();
                    $('.delDiNull').show();
                }
                else if(param == 'del'){
                    $('.co').hide();
                    $('.di').hide();
                    $('.del').show();
                    $('.delCoNull').hide();
                    $('.delDiNull').hide();
                    $('.coDiNull').show();
                    $('.diNull').show();
                }
                else if(param == 'co'){
                    $('.del').hide();
                    $('.di').hide();
                    $('.co').show();
                    $('.delCoNull').hide();
                    $('.coDiNull').hide();
                    $('.diNull').show();
                    $('.delDiNull').show();
                }
                else if(param == 'di'){
                    $('.del').hide();
                    $('.co').hide();
                    $('.di').show();
                    $('.coDiNull').hide();
                    $('.diNull').hide();
                    $('.delDiNull').hide();
                    $('.delCoNull').show();
                }
            }
            
            function indexToClassObj(rowId, htmlClass){
                this.rowId = rowId;
                this.htmlClass = htmlClass;
            }
            
            function sort(param){
                var rows = 0;
                if(param == 'id'){
                    $('tbody tr').each(function(){
                        rows++;
                    });
                    var rowArray = new Array(rows);
                    rows = 0;
                    $('tbody tr').each(function(){
                        var cellText = $('td:first', $(this)).text();
                        var rowClass = $(this).attr('class');
                        rowArray[rows] = new indexToClassObj(cellText, rowClass);
                        rows++;
                    });
                    if(_idSortToggle == 'ascending'){
                        rowArray.sort(function(a,b){
                            return a.rowId-b.rowId;
                        });
                        _idSortToggle = 'descending';
                    }
                    else{
                        rowArray.sort(function(a,b){
                            return b.rowId-a.rowId;
                        });
                        _idSortToggle = 'ascending';
                    }
                    var tableString = "";
                    for(var i=0; i<rowArray.length; i++){
                        tableString += "<tr id=\"" + rowArray[i].rowId + "\" class=\"" + rowArray[i].htmlClass + "\">";
                        tableString += $('#' + rowArray[i].rowId).html();
                        tableString += "</tr>";
                    }
                    $('#orders tbody').html(tableString);
                }
            }
        </script>
    </head>
    <body>
        <div id="filterBox">
            <input type="radio" name="filter" checked onclick="javascript:filter('all')"/><label>Show All</label><br>
            <input type="radio" name="filter" onclick="javascript:filter('del')"/><label>Show Delivery</label><br>
            <input type="radio" name="filter" onclick="javascript:filter('co')"/><label>Show Carry Out</label><br>
            <input type="radio" name="filter" onclick="javascript:filter('di')"/><label>Show Dine In</label><br>
        </div>
        <table id="orders">
            <thead id="tableHeader">
                <th onclick="javascritp:sort('id')">Order ID</th>
                <th>Serve Type</th>
                <th class="delDiNull">Name</th>
                <th class="coDiNull">Address</th>
                <th class="diNull">Phone #</th>
                <th class="diNull">Time Wanted</th>
                <th class="delCoNull">Table #</th>
                <th>Total</th>
                <th>Time Ordered</th>
            </thead>
            <c:forEach var="order" items="${deliveryOrders}">
                <c:out escapeXml="false" value="<tr id=\"${order.orderId}\" class=\"del\"><td>${order.orderId}</td><td>Delivery</td><td class=\"delDiNull\">---</td><td>${order.address}</td><td>${order.phoneNumber}</td><td>${order.wantTime}</td><td class=\"delCoNull\">---</td><td>$${order.totalPrice}</td><td>${order.humanReadableTime}</td></tr>"/>
            </c:forEach>
            <c:forEach var="order" items="${carryoutOrders}">
                <c:out escapeXml="false" value="<tr id=\"${order.orderId}\" class=\"co\"><td>${order.orderId}</td><td>Carry Out</td><td>${order.name}</td><td class=\"coDiNull\">---</td><td>${order.phoneNumber}</td><td>${order.wantTime}</td><td class=\"delCoNull\">---</td><td>$${order.totalPrice}</td><td>${order.timeStamp}</td></tr>"/>
            </c:forEach>
            <c:forEach var="order" items="${dineInOrders}">
                <c:out escapeXml="false" value="<tr id=\"${order.orderId}\" class=\"di\"><td>${order.orderId}</td><td>Dine In</td><td class=\"diNull\">---</td><td class=\"coDiNull\">---</td><td class=\"diNull\">---</td><td class=\"diNull\">---</td><td>${order.tableNumber}</td><td>$${order.totalPrice}</td><td>${order.timeStamp}</td></tr>"/>
            </c:forEach>
        </table>
    </body>
</html>
