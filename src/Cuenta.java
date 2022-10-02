public class Cuenta {
    private int id, saldo, numTransacciones;
    private String tipoCuenta;
    Transaccion[] transacciones = new Transaccion [50];

    public Cuenta(String tipoCuenta){
        this.saldo=0;
        this.tipoCuenta = tipoCuenta;
        this.numTransacciones=0;
    }

    public int getId(){
        return this.id;
    }

    public int getSaldo(){
        return this.saldo;
    }

    public void putSaldo(int saldo){
        this.saldo = saldo;
    }

    public String getTipoCuenta(){
        return this.tipoCuenta;
    }

    public Transaccion[] getTransacciones(){
        return transacciones;
    }

    public void putTransacciones(Transaccion transaccion){
        transacciones[numTransacciones] = transaccion;
        numTransacciones++;
    }
}
