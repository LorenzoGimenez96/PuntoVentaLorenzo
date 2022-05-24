package py.com.puntoventalorenzo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.puntoventalorenzo.modelo.TipoItem;
import py.com.puntoventalorenzo.util.Configuracion;

public class TipoItemDAO {
    
    public TipoItem buscarId(int id){
        TipoItem tipoitem = new TipoItem();
        tipoitem.setId_tipoitem(0);
        tipoitem.setNombre_tipoitem("");
        
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM tipositems "
                           + "WHERE id_tipoitem=?";
                PreparedStatement ps =
                    conexion.getCon().prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    tipoitem.setId_tipoitem(rs.getInt("id_tipoitem"));
                    tipoitem.setNombre_tipoitem(rs.getString("nombre_tipoitem"));
                }
                ps.close();
            } catch (Exception e) {
                System.out.println("--> " + 
                        e.getLocalizedMessage());
            }
        }
        return tipoitem;
    }
    
    public ArrayList buscarNombre(String texto, int pagina) {
        int limit = Configuracion.REGISTROS_POR_PAGINA;
        int offset = (pagina - 1) * limit;
        ArrayList tipoitems = new ArrayList();
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM tipositems "
                        + "WHERE UPPER(nombre_tipoitem) LIKE ? "
                        + "ORDER BY id_tipoitem "
                        + "LIMIT ? OFFSET ?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, "%" + texto.toUpperCase() + "%");
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        TipoItem tipoitem = new TipoItem();
                        tipoitem.setId_tipoitem(rs.getInt("id_tipoitem"));
                        tipoitem.setNombre_tipoitem(rs.getString("nombre_tipoitem"));
                        
                        tipoitems.add(tipoitem);
                    }
                    ps.close();
                }
            } catch (Exception ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            conexion.cerrar();
        }
        return tipoitems;
    }
    
    public boolean agregar(TipoItem tipoitem) {
        boolean agregado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "insert into tipositems ("
                        + "nombre_tipoitem"
                        + ") "
                        + "values (?)";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, tipoitem.getNombre_tipoitem());
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
    
    public boolean modificar(TipoItem tipoitem) {
        boolean modificado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "UPDATE tipositems SET "
                        + "nombre_tipoitem=? "
                        + "WHERE id_tipoitem=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, tipoitem.getNombre_tipoitem());
                    ps.setInt(2, tipoitem.getId_tipoitem());
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
    
    public boolean eliminar(int id_tipoitem) {
        boolean eliminado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "delete from tipositems "
                        + "where id_tipoitem=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_tipoitem);
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
