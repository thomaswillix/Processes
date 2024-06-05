package util.P1Redireccionamiento;
import java.io.File;
import java.io.IOException;

public class MainRedireccionamiento {
	public static void main(String[] args) {
		ProcessBuilder pb= new ProcessBuilder("./files/juego");
		File fin = new File("./files/entrada1.txt");
		File fout = new File("./files/salida1.txt");
		File ferr =  new File("./files/error1.txt");
		
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
