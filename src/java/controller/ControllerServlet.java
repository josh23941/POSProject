/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import action.SaveMenuItemAction;
import form.MenuItemForm;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuItem;

/**
 *
 * @author Joshua Miller
 */

@WebServlet(name = "ControllerServlet", urlPatterns = {"/item_input", "/item_save"})
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
        
        String dispatchUrl = null;
        
        if (action.equals("item_input")){
            dispatchUrl = "jsp/MenuItemForm.jsp";
        }
        
        else if (action.equals("item_save")){
            dispatchUrl = "jsp/ItemDetails.jsp";
        }
        
        if(dispatchUrl != null){
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
