import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Banco {
    Cliente [] clientes = new Cliente[50];
    private int numClientesTotales, numCuentasTotales;

    public Banco(){

    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public int getNumClientesTotales() {
        return numClientesTotales;
    }

    public void putNumClientesTotales(int numClientesTotales){
        this.numClientesTotales=numClientesTotales;
    }

    public void putNumeroCuentasTotales(int numCuentasTotales){
        this.numCuentasTotales=numCuentasTotales;
    }

    public int getNumCuentasTotales() {
        return numCuentasTotales;
    }

    public boolean generarEstadoDeCuenta(Cliente cliente, Cuenta cuenta, String FechaInicial, String FechaFinal){

        if(cliente == null || cuenta == null || FechaInicial == null || FechaFinal == null ) return false; // nulos

        String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        if(FechaFinal.compareTo(date) > 0) return false; // fecha final es despues de hoy

        File rutaBanco = new File("./src/Banco"); // ruta relativa, abre carpeta Banco

        if(rutaBanco.exists()){

            File rutaCliente = new File("./src/Banco/"+encontrarFolderCliente(rutaBanco, cliente)); // ruta de la carpeta cliente

            if(rutaCliente.exists()){

                File rutaCuenta = new File("./src/Banco/"+encontrarFolderCliente(rutaBanco, cliente)+"/" // ruta de la la carpeta cuetna
                +encontrarFolderCuenta(rutaCliente, cuenta));

                if(rutaCuenta.exists()){ // abre el archivo transacciones

                    try {
                        File transacciones = new File(rutaCuenta.getPath()+"/transacciones.txt");
                        Scanner archivo = new Scanner(transacciones);
                        String transaccion;
                        while(archivo.hasNextLine()){

                            transaccion = archivo.nextLine();
                            if(transaccion.substring(0,10).compareTo(FechaInicial) >= 0
                            && transaccion.substring(0,10).compareTo(FechaFinal) <= 0)
                            
                                System.out.println(transaccion);

                        }
                        archivo.close();
                        return true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Ocurrio un error leyendo las transacciones");
                        e.printStackTrace();
                    }
                        

                    

                }

            }

        }

        return false;


    }

    public String encontrarFolderCliente(File rutaBanco, Cliente cliente){

        String[] clientes = rutaBanco.list();
        for(int i = 0; i < clientes.length-1; ++i)
            if(cliente.getID() == Integer.parseInt(clientes[i])) return clientes[i];
        return "#";

    }

    public String encontrarFolderCuenta(File rutaCliente, Cuenta cuenta){

        String[] cuentas = rutaCliente.list();
        for(int i = 0; i < cuentas.length-1; ++i){

            if(cuenta.getId() == Integer.parseInt(cuentas[i])) return cuentas[i];

        }
        return "#";

    }


}
