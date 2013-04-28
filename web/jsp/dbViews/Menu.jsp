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
        <link rel="sytlesheet" href="/POSProject/resources/placeOrder.css" type="text/css" />
        <script type="text/javascript" src="/POSProject/resources/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxcore.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxlistmenu.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxbuttons.js"></script>
        <script type="text/javascript">
           $(document).ready(function(){
                console.log("inside ready function");
                $('#list').jqxListMenu({width:'44%',
                                        enableScrolling: false,
                                        theme: getDemoTheme(),
                                        showHeader: true,
                                        showBackButton: true,
                                        showFilter: false})
            });
            
            //GLOBALS:
            var _serveType;
            //sends off update to order in DB...for now this is set up to test one order with uid = 0.
            //also adds to the visible representation of the current order on screen.
            function addItemToOrder(name, price){
                var request = new XMLHttpRequest();
                var orderId = $('#orderIdCell').html();
                var paramString = "itemName=" + name + "&uid=" + orderId + "&price=" + price;
                request.open("POST", "update_order", true);
                request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                request.setRequestHeader("Content-length", paramString.length);
                request.setRequestHeader("Connection", "close");
                request.send(paramString);
                //code for building the visual order representation which is separate from the actual DB entry.
                //may want to make sure DB was updated by checking status of AJAX request?
                $('#orderItemsTable tbody:last').append('<tr><td class="qty">1</td><td class="name">' 
                    + name + '</td><td class="unitPrice">$' + parseFloat(price).toFixed(2) + '</td><td class="priceWithQty">$' 
                    + parseFloat(price).toFixed(2) + '</td>');
                updateTotalsBox(price);
            }
            
            function updateTotalsBox(price){
                var taxRate = .0725;
                subtotal = parseFloat($('#subtotal').html().substr(1));
                subtotal += parseFloat(price);
                $('#subtotal').html('$' + subtotal.toFixed(2));
                var tax = subtotal * taxRate;
                $('#tax').html('$' + tax.toFixed(2));
                var total = tax + subtotal;
                $('#total').html('$' + total.toFixed(2));
            }
            
            function displayServeTypeForm(serveType){
                if(serveType == 'delivery'){
                    $('#dineInForm').hide();
                    $('#carryOutForm').hide();
                    $('#deliveryForm').show();
                    _serveType = 'delivery';
                }
                else if(serveType == 'carryout'){
                    $('#dineInForm').hide();
                    $('#deliveryForm').hide();
                    $('#carryOutForm').show();
                    _serveType = 'carryout';
                }
                else if(serveType == 'dinein'){
                    $('#carryOutForm').hide();
                    $('#deliveryForm').hide();
                    $('#dineInForm').show();
                    _serveType = 'dinein';
                }
            }
            
            function completeOrder(){
               //need to check if servetype set ....fire alert if not.
               //also need form validation...fire alert if fields missing.
               var request = new XMLHttpRequest();
               var orderId = $('#orderIdCell').html();
               var paramString = "orderId=" + orderId +
                   "&subtotal=" + $('#subtotal').html().substr(1) +
                   "&tax=" + $('#tax').html().substr(1) +
                   "&total=" + $('#total').html().substr(1) +
                   "&time=" + $.now();
               console.log("inside complete order()");
               console.log(paramString);
               var serve = _serveType;
               if(serve == 'delivery'){
                   console.log("insideDeliveryblock");
                   paramString += 
                       "&address=" + $('#delAddr').val() +
                       "&phone=" + $('#delPhone').val() +
                       "&wantTime=" + $('#delTime').val() +
                       "&serveType=delivery";    
               }
               else if(serve == 'carryout'){
                   paramString += 
                       "&name=" + $('#coname').val() +
                       "&phone=" + $('#cophone').val() +
                       "&wantTime=" + $('#cotime').val() +
                       "&serveType=carryout";
               }
               else if(serve == 'dinein'){
                   paramString += 
                       "&table=" + $('#ditable').val() +
                       "&serveType=dinein";
               }
               console.log(paramString);
               request.open('POST', 'complete_order', true);
               request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
               request.setRequestHeader("Content-length", paramString.length);
               request.setRequestHeader("Connection", "close");
               request.send(paramString);
            }
            
            function cancelOrder(){
                //AJAX request to remove order id from DB and static array of assigned order Id's
                //also remove any orders entries that correspond to order id
            }
        </script>
    </head>
    
    <body class="default">
        <ul id="list" data-role="listmenu">
           <c:out value="${htmlMenu}" escapeXml="false"/>
        </ul>
        
        <div id="orderScreen">
            <table id="orderItemsTable">
                <tbody></tbody>
            </table>
        </div>
        <div id="orderInfo">
            <table id="orderTotalTable">
                <tr>
                    <td class="orderTotalTableData">Subtotal:   </td><td id="subtotal"class="orderTotalTableData">$0.00</td>
                </tr>
                <tr>
                    <td class="orderTotalTableData">Tax: </td><td id="tax" class="orderTotalTableData">$0.00</td>
                </tr>
                <tr>
                    <td class="orderTotalTableData">Total: </td><td id="total" class="orderTotalTableData">$0.00</td>
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
                        <tr><td><label class="deliveryForm">Address: </td><td></label><input id="delAddr" class="deliveryForm" type="text" name="address"></td></tr>
                        <tr><td><label class="deliveryForm">Phone Number: </td><td></label><input id="delPhone" class="deliveryForm" type="text" name="phone"></td></tr>
                        <tr><td><label class="deliveryForm">Delivery Time: </td><td></label><input id="delTime" class="deliveryForm" type="text" name="dtime"></td></tr>
                    </table>
                    <!-- Begin Carry Out Form -->
                    <table id="carryOutForm" style="display:none;">
                        <tr><td><label class="carryOutForm">Name: </td><td></label><input id="coname" class="carryOutForm" type="text" name="name"></td></tr>
                        <tr><td><label class="carryOutForm">Phone Number: </td><td></label><input id="cophone" class="carryOutForm" type="text" name="phone"></td></tr>
                        <tr><td><label class="carryOutForm">Pick Up Time: </td><td></label><input id="cotime" class="carryOutForm" type="text" name="cotime"></td></tr>
                    </table>
                    <!-- Begin Dine In Form -->
                    <table id="dineInForm" style="display:none;">
                        <tr><td><label class="dineInForm">Table Number: </td><td><input id="ditable" class="dineInForm" type="text" name="tableNumber"></td></tr>
                    </table>
                
         </div>
         <div id="buttons">
            <input id="completeButton" type="button" value="Complete Order" onclick="javascript:completeOrder()"/>
            <input id="cancelButton" type="button" value="Cancel Order" onclick="javascript:cancelOrder()"/>
         </div>
    </body>

</html>
