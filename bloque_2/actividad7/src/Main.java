import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime la ruta del fichero");
        String ruta = sc.nextLine();

        //2 rutas una de entorno de trabajo y lar ruta del arcivo
        Path ejecutableJava = Path.of(System.getProperty("java.home"), "bin", "java");  // -> Esta es la ruta del hijo!
        ProcessBuilder pb = new ProcessBuilder(
                ejecutableJava.toString(), "-cp", System.getProperty("java.class.path"),
                "ValidarContenido", ruta);

        /*Process proceso = pb.start();*/ //-> Path ejecutableJava = Path.of(System.getProperty("java.home"), "bin", "java");
        //ProcessBuilder pb = new ProcessBuilder(
          //      ejecutableJava.toString(), "-cp", System.getProperty("java.class.path"),
            //    "org.svillaponce.psp.actividad07.Actividad7CorrectorCsv", ruta);
        // REVISAR RUTAS PARA EJECUCIÓN






        //delegar mucho sobre el sistema operativo
        // system.getProperty(
        /* Los errores pueden ocupar muchas líneas. Los guardamos en un fichero para
        que el hijo no se bloquee llenando stderr mientras el padre lee stdout.*/


        /* HECHO MAL POR MÍ
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
         */

        //Corregido y porqué:

        Path carpeta = Path.of("actividad7", "target", "salidas"); //Creo una rama de carpetas donde pondré los archivos de salida
        Files.createDirectory(carpeta); // Con esto creo las carpetas si faltas y eso
        Path stdout = carpeta.resolve("salida.txt"); //con esto le damos la ruta a salida txtx, no sé si crea txt
        Path stderr = carpeta.resolve("errores.txt");
        pb.redirectOutput(stdout.toFile());
        pb.redirectOutput(stderr.toFile()); // con esto redirigimos la salida de cada canal a uno de los archivos
        Process proceso = pb.start();

        int codigoSalida = proceso.waitFor();
        //System.out.println("El proceso hijo ha terminado con el código: " + codigoSalida);

        System.out.println("PID: " + proceso.pid());
        System.out.println(Files.readString(stdout));
        System.out.println(Files.readString(stderr)); //aquí escribimos en los archivos salida y errores porque hemos redirigido las salidas
        System.out.println("Estado del proceso: " + (codigoSalida == 0 ? "Ok" : "Error"));
        System.out.println("CODIGO: " + codigoSalida);
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
