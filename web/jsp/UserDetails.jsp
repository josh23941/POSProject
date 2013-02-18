<%-- 
    Document   : UserDetails
    Created on : Feb 18, 2013, 1:43:26 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details</title>
        <style type="text/css">@import url(css/item_table.css)</style>
    </head>
    <body>
        <div id="global">
            <h4>The user has been saved</h4>
            <p>
                <h5>Details:</h5>
                <table>
                    <tr><td class="table_header">Name</td><td class="table_header">Role</td></tr>
                    <tr><td>${user.name}</td><td>${user.role}</td></tr>
                </table>
            </p>
        </div>
    </body>
</html>
