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
        <style type="text/css">@import url(css/main.css);</style>
    </head>
    <body>
        <div id="global">
            <h4>The item has been saved</h4>
            <p>
                <h5>Details:</h5>
                Item Name: ${menuItem.name}<br/>
                Item Price: $${menuItem.price}
            </p>
        </div>
    </body>
</html>
