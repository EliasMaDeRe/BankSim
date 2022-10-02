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


}
