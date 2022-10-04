import java.io.File;
public class Ruta {

    static public boolean existe(String idCliente){

        File archivo = new File(Ruta.path(idCliente));

        return archivo.exists();

    }

    static public boolean existe(String idCliente, String idCuenta){

        File archivo = new File(Ruta.path(idCliente,idCuenta));

        return archivo.exists();

    }

    static public String path(String idCliente){

        return Ruta.pathBanco()+idCliente+"/";

    }

    static public String path(String idCliente, String idCuenta){

        return Ruta.path(idCliente)+idCuenta+"/";

    }

    static public String pathBanco(){

        return "./src/Banco/";

    }
}
