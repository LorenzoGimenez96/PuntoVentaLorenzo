package py.com.puntoventalorenzo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.puntoventalorenzo.modelo.Rol;
import py.com.puntoventalorenzo.modelo.Usuario;
import py.com.puntoventalorenzo.util.Configuracion;

public class UsuarioDAO {
    
    public boolean validar(Usuario usuario){
        boolean acceso = false;
        
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM usuarios "
                           + "WHERE usuario_usuario = ? "
                           + "AND clave_usuario = ?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)){
                    ps.setString(1,usuario.getUsuario_usuario());
                    ps.setString(2,usuario.getClave_usuario());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        acceso = true;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            conexion.cerrar();
 
        }
        
        return acceso;
    }
    
    public Usuario buscarId(int id){
        Usuario usuario = new Usuario();
        usuario.setId_usuario(0);
        usuario.setNombre_usuario("");
        usuario.setUsuario_usuario("");
        usuario.setClave_usuario("");
        
        Rol rol = new Rol();
        rol.setId_rol(0);
        rol.setNombre_rol("");
        rol.setMenu_rol("");
        
        usuario.setRol(rol);
        
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM usuarios u "
                           + "LEFT JOIN roles r ON u.id_rol = r.id_rol "
                           + "WHERE id_usuario=?";
                PreparedStatement ps =
                    conexion.getCon().prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setUsuario_usuario(rs.getString("usuario_usuario"));
                    usuario.setClave_usuario(rs.getString("clave_usuario"));
                    
                    usuario.getRol().setId_rol(rs.getInt("id_rol"));
                    usuario.getRol().setNombre_rol(rs.getString("nombre_rol"));
                    usuario.getRol().setMenu_rol(rs.getString("menu_rol"));
                    
                }
                ps.close();
            } catch (Exception e) {
                System.out.println("--> " + 
                        e.getLocalizedMessage());
            }
        }
        return usuario;
    }
    
    public ArrayList buscarNombre(String texto, int pagina) {
        int limit = Configuracion.REGISTROS_POR_PAGINA;
        int offset = (pagina - 1) * limit;
        ArrayList usuarios = new ArrayList();
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM usuarios u "
                        + "LEFT JOIN roles r ON u.id_rol = r.id_rol "
                        + "WHERE UPPER(nombre_usuario) LIKE ? "
                        + "ORDER BY id_usuario "
                        + "LIMIT ? OFFSET ?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, "%" + texto.toUpperCase() + "%");
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setUsuario_usuario(rs.getString("usuario_usuario"));
                        usuario.setClave_usuario(rs.getString("clave_usuario"));
                        
                        Rol rol = new Rol();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        rol.setMenu_rol(rs.getString("menu_rol"));
                        
                        usuario.setRol(rol);

                        usuarios.add(usuario);
                    }
                    ps.close();
                }
            } catch (Exception ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            conexion.cerrar();
        }
        return usuarios;
    }
    
    public boolean agregar(Usuario usuario) {
        boolean agregado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "insert into usuarios ("
                        + "nombre_usuario, usuario_usuario, clave_usuario, id_rol "
                        + ") "
                        + "values (?,?,?,?)";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, usuario.getNombre_usuario());
                    ps.setString(2, usuario.getUsuario_usuario());
                    ps.setString(3, usuario.getClave_usuario());
                    ps.setInt(4,usuario.getRol().getId_rol());
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
    
    public boolean modificar(Usuario usuario) {
        boolean modificado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "UPDATE usuarios SET "
                        + "nombre_usuario=?, usuario_usuario=?, clave_usuario=?, id_rol=? "
                        + "WHERE id_usuario=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, usuario.getNombre_usuario());
                    ps.setString(2, usuario.getUsuario_usuario());
                    ps.setString(3, usuario.getClave_usuario());
                    ps.setInt(4, usuario.getRol().getId_rol());
                    ps.setInt(5, usuario.getId_usuario());
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
    
    public boolean eliminar(int id_usuario) {
        boolean eliminado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "delete from usuarios "
                        + "where id_usuario=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_usuario);
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
