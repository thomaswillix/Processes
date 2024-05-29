package ejercicio1Sockets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class InputHandler implements Runnable{
    private Socket socket;
    public InputHandler(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            String response, message;
            do {
                Scanner sc = new Scanner(System.in);
                message = sc.nextLine();

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(message);

                DataInputStream in = new DataInputStream(socket.getInputStream());
                response = in.readUTF();
                if (response.equals("/salir"))
                    System.err.println("Saliendo...");
                else System.out.println(response);
            }while(!response.equals("/salir"));
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
