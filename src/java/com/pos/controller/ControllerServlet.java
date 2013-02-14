/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.action.SaveMenuItemAction;
import com.pos.form.MenuItemForm;
import com.pos.model.Menu;
import com.pos.model.MenuItem;
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

@WebServlet(name = "ControllerServlet", 
    urlPatterns = {
        "/item_input", 
        "/item_save",
        "/view_items", 
        "/", 
        "", 
        "/login",
        "/verify_login"})
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
        String uri = request.getRequestURI();
        int lastSlashIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastSlashIndex + 1);
        
        if (action.equals("item_input")){
            //ACTION:
            //DISPATCH:
            dispatchUrl = "jsp/MenuItemForm.jsp";
        }
        
        else if (action.equals("item_save")){
            //ACTION:
            MenuItemForm menuItemForm = new MenuItemForm();
            menuItemForm.setName(request.getParameter("name"));
            menuItemForm.setPrice(request.getParameter("price"));
            MenuItem menuItem = new MenuItem();
            menuItem.setName(request.getParameter("name"));
            menuItem.setPrice(Float.parseFloat(request.getParameter("price")));
            SaveMenuItemAction saveMenuItemAction = new SaveMenuItemAction();
            saveMenuItemAction.save(menuItem);
            request.setAttribute("menuItem", menuItem);
            //DISPATCH:
            dispatchUrl = "jsp/ItemDetails.jsp";
        }
        
        else if (action.equals("view_items")){
            //ACTION:
            Menu menu = new Menu();
            menu.refreshMenu();
            List<MenuItem> menuList = menu.getItems();
            request.setAttribute("menuList", menuList);
            //DISPATCH:
            dispatchUrl = "jsp/ViewItems.jsp";
        }
        
        else if (action.equals("verify_login")){
            //@todo add verification procedure set
            //ACTION:
            //DISPATCH:
        }
        
        else if (action.equals("login") || action.equals("") || action.equals("login")){
            //ACTION:
                //@todo handle those with valid sessions (send right to menu)
            //DISPATCH:
            dispatchUrl = "jsp/Login.jsp";
        }
        
        if(dispatchUrl != null){
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
