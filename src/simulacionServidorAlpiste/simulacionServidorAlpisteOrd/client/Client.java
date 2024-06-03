package simulacionServidorAlpiste.simulacionServidorAlpisteOrd.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("---------------------- COMPRA DE SEMILLAS ----------------------");
        try {
            socket = new Socket("127.0.0.1", 12345);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String response;
            System.out.println("Indica el número de criador");
            int nCriador, num;
            //Validación del número de criador
            do {
                nCriador = sc.nextInt();
                if (nCriador == 0){
                    System.out.println("Bienvenido admin");
                }else if (nCriador<0 || nCriador>100){
                    System.err.println("Usted no está registrado/a");
                }
            }while (nCriador<0|| nCriador>100);
            sc.nextLine();

            //Envío del número de criador al servidor.
            out.writeInt(nCriador);
            if (nCriador == 0){

                System.out.println(in.readUTF());
                String pass = sc.nextLine();
                out.writeUTF(pass);
                do {
                    response = in.readUTF();
                    if (response.startsWith("/precio")) {
                        System.out.println(response.split(";")[1]);
                        num = sc.nextInt();
                        out.writeInt(num);
                    }else if (response.equals("Contraseña incorrecta!")){
                        System.err.println(response);
                        break;
                    }else{
                        System.out.println(response);
                        nCriador = sc.nextInt();
                        if (nCriador<1 || nCriador>5) System.err.println("Operación incorrecta");
                        else out.writeInt(nCriador);
                    }
                }while( nCriador!= 5);
                System.err.println("Saliendo...");
            } else{
                    response = in.readUTF();
                    System.out.println(response);
                    out.writeUTF("init;");

                    do {
                        response = in.readUTF();
                        if (response.startsWith("/compra")){
                            System.out.println(response.split(";")[1]);
                            num = sc.nextInt();
                            out.writeInt(num);
                        }else if (response.startsWith("/menu")){
                            System.out.println(response.split(";")[1]);
                            nCriador = sc.nextInt();
                            if (nCriador < 1 || nCriador > 5){
                                System.err.println("Operación incorrecta");
                            }else{
                                out.writeInt(nCriador);
                            }
                        }
                } while (nCriador!=5);
                System.out.println(in.readUTF());
                    socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
