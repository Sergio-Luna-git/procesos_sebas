import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.chrono.IsoEra;

public class conversorArchivos {
    public static Path salida(Path archivo) {
        String nombre = archivo.getFileName().toString();
        return archivo.resolveSibling(nombre.substring(0, nombre.length() - 4) + "_convertidas.csv");
    }
    public static void main(String[] args) {
        File archivo = new File(args[0]);
    }

    public static int convertidor(Path archivo) {

        // File y Path es lo mismo sirve para lo mismo
        if (!Files.isRegularFile(archivo)) {
            System.err.println("El archivo no existe");
            System.exit(1);
        }
        if (!archivo.toString().toLowerCase().endsWith(".csv")) {
            System.err.println("La extensión del archivo no es correcta.");
            System.exit(2);
        }
        int errores = 0;
        int correctos = 0;
//con esto hago uno solo para todo lo que hay que comprobar
        try (BufferedReader reader = Files.newBufferedReader(entrada)) {
            try {
                Path destino = salida(entrada).toAbsolutePath();
                try (BufferedReader writer =  Files.newBufferedReader(archivoDestino)){
                    String lineas;
                    int numero = 0;
                    while ((lineas = reader.readLine()) != null) {
                        numero++;
                        try {
                            String[] campos = lineas.split(";", - 1);
                            if(campos.length != 2) {
                                throw new IllegalArgumentException("Estructura incorrecta");
                            }
                            if(campos[0].isBlank()) {
                                throw new IllegalArgumentException("Alumno Vacio");
                            } else if (campos[1].isBlank()) {
                                throw new IllegalArgumentException("Nota vacía");
                            }
                            double nota;
                            try {
                                nota= Double.parseDouble(campos[1].trim());
                            } catch (NumberFormatException ex) {
                                throw new IllegalArgumentException("La nota no es numérica");
                            }
                            if ( nota < 0 || nota > 10 ){
                                throw new IllegalArgumentException("La nota no es correcta");
                            }
                            writer.write(campos[0].trim() + ";" + calificacion(nota));
                            writer.newLine();
                            correctos ++;

                        } catch (IllegalArgumentException ex) {
                            System.err
                        }
                    }
                }
            }
        }

        return 0;
    }


}