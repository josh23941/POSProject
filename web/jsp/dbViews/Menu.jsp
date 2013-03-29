<%-- 
    Document   : Menu
    Created on : Mar 29, 2013, 1:23:14 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>
        <li>
            <c:out value="${htmlMenu}" escapeXml="false"/>
        </li>
    </body>
</html>
