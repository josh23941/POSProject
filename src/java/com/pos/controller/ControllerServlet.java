/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.action.UserAction;
import com.pos.action.MenuItemAction;
import com.pos.dao.UserDAO;
import com.pos.form.MenuItemForm;
import com.pos.form.UserForm;
import com.pos.model.menu.MenuItem;
import com.pos.model.user.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joshua Miller
 */
//@todo probably shouldn't have hard coded strings here?  Check best practice...Low Priority...also I don't think I need all these patterns.
@WebServlet(name = "ControllerServlet", 
    urlPatterns = {
        "/place_order",
        "/logout",
        "/item_input", 
        "/item_save",
        "/view_items", 
        "/", 
        "", 
        "/login",
        "/verify_login",
        "/POSProject",
        "/view_users"
        })
public class ControllerServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        process(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        process(request, response);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        
        String dispatchUrl = null;
        //Get the last part of the URI
        
        //@todo when no trailing slash this block fails to work as intended
        String uri = request.getRequestURI();
        int lastSlashIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastSlashIndex + 1);
        
        if(action.equals("POSProject")){
            //@todo grab hostname from properties file or somewhere else
            //shouldn't be hardcoded.
            response.sendRedirect("http://localhost:8080/POSProject/");
            
        }
        
        else if (action.equals("item_input")){
            //ACTION:
            //DISPATCH:
            dispatchUrl = "jsp/forms/MenuItemForm.jsp";
        }
        
        else if (action.equals("item_save")){
            //ACTION:
            MenuItemForm menuItemForm = new MenuItemForm();
            menuItemForm.setName(request.getParameter("name"));
            menuItemForm.setPrice(request.getParameter("price"));
            MenuItem menuItem = new MenuItem();
            menuItem.setName(request.getParameter("name"));
            menuItem.setPrice(Float.parseFloat(request.getParameter("price")));
            MenuItemAction menuItemAction = new MenuItemAction();
            menuItemAction.save(menuItem);
            request.setAttribute("menuItem", menuItem);
            //DISPATCH:
            dispatchUrl = "jsp/dbViews/ItemDetails.jsp";
        }
        
        else if (action.equals("view_items")){
            //ACTION:
            MenuItemAction menuItemAction = new MenuItemAction();
            List<MenuItem> menuList = menuItemAction.getMenuItems();
            request.setAttribute("menuList", menuList);
            //DISPATCH:
            dispatchUrl = "jsp/dbViews/ViewItems.jsp";
        }
        
        else if (action.equals("verify_login")){
            //ACTION:
            UserAction userAction = new UserAction();
            User user;
            user = userAction.getUser(request.getParameter("username"));
            int authenticationResult = userAction.authenticate(request.getParameter("username"),
                    request.getParameter("password"));
            //DISPATCH:
            if(authenticationResult == UserDAO.Result.PASS){
                //@todo get and pass organization
                if(user.getRole().equals("manager")){
                    dispatchUrl = "jsp/landings/ManagerLanding.jsp";
                }
                else if(user.getRole().equals("employee")){
                    dispatchUrl = "jsp/landings/EmployeeLanding.jsp";
                }
            }
            else if (authenticationResult == UserDAO.Result.WRONG_PASSWORD){
                /*@todo all of these setAttribute()'s should conform to package naming standards
                ...should be com.pos.controller.errorMessage?...look up best practice*/
                request.setAttribute("errorMessage", "wrong_password");
                dispatchUrl = "jsp/forms/LoginForm.jsp";
            }
            else if (authenticationResult == UserDAO.Result.NO_USER){
                request.setAttribute("errorMessage", "no_user");
                dispatchUrl = "jsp/forms/LoginForm.jsp";
            }
        }
        
        else if (action.equals("login") || action.equals("") || action.equals("/")){
            //ACTION:
                //@todo handle those with valid sessions (send right to menu)
            //DISPATCH:
            dispatchUrl = "jsp/forms/LoginForm.jsp";
            
        }
        
        else if (action.equals("place_order")){
            //ACTION:
            //DISPATCH:
                //@todo implement jsp/PlaceOrder.jsp
            dispatchUrl = "jsp/PlaceOrder.jsp";
        }
        
        else if (action.equals("logout")){
            //ACTION:
                //@todo kill session 
            //DISPATCH:
                //@todo Decide if you want some type of logout specific page
            dispatchUrl = "jsp/forms/LoginForm.jsp";
        }
        
        else if (action.equals("add_employee")){
            //ACTION:  
            //DISPATCH:
            dispatchUrl = "jsp/forms/AddUserForm.jsp";
        }
        
        else if (action.equals("user_save")){
            //ACTION:
            UserForm userForm = new UserForm();
            userForm.setName(request.getParameter("name"));
            userForm.setPassword(request.getParameter("password"));
            userForm.setOrganization(request.getParameter("organization"));
            userForm.setRole(request.getParameter("role"));
            //@todo auto set org here depending on the manager's org for now its just setting so field is NN in DB.
            User user = new User();
            //@todo might be better to have UserForm and other patterns like this contain method to spit out User instead of constructing both here.
            user.setName(request.getParameter("name"));
            user.setPassword(request.getParameter("password"));
            user.setOrganization(request.getParameter("organization"));
            user.setRole("role");
            UserAction userAction = new UserAction();
            userAction.addUser(user);
            request.setAttribute("user", user);
            //DISPATCH:
            dispatchUrl = "jsp/dbViews/UserDetails.jsp";
        }
        
        else if (action.equals("view_users")){
            //ACTION:
            UserAction userAction = new UserAction();
            List<User> userList = userAction.getUsers();
            request.setAttribute("userList", userList);
            //DISPATCH:
            dispatchUrl = "jsp/dbViews/ViewUsers.jsp";
        }
        
        if(dispatchUrl != null){
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
