package threads.simulacionAlmazara.sharedData;

public class Datos {
    private int tanqueOrujo;
    private int depositoAceite;

    public Datos() {
        this.tanqueOrujo = 0;       //CAPACIDAD MÁXIMA = 1600;
        this.depositoAceite = 0;    //CAPACIDAD MÁXIMA = 3600;
    }

    public int getTanqueOrujo() {
        return tanqueOrujo;
    }

    public void depositarOrujo(int kgOrujo){
        this.tanqueOrujo+=kgOrujo;
        //System.out.println("[ALMAZARA] UN ACEITUNERO HA DEPOSITADO ORUJO, KG EN EL TANQUE: " +tanqueOrujo);
    }
    public void transportarOrujo(int kgOrujo){
        this.tanqueOrujo-=kgOrujo;
        //System.out.println("[ALMAZARA] UN TRANSPORTISTA HA TRANSPORTADO ORUJO, KG EN EL TANQUE: " +tanqueOrujo);
    }
    public void depositarAceite(int kgAceite){
        this.depositoAceite+=kgAceite;
        //System.out.println("[ALMAZARA] UN ACEITUNERO HA DEPOSITADO ACEITE, KG EN EL DEPOSITO: " +depositoAceite);
    }
    public void transportarAceite(int kgAceite){
        this.depositoAceite-=kgAceite;
        //System.out.println("[ALMAZARA] UN TRANSPORTISTA HA TRANSPORTADO ACEITE, KG EN EL DEPOSITO: " +depositoAceite);
    }
    public int getDepositoAceite() {
        return depositoAceite;
    }

}
