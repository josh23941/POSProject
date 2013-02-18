<%-- 
    Document   : AddUserForm
    Created on : Feb 18, 2013, 11:59:16 AM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User Form</title>
    </head>
    <body>
        <form method="post" action="user_save">
            <table>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="password"/></td>
                </tr>
                <tr>
                    <td>Organization: </td>
                    <td><input type="text" name="organization"/></td>
                </tr>
                <tr>
                    <td>Role: </td>
                    <td><select name="role">
                            <option value="manager">Manager</option>
                            <option value="employee">Employee</option>
                        </select>
                    </td>
                </tr>
                <tr><td><input type="submit" value="Add User"/></td></tr>
            </table>
        </form>
    </body>
</html>
