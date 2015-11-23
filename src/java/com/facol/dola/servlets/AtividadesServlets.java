package com.facol.dola.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.facol.dola.business.ActivityBLL;
import com.facol.dola.business.UserBLL;
import com.facol.dola.models.Activity;
import com.facol.dola.models.JsonResponse;
import com.facol.dola.models.User;
import com.facol.dola.tools.PersistenceUnit;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.persistence.sessions.serializers.JSONSerializer;

/**
 *
 * @author dolalima
 */
public class AtividadesServlets extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AtididadesServlets</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AtididadesServlets at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        PersistenceUnit.start();
        JSONSerializer jSONSerializer = new JSONSerializer();
        JsonResponse jsonResponse = new JsonResponse();
        
        
            Activity activity = new Activity(request);
            ActivityBLL activityBLL = new ActivityBLL();
            UserBLL userBLL = new UserBLL();
            //User user = userBLL.findByCpf(request.getParameter("070.058.184-74"));
            //activity.setUser(user);
            //activityBLL.create(activity);
       

        try (PrintWriter out = response.getWriter()) {
            out.println(jSONSerializer.serialize(activity,null).toString());
        }
        PersistenceUnit.close();
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
