package ejercicio3Hilos.datos;

public class Contador {
    int points;

    public Contador(int points) {
        this.points = points;
    }

    public void correctAnswer() {
        System.out.println("Correct!");
        points += 2;
    }

    public void incorrectAnswer() {
        System.err.println("Incorrect!");
        points -= 1;
    }

    public synchronized int value(){ return points;}
}
