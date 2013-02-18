<%-- 
    Document   : ViewUsers
    Created on : Feb 18, 2013, 2:52:10 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Users</title>
        <style type="text/css">@import url(css/item_table.css);</style>
    </head>
    <body>
        <h2><p class="page_header">Menu Item List</p></h2>
        <table>
            <tr>
                <td class="table_header">Name</td>
                <td class="table_header">Role</td>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.role}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
