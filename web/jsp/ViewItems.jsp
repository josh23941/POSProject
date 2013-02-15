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
    </head>
    <body>
        <p style="font-weight: bold">
            <h2>Menu Item List</h2>
        </p>
        <table>
            <tr>
                <td style="font-weight:bold">Name</td>
                <td style="font-weight:bold">Price</td>
            </tr>
            <c:forEach var="item" items="${menuList}">
                    <tr>
                        <td style="padding-right:20px"><c:out value="${item.name}: "/></td>
                        <td><c:out value="$${item.price}"/></td>
                    </tr> 
            </c:forEach>
        </table>
    </body>
</html>
