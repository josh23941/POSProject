<%-- 
    Document   : MenuItemForm.jsp
    Created on : Feb 11, 2013, 4:50:10 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Menu Item Form</title>
        
    </head>
    <body>
        <div id="global">
            <h3>Add a Menu Item</h3>
            <form method="post" action="item_save">
                <table>
                    <tr>
                        <td>Item Name: </td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td>Price: </td>
                        <td><input type="text" name="price"/></td>
                        <td><input type="reset"/></td>
                        <td><input type="submit" value="Add Item"/></td>
                    </tr>
                    <tr>
                        <td>Category: </td>
                        <td>
                            <select name="category">
                                <!-- JSP pulls categories in here -->
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
