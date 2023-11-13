package ejercicio2Hilos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Hilo extends Thread{
    char[] buffer;
    char[] vocales = {'a','e','i','o','u'};
    Scanner sc =  new Scanner(System.in);


    @Override
    public void run() {
        //leerArchivoVocal();
    }
    /*private void leerArchivoVocal(){
        try {
            File f = new File(ejemplo.txt);
            FileReader fr =  new FileReader(f);
            while(true) {
                fr.read(buffer);

            }
        }catch (IOException e){
            System.err.println("Error de lectura y/o escritura");
        } catch(FileNotFoundException e){
            System.err.println("Archivo no encontrado");
        }
    }*/
}
