package simulacionServidorAlpiste.sharedData;


public class Datos {
    private static int precioAlpiste;
    private static int precioNabina;
    private static int precioAvena;
    private static int precioPerilla;

    public Datos() {
        precioAlpiste = 1500;
        precioNabina = 1750;
        precioAvena = 750;
        precioPerilla = 6500;
    }

    public static synchronized int getPrecioAlpiste() {
        return precioAlpiste;
    }

    public void setPrecioAlpiste(int precioAlpiste) {
        Datos.precioAlpiste = precioAlpiste;
    }

    public static synchronized int getPrecioNabina() {
        return precioNabina;
    }

    public void setPrecioNabina(int precioNabina) {
        Datos.precioNabina = precioNabina;
    }

    public static synchronized int getPrecioAvena() {
        return precioAvena;
    }

    public void setPrecioAvena(int precioAvena) {
        Datos.precioAvena = precioAvena;
    }

    public static synchronized int getPrecioPerilla() {
        return precioPerilla;
    }

    public void setPrecioPerilla(int precioPerilla) {
        Datos.precioPerilla = precioPerilla;
    }

}
