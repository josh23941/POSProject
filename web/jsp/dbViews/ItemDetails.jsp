<%-- 
    Document   : ItemDetails
    Created on : Feb 11, 2013, 4:59:00 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Save Item</title>
        <style type="text/css">@import url(css/item_table.css);</style>
    </head>
    <body>
        <div id="global">
            <h4>The item has been saved</h4>
            <p>
                <h5>Details:</h5>
                <table>
                    <tr><td class="table_header">Item Name</td><td class="table_header">Price</td></tr>
                    <tr><td>${menuItem.name}</td><td>$${menuItem.price}</td></tr>
                </table>
            </p>
        </div>
    </body>
</html>
