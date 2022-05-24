package py.com.puntoventalorenzo.DAO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.puntoventalorenzo.modelo.Impuesto;
import py.com.puntoventalorenzo.util.Configuracion;

public class ImpuestoDAO {
    
    public Impuesto buscarId(int id){
        Impuesto impuesto = new Impuesto();
        impuesto.setId_impuesto(0);
        impuesto.setNombre_impuesto("");
        impuesto.setPorcentaje_impuesto(BigDecimal.ZERO);
        
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM impuestos "
                           + "WHERE id_impuesto=?";
                PreparedStatement ps =
                    conexion.getCon().prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    impuesto.setId_impuesto(rs.getInt("id_impuesto"));
                    impuesto.setNombre_impuesto(rs.getString("nombre_impuesto"));
                    impuesto.setPorcentaje_impuesto(rs.getBigDecimal("porcentaje_impuesto"));
                }
                ps.close();
            } catch (Exception e) {
                System.out.println("--> " + 
                        e.getLocalizedMessage());
            }
        }
        return impuesto;
    }
    
    public ArrayList buscarNombre(String texto, int pagina) {
        int limit = Configuracion.REGISTROS_POR_PAGINA;
        int offset = (pagina - 1) * limit;
        ArrayList impuestos = new ArrayList();
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM impuestos "
                        + "WHERE UPPER(nombre_impuesto) LIKE ? "
                        + "ORDER BY id_impuesto "
                        + "LIMIT ? OFFSET ?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, "%" + texto.toUpperCase() + "%");
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Impuesto impuesto = new Impuesto();
                        impuesto.setId_impuesto(rs.getInt("id_impuesto"));
                        impuesto.setNombre_impuesto(rs.getString("nombre_impuesto"));
                        impuesto.setPorcentaje_impuesto(rs.getBigDecimal("porcentaje_impuesto"));

                        impuestos.add(impuesto);
                    }
                    ps.close();
                }
            } catch (Exception ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            conexion.cerrar();
        }
        return impuestos;
    }
    
    public boolean agregar(Impuesto impuesto) {
        boolean agregado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "insert into impuestos ("
                        + "nombre_impuesto, porcentaje_impuesto"
                        + ") "
                        + "values (?,?)";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, impuesto.getNombre_impuesto());
                    ps.setBigDecimal(2, impuesto.getPorcentaje_impuesto());
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
    
    public boolean modificar(Impuesto impuesto) {
        boolean modificado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "UPDATE impuestos SET "
                        + "nombre_impuesto=?, porcentaje_impuesto=? "
                        + "WHERE id_impuesto=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, impuesto.getNombre_impuesto());
                    ps.setBigDecimal(2, impuesto.getPorcentaje_impuesto());
                    ps.setInt(3, impuesto.getId_impuesto());
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
    
    public boolean eliminar(int id_impuesto) {
        boolean eliminado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "delete from impuestos "
                        + "where id_impuesto=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_impuesto);
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