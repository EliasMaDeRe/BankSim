public class Cuenta {

    public static int getSaldo(String idCliente, String idCuenta){

        return 1;

    }

    public static void putSaldo(String idCliente, String idCuenta){

        

    }

    public static String[] getTransacciones(String idCliente, String idCuenta){

        return {};

    }

    public static int getTipoDeCuenta(String idCliente, String idCuenta){

        return 1;

    }

    public static int getNumeroDeTransacciones(String idCliente, String idCuenta){

        return 1;

    }

    public static void putNumeroDeTransacciones(String idCliente, String idCuenta){

        

    }

    public static void retiro(String idCliente, String idCuenta, int monto){

        

    }
    public static void deposito(String idCliente, String idCuenta, int monto){

        

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
