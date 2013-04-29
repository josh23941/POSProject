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
            
            var _filterValue = 'all';
            var _idSortToggle = 'ascending';
            var _nameSortToggle = 'ascending';
            
            function filter(param){
                if(param == 'all'){
                    $('.del, .co, .di, .delCoNull, .coDiNull, .diNull, .delDiNull').show();
                    _filterValue = 'all';
                }
                else if(param == 'del'){
                    $('.co, .di, .delCoNull, .delDiNull').hide();
                    $('.del, .coDiNull, .diNull').show();
                    _filterValue = 'del';
                }
                else if(param == 'co'){
                    $('.del, .di, .delCoNull, .coDiNull').hide();
                    $('.co, .diNull, .delDiNull').show();
                    _filterValue = 'co';
                }
                else if(param == 'di'){
                    $('.del, .co, .coDiNull, .diNull, .delDiNull').hide();
                    $('.di, .delCoNull').show();
                    _filterValue = 'di';
                }
            }
            
            function indexToClassObj(rowId, data, htmlClass){
                this.rowId = rowId;
                this.data = data;
                this.htmlClass = htmlClass;
            }
            
            function toggleSortVar(param){
                switch(param){
                    case 'id':
                        if(_idSortToggle == 'ascending'){
                            _idSortToggle = 'descending';
                        }
                        else{
                            _idSortToggle = 'ascending';
                        }
                        break;
                    case 'name':
                        if(_nameSortToggle == 'ascending'){
                            _nameSortToggle = 'descending'; 
                        }
                        else{
                            _nameSortToggle = 'ascending';
                        }
                        break;
                }
            }
            function sort(param){
                var rows = 0;
                var sortMethod;
                var toggle;
                
                $('tbody tr').each(function(){
                    rows++;
                });
                var rowArray = new Array(rows);
                rows = 0;
                $('tbody tr').each(function(){
                    var id = $(this).attr('id');
                    var data;
                    switch(param){
                        case 'id': 
                            data = $('td:first', $(this)).text();
                            sortMethod = 'numeric';
                            toggle = _idSortToggle;
                            break;
                        case 'name':
                            data = $('td:nth-child(2)', $(this)).text();
                            sortMethod = 'alphabetic';
                            toggle = _nameSortToggle;
                            break;
                    }
                    var rowClass = $(this).attr('class');
                    rowArray[rows] = new indexToClassObj(id, data, rowClass);
                    rows++;
                });
                if(sortMethod == 'numeric'){
                    if(toggle == 'ascending'){
                        rowArray.sort(function(a,b){
                            return a.data-b.data;
                        });
                    }
                    else{
                        rowArray.sort(function(a,b){
                            return b.data-a.data;
                        });
                    }
                    toggleSortVar(param);
                }
                else if(sortMethod == 'alphabetic'){
                    if(toggle == 'ascending'){
                        rowArray.sort(function(a,b){
                            if(a.data < b.data){
                                return -1;
                            }
                            if(a.data > b.data){
                                return 1;
                            }
                            else
                                return 0;
                        });        
                    }
                    else{
                        rowArray.sort(function(a,b){
                            if(a.data > b.data){
                                return -1;
                            }
                            if(a.data < b.data){
                                return 1;
                            }
                            else
                                return 0;
                        });
                        rowArray.reverse();
                    }
                    toggleSortVar(param);
                }
                var tableString = "";
                for(var i=0; i<rowArray.length; i++){
                    tableString += "<tr id=\"" + rowArray[i].rowId + "\" class=\"" + rowArray[i].htmlClass + "\">";
                    tableString += $('#' + rowArray[i].rowId).html();
                    tableString += "</tr>";
                }
                $('#orders tbody').html(tableString);
                filter(_filterValue);
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
                <th onclick="javascript:sort('id')">Order ID</th>
                <th>Serve Type</th>
                <th onclick="javascript:sort('name')" class="delDiNull">Name</th>
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