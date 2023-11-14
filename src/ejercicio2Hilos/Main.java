package ejercicio2Hilos;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        
        char[] vocales = {'a','e','i','o','u'};
        for (int i = 0; i < vocales.length; i++) {
        	Hilo h =  new Hilo();
            h.run(vocales[i]);
            
            /*BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (char clave : vocales[i]) {
                int valor = veces.get(clave);
                frase = "La letra: " + clave + ", sale " + valor + "\n";
                bw.write(frase);
            }
            bw.close();*/
		}
        
    }
}
