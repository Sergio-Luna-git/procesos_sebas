import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime la ruta del fichero");
        String ruta = sc.nextLine();

        Path ejecutable = Path.of(System.getProperty("java.home"), "bin", "java");
        ProcessBuilder pb = new ProcessBuilder(
                ejecutable.toString(), "-cp", System.getProperty("java.class.path"),
                "Convertidor", ruta);

        Path carpeta = Path.of("actividad8", "target", "salidas");
        Files.createDirectory(carpeta);
        Path stdout = carpeta.resolve("salida.txt");
        Path stderr = carpeta.resolve("erroes.txt");
        pb.redirectOutput(stdout.toFile());
        pb.redirectOutput(stderr.toFile());
        Process proceso = pb.start();

        System.out.println("PID del proceso: " + proceso.pid());
        //como no sé como comprobar el timepo vamos a comprobarlo con un limite de 10 segundos que no se si es much0
        int tiempoLimite = 10;

        boolean maxtiempo = proceso.waitFor(tiempoLimite, TimeUnit.SECONDS);
        if (!maxtiempo) {
            System.out.println("ESTADO: LIMITE DE TIEMPO ALCANZADO");
            proceso.destroyForcibly();
        }

        int codigoSalida = proceso.exitValue();
        System.out.print(Files.readString(stdout));
        System.out.print(Files.readString(stderr));
        System.out.println("Codigo: " + codigoSalida);
        boolean archivogenerado = maxtiempo && (codigoSalida == 0 || codigoSalida == 3) &&
                Files.isRegularFile(conversorArchivos.salida(Path.of(ruta)));
        System.out.println("El archivo se generó?: " + archivogenerado);

    }
}