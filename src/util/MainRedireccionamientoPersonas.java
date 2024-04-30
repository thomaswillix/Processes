package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainRedireccionamientoPersonas {
    public static void main(String[] args) {
        ProcessBuilder[] pb = new ProcessBuilder[3];
        File[] salida = new File[3];
        File[] error = new File[3];
        for (int i = 0; i < 3; i++) {
            pb[i] = new ProcessBuilder("./mt", "entrada"+(i+1) + ".txt");
            salida[i] = new File("salida" + (i+1) + ".txt");
            error[i] =  new File("error" + (i+1) + ".txt");

        }
/*
        pb_programa.redirectInput(entrada);
//        pb_programa.redirectOutput(salida);
        pb_programa.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb_programa.redirectError(error);
        try {
            Process p = pb_programa.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------------------MEDIA------------------------");
        ProcessBuilder pb_media = new ProcessBuilder("./media");
        pb_media.redirectInput(entrada2);
        pb_media.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb_media.redirectError(error);
        try {
            Process p2 = pb_media.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fin del programa");*/
    }
}
