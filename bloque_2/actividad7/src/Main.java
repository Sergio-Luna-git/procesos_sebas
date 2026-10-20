import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime la ruta del fichero");
        String ruta = sc.nextLine();

        //2 rutas una de entorno de trabajo y lar ruta del arcivo

        ProcessBuilder pb = new ProcessBuilder("java", "ValidarContenido", ruta);
        Path ejecutableJava = System.getProperty("java.home") - > //revisar poner ruta a java
        Process proceso = pb.start(); -> //Path ejecutableJava = Path.of(System.getProperty("java.home"), "bin", "java");
        //ProcessBuilder pb = new ProcessBuilder(
          //      ejecutableJava.toString(), "-cp", System.getProperty("java.class.path"),
            //    "org.svillaponce.psp.actividad07.Actividad7CorrectorCsv", ruta);
        // REVISAR RUTAS PARA EJECUCIÓN

        //delegar mucho sobre el sistema operativo
        // system.getProperty(

        try (BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(proceso.getInputStream())
                )){
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Proceso hijito devuelve: " + linea);
            }
        }

        try (BufferedReader readerError =
                new BufferedReader(
                        new InputStreamReader(proceso.getErrorStream())
                )){
            String lineaError;
            while ((lineaError = readerError.readLine()) != null) {
                System.err.println("Error del hijo: " + lineaError);
            }
        }

        int codigoSalida = proceso.waitFor();
        System.out.println("El proceso hijo ha terminado con el código: " + codigoSalida);
    }

    /*Path carpeta = Path.of("tema-01-procesos", "target", "salidas", "actividad07");
        Files.createDirectories(carpeta);
        Path stdout = carpeta.resolve("salida.txt");
        Path stderr = carpeta.resolve("errores.txt");
        pb.redirectOutput(stdout.toFile());
        pb.redirectError(stderr.toFile());
*/
    // REVISA R CREADA ENTORNO TRABAJO Y REDIRECCION DE SALIDA ERROR Y CHILL
}
