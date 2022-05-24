package py.com.puntoventalorenzo.modelo;

import org.json.simple.JSONObject;

public class TipoItem {
    
    private int id_tipoitem;
    private String nombre_tipoitem;

    public TipoItem() {
    }

    public TipoItem(int id_tipoitem, String nombre_tipoitem) {
        this.id_tipoitem = id_tipoitem;
        this.nombre_tipoitem = nombre_tipoitem;
    }

    public int getId_tipoitem() {
        return id_tipoitem;
    }

    public void setId_tipoitem(int id_tipoitem) {
        this.id_tipoitem = id_tipoitem;
    }

    public String getNombre_tipoitem() {
        return nombre_tipoitem;
    }

    public void setNombre_tipoitem(String nombre_tipoitem) {
        this.nombre_tipoitem = nombre_tipoitem;
    }
   

    
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_tipoitem", this.id_tipoitem);
        obj.put("nombre_tipoitem", this.nombre_tipoitem);
        return obj;
    }
    
}
