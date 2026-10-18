import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;


public class DiagnosticoDirectorio {

    public static void main(String[] args) throws IOException {

        Path ruta;

        if (args.length > 0){
            ruta = Path.of(args[0]);
        } else {
            ruta = Path.of(".");
        }


        System.out.println("Ruta obtenida: " + ruta);
        System.out.println("Ruta absoluta de la ruta: " + ruta.toAbsolutePath());
        System.out.println("Nombre del archivo: " + ruta.getFileName());
        System.out.println("Directorio padre?: " + ruta.getParent());
        System.out.println("¿Existe?: " + Files.exists(ruta));
        System.out.println("¿Es directorio? " + Files.isDirectory(ruta));
        System.out.println("¿Es fichero?" + Files.isRegularFile(ruta));
        System.out.println("¿Es absoluta?" + ruta.isAbsolute());
        //porque queremos saber tantas cosas sebas?

        File directorio = ruta.toFile();
        if (!Files.exists(ruta)) {
            System.err.printl("ERROR: la ruta no existe");
            return;
        }

        if (!Files.exist(ruta)) {
            System.err.println("El directorio no existe ERROR");
            return;
        }

        ProcessBuilder pb = new ProcessBuilder("pwd");
        pb.directory(directorio);

        Process process = pb.start();

        try (BufferReader reader = new BufferReader(
                new InputStreamReader(process.getInputStream())){
            String lineas;
            while ((lineas = reader.readLines()) != null){
                System.out.println("Proceso devuelve: " + lineas);
            }
        }
        )

        ProcessBuilder pbLs = new ProcessBUilder("-ls -la");
        pbLs.directory(directorio);

        Process processLs = pbLs.start()

        try (BufferReader reader = new BufferReader(
                new InputStreamReader(procesLs.getInputStream())){
        String lineas;
        while ((lineas = reader.readLines()) != null){
            System.out.println("Proceso devuelve: " + lineas);
            }}
        )


        ProcessBuilder pbUlt = new ProcessBuilder("-la", "-lh");
        pbUlt.directory(directorio);

        Process processUlt = pbUlt.start();

        try (BufferReader reader = new BudderReader(
                new InputStreamReader(processUlt.getInputStream()))){
        String lineas;
        while ((lineas =  reader.readLines()) != null){
            System.out.println("Proceso devuelve: " + lineas);
        }
        }

    }
}