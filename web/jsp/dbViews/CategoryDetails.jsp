<%-- 
    Document   : ViewCategory
    Created on : Feb 19, 2013, 9:34:25 AM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Category</title>
        <style type="text/css">@import url(css/item_table.css)</style>
    </head>
    <body>
        <table>
            <tr>
                <td class="table_header">Name</td>
                <td class="table_header">Parent UID</td>
            </tr>
                <td><c:out value="${menuCategory.name}"/></td>
                <td><c:out value="${menuCategory.parent}"/></td>
        </table>
    </body>
</html>
