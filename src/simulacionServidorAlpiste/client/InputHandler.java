package simulacionServidorAlpiste.client;

import simulacionServidorAlpiste.server.Server;
import simulacionServidorAlpiste.sharedData.Datos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class InputHandler implements Runnable{
    private Socket socket;
    private static Scanner sc = new Scanner(System.in);
    private Datos d;

    public InputHandler(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String response;
            int kgAlpiste, kgNabina, kgAvena, kgPerilla;
            System.out.println("Indica el número de criador");
            int nCriador;
            //Validación del número de criador
            do {
                nCriador = sc.nextInt();
                if (nCriador<0 || nCriador>90){
                    System.err.println("El número de criador no puede ser menor a 0 o mayor a 90");
                }
            }while (nCriador<0|| nCriador>90);
            //Envío del número de criador al servidor.
            out.writeInt(nCriador);
            if (nCriador==0){
                //Menu Criador
                System.out.println(in.readUTF());
            }else{
                System.out.println("Kg de Alpiste: (Precio actual +" + d.getPrecioAlpiste() +") " );
                kgAlpiste = sc.nextInt();

                System.out.println("Kg de Nabina: (Precio actual +" + d.getPrecioNabina() +") " );
                kgNabina = sc.nextInt();

                System.out.println("Kg de Avena: (Precio actual +" + d.getPrecioAvena() +") " );
                kgAvena = sc.nextInt();

                System.out.println("Kg de Perilla: (Precio actual +" + d.getPrecioPerilla() +") " );
                kgPerilla = sc.nextInt();

                out.writeUTF(kgAlpiste + " " + kgNabina + " " + kgAvena + " " + kgPerilla);
            }
            response = in.readUTF();
            if (response.equals("/salir")) {
                System.err.println("Saliendo...");
            }
            else System.out.println(response);
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
