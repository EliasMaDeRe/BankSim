public class Cliente {
    private int id;
    private String nombre;
    private Cuenta cuentas[];
    private int numeroCuentas;

    public Cliente(int id, String nombre, int numeroCuentas){
        this.id = id;
        this.nombre = nombre;
        this.cuentas = new Cuenta [50];
        this.numeroCuentas = numeroCuentas;
    }

    public int getID (){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Cuenta[] getCuentas(){
        return this.cuentas;
    }

    public int getNumeroCuentas(){
        return this.numeroCuentas;
    }

    public void putNumeroCuentas(int numeroCuentas){
        this.numeroCuentas = numeroCuentas;
    }

}
