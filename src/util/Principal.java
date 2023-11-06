package util;

import java.io.InputStream;

public class Principal {
 public static void main(String[] args) {
	 /*ProcessBuilder pb =  new ProcessBuilder("pluma", "Hola.txt");
	 try {
		 Process p  =  pb.start();
		 System.out.println("Proceso lanzado");
		 
		 p.waitFor();
		 System.out.println("Proceso terminado");
	} catch (Exception e) {
		e.printStackTrace();
	}*/
	 
	 //---------------------------------------------------------
	 
	 /*ProcessBuilder pbc= new ProcessBuilder("ls", "-l", "/home/alumnotd");
	 try {
		System.out.println("lanzo comando");
		Process pc =  pbc.start();
		InputStream is = pc.getInputStream();
		int c;
		while((c=is.read())!=-1) {
			System.out.print((char) c);
		}
		is.close();
		System.out.println("comando terminado");
	} catch (Exception e) {
		// TODO: handle exception
	}
	System.out.println("Final");*/
	//-----------------------------------------------------------
	 ProcessBuilder pbc= new ProcessBuilder("java", "-cp", "/home/alumnotd/eclipse-workspace/HolaMundo/bin", "Principal.mainSaludo");
	 try {
		System.out.println("lanzo el saludar.jar");
		Process pc =  pbc.start();
		InputStream is = pc.getInputStream();
		int c;
		while((c=is.read())!=-1) {
			
			System.out.print((char) c);
			
		}
		is.close();
		System.out.println("Termino saludar.jar");
	} catch (Exception e) {
		// TODO: handle exception
	}
	System.out.println("Final");
	//-----------------------------------------------------------
}
}
