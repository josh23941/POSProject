<%-- 
    Document   : AddMenuCategory
    Created on : Feb 18, 2013, 7:09:16 PM
    Author     : Josh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Menu Category</title>
    </head>
    <body>
        <h2><p>Add Menu Category</p></h2>
        <form action="save_category" method="post">
            <table>
                <tr>
                    <td>Category Name: </td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>Parent Category: </td>
                    <td>
                        <select name="parent">
                            <!-- JSP grabs options from the category table -->
                            <c:forEach var="category" items="${menuCategoryList}">
                                <option value="${category.name}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Add Menu Category"/>
                    </td>
                </tr>
            </table>
        </form>    
    </body>
</html>
