package servidoresTCP.simulacionChatGPTfrasesAleatorias.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket;
    public static void main(String[] args) {
        System.out.println("----------------------Chat con IA----------------------\n" +
                "\n----------------------COMANDOS----------------------\nadios --> Salir" +
                " del chat\n------------------------------------------------------");

        try {
            socket = new Socket("127.0.0.1", 12345);
            InputHandler handler = new InputHandler(socket);
            Thread t = new Thread(handler);
            t.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
