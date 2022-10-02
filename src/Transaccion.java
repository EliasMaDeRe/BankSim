public class Transaccion {
    private int monto, saldoInicial, saldoFinal;
    private String fecha, tipo;


    public Transaccion(String fecha, int monto, String tipo, int saldoInicial ){
        this.fecha= fecha;
        this.monto = monto;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoInicial+monto;
    }

    public String getFecha(){
        return this.fecha;
    }

    public int getSaldoFinal() {
        return saldoFinal;
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public String toStringTransaccion(){

        return fecha+"-"+tipo+"-"+saldoInicial+"-"+monto+"-"+saldoFinal;
    }

}
