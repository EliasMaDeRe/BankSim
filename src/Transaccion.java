import java.io.File;
import java.io.FileWriter;

public class Transaccion {

    public static String[] splitTransaccion(String transaccion) {

        String[] transaccionSeparado = transaccion.split(",");

        return transaccionSeparado;
    }

    public static String getFecha(String transaccion) {

        String fecha = splitTransaccion(transaccion)[0];

        return fecha;
    }

    public static String getTipo(String transaccion) {

        String tipo = splitTransaccion(transaccion)[1];

        return tipo;
    }

    public static String getMonto(String transaccion) {

        String monto = splitTransaccion(transaccion)[2];

        return monto;
    }

    public static String getSaldoInicial(String transaccion) {

        String SaldoInicial = splitTransaccion(transaccion)[3];

        return SaldoInicial;
    }

    public static String getSaldoFinal(String transaccion) {

        String SaldoFinal = splitTransaccion(transaccion)[4];

        return SaldoFinal;
    }

    public static void guardarTransaccion(String transaccion, String idCliente, String idCuenta) {
        
        File rutaCuenta = new File(Ruta.path(idCliente, idCuenta) + "transacciones.txt");
        
        try {

            FileWriter escribir = new FileWriter(rutaCuenta, true);

            escribir.write(transaccion);

            escribir.close();

        } catch (Exception e) {

            System.out.println("Error al escribir");
        }
    }

    public static String toString(String fecha, String tipo, String monto, String saldoInicial, String saldoFinal ) {

        String nuevaTransacción = fecha + "," + tipo + "," + monto + "," + saldoInicial + "," + saldoFinal;

        return nuevaTransacción;
    }
}