package py.com.puntoventalorenzo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Plano --> Fabrica (contruye) --> Un auto
// Clase --> Instanciacion --> objeto

public class Conexion {

    // atributos o variables o constantes
    private final String DRIVER = "org.postgresql.Driver";
    private final String SERVIDOR = "localhost";
    private final String PUERTO = "5432";
    private final String USUARIO = "postgres";
    private final String CLAVE = "1";
    private final String BASEDATO = "puntoventalorenzo";
    private Connection con;
    private Statement st;

    // metodos o funciones
    public boolean conectar() {
        boolean ok = false;
        try {
            Class.forName(DRIVER);
            // jdbc:postgresql://localhost:5432/puntoventalorenzo
            String connectString = "jdbc:postgresql://" + SERVIDOR + ":" + PUERTO + "/" + BASEDATO;
            con = DriverManager.getConnection(connectString, USUARIO, CLAVE);
            st = con.createStatement();
            con.setAutoCommit(false);
            st.setFetchSize(100);
            ok = true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("error --> " + ex.getLocalizedMessage());
        }
        return ok;
    }

    public boolean cerrar() {
        boolean ok = false;
        try {
            st.close();
            con.close();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("--> " + ex.getLocalizedMessage());
        }
        return ok;
    }

    public Connection getCon() {
        return con;
    }

    public Statement getSt() {
        return st;
    }

}
