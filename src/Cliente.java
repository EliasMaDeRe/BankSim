import java.io.File;
import java.util.Scanner;


public class Cliente {

    public static String getNombre(String idCliente){

        File rutaCliente = new File(Ruta.path(idCliente));
        String[] cliente = new String [3];
        int cont = 0;

        try {
            File cuenta = new File(rutaCliente+"info.txt");
            try (Scanner archivo = new Scanner(cuenta)) {
                while(archivo.hasNextLine()){
                    cliente[cont] = archivo.nextLine();
                    cont++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }
        
        return cliente[2];

    }
    public static String[] getCuentas(String idCliente){

        String [] cuentas = new String [50];

        File rutaCliente = new File(Ruta.path(idCliente));

        String [] archivos = rutaCliente.list();

        for (int posicion=0; posicion<archivos.length; posicion++){

            File directorio = new File (rutaCliente, archivos[posicion]);

            if (directorio.isDirectory()){
                cuentas[posicion] = archivos[posicion];
            }
        }

        return cuentas;

    }
    public static int getNumeroCuentas(String idCliente){


        File rutaCuenta = new File(Ruta.path(idCliente));
        String [] archivos = rutaCuenta.list();
        int numCuentasTotales = archivos.length-1;

        return numCuentasTotales;

    }

}
