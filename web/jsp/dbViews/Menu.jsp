<%-- 
    Document   : Menu
    Created on : Mar 29, 2013, 1:23:14 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>-->
        <script type="text/javascript" src="/POSProject/resources/gettheme.js"></script>
        <link rel="stylesheet" href="/POSProject/resources/jqx.base.css" type="text/css" />
        <!--<link rel="stylesheet" href="/POSProject/resources/placeOrder.css" type="text/css" />-->
        <script type="text/javascript" src="/POSProject/resources/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxcore.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxlistmenu.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxbuttons.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqueryui.js"></script>
        <link rel="stylesheet" type="text/css" href="/POSProject/resources/jqueryuicss.css"/>
        <script type="text/javascript">
            var _serveType = '${serveType}';
            var _loadType = '${loadType}';
            var _orderJSON = ${orderJSON};
            var _itemJSONArray = ${itemsJSON};
        </script>
        <script type="text/javascript" src="/POSProject/resources/menuFunctions.js"></script>
    </head>
    
    <body class="default">
        <ul id="list" data-role="listmenu">
           <c:out value="${htmlMenu}" escapeXml="false"/>
        </ul>
        
        <div id="orderScreen">
            <table id="orderItemsTable">
                <thead>
                    <tr><th>Quantity</th><th>Description</th><th>Price Each</th><th>Total</th></tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div id="orderInfo">
            <table id="orderTotalTable">
                <tr>
                    <td>Subtotal: </td><td id="subtotal">$0.00</td>
                </tr>
                <tr>
                    <td>Tax: </td><td id="tax">$0.00</td>
                </tr>
                <tr>
                    <td>Total: </td><td id="total">$0.00</td>
                </tr>
            </table>
        </div>
        <div id ="orderRoutingInfo">
            <table>
                <tr>
                    <td>Order Id: </td><td id="orderIdCell"><c:out value="${orderId}"/></td>
                </tr>
            </table>
                
                    <input type="radio" name="serveType" value="delivery" onclick="javascript:displayServeTypeForm('delivery')"/>Delivery
                    <input type="radio" name="serveType" value="carryout" onclick="javascript:displayServeTypeForm('carryout')"/>Carry Out
                    <input type="radio" name="serveType" value="dinein" onclick="javascript:displayServeTypeForm('dinein')"/>Dine In
                    <br>
                    <!-- Begin delivery form -->
                    <table id="deliveryForm" style="display:none;">
                        <tr>
                            <td>
                                <label class="deliveryForm">Address: </label>
                            </td>
                            <td>
                                <input id="delAddr" class="deliveryForm" type="text" name="address">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="deliveryForm">Phone Number: </label>
                            </td>
                            <td>
                                <input id="delPhone" class="deliveryForm" type="text" name="phone">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="deliveryForm">Delivery Time:</label>
                            </td>
                            <td>
                                <input type="checkbox" checked id="delTime" class="deliveryForm" onclick="javascript:showTimeInput(this)"/>ASAP
                                <span id="delTimeInfo" style="display:none;"><input size="2" maxlength="2" type="text" id="delHour"/>:
                                <input size="2" maxlength="2" type="text" id="delMins"/>
                                <select id="delAmPm">
                                    <option>AM</option>
                                    <option>PM</option>
                                </select>
                                Date:<input type="text" size="10" maxlength="10" id="delDatePicker"/></span>
                            </td>
                        </tr>
                    </table>
                    
                    <!-- Begin Carry Out Form -->
                    <table id="carryOutForm" style="display:none;">
                        <tr>
                            <td>
                                <label class="carryOutForm">Name: </label>
                            </td>
                            <td>
                                <input id="coname" class="carryOutForm" type="text" name="name">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="carryOutForm">Phone Number: </label>
                            </td>
                            <td>
                                <input id="cophone" class="carryOutForm" type="text" name="phone">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="carryOutForm">Pick Up Time: </label>
                            </td>
                            <td>
                                <input type="checkbox" checked id="cotime" class="carryOutForm" onclick="javascript:showTimeInput(this)"/>ASAP
                                <span id="coTimeInfo" style="display:none;"><input size="2" maxlength="2" type="text" id="coHour"/>:
                                <input size="2" maxlength="2" type="text" id="coMins"/>
                                <select id="coAmPm">
                                    <option>AM</option>
                                    <option>PM</option>
                                </select>
                                Date:<input type="text" size="10" maxlength="10" id="coDatePicker"/></span>
                            </td>
                        </tr>
                    </table>
                    
                    <!-- Begin Dine In Form -->
                    <table id="dineInForm" style="display:none;">
                        <tr><td><label class="dineInForm">Table Number: </label></td><td><input id="ditable" class="dineInForm" type="text" name="tableNumber"></td></tr>
                    </table>
                
         </div>
         <div id="buttons">
            <input id="completeButton" type="button" value="Complete Order" onclick="javascript:completeOrder()"/>
            <input id="cancelButton" type="button" value="Cancel Order" onclick="javascript:cancelOrder()"/>
         </div>
    </body>

</html>
