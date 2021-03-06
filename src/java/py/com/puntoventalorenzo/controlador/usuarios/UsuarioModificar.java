/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.puntoventalorenzo.controlador.usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import py.com.puntoventalorenzo.DAO.UsuarioDAO;
import py.com.puntoventalorenzo.modelo.Rol;
import py.com.puntoventalorenzo.modelo.Usuario;

/**
 *
 * @author miguelangel
 */
@WebServlet(name = "UsuarioModificar", urlPatterns = {"/usuario/modificar"})
public class UsuarioModificar extends HttpServlet {

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
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            String nombre_usuario = request.getParameter("nombre_usuario");
            String usuario_usuario = request.getParameter("usuario_usuario");
            String clave_usuario = request.getParameter("clave_usuario");
            int id_rol = Integer.parseInt(request.getParameter("id_rol"));

            
            Usuario usuario = new Usuario();
            usuario.setId_usuario(id_usuario);
            usuario.setNombre_usuario(nombre_usuario);
            usuario.setUsuario_usuario(usuario_usuario);
            usuario.setClave_usuario(clave_usuario);
            
            Rol rol = new Rol();
            rol.setId_rol(id_rol);
            
            usuario.setRol(rol);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean modificado = usuarioDAO.modificar(usuario);
            
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
