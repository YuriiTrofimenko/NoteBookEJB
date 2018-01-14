/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.notebook.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.tyaa.notebook.entity.Order1;
import org.tyaa.notebook.entity.State;
import org.tyaa.notebook.facade.Order1Facade;
import org.tyaa.notebook.facade.StateFacade;
import org.tyaa.notebook.model.States;

/**
 *
 * @author student
 */
public class Api extends HttpServlet {
    
    @EJB
    private StateFacade stateFacade;
    @EJB
    private Order1Facade order1Facade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new Gson();
        
        try (PrintWriter out = response.getWriter()) {
            
            if (request.getParameterMap().keySet().contains("action")) {
                
                switch(request.getParameter("action")) {
                    case "get_states" : {
                    
                        try {
                            States states = new States();
                            states.states.addAll(stateFacade.findAll());
                            out.print(gson.toJson(states));
                        } catch (Exception e) {
                            out.print(gson.toJson("error"));
                        }
                        break;
                    }
                    case "create_order" : {
                    
                        try {
                            String customerName = request.getParameter("customer_name");
                            String description = request.getParameter("description");
                            State state = stateFacade.findByName("created");
                            
                            if (state == null) {
                                
                                out.print(gson.toJson("State not exists"));
                                break;
                            }
                            
                            Order1 order1 = new Order1();
                            order1.setCustomerName(customerName);
                            order1.setDescription(description);
                            order1.setCreatedAt(new Date());
                            order1.setStateId(state);
                            order1Facade.create(order1);
                            out.print(gson.toJson("ok"));
                        } catch (Exception e) {
                            out.print(gson.toJson("error"));
                        }
                    }
                }
            }
            
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
