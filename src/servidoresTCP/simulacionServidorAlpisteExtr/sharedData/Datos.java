package servidoresTCP.simulacionServidorAlpisteExtr.sharedData;


public class Datos {
    private int precioAlpiste;
    private int precioNabina;
    private int precioAvena;
    private int precioPerilla;

    public Datos() {
        precioAlpiste = 150;
        precioNabina = 175;
        precioAvena = 75;
        precioPerilla = 650;
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
