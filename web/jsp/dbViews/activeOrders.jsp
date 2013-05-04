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
        <script type="text/javascript" src="/POSProject/resources/orderList.js"></script>
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
                <th onclick="javascript:sort('serve')">Serve Type</th>
                <th onclick="javascript:sort('name')" class="delDiNull">Name</th>
                <th class="coDiNull">Address</th>
                <th class="diNull">Phone #</th>
                <th class="diNull">Time Wanted</th>
                <th onclick="javascript:sort('table')" class="delCoNull">Table #</th>
                <th>Total</th>
                <th onclick="javascript:sort('time')">Time Ordered</th>
            </thead>
            <c:forEach var="order" items="${deliveryOrders}">
                <c:out escapeXml="false" value="<tr id=\"${order.orderId}\" class=\"del\"><td>${order.orderId}</td><td>Delivery</td><td class=\"delDiNull\">---</td><td>${order.address}</td><td>${order.phoneNumber}</td><td>${order.wantTime}</td><td class=\"delCoNull\">---</td><td>$${order.totalPrice}</td><td>${order.humanReadableTime}</td><td style=\"display:none\">${order.timeStamp}</td><td><input type=\"button\" value=\"Serve\" onclick=\"javascript:serve('${order.orderId}')\" /></td><td><form method=\"POST\" action=\"menu_test\"><input type=\"submit\" value=\"Edit\" /><input type=\"hidden\" value=\"${order.orderId}\" name=\"editId\" /><input type=\"hidden\" value=\"delivery\" name=\"serveType\" /></form></td></tr>"/>
            </c:forEach>
            <c:forEach var="order" items="${carryoutOrders}">
                <c:out escapeXml="false" value="<tr id=\"${order.orderId}\" class=\"co\"><td>${order.orderId}</td><td>Carry Out</td><td>${order.name}</td><td class=\"coDiNull\">---</td><td>${order.phoneNumber}</td><td>${order.wantTime}</td><td class=\"delCoNull\">---</td><td>$${order.totalPrice}</td><td>${order.humanReadableTime}</td><td style=\"display:none\">${order.timeStamp}</td><td><input type=\"button\" value=\"Serve\" onclick=\"javascript:serve('${order.orderId}')\" /></td><td><form method=\"POST\" action=\"menu_test\"><input type=\"submit\" value=\"Edit\" /><input type=\"hidden\" value=\"${order.orderId}\" name=\"editId\" /><input type=\"hidden\" value=\"carryout\" name=\"serveType\" /></form></td></tr>"/>
            </c:forEach>
            <c:forEach var="order" items="${dineInOrders}">
                <c:out escapeXml="false" value="<tr id=\"${order.orderId}\" class=\"di\"><td>${order.orderId}</td><td>Dine In</td><td class=\"diNull\">---</td><td class=\"coDiNull\">---</td><td class=\"diNull\">---</td><td class=\"diNull\">---</td><td>${order.tableNumber}</td><td>$${order.totalPrice}</td><td>${order.humanReadableTime}</td><td style=\"display:none\">${order.timeStamp}</td><td><input type=\"button\" value=\"Serve\" onclick=\"javascript:serve('${order.orderId}')\" /></td><td><form method=\"POST\" action=\"menu_test\"><input type=\"submit\" value=\"Edit\" /><input type=\"hidden\" value=\"${order.orderId}\" name=\"editId\" /><input type=\"hidden\" value=\"dinein\" name=\"serveType\" /></form></td></tr>"/>
            </c:forEach>
        </table>
    </body>
</html>
