
package FuncionamientoTablas;

import java.util.Random;

public class Generador {
    public static String generarUsuario() {
        return "User" + generarCadenaAleatoria(5); // Ejemplo: UserA1B2C
    }

    public static String generarClave() {
        return generarCadenaAleatoria(8); // Ejemplo: A1B2C3D4
    }
    private static String generarCadenaAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder cadena = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            cadena.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return cadena.toString();
    }
    
}
