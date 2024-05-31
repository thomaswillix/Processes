package simulacionServidorAlpiste.sharedData;


public class Datos {
    private int precioAlpiste;
    private int precioNabina;
    private int precioAvena;
    private int precioPerilla;

    public Datos() {
        precioAlpiste = 1500;
        precioNabina = 1750;
        precioAvena = 750;
        precioPerilla = 6500;
    }

    public synchronized int getPrecioAlpiste() {
        return precioAlpiste;
    }

    public void setPrecioAlpiste(int precioAlpiste) {
        this.precioAlpiste = precioAlpiste;
    }

    public synchronized int getPrecioNabina() {
        return precioNabina;
    }

    public void setPrecioNabina(int precioNabina) {
        this.precioNabina = precioNabina;
    }

    public synchronized int getPrecioAvena() {
        return precioAvena;
    }

    public void setPrecioAvena(int precioAvena) {
        this.precioAvena = precioAvena;
    }

    public synchronized int getPrecioPerilla() {
        return precioPerilla;
    }

    public void setPrecioPerilla(int precioPerilla) {
        this.precioPerilla = precioPerilla;
    }

}
