package practica1;
// 16/11/23
import java.io.File;
import java.io.IOException;

public class MainRedireccionamiento {
	public static void main(String[] args) {
		//ProcessBuilder pb= new ProcessBuilder("gcc juego.c juego.h main.c", " -o ", " jugar");
		ProcessBuilder pb= new ProcessBuilder("./juego");
		File fin = new File("entrada.txt");
		File fout = new File("salida.txt");
		File ferr =  new File("error.txt");
		
		pb.redirectInput(fin);
		pb.redirectOutput(fout);
		pb.redirectError(ferr);
		
		System.out.println("lanzando un comando");
		
		try {
			pb.start();
		}
		catch (IOException e) 
		{
 			e.printStackTrace();
 		}
	}
}
