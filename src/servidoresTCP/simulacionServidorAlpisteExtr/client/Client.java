package servidoresTCP.simulacionServidorAlpisteExtr.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static Scanner sc = new Scanner(System.in);
    private static File f = new File("files/pedidos.csv");
    //En esta versión hay persistencia en unn fichero y se hace una buena práctica de utilización de dos hilos, uno para el menu del servidor y otro para el cliente.
    public static void main(String[] args) {
        System.out.println("---------------------- COMPRA DE ALPISTE ----------------------");
        try {
            socket = new Socket("127.0.0.1", 9876);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String response;
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

            int kgAlpiste, kgNabina, kgAvena, kgPerilla, precio = in.readInt();
            int importe;

            System.out.println("Kg de Alpiste: (Precio actual " + precio + ") ");
            kgAlpiste = sc.nextInt();
            out.writeUTF("Kilos de alpiste: " + kgAlpiste);
            importe = precio * kgAlpiste;

            precio = in.readInt();
            System.out.println("Kg de Nabina: (Precio actual " + precio + ") ");
            kgNabina = sc.nextInt();
            out.writeUTF("Kilos de nabina: " + kgNabina);
            importe += precio * kgNabina;

            precio = in.readInt();
            System.out.println("Kg de Avena: (Precio actual " + precio + ") ");
            kgAvena = sc.nextInt();
            out.writeUTF("Kilos de avena: " + kgAvena);
            importe += precio * kgAvena;

            precio = in.readInt();
            System.out.println("Kg de Perilla: (Precio actual " + precio + ") ");
            kgPerilla = sc.nextInt();
            out.writeUTF("Kilos de perilla: " + kgPerilla);
            importe += precio * kgPerilla;
            double importeD = (double) importe / 100;
            System.out.println("Importe total: " + importeD);
            response = in.readUTF();
            int numeroRegistro = Integer.parseInt(response.split(" ")[1]);
            guardarRegistro(numeroRegistro, kgAlpiste, kgNabina, kgAvena, kgPerilla, importeD);

            response = in.readUTF();
            if (response.equals("exit")) {
                System.err.println("Saliendo...");
            }
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void guardarRegistro(int numeroRegistro, int kgAlpiste, int kgNabina, int kgAvena, int kgPerilla, double importe) {
        try {
            BufferedWriter bw =  new BufferedWriter(new FileWriter(f, true));
            String registro = numeroRegistro + ";" + kgAlpiste + ";"+ kgNabina + ";"+ kgAvena + ";"+ kgPerilla + ";" +importe;
            bw.write(registro);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
