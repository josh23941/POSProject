/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.model.order.CarryoutOrder;
import com.pos.model.order.DineInOrder;
import com.pos.model.order.DeliveryOrder;
import com.pos.action.MenuCategoryAction;
import com.pos.action.MenuItemAction;
import com.pos.action.OrderAction;
import com.pos.action.UserAction;
import com.pos.dao.UserDAO;
import com.pos.form.AddUserForm;
import com.pos.form.MenuCategoryForm;
import com.pos.form.MenuItemForm;
import com.pos.model.menu.*;
import com.pos.model.user.User;
import com.pos.util.TimeUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
            MenuCategoryAction menuCategoryAction = new MenuCategoryAction();
            List<MenuCategory> menuCategoryList = menuCategoryAction.getMenuCategories();
            request.setAttribute("menuCategoryList", menuCategoryList);
            //DISPATCH:
            dispatchUrl = "jsp/forms/MenuItemForm.jsp";
        }
        
        else if (action.equals("item_save")){
            //ACTION:
            MenuItemForm menuItemForm = new MenuItemForm();
            menuItemForm.setName(request.getParameter("name"));
            menuItemForm.setPrice(request.getParameter("price"));
            menuItemForm.setCategoryUID(request.getParameter("category"));
            MenuItem menuItem = menuItemForm.getMenuItemInstance();
            MenuItemAction menuItemAction = new MenuItemAction();
            menuItemAction.saveMenuItem(menuItem);
            request.setAttribute("menuItem", menuItem);
            //DISPATCH:
            dispatchUrl = "jsp/dbViews/ItemDetails.jsp";
        }
        
        else if (action.equals("view_items")){
            //ACTION:
            MenuItemAction menuItemAction = new MenuItemAction();
            List<MenuItem> menuList = menuItemAction.getMenuItems("all");
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
            AddUserForm userForm = new AddUserForm();
            userForm.setName(request.getParameter("name"));
            userForm.setPassword(request.getParameter("password"));
            userForm.setOrganization(request.getParameter("organization"));
            userForm.setRole(request.getParameter("role"));
            //@todo auto set org here depending on the manager's org for now its just setting so field is NN in DB.
            User user = userForm.getUserInstance();
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
        
        else if (action.equals("add_category")){
            //ACTION:
            MenuCategoryAction menuCategoryAction = new MenuCategoryAction();
            List<MenuCategory> menuCategoryList = menuCategoryAction.getMenuCategories();
            request.setAttribute("menuCategoryList", menuCategoryList);
            //DISPATCH:
            dispatchUrl = "jsp/forms/AddMenuCategory.jsp";
        }
        
        else if (action.equals("save_category")){
            //ACTION:
            MenuCategoryForm menuCategoryForm = new MenuCategoryForm();
            menuCategoryForm.setName(request.getParameter("name"));
            menuCategoryForm.setParent(request.getParameter("parent"));
            MenuCategory menuCategory = menuCategoryForm.getMenuCategoryInstance();
            MenuCategoryAction menuCategoryAction = new MenuCategoryAction();
            menuCategoryAction.saveMenuCategory(menuCategory);
            //@todo Might need Form > Model conversion here if non-string parameters become involved.
            request.setAttribute("menuCategory", menuCategory);
            //DISPATCH:
            dispatchUrl = "jsp/dbViews/CategoryDetails.jsp";
        }
        
        else if (action.equals("menu_test")){
            MenuCategoryAction menuCategoryAction = new MenuCategoryAction();
            MenuCategoryTreeNode rootNode = menuCategoryAction.getRootNode();
            rootNode.resetHTML();
            String htmlMenu = rootNode.outputHTML();
            request.setAttribute("htmlMenu", htmlMenu);
            OrderAction orderAction = new OrderAction();
            int orderId;
            if(request.getParameter("editId")!= null){
                orderId = Integer.parseInt(request.getParameter("editId"));
                //Refill order state, thinking build order obj here and access via jsp
                String serveType = request.getParameter("serveType");
                if(serveType.equals("delivery")){
                    DeliveryOrder order = orderAction.getDeliveryOrder(orderId);
                    request.setAttribute("orderJSON", order.getJSON());
                    request.setAttribute("serveType", "delivery");
                }
                else if(serveType.equals("carryout")){
                    CarryoutOrder order = orderAction.getCarryoutOrder(orderId);
                    request.setAttribute("orderJSON", order.getJSON());
                    request.setAttribute("serveType", "carryout");
                }
                else if(serveType.equals("dinein")){
                    DineInOrder order = orderAction.getDineInOrder(orderId);
                    request.setAttribute("orderJSON", order.getJSON());
                    request.setAttribute("serveType", "dinein");
                }
            request.setAttribute("loadType", "edit");
            request.setAttribute("itemsJSON", orderAction.getOrderItemInfoJSON(orderId));
            }
            else{
                orderId = orderAction.startNewOrder();
                request.setAttribute("loadType", "new");
                request.setAttribute("itemsJSON", "''");
                request.setAttribute("orderJSON", "''");
            }
            request.setAttribute("orderId", orderId);
            //DISPATCH:
            dispatchUrl = "jsp/dbViews/Menu.jsp";    
        }
        
        else if (action.equals("update_order")){
            //build new action object for orders
            OrderAction orderAction = new OrderAction();
            int uid = Integer.parseInt(request.getParameter("uid"));
            String item = request.getParameter("itemName");
            double price = Double.parseDouble(request.getParameter("price"));
            int itemIndex = Integer.parseInt(request.getParameter("index"));
            orderAction.addItemToOrder(uid, item, price, itemIndex);
            //possibly build model object for orderupdate?
            //pull post data from request put in ^ object
            //execute the correct DB operation using the action object
            
            //DISPATCH:
            dispatchUrl = null;
        }
        
        else if (action.equals("create_order")){
            OrderAction orderAction = new OrderAction();
            String newOrderId = Integer.toString(orderAction.startNewOrder());
            String callback = request.getParameter("callback");
            String responseString = callback + "({'id': '" + newOrderId + "'})"; 
            response.setContentType("text/javascript");
            response.setContentLength(responseString.length());
            response.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.println(responseString);
            out.close();
            out.flush();
            //DISPATCH:
            dispatchUrl = null;
        }
        
        else if (action.equals("complete_order")){
            OrderAction orderAction = new OrderAction();
            String serveType = request.getParameter("serveType");
            if(serveType.equals("delivery")){
                DeliveryOrder order = new DeliveryOrder();
                order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
                order.setSubTotal(Double.parseDouble(request.getParameter("subtotal")));
                order.setTax(Double.parseDouble(request.getParameter("tax")));
                order.setTotalPrice(Double.parseDouble(request.getParameter("total")));
                order.setAddress(request.getParameter("address"));
                order.setPhoneNumber(request.getParameter("phone"));
                order.setWantDate(request.getParameter("wantDate"));
                if(!request.getParameter("wantTime").equals("ASAP")){
                    order.setMilitaryWantTime(Integer.parseInt(request.getParameter("wantTime")));
                    order.setWantTime(TimeUtil.militaryToStringTime(request.getParameter("wantTime")));
                }
                else{
                    order.setMilitaryWantTime(-1);
                    order.setWantTime("ASAP");
                }
                order.setTimeStamp(Long.parseLong(request.getParameter("time")));                
                orderAction.completeOrder(order);
            }
            else if(serveType.equals("carryout")){
                CarryoutOrder order = new CarryoutOrder();
                order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
                order.setSubTotal(Double.parseDouble(request.getParameter("subtotal")));
                order.setTax(Double.parseDouble(request.getParameter("tax")));
                order.setTotalPrice(Double.parseDouble(request.getParameter("total")));
                order.setName(request.getParameter("name"));
                order.setPhoneNumber(request.getParameter("phone"));
                order.setWantDate(request.getParameter("wantDate"));
                if(!request.getParameter("wantTime").equals("ASAP")){
                    order.setMilitaryWantTime(Integer.parseInt(request.getParameter("wantTime")));
                    order.setWantTime(TimeUtil.militaryToStringTime(request.getParameter("wantTime")));
                }
                else{
                    order.setMilitaryWantTime(-1);
                    order.setWantTime("ASAP");
                }
                order.setTimeStamp(Long.parseLong(request.getParameter("time")));
                orderAction.completeOrder(order);
            }
            else if(serveType.equals("dinein")){
                DineInOrder order = new DineInOrder();
                order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
                order.setSubTotal(Double.parseDouble(request.getParameter("subtotal")));
                order.setTax(Double.parseDouble(request.getParameter("tax")));
                order.setTotalPrice(Double.parseDouble(request.getParameter("total")));
                order.setTableNumber(Integer.parseInt(request.getParameter("table")));
                order.setTimeStamp(Long.parseLong(request.getParameter("time")));
                order.setMilitaryWantTime(-1);
                orderAction.completeOrder(order);
            }
        }
        
        else if (action.equals("view_orders")){
            //ACTION:
            OrderAction orderAction = new OrderAction();
            ArrayList<DeliveryOrder> deliveryOrders;
            ArrayList<CarryoutOrder> carryoutOrders;
            ArrayList<DineInOrder> dineInOrders;
            if(request.getParameter("type").equals("active")){
                deliveryOrders = orderAction.getDeliveryOrders(true);
                carryoutOrders = orderAction.getCarryoutOrders(true);
                dineInOrders = orderAction.getDineInOrders(true);
                dispatchUrl = "jsp/dbViews/activeOrders.jsp";
            }
            else{
                deliveryOrders = orderAction.getDeliveryOrders(false);
                carryoutOrders = orderAction.getCarryoutOrders(false);
                dineInOrders = orderAction.getDineInOrders(false);
                dispatchUrl = "jsp/dbViews/ServedOrders.jsp";
            }
            request.setAttribute("deliveryOrders", deliveryOrders);
            request.setAttribute("carryoutOrders", carryoutOrders);
            request.setAttribute("dineInOrders", dineInOrders);
        }
        
        else if (action.equals("cancel_order")){
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            OrderAction orderAction = new OrderAction();
            orderAction.cancelOrder(orderId);
        }
        
        else if (action.equals("serve_order")){
            OrderAction orderAction = new OrderAction();
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            orderAction.serveOrder(orderId);
            
        }
        
        else if (action.equals("remove_item")){
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int itemIndex = Integer.parseInt(request.getParameter("itemIndex"));
            OrderAction orderAction = new OrderAction();
            orderAction.removeItemFromOrder(orderId, itemIndex);
        }
       
        if(dispatchUrl != null){
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
