package py.com.puntoventalorenzo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.puntoventalorenzo.modelo.Rol;
import py.com.puntoventalorenzo.util.Configuracion;

public class RolDAO {
    
    public Rol buscarId(int id){
        Rol rol = new Rol();
        rol.setId_rol(0);
        rol.setNombre_rol("");
        rol.setMenu_rol("");
        
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM roles "
                           + "WHERE id_rol=?";
                PreparedStatement ps =
                    conexion.getCon().prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    rol.setId_rol(rs.getInt("id_rol"));
                    rol.setNombre_rol(rs.getString("nombre_rol"));
                    rol.setMenu_rol(rs.getString("menu_rol"));
                }
                ps.close();
            } catch (Exception e) {
                System.out.println("--> " + 
                        e.getLocalizedMessage());
            }
        }
        return rol;
    }
    
    public ArrayList buscarNombre(String texto, int pagina) {
        int limit = Configuracion.REGISTROS_POR_PAGINA;
        int offset = (pagina - 1) * limit;
        ArrayList roles = new ArrayList();
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM roles "
                        + "WHERE UPPER(nombre_rol) LIKE ? "
                        + "ORDER BY id_rol "
                        + "LIMIT ? OFFSET ?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, "%" + texto.toUpperCase() + "%");
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Rol rol = new Rol();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        rol.setMenu_rol(rs.getString("menu_rol"));

                        roles.add(rol);
                    }
                    ps.close();
                }
            } catch (Exception ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            conexion.cerrar();
        }
        return roles;
    }
    
    public boolean agregar(Rol rol) {
        boolean agregado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "insert into roles ("
                        + "nombre_rol, menu_rol"
                        + ") "
                        + "values (?,?)";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, rol.getNombre_rol());
                    ps.setString(2, rol.getMenu_rol());
                    int cr = ps.executeUpdate();
                    if (cr > 0) {
                        agregado = true;
                        conexion.getCon().commit();
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--->" + ex.getLocalizedMessage());
                try {
                    conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("-->" + ex.getLocalizedMessage());
                }
            }
        }
        conexion.cerrar();
        return agregado;
    }
    
    public boolean modificar(Rol rol) {
        boolean modificado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "UPDATE roles SET "
                        + "nombre_rol=?, menu_rol=? "
                        + "WHERE id_rol=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, rol.getNombre_rol());
                    ps.setString(2, rol.getMenu_rol());
                    ps.setInt(3, rol.getId_rol());
                    int cr = ps.executeUpdate();
                    if (cr > 0) {
                        modificado = true;
                        conexion.getCon().commit();
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--->" + ex.getLocalizedMessage());
                try {
                    conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("-->" + ex.getLocalizedMessage());
                }
            }
        }
        conexion.cerrar();
        return modificado;
    }
    
    public boolean eliminar(int id_rol) {
        boolean eliminado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "delete from roles "
                        + "where id_rol=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_rol);
                    int cr = ps.executeUpdate();
                    if (cr > 0) {
                        eliminado = true;
                        conexion.getCon().commit();
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--->" + ex.getLocalizedMessage());
                try {
                    conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("-->" + ex.getLocalizedMessage());
                }
            }
        }
        conexion.cerrar();
        return eliminado;
    }
    
}
