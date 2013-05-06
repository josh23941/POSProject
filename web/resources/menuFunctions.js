$(document).ready(function(){
                console.log(new Date());
                $('#list').jqxListMenu({width:'44%',
                                        enableScrolling: false,
                                        theme: getDemoTheme(),
                                        showHeader: true,
                                        showBackButton: true,
                                        showFilter: false})
                
                //Initialize Datepickers:
                $('#delDatePicker').datepicker();
                $('#delDatePicker').datepicker('setDate', new Date());
                $('#coDatePicker').datepicker();
                $('#coDatePicker').datepicker('setDate', new Date());
                if(_loadType == 'edit'){
                    //Extract item data
                    var existingOrderItemsHTML = "";
                    for(var i = 0; i < _itemJSONArray.items.length; i++){
                        var itemIndex = _itemJSONArray.items[i].itemIndex;
                        existingOrderItemsHTML += "<tr id=\"item" + itemIndex + "\"><td style=\"display:none;\">" + 
                            itemIndex + "</td><td>1</td><td>" + 
                            _itemJSONArray.items[i].description + "</td><td>$" +
                            parseFloat(_itemJSONArray.items[i].unitPrice).toFixed(2) + "</td><td class=\"priceWithQty\">$" +
                            parseFloat(_itemJSONArray.items[i].unitPrice).toFixed(2) + 
                            "</td><td><input type=\"button\" value=\"Remove\" onclick=\"javascript:removeItem(\'" 
                            + itemIndex + "\')\"/></td></tr>";
                        //this ensures an additional items start from highest index regardless of itemCount (could happen when removing items and editing to add more)
                        if(_itemCount < itemIndex){
                            _itemCount = itemIndex;
                        }
                    }
                    $('#orderItemsTable tbody').html(existingOrderItemsHTML);
                    $('#subtotal').html('$' + parseFloat( _orderJSON.subTotal).toFixed(2));
                    $('#tax').html('$' + parseFloat(_orderJSON.tax).toFixed(2));
                    $('#total').html('$' + parseFloat(_orderJSON.totalPrice).toFixed(2));
                    switch(_serveType){
                        case 'delivery':{
                            $('#delAddr').val(_orderJSON.address);
                            $('#delPhone').val(_orderJSON.phone);
                            //$('#delTime').val(_orderJSON.wantTime);
                            if(_orderJSON.wantTime != 'ASAP'){
                                //uncheck ASAP fill in form
                                $('#delTime').attr('checked', false);
                                $('#delTimeInfo').show();
                                fillInTimeFields("#delHour", "#delMins", "#delAmPm");
                                $('#delDatePicker').val(_orderJSON.wantDate);
                            }
                            break;
                        }
                        case 'carryout':{
                            $('#coname').val(_orderJSON.name);
                            $('#cophone').val(_orderJSON.phone);
                            //$('#cotime').val(_orderJSON.wantTime);
                            if(_orderJSON.wantTime != 'ASAP'){
                                //uncheck ASAP fill in form
                                $('#cotime').attr('checked', false);
                                $('#coTimeInfo').show();
                                fillInTimeFields("#coHour", "#coMins", "#coAmPm");
                                $('#coDatePicker').val(_orderJSON.wantDate);
                            }
                            break;
                        }
                        case 'dinein':{
                            $('#ditable').val(_orderJSON.table);
                            break;
                        }
                    }
                    displayServeTypeForm(_serveType);
                    $('input:radio').each(function(){
                        if($(this).val() == _serveType){
                            $(this).attr('checked', true);
                        }
                    });
                }
            });
            
            //GLOBALS:
            
            var _itemCount = 0;
            
            //sends off update to order in DB...for now this is set up to test one order with uid = 0.
            //also adds to the visible representation of the current order on screen.
            function addItemToOrder(name, price){
                var request = new XMLHttpRequest();
                var orderId = $('#orderIdCell').html();
                var paramString = "itemName=" + name + "&uid=" + orderId + "&price=" + price +"&index=" + _itemCount;
                request.open("POST", "update_order", true);
                request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                request.setRequestHeader("Content-length", paramString.length);
                request.setRequestHeader("Connection", "close");
                request.send(paramString);
                //code for building the visual order representation which is separate from the actual DB entry.
                //may want to make sure DB was updated by checking status of AJAX request?
                $('#orderItemsTable tbody:last').append('<tr id=\"item' + _itemCount + '\"><td style=\"display:none;\">'+ _itemCount + '</td><td class="qty">1</td><td class="name">' 
                    + name + '</td><td class="unitPrice">$' + parseFloat(price).toFixed(2) + '</td><td class="priceWithQty">$' 
                    + parseFloat(price).toFixed(2) + '</td><td><input type=\"button\" value=\"Remove\" onclick=\"javascript:removeItem(\''+ _itemCount + '\')\"/></td>(</tr>');
                updateTotalsBox();
                _itemCount++;
            }
            
            function updateTotalsBox(){
                var taxRate = .0725;
                var subtotal = 0;
                $('.priceWithQty').each(function(){
                    subtotal += parseFloat($(this).html().substr(1));
                });
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
               var time;
               if (_loadType == 'edit'){
                   time = _orderJSON.timeStamp;
               }
               else{
                   time = $.now();
               }               
               var paramString = "orderId=" + orderId +
                   "&subtotal=" + $('#subtotal').html().substr(1) +
                   "&tax=" + $('#tax').html().substr(1) +
                   "&total=" + $('#total').html().substr(1) +
                   "&time=" + time + "&loadType=" + _loadType;
               var serve = _serveType;
               if(serve == 'delivery'){
                   paramString += 
                       "&address=" + $('#delAddr').val() +
                       "&phone=" + $('#delPhone').val() +
                       "&wantTime=" + getWantTime() +
                       "&serveType=delivery" +
                       "&wantDate=" + $('#delDatePicker').val();    
               }
               else if(serve == 'carryout'){
                   paramString += 
                       "&name=" + $('#coname').val() +
                       "&phone=" + $('#cophone').val() +
                       "&wantTime=" + getWantTime() +
                       "&serveType=carryout" +
                       "&wantDate=" + $('#coDatePicker').val();
               }
               else if(serve == 'dinein'){
                   //use the delDatePicker to pull todays date from since 
                   //resetting it in case it was messed with.
                   $('#delDatePicker').datepicker('setDate', new Date());
                   paramString += 
                       "&table=" + $('#ditable').val() +
                       "&serveType=dinein" +
                       "&wantDate=" + $('#delDatePicker').val();
               }
               request.open('POST', 'complete_order', true);
               request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
               request.setRequestHeader("Content-length", paramString.length);
               request.setRequestHeader("Connection", "close");
               request.send(paramString);
               //Get next orderId from server (requires padded JSON to use response)
               $.getJSON("http://localhost:8080/POSProject/create_order?callback=?", function(result){
                   $('#orderIdCell').html(result.id);
               });
               //Reset everything for next order
               resetFields();
               _loadType = 'new';
            }
            
            function cancelOrder(){
                //AJAX request to controller to remove all data about the order
                var request = new XMLHttpRequest();
                var paramString = "orderId=" + $('#orderIdCell').html();
                request.open('POST', 'cancel_order', true);
                request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                request.setRequestHeader("Content-length", paramString.length);
                request.setRequestHeader("Connection", "close");
                request.send(paramString);
                //remove form data and javascript data in the right hand boxes
                resetFields();
            }
            
            function resetFields(){
                $('#subtotal, #tax, #total').html("$0.00");
                $('#orderItemsTable tbody').html("");
                $('input:text').val("");
                $('radio').prop("checked", false);
                $('#carryOutForm').hide();
                $('#deliveryForm').hide();
                $('#dineInForm').hide();
                $('#delTime, #cotime').attr('checked', true);
                $('#delTimeInfo, #coTimeInfo').hide();
                _serveType = "";
            }
            
            function removeItem(itemIndex){
                $.ajax({
                   url: 'remove_item',
                   asynch: true,
                   type: 'POST',
                   data: 'orderId=' + $('#orderIdCell').html() +
                         '&itemIndex=' + itemIndex
                });
                $('#item' + itemIndex).remove();
                updateTotalsBox();
            }
            
            function showTimeInput(t){
                if(t.checked){
                    if(t.id == 'delTime'){
                        $('#delTimeInfo').hide();
                    }
                    else{
                        $('#coTimeInfo').hide();
                    }
                }
                else{
                    if(t.id == 'delTime'){
                        $('#delTimeInfo').show();
                    }
                    else{
                        $('#coTimeInfo').show();
                    }
                }
            }
            
            function getWantTime(){
                var hours, mins, ampm, timeString;
                var time = "ASAP";
                if(_serveType == 'delivery'){
                    if(!$('#delTime').is(':checked')){
                        hours = Number($('#delHour').val()) * 100;
                        if(hours == 1200){
                            hours = 0;
                        }
                        mins = Number($('#delMins').val());
                        ampm = $('#delAmPm').val();
                        time = hours + mins;
                    }
                }
                else{
                    if(!$('#cotime').is(':checked')){
                        hours = Number($('#coHour').val()) * 100;
                        if(hours == 1200){
                            hours = 0;
                        }
                        mins = Number($('#coMins').val());
                        ampm = $('#coAmPm').val();
                        time = hours + mins;
                    }
                }
                if(ampm == 'PM' && time != "ASAP"){
                    time += 1200;
                }
                timeString = String(time);
                if(timeString.length == 2){
                    timeString = '0' + timeString;
                    time = timeString;
                }
                if(timeString.length == 1){
                    timeString = '00' + timeString;
                    time = timeString;
                }
                return time;        
            }
            
            function fillInTimeFields(hourField, minField, amPmField){
                $(amPmField).val(_orderJSON.wantTime.substring(_orderJSON.wantTime.length-2));
                var remainingString = _orderJSON.wantTime.substring(0, _orderJSON.wantTime.length-2);
                $(minField).val(remainingString.substring(remainingString.length-2));
                remainingString = remainingString.substring(0,remainingString.length-3);
                $(hourField).val(remainingString);
            }
            
            
            