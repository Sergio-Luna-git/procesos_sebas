import java.io.File;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        File file = new File("/home/Documentos");
        if (file.exists()) {
            System.out.println("Existe el archivo");
        } else {
            System.out.println("No existe el archivo");
        }

        ProcessBuilder pb = new ProcessBuilder();
        Process proceso = pb.start();


    }
}