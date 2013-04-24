/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Josh
 */
@WebFilter(displayName="default",
           servletNames={"default"},
           urlPatterns={"/resources/*"})

public class DefaultFilter implements Filter{
    private RequestDispatcher defaultServletDispatcher;
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.defaultServletDispatcher = fc.getServletContext().getNamedDispatcher("default");
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        defaultServletDispatcher.forward(sr, sr1);
    }

    @Override
    public void destroy() {
    }
    
}
