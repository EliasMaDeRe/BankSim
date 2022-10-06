import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Cuenta {

    public static int getSaldo(String idCliente, String idCuenta){
        int contador = 0;
        int saldo = 0;
        if(Ruta.existe(idCliente,idCuenta)){

            try{
                File cuenta = new File (Ruta.pathInfo(idCliente,idCuenta));
                Scanner archivo = new Scanner(cuenta);
                String info;
                
                while(archivo.hasNextLine()){
                    info = archivo.nextLine();
                    if(contador == 1){
                        saldo = Integer.valueOf(info);
                    }
                    contador++;
                }
                archivo.close();
            }catch (Exception e) {
               System.out.println("Ocurrio un error");
            }
            return saldo;
            
        }else{
            return -1;
        }
    }

    public static void putSaldo(String idCliente, String idCuenta, int saldo){
        int contador = 0;
        String arreglo [] = new String [4];
        if(Ruta.existe(idCliente,idCuenta)){
            
            try{
                File cuenta = new File (Ruta.pathInfo(idCliente,idCuenta));
                Scanner archivo = new Scanner(cuenta);
                
                while(archivo.hasNextLine()){
                    
                    arreglo[contador++] = archivo.nextLine();

                }
                archivo.close();

                try (FileWriter writeInFile = new FileWriter(cuenta,false)) {
                    for(contador = 0; contador < 4; ++contador){

                        if(contador == 1){

                            writeInFile.write(String.valueOf(saldo));
                            writeInFile.write(System.getProperty("line.separator"));
                            continue;

                        }
                        writeInFile.write(arreglo[contador]);
                        writeInFile.write(System.getProperty("line.separator"));

                    }
                }

            }catch (Exception e) {
               System.out.println("Ocurrio un error");
            }
        }
    }

    public static String[] getTransacciones(String idCliente, String idCuenta){

        String[] transaccion = new String[50];
        int cont = 0;

        try {
            File cuenta = new File(Ruta.pathTransacciones(idCliente, idCuenta));
            Scanner archivo = new Scanner(cuenta);
    

        while(archivo.hasNextLine()){
            transaccion[cont] = archivo.nextLine();
            cont++;
        }

        archivo.close();

            
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }


        return transaccion;

    }

    public static int getTipoDeCuenta(String idCliente, String idCuenta){
        
        int tipocuenta = -1, cont = 1;

        try {
            File cuenta = new File(Ruta.pathInfo(idCliente,idCuenta));
            Scanner archivo = new Scanner(cuenta);
            
            while(archivo.hasNextLine()){
                if(cont == 3){
                    tipocuenta = Integer.parseInt(archivo.nextLine());
                }
                cont++;

            archivo.close();
        }

        
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }


        return tipocuenta;

    }

    public static int getNumeroDeTransacciones(String idCliente, String idCuenta){
        Ruta.existe(idCliente, idCuenta); 
        if (Ruta.existe(idCliente, idCuenta) == false){
           System.out.println("La transaccion no se puede realizar debido a que el cliente o la cuenta son inexistentes.");
           return -1;
        }

        File myFile = new File(Ruta.pathInfo(idCliente,idCuenta)); 
        //leer exactamente la linea de numero de transacciones (última)
        String [] valores = new String [4];

        try {
        Scanner readFile = new Scanner(myFile);
        int contador = 0;

        while (readFile.hasNextLine()) {
        String data = readFile.nextLine();
        valores[contador] = data;
        contador += 1;

        }
        readFile.close();

        } catch (FileNotFoundException x) {
            System.out.println("Error.");
            x.printStackTrace();
        }

        return Integer.parseInt(valores[3]);
    }

    public static void putNumeroDeTransacciones(String idCliente, String idCuenta ,int NumeroDeTransacciones){
        File myFile = new File(Ruta.pathInfo(idCliente,idCuenta)); 
        //leer exactamente la linea de numero de transacciones (última)
        String [] valores = new String [4];

        try {
        Scanner readFile = new Scanner(myFile);
        int contador = 0;

        while (readFile.hasNextLine()) {
        String data = readFile.nextLine();
        valores[contador] = data;
        contador += 1;

        }
        readFile.close();

        } catch (FileNotFoundException x) {
            System.out.println("Error.");
            x.printStackTrace();
        }

        try {

            FileWriter writeInFile = new FileWriter(Ruta.pathInfo(idCliente, idCuenta),false);

           //Adding content to this file
            for (int i = 0; i < 3; i++) {
                writeInFile.write(valores[i]);
                writeInFile.write(System.getProperty("line.separator"));
            }
            writeInFile.write(Integer.toString(NumeroDeTransacciones));
            writeInFile.close();

          } catch (IOException x) {
            System.out.println("Error.");
            x.printStackTrace();
        }
    }

    public static void retiro(String idCliente, String idCuenta, int monto){
        
        int saldo = 0;
        int saldoNuevo = 0;
        if(Ruta.existe(idCliente,idCuenta)){
            
            try{
                saldo = getSaldo(idCliente, idCuenta);
                saldoNuevo = saldo - monto;
                if(saldoNuevo < 0){

                    System.out.println("El monto de retiro debe ser mayor o igual al saldo");
                    return;

                }
                putSaldo(idCliente, idCuenta, saldoNuevo);
                String fecha = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                Transaccion.guardarTransaccion(Transaccion.toString(fecha, "retiro", String.valueOf(monto),
                String.valueOf(saldo), String.valueOf(saldoNuevo)), idCliente, idCuenta);
                putNumeroDeTransacciones(idCliente, idCuenta, getNumeroDeTransacciones(idCliente, idCuenta)+1);
            }catch (Exception e) {
               System.out.println("Ocurrio un error");
            }
        }
        
    }

    public static void deposito(String idCliente, String idCuenta, int monto){

        
        if(Ruta.existe(idCliente,idCuenta)){

            int saldo = getSaldo(idCliente, idCuenta);
            int saldoNuevo = getSaldo(idCliente, idCuenta)+monto;

            try{
                
                
                putSaldo(idCliente, idCuenta, saldoNuevo);
                String fecha = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                Transaccion.guardarTransaccion(Transaccion.toString(fecha, "deposito", String.valueOf(monto),
                String.valueOf(saldo), String.valueOf(saldoNuevo)), idCliente, idCuenta);
                Cuenta.putNumeroDeTransacciones(idCliente, idCuenta, Cuenta.getNumeroDeTransacciones(idCliente, idCuenta)+1);

            }catch (Exception e) {
               System.out.println("Ocurrio un error");
            }
            

        }

    }

    public static void transferencia(String idClienteE, String idCuentaE, int monto, String idClienteR, String idCuentaR){

        if(Cuenta.getSaldo(idClienteE, idCuentaE) < monto){

            System.out.println("La cuenta emisora no cuenta con fondos suficientes");
            return;

        } 

        retiro(idClienteE, idCuentaE, monto);
        deposito(idClienteR, idCuentaR, monto);

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
            if(transacciones[i] == null) continue;
            if(Transaccion.getFecha(transacciones[i]).compareTo(FechaInicial) >= 0 &&
                Transaccion.getFecha(transacciones[i]).compareTo(FechaFinal) <= 0){

                    System.out.println(transacciones[i]);

                }

        }

    }
}
