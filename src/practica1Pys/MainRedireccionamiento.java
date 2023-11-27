package practica1Pys;
// 16/11/23
import java.io.File;
import java.io.IOException;

public class MainRedireccionamiento {
	public static void main(String[] args) {
		ProcessBuilder pb_java_jar= new ProcessBuilder("gcc", "-	o", "/home/alumnotd/eclipse-workspace/votar.jar");
		
		File fin = new File("/home/alumnotd/eclipse-workspace/HolaMundo/src/entrada.txt");
		File fout = new File("/home/alumnotd/eclipse-workspace/HolaMundo/src/salida.txt");
		File ferr =  new File("/home/alumnotd/eclipse-workspace/HolaMundo/src/error.txt");
		
		pb_java_jar.redirectInput(fin);
		pb_java_jar.redirectOutput(fout);
		pb_java_jar.redirectError(ferr);
		
		System.out.println("lanzando un comando");
		
		try {
			pb_java_jar.start();
//			System.out.println("salida");
//			InputStream is=p2.getInputStream();
//			int c;
//			while ((c=is.read())!=-1)
//			{
//				System.out.print((char)c);
//				
//			}	
//			is.close();
//			
//			System.out.println("errores");
//			InputStream er=p2.getErrorStream();
//			BufferedReader brer= new BufferedReader(new InputStreamReader(er));
//			String liner=null;
//			while((liner=brer.readLine())!=null)
//				System.out.println("ERROR >"+liner);
			
		}
		catch (IOException e) 
		{
 			e.printStackTrace();
 		}
	}
}
