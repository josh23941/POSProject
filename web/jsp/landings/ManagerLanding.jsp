<%-- 
    Document   : ManagerLanding
    Created on : Feb 13, 2013, 9:42:59 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Landing</title>
    </head>
    <body>
        <h1>Manager Menu</h1>
        <table>
            <tr><td><a href="add_category" class="menuLink">Add Menu Category</a></td></tr>
            <tr><td><a href="item_input" class="menuLink">Add Menu Items</a></td></tr>
            <tr><td><a href="view_items" class="menuLink">View Menu Items</a></td></tr>
            <tr><td><a href="add_employee" class="menuLink">Add Employee</a></td></tr>
            <tr><td><a href="view_employees" class="menuLink"">View Employees</a></td></tr>
            <tr><td><a href="menu_test" class="menuLink">Place Order</a></td></tr>
            <tr><td><a href="view_orders?type=active" class="menuLink">View Active Orders</a></td></tr>
            <tv><td><a href="view_orders?type=served" class="menuLink">View Served Orders</a></td></tr>
            <tr><td><a href="logout" class="menuLink">Log Out</a></td></tr>
        </table>
    </body>
</html>
