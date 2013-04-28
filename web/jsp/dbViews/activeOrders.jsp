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
            function filter(param){
                if(param == 'all'){
                    $('.del').show();
                    $('.co').show();
                    $('.di').show();
                }
                else if(param == 'del'){
                    $('.co').hide();
                    $('.di').hide();
                    $('.del').show();
                }
                else if(param == 'co'){
                    $('.del').hide();
                    $('.di').hide();
                    $('.co').show();
                }
                else if(param == 'di'){
                    $('.del').hide();
                    $('.co').hide();
                    $('.di').show();
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
            <tr id="tableHeader">
                <th>Order ID</th>
                <th>Serve Type</th>
                <th>Name</th>
                <th>Address</th>
                <th>Phone #</th>
                <th>Time Wanted</th>
                <th>Table #</th>
                <th>Total</th>
                <th>Time Ordered</th>
            </tr>
            <c:forEach var="order" items="${deliveryOrders}">
                <c:out escapeXml="false" value="<tr class=\"del\"><td>${order.orderId}</td><td>Delivery</td><td>---</td><td>${order.address}</td><td>${order.phoneNumber}</td><td>${order.wantTime}</td><td>---</td><td>$${order.totalPrice}</td><td>${order.humanReadableTime}</td></tr>"/>
            </c:forEach>
            <c:forEach var="order" items="${carryoutOrders}">
                <c:out escapeXml="false" value="<tr class=\"co\"><td>${order.orderId}</td><td>Carry Out</td><td>${order.name}</td><td>---</td><td>${order.phoneNumber}</td><td>${order.wantTime}</td><td>---</td><td>$${order.totalPrice}</td><td>${order.timeStamp}</td></tr>"/>
            </c:forEach>
            <c:forEach var="order" items="${dineInOrders}">
                <c:out escapeXml="false" value="<tr class=\"di\"><td>${order.orderId}</td><td>Dine In</td><td>---</td><td>---</td><td>---</td><td>---</td><td>${order.tableNumber}</td><td>$${order.totalPrice}</td><td>${order.timeStamp}</td></tr>"/>
            </c:forEach>
        </table>
    </body>
</html>
