package casino.datos;

public class CuentaCasino {
    int banca;
    public CuentaCasino(int banca) {
        this.banca = banca;
    }

    public void ingresar(int n) {
        banca+=n;
        if (banca <0) {
            System.out.println("Negativo");
        }
    }

    public void retirar(int n) {
        banca-=n;
        if (n < 0) {
            System.out.println("Negativo");
        }
    }
    public int getBanca() {
        return banca;
    }
}
