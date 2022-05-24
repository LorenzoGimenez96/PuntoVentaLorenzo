package py.com.puntoventalorenzo.modelo;

import java.util.Scanner;

/**
 *
 * @author programador
 */
public class Test {

    public static void main(String[] args) {
        // instanciacion (creando un nuevo objeto) 
        Usuario usuario1 = new Usuario(); // constructor sin parametros
        usuario1.setId_usuario(1);
        usuario1.setNombre_usuario("Juan");
        usuario1.setUsuario_usuario("juan");
        usuario1.setClave_usuario("12345");
        Rol rol = null;
        

        Usuario usuario2 = new Usuario(2, "Ana", "ana", "5", rol ); // constructor con parametros

        System.out.println(usuario1.getNombre_usuario());
        System.out.println(usuario1.getUsuario_usuario());
        System.out.println(usuario1.getClave_usuario());

        System.out.println("--------------------------------------");

        System.out.println(usuario2.getNombre_usuario());
        System.out.println(usuario2.getUsuario_usuario());
        System.out.println(usuario2.getClave_usuario());

        System.out.println("--------------------------------------");

        System.out.println(usuario1);

        // Variables
        String nombre = "Juan";
        int edad = 25;
        boolean estudia = false;
        // Constantes
        final float PI = 3.14f;
        // PI = 3.15; // No se puede hacer porque es una constante
        nombre = "Ana";

        // Pedir datos al usuario        
        System.out.print("Que edad tienes?: ");
        Scanner scanner = new Scanner(System.in);
        edad = scanner.nextInt();

        if (edad >= 18) {
            System.out.println("Eres mayor de edad");
        } else {
            System.out.println("Eres menor de edad");
        }

        System.out.print("Estudias?: ");
        estudia = scanner.nextBoolean();

        // Structura del lenguaje
        // Condicionales
        // If (Si)
        if (estudia) {
            System.out.println("Eres estudioso");
        } else {
            System.out.println("No quieres estudiar?");
        }

        // If anidado
        System.out.print("En que dia estamos?: ");
        int dia = scanner.nextInt();

        if (dia == 1) {
            System.out.println("Domingo");
        } else {
            if (dia == 2) {
                System.out.println("Lunes");
            } else {
                if (dia == 3) {
                    System.out.println("Martes");
                } else {
                    if (dia == 4) {
                        System.out.println("Miercoles");
                    }
                }
            }
        }
        // If else If
        if (dia == 1) {
            System.out.println("Domingo");
        } else if (dia == 2) {
            System.out.println("Lunes");
        } else if (dia == 3) {
            System.out.println("Martes");
        } else if (dia == 4) {
            System.out.println("Miercoles");
        }
        // Switch
        switch (dia) {
            case 1:
                System.out.println("Domingo");
                break;
            case 2:
                System.out.println("Lunes");
                break;
            case 3:
                System.out.println("Martes");
                break;
            case 4:
                System.out.println("Miercoles");
                break;
            default:
                System.out.println("Dia Erroneo");
                break;
        }
        // Ciclicos (Repetir)
        // Automatico
        // for
        for (int i = 1; i <= 10; i=i+3) {
            System.out.println("For i="+i);
        }
        
        // Vectores
        //           posiciones ->   0       1       2      3    
        String[] nombresAlumnos = {"Juan","Andres","Ana","Luis"}; // <- valores
        // for each
        for (String nombreAlumno : nombresAlumnos) {
            System.out.println(nombreAlumno);
        }
        
        Usuario[] usuarios = new Usuario[2];
        usuarios[0] = usuario1;
        usuarios[1] = usuario2;
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre_usuario());
        }
        
        // Manuales
        // while
        int i = 1;
        while (i <= 10) {            
            System.out.println("while "+i);
            i++;
        }
        // do while
        i = 100;
        do {            
            System.out.println("do while "+i);
            i++;
        } while (i <= 10);
        
        // Try (intentar) catch (capturar)
        
        try {
            int resultado = 100/0;
            System.out.println("No hubo error");
            System.out.println("resultado = "+resultado);
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getLocalizedMessage());
        }
        
        System.out.println("Programa continuando ...");
   
    }

}
