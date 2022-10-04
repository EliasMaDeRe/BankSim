import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class Cuenta {

    public static int getSaldo(String idCliente, String idCuenta){

        return 1;

    }

    public static void putSaldo(String idCliente, String idCuenta){

        

    }

    public static String[] getTransacciones(String idCliente, String idCuenta){
<<<<<<< Updated upstream
=======

        File rutacuenta = new File(Ruta.path(idCliente, idCuenta));
        String[] transaccion;
        int cont = 0;

        try {
            File cuenta = new File(rutacuenta.getPath()+"./transacciones.txt");
            Scanner archivo = new Scanner(cuenta);
    

        while(archivo.hasNextLine()){
            transaccion[cont] = archivo.nextLine();
            cont++;
        }
>>>>>>> Stashed changes

            
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }


        return transaccion;

    }

    public static int getTipoDeCuenta(String idCliente, String idCuenta){
        
        File rutacuenta = new File(Ruta.path(idCliente, idCuenta));
        int tipocuenta, cont = 1;

        try {
            File cuenta = new File(rutacuenta.getPath()+"./info.txt");
            Scanner archivo = new Scanner(cuenta);
            
            while(archivo.hasNextLine()){
                if(cont == 3){
                    tipocuenta = archivo.nextLine();
                }
                cont++;
        }

        
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }


        return tipocuenta;

    }

    public static int getNumeroDeTransacciones(String idCliente, String idCuenta){

        return 1;

    }

    public static void putNumeroDeTransacciones(String idCliente, String idCuenta){

        

    }

    public static void retiro(String idCliente, String idCuenta, int monto){

        

    }

    public static void deposito(String idCliente, String idCuenta, int monto){

        int contador = 0;
        int saldo = 0;
        String arreglo [] = new String [4];
        int saldo_nuevo = 0;
        if(Ruta.existe(idCliente,idCuenta)){
            File rutaCuenta = new File(Ruta.path(idCliente,idCuenta));
            try{
                File cuenta = new File (rutaCuenta.getPath()+"/info.txt");
                try (Scanner archivo = new Scanner(cuenta)) {
                    String info;
                    
                    while(archivo.hasNextLine()){
                        info = archivo.nextLine();
                        if(contador == 1){
                            saldo = Integer.valueOf(info);
                                saldo_nuevo = saldo + monto;
                                info = String.valueOf(saldo_nuevo);
                        }
                        arreglo[contador] = info;
                        contador++;
                    }
                    archivo.close();

                    contador = 0;
                    FileWriter escribir = new FileWriter(cuenta,false);
                    while(archivo.hasNextLine()){
                        escribir.write(arreglo[contador]);
                        contador++;
                    }
                    escribir.close();
                }

                Transaccion.guardarTransaccion(Transaccion.toString(SimpleDateFormat("yyyy/MM/dd").format(new Date()), "deposito", String.valueOf(monto), 
                String.valueOf(Cuenta.getSaldo(idCliente,idCuenta)+monto), String.valueOf(Cuenta.getSaldo(idCliente,idCuenta))), idCliente,idCuenta);

            }catch (Exception e) {
               System.out.println("Ocurrio un error");
            }
            

        }

    }


    public static void transferencia(String idClienteE, String idCuentaE, int monto, String idClienteR, String idCuentaR){

        

    }
    public static void estadoCuenta(String idCliente, String idCuenta, String FechaInicial, String FechaFinal){

        if(idCliente == null || idCuenta == null || FechaInicial == null || FechaFinal == null){

            System.out.println("Un parametro no es valido");
            return;

        }

        if(!Ruta.existe(idCliente,idCuenta)){
            
            System.out.println("No existe la ruta para el cliente o cuenta");
            return;

        }

        String[] transacciones = Cuenta.getTransacciones(idCliente,idCuenta);

        for(int i = 0; i < transacciones.length; ++i){

            if(Transaccion.getFecha(transacciones[i]).compareTo(FechaInicial) >= 0 &&
                Transaccion.getFecha(transacciones[i]).compareTo(FechaFinal) <= 0){

                    System.out.println(transacciones[i]);

                }

        }

    }
}
