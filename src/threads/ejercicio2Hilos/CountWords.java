package threads.ejercicio2Hilos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountWords extends Thread{
    private String word;
    File input;
    private int counter;

    public CountWords(String word, File f) {
        this.word = word;
        this.input = f;
        counter = 0;
    }

    @Override
    public void run() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Entramos al bucle cuentaPalabra " + word);
        readText();
    }

    private void readText() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;

            while((line = br.readLine())!= null){
                countWord(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void countWord(String line) {
        String[] wordsPerLine = line.split(" ");
        for (String s: wordsPerLine) {
            if (s.equalsIgnoreCase(word)){
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        File f = new File("ejemplo.txt");
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World!");
        words.add("Error");
        for (String word : words) {
            CountWords cw = new CountWords(word,f);
            cw.start();
            try{
                cw.join();
                System.out.println("El número de veces que ha aparecido la palabra " + word + " es de " + cw.counter + " veces.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
