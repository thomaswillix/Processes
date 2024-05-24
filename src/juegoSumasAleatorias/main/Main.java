package juegoSumasAleatorias.main;

import juegoSumasAleatorias.hilos.Hilo;


public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenido al concurso de cálculos mentales!\nCada vez que aciertes un número se te sumará un punto, por el contrario, si fallas se te restarán dos puntos.\nTendrás 1 minuto para contestar");
        Hilo h = new Hilo();
        Thread t = new Thread(h);
        t.start();
    }
}

