/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.puntoventalorenzo.controlador.impuestos;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
// import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import py.com.puntoventalorenzo.DAO.ImpuestoDAO;
import py.com.puntoventalorenzo.modelo.Impuesto;

/**
 *
 * @author miguelangel
 */
@WebServlet(name = "ImpuestoModificar", urlPatterns = {"/impuesto/modificar"})
public class ImpuestoModificar extends HttpServlet {

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
            int id_impuesto = Integer.parseInt(request.getParameter("id_impuesto"));
            String nombre_impuesto = request.getParameter("nombre_impuesto"); 
            BigDecimal porcentaje_impuesto = new BigDecimal (request.getParameter("porcentaje_impuesto"));
            
            Impuesto impuesto = new Impuesto();
            impuesto.setId_impuesto(id_impuesto);
            impuesto.setNombre_impuesto(nombre_impuesto);
            impuesto.setPorcentaje_impuesto(porcentaje_impuesto);
            
            ImpuestoDAO impuestoDAO = new ImpuestoDAO();
            boolean modificado = impuestoDAO.modificar(impuesto);
            
            JSONObject obj = new JSONObject();
            obj.put("modificado", modificado);
        
            out.print(obj);
            out.flush();
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
