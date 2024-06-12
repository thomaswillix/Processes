package servidoresTCP.iesPerico.sharedData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Database {
    private HashMap<Integer, HashMap<Integer, Integer>> notasPorAlumno = new HashMap<>();
    double notaFinal;
    public Database() {
    }

    public synchronized boolean registrarEjercicio(int expediente, int ejercicio, int nota){
        this.notasPorAlumno.get(expediente).put(ejercicio,nota);
        return true;
    }
    public synchronized boolean registrarMedia(int expediente, double nota){
        //ni idea
        return true;
    }
}
