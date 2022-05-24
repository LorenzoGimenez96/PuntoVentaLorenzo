package py.com.puntoventalorenzo.modelo;

import java.math.BigDecimal;
import org.json.simple.JSONObject;

public class Impuesto {
    
    private int id_impuesto;
    private String nombre_impuesto;
    private BigDecimal porcentaje_impuesto;

    public Impuesto() {
    }

    public Impuesto(int id_impuesto, String nombre_impuesto, BigDecimal porcentaje_impuesto) {
        this.id_impuesto = id_impuesto;
        this.nombre_impuesto = nombre_impuesto;
        this.porcentaje_impuesto = porcentaje_impuesto;
    }

    public int getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(int id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public String getNombre_impuesto() {
        return nombre_impuesto;
    }

    public void setNombre_impuesto(String nombre_impuesto) {
        this.nombre_impuesto = nombre_impuesto;
    }

    public BigDecimal getPorcentaje_impuesto() {
        return porcentaje_impuesto;
    }

    public void setPorcentaje_impuesto(BigDecimal porcentaje_impuesto) {
        this.porcentaje_impuesto = porcentaje_impuesto;
    }

   
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_impuesto", this.id_impuesto);
        obj.put("nombre_impuesto", this.nombre_impuesto);
        obj.put("porcentaje_impuesto", this.porcentaje_impuesto);
        return obj;
    }
}
