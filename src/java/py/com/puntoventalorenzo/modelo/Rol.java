package py.com.puntoventalorenzo.modelo;

import org.json.simple.JSONObject;

public class Rol {
    
    private int id_rol;
    private String nombre_rol;
    private String menu_rol;

    public Rol(){
    
    }
    
    public Rol(int id_rol, String nombre_rol, String menu_rol) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
        this.menu_rol = menu_rol;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getMenu_rol() {
        return menu_rol;
    }

    public void setMenu_rol(String menu_rol) {
        this.menu_rol = menu_rol;
    }
    

    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_rol", this.id_rol);
        obj.put("nombre_rol", this.nombre_rol);
        obj.put("menu_rol", this.menu_rol);
        return obj;
    }
    
}
