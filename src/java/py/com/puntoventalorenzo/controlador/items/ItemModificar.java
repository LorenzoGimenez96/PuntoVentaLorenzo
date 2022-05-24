/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.puntoventalorenzo.controlador.items;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import py.com.puntoventalorenzo.DAO.ItemDAO;
import py.com.puntoventalorenzo.modelo.Item;

/**
 *
 * @author miguelangel
 */
@WebServlet(name = "ItemModificar", urlPatterns = {"/item/modificar"})
public class ItemModificar extends HttpServlet {

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
            int id_item = Integer.parseInt(request.getParameter("id_item"));
            String nombre_item = request.getParameter("nombre_item");
            int id_tipoitem = Integer.parseInt(request.getParameter("id_tipoitem"));
           int id_impuesto = Integer.parseInt(request.getParameter("id_impuesto"));
            
            Item item = new Item();
            item.setId_item(id_item);
            item.setNombre_item(nombre_item);
            item.getTipoItem().getId_tipoitem();
            item.getTipoItem().getNombre_tipoitem();
            item.getImpuesto().getId_impuesto();
            item.getImpuesto().getNombre_impuesto();
            item.getImpuesto().getPorcentaje_impuesto();
            
            ItemDAO itemDAO = new ItemDAO();
            boolean modificado = itemDAO.modificar(item);
            
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
