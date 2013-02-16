<%-- 
    Document   : ViewItems
    Created on : Feb 12, 2013, 11:12:50 AM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Menu Items</title>
        <style type="text/css">@import url(css/item_table.css)</style>
    </head>
    <body>
        <p class="page_header">
            <h2>Menu Item List</h2>
        </p>
        <table>
            <tr>
                <td class="table_header">Item Name</td>
                <td class="table_header">Price</td>
            </tr>
            <c:forEach var="item" items="${menuList}">
                    <tr>
                        <td class="item_name"><c:out value="${item.name}: "/></td>
                        <td><c:out value="$${item.price}"/></td>
                    </tr> 
            </c:forEach>
        </table>
    </body>
</html>
