import java.io.File;

public class Banco {

    public static String[] getClientes(){

        String [] clientes = new String [50];

        File rutabanco = new File(Ruta.pathBanco());

        String [] archivos = rutabanco.list();

        for (int posicion=0; posicion<archivos.length; posicion++){

            File directorio = new File (rutabanco, archivos[posicion]);

            if (directorio.isDirectory()){
                clientes[posicion] = archivos[posicion];
            }
        }
        return clientes;

    }

    public static int getNumClientesTotales() {
        
        File rutabanco = new File(Ruta.pathBanco());
        String [] archivos = rutabanco.list();
        int numClientesTotales = archivos.length-1;

        return numClientesTotales;
    
    }

}
