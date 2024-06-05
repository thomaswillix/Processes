package simulacionServidorFotocopias.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---------------------- LOGIN ----------------------");
        try {
            socket = new Socket("127.0.0.1", 12345);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("ID DEL EMPLEADO FORMATO X000: ");
            String s, user;
            do {
                s = sc.nextLine();
            } while (!s.matches("^[A-Za-z][0-9]{3}$")); //^: marca el inicio,
            // [A-Za-z]: cualquier letra del abecedario (mayúscula o minúscula)
            // [0-9]: cualquier número
            // {3} que haya 3 números seguidos (a la letra no hace falta ponerle {1} porque sería redundante)

            out.writeUTF(s);
            String message = in.readUTF();
            System.out.println(message.split(";")[1]);
            int n;
            if (message.split(";")[1].equals("Bienvenido, admin")){
                out.writeUTF("/login");
                do {
                    message = in.readUTF();
                    if (message.startsWith("/renovar")){
                        System.out.println(message.split(";")[1]);
                        user = sc.nextLine();
                        out.writeUTF(user);
                        System.out.println(in.readUTF());
                        out.writeInt(0);
                    }else if (message.startsWith("USUARIO")) {
                        System.out.println(message);
                        out.writeInt(0);
                    }else if (message.equals("exit")) {
                        //ignore
                    }else{
                        System.out.println(message);
                        n = sc.nextInt();
                        out.writeInt(n);
                        sc.nextLine();
                    }
                }while (!message.equals("exit"));
            } else{
                do {
                    message = in.readUTF();
                    if (message.startsWith("/copias")) {
                        System.out.println(message.split(";")[1]);
                        n = sc.nextInt();
                        out.writeInt(n);
                    } else System.err.println(message);
                }while(!message.equals("Saldo insuficiente, pida al administrador una recarga."));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
