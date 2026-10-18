import java.io.File;

public class ValidarContenido{
    public static void main(String[] args) {
        File archivo = new File("");

        if (archivo.exists()){
            System.out.println("Existe el archivo.");
        } else {
            System.out.println("El archivo no existe.");
        }


        ProcessBuilder pb = new ProcessBuilder()

    }


}
