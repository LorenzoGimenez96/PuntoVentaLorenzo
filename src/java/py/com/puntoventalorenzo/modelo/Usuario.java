package py.com.puntoventalorenzo.modelo;

// Clase

import org.json.simple.JSONObject;

// Visibilidad
//     clase Nombre de la Clase
public class Usuario {
    
    // Atributos o Variables o Constantes
    private int id_usuario;
    private String nombre_usuario;
    private String usuario_usuario;
    private String clave_usuario;
    private Rol rol;
    
//Metodos o funciones
    public Usuario(){
        
    }
    
    public Usuario(int id_usuario, String nombre_usuario, String usuario_usuario, String clave_usuario, Rol rol) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.usuario_usuario = usuario_usuario;
        this.clave_usuario = clave_usuario;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getUsuario_usuario() {
        return usuario_usuario;
    }

    public void setUsuario_usuario(String usuario_usuario) {
        this.usuario_usuario = usuario_usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_usuario", this.id_usuario);
        obj.put("nombre_usuario", this.nombre_usuario);
        obj.put("usuario_usuario", this.usuario_usuario);
        obj.put("clave_usuario", this.clave_usuario);
        obj.put("id_rol", this.rol.getId_rol());
        obj.put("nombre_rol", this.rol.getNombre_rol());
        obj.put("menu_rol", this.rol.getMenu_rol());
        
        return obj;
    }
    
    
}
