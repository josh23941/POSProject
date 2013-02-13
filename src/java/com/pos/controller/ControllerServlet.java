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

@WebServlet(name = "ControllerServlet", urlPatterns = {"/item_input", "/item_save",
    "/view_items"})
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
        //Get the last part of the URI
        String uri = request.getRequestURI();
        int lastSlashIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastSlashIndex + 1);
        
        if (action.equals("item_input")){
            
        }
        
        else if (action.equals("item_save")){
            MenuItemForm menuItemForm = new MenuItemForm();
            menuItemForm.setName(request.getParameter("name"));
            menuItemForm.setPrice(request.getParameter("price"));
            MenuItem menuItem = new MenuItem();
            menuItem.setName(request.getParameter("name"));
            menuItem.setPrice(Float.parseFloat(request.getParameter("price")));
            SaveMenuItemAction saveMenuItemAction = new SaveMenuItemAction();
            saveMenuItemAction.save(menuItem);
            request.setAttribute("menuItem", menuItem);
        }
        
        else if (action.equals("view_items")){
            Menu menu = new Menu();
            menu.refreshMenu();
            List<MenuItem> menuList = menu.getItems();
            request.setAttribute("menuList", menuList);
        }
        
        String dispatchUrl = null;
        
        if (action.equals("item_input")){
            dispatchUrl = "jsp/MenuItemForm.jsp";
        }
        
        else if (action.equals("item_save")){
            dispatchUrl = "jsp/ItemDetails.jsp";
        }
        
        else if (action.equals("view_items")){
            dispatchUrl = "jsp/ViewItems.jsp";
        }
        
        if(dispatchUrl != null){
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
