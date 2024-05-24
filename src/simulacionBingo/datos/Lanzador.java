package simulacionBingo.datos;

public class Lanzador {

    private int numero;
    private boolean bingo;

    public Lanzador() {
        bingo = false;
    }

    public void sacarBola() {
        numero = (int) (Math.random() * 90) +1;
        System.out.println("El número que ha salido es: " + numero);
    }
    public int getNum() {
        return numero;
    }


    public boolean hanGanado(){
        return bingo;
    }
    public void ganador(){
        this.bingo = true;
    }
}

