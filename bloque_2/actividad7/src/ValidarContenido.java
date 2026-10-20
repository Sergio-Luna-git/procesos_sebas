import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ValidarContenido{
    public static void main(String[] args) throws IOException {
        File archivo = new File("archivo.csv");

        if (archivo.exists()){
            System.out.println("Existe el archivo.");
        } else {
            System.out.println("El archivo no existe.");
        }

        if ( archivo.getName().toLowerCase().endsWith(".csv")) {
            System.out.println("El archivo es csv");
            System.exit(0);
        } else {
            System.out.println("El archivo no es csv");
            System.exit(2);
        }

        List<String> lineas = Files.readAllLines(archivo.toPath());

        for (String linea: lineas){

            String[] campos = linea.split(";");

            if (campos.length != 4){
                System.out.println("ERROR: Una línea no tiene 4 campos brodi");
                System.exit(3);
            }
        }

        for (String linea: lineas) {

            String[] campos = linea.split(";");

            for (String campo: campos) {
                if (campo.trim().isEmpty()) {
                    System.err.println("El campo está vacío");
                    System.exit(3);
                }
            }
        }

        int contador = 0;
        for (String linea: lineas) {
            String[] campos = linea.split(";");
            if ( contador>= 1) {
                try {
                    if(Double.parseDouble(campos[2]) > 0) {
                        System.out.println("El número es válido");
                    } else {
                        System.err.println("El número no es válido");
                        System.exit(3);
                    }
                } catch ( NumberFormatException ns) {
                    System.out.println("Excepción que Germán no me enseñó a tratar");
                    //tiene que seguir
                }
            }
            contador = contador + 1;
        }

        int contador_stock = 0;
        for (String linea: lineas) {

            String[] campos = linea.split(";");
            if (contador_stock >= 1) {
                try {
                    if(Double.parseDouble(campos[3]) >= 0 && Double.parseDouble(campos[3])  % 1 == 0) {
                        System.out.println("El stock esta guay");
                    } else {
                        System.err.println("Aquí solo llega si es negtivo creo idk");
                        System.exit(3);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Sigo sin saber hacerlo preguntale a sebas");
                    System.exit(3);
                }
            }
            contador_stock = contador_stock + 1;
        }

        int registrosValidos = 0;
        double sumarPrecios = 0;
        int stockTotal = 0;

        int ultimo_contador = 0;
        for( String linea: lineas ) {
            String[] campos = linea.split(";");

            if ( ultimo_contador >= 1) {
                try {
                    double precio = Double.parseDouble(campos[2]);
                    int stock = Integer.parseInt(campos[3]);

                    if ( precio > 0 && stock >= 0 ){
                        sumarPrecios += precio;
                        stockTotal += stock;
                        registrosValidos++;
                    } else {
                        System.err.println("Mal");
                        System.exit(3);
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("También está mal");
                    System.exit(3);
                }
            }
            contador++;
        }

        System.out.println("Info del archivo:");
        System.out.println("Número de registros procesados: " + registrosValidos);
        System.out.println("Número de registros válidos: " + registrosValidos);
        System.out.println("Precio medio de los productos: " + (sumarPrecios / registrosValidos));
        System.out.println("Stock total: " + stockTotal);
    }
}
