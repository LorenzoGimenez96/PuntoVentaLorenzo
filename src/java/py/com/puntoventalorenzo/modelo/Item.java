package py.com.puntoventalorenzo.modelo;

import org.json.simple.JSONObject;

public class Item {
    
    private int id_item;
    private String nombre_item;
    private int precio_item;
    private TipoItem tipoItem;
    private Impuesto impuesto;

    public Item() {
    }

    public Item(int id_item, String nombre_item, int precio_item, TipoItem tipoItem, Impuesto impuesto) {
        this.id_item = id_item;
        this.nombre_item = nombre_item;
        this.precio_item = precio_item;
        this.tipoItem = tipoItem;
        this.impuesto = impuesto;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getNombre_item() {
        return nombre_item;
    }

    public void setNombre_item(String nombre_item) {
        this.nombre_item = nombre_item;
    }

    public int getPrecio_item() {
        return precio_item;
    }

    public void setPrecio_item(int precio_item) {
        this.precio_item = precio_item;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

   
    
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_item", this.id_item);
        obj.put("nombre_item", this.nombre_item);
        obj.put("precio_item", this.precio_item);
        obj.put("id_tipoitem", this.tipoItem.getId_tipoitem());
        obj.put("nombre_tipoitem", this.tipoItem.getNombre_tipoitem());
        obj.put("id_impuesto", this.impuesto.getId_impuesto());
        obj.put("nombre_impuesto", this.impuesto.getNombre_impuesto());
        obj.put("porcentaje_impuesto", this.impuesto.getPorcentaje_impuesto().toString());
        
        return obj;
    }
    
}
