<%-- 
    Document   : AddMenuCategory
    Created on : Feb 18, 2013, 7:09:16 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Menu Category</title>
    </head>
    <body>
        <h2><p>Add Menu Category</p></h2>
        <form action="add_menu_category" method="post">
            <table>
                <tr>
                    <td>Category Name: </td>
                    <td><input type="text" name="categoryName"/></td>
                </tr>
                <tr>
                    <td>Parent Category: </td>
                    <td>
                        <select name="parent">
                            <!-- JSP grabs options from the submenu table -->
                        </select>
                    </td>
                </tr>
            </table>
        </form>    
    </body>
</html>
