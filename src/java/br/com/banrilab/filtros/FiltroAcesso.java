/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.filtros;

import br.com.banrilab.beans.UsuariosBean;
import br.com.banrilab.entidades.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pedro
 */
public class FiltroAcesso implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect(request.getContextPath() + "/faces/login.xhtml"); // No logged-in user found, so redirect to login page.
        } else {
            if (session.getAttribute("perfil").equals(1) && request.getRequestURI().contains("/faces/banrilab/adminlab/")) {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            } else if (session.getAttribute("perfil").equals(2) && request.getRequestURI().contains("/faces/banrilab/coordenadortestes/")) {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            } else if (session.getAttribute("perfil").equals(3) && request.getRequestURI().contains("/faces/banrilab/analistatestes/")) {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            } else if (session.getAttribute("perfil").equals(4) && request.getRequestURI().contains("/faces/banrilab/testador/")) {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            } else if (session.getAttribute("perfil").equals(5) && request.getRequestURI().contains("/faces/banrilab/desenvolvedor/")) {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            } else {
                response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
            }
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

}
