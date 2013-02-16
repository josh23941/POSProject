<%-- 
    Document   : index
    Created on : Feb 11, 2013, 1:58:14 PM
    Author     : Josh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POS Login</title>
        <style type="text/css">@import url(css/login.css);</style>
    </head>
    <body>
        <div id="global">
            <c:choose>
                <c:when test="${errorMessage == 'wrong_password'}">
                    <p class="error">Password Is Incorrect.  Try Again</p>"/>
                </c:when>
                <c:when test="${errorMessage == 'no_user'}">
                    <p class="error">User does not exist.  Try Again</p>"/>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <h3>Enter Login Credentials</h3>
            <form method="post" action="verify_login">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="password" /></td>
                        <td><input type="submit" value="Login" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
