package py.com.puntoventalorenzo.DAO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.puntoventalorenzo.modelo.Impuesto;
import py.com.puntoventalorenzo.modelo.Item;
import py.com.puntoventalorenzo.modelo.TipoItem;
import py.com.puntoventalorenzo.util.Configuracion;

public class ItemDAO {

    public Item buscarId(int id) {
        Item item = new Item();
        item.setId_item(0);
        item.setNombre_item("");
        item.setPrecio_item(0);
        TipoItem tipoItem = new TipoItem();
        tipoItem.setId_tipoitem(0);
        tipoItem.setNombre_tipoitem("");
        item.setTipoItem(tipoItem);
        Impuesto impuesto = new Impuesto();
        impuesto.setId_impuesto(0);
        impuesto.setNombre_impuesto("");
        impuesto.setPorcentaje_impuesto(BigDecimal.ZERO);
        item.setImpuesto(impuesto);

        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM items i "
                        + "LEFT JOIN tipositems ti ON i.id_tipoitem = ti.id_tipoitem "
                        + "LEFT JOIN impuestos im ON i.id_impuesto = im.id_impuesto "
                        + "WHERE id_item=?";
                PreparedStatement ps
                        = conexion.getCon().prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    item.setId_item(rs.getInt("id_item"));
                    item.setNombre_item(rs.getString("nombre_item"));
                    item.setPrecio_item(rs.getInt("precio_item"));
                    item.getTipoItem().setId_tipoitem(rs.getInt("id_tipoitem"));
                    item.getTipoItem().setNombre_tipoitem(rs.getString("nombre_tipoitem"));
                    item.getImpuesto().setId_impuesto(rs.getInt("id_impuesto"));
                    item.getImpuesto().setNombre_impuesto(rs.getString("nombre_impuesto"));
                    item.getImpuesto().setPorcentaje_impuesto(rs.getBigDecimal("porcentaje_impuesto"));

                }
                ps.close();
            } catch (Exception e) {
                System.out.println("--> "
                        + e.getLocalizedMessage());
            }
        }
        return item;
    }

    public ArrayList buscarNombre(String texto, int pagina) {
        int limit = Configuracion.REGISTROS_POR_PAGINA;
        int offset = (pagina - 1) * limit;
        ArrayList items = new ArrayList();
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "SELECT * FROM items i "
                        + "LEFT JOIN tipositems ti ON i.id_tipoitem = ti.id_tipoitem "
                        + "LEFT JOIN impuestos im ON i.id_impuesto = im.id_impuesto "
                        + "WHERE UPPER(nombre_item) LIKE ? "
                        + "ORDER BY id_item "
                        + "LIMIT ? OFFSET ?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, "%" + texto.toUpperCase() + "%");
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Item item = new Item();
                        item.setId_item(rs.getInt("id_item"));
                        item.setNombre_item(rs.getString("nombre_item"));
                        item.setPrecio_item(rs.getInt("precio_item"));
                        item.getTipoItem().setId_tipoitem(rs.getInt("id_tipoitem"));
                        item.getTipoItem().setNombre_tipoitem(rs.getString("nombre_tipoitem"));
                        item.getImpuesto().setId_impuesto(rs.getInt("id_impuesto"));
                        item.getImpuesto().setNombre_impuesto(rs.getString("nombre_impuesto"));
                        item.getImpuesto().setPorcentaje_impuesto(rs.getBigDecimal("porcentaje_impuesto"));

                        items.add(item);
                    }
                    ps.close();
                }
            } catch (Exception ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            conexion.cerrar();
        }
        return items;
    }

    public boolean agregar(Item item) {
        boolean agregado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "insert into items ("
                        + "nombre_item, precio_item, id_tipoitem, nombre_tipoitem, id_impuesto, nombre_impuesto, porcentaje_impuesto "
                        + ") "
                        + "values (?,?,?,?,?,?,?)";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, item.getNombre_item());
                    ps.setInt(2, item.getPrecio_item());
                    ps.setInt(3, item.getTipoItem().getId_tipoitem());
                    ps.setString(4, item.getTipoItem().getNombre_tipoitem());
                    ps.setInt(5, item.getImpuesto().getId_impuesto());
                    ps.setString(6, item.getImpuesto().getNombre_impuesto());
                    ps.setBigDecimal(7, item.getImpuesto().getPorcentaje_impuesto());
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

    public boolean modificar(Item item) {
        boolean modificado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "UPDATE items SET "
                        + "nombre_item=?, precio_item=?, id_tipoitem=?, nombre_tipoitem=?, id_impuesto=?, nombre_impuesto=?, porcentaje_impuesto=? "
                        + "WHERE id_item=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, item.getNombre_item());
                    ps.setInt(2, item.getPrecio_item());
                    ps.setInt(3, item.getTipoItem().getId_tipoitem());
                    ps.setString(4, item.getTipoItem().getNombre_tipoitem());
                    ps.setInt(5, item.getImpuesto().getId_impuesto());
                    ps.setString(6, item.getImpuesto().getNombre_impuesto());
                    ps.setBigDecimal(7, item.getImpuesto().getPorcentaje_impuesto());
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

    public boolean eliminar(int id_item) {
        boolean eliminado = false;
        Conexion conexion = new Conexion();
        if (conexion.conectar()) {
            try {
                String sql = "delete from items "
                        + "where id_item=?";
                try (PreparedStatement ps = conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_item);
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
