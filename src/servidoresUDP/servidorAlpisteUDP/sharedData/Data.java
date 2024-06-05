package servidoresUDP.servidorAlpisteUDP.sharedData;

public class Data {

    private int alpiste;
    private int nabina;
    private int caniamon;
    private int negrillo;

    public Data() {
        alpiste = 250;
        nabina = 150;
        caniamon = 100;
        negrillo = 300;
    }

    public synchronized int getAlpiste() {
        return alpiste;
    }

    public void setAlpiste(int alpiste) {
        this.alpiste = alpiste;
    }

    public synchronized int getNabina() {
        return nabina;
    }

    public void setNabina(int nabina) {
        this.nabina = nabina;
    }

    public synchronized int getCaniamon() {
        return caniamon;
    }

    public void setCaniamon(int caniamon) {
        this.caniamon = caniamon;
    }

    public synchronized int getNegrillo() {
        return negrillo;
    }

    public void setNegrillo(int negrillo) {
        this.negrillo = negrillo;
    }
}
