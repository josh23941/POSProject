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
        <p>
            <c:forEach var="item" items="${menuList}">
                <c:out value="${item.name}: ${item.price}" />
            </c:forEach>
        </p>
    </body>
</html>
