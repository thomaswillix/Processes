package ejercicio2Hilos;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Hilo extends Thread{
    int cont ;

    public void run(char l) {
    	 try {
             File f = new File("ejemplo.txt");
             FileReader fr =  new FileReader(f);
             int c = fr.read();
             while(c != -1) {
             	if ((char)c == l) {
 					cont++;
 					c = fr.read();
 				}                 
             }
             fr.close();
             System.out.println("la vocsl " + l + " aparece " +cont + " veces");
         }catch (IOException e){
             System.err.println("Error de lectura y/o escritura");
         } 
    }
}
