package simulacionServidorAlpiste.sharedData;


public class Datos {
    private static int precioAlpiste = 1500;
    private static int precioNabina = 1750;
    private static int precioAvena = 750;
    private static int precioPerilla = 6500;


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
