package simulacionServidorAlpiste.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static Scanner sc = new Scanner(System.in);
    private static File f = new File("files/pedidos.csv");
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

            if (nCriador==0){
                int operacion = 0, pedidos = 0;
                String s;
                double recaudacion;
                //Menu Admin
                do {
                    response = in.readUTF();

                    if(response.startsWith("Administración ")) {
                        System.out.println(response);
                        operacion = sc.nextInt();
                        out.writeInt(operacion);

                    } else if (response.equals("/precio")){
                        System.out.println("¿Qué precio le quiere dar? (en céntimos)");
                        operacion = sc.nextInt();
                        out.writeInt(operacion);

                    } else if(response.startsWith("/recaudacion")){
                        recaudacion = Double.parseDouble(response.split(" ")[1]);
                        System.out.println("la recaudación total es de: " + recaudacion);

                    } else if (response.startsWith("/pedidosAtendidos")){
                        pedidos = Integer.parseInt(response.split(" ")[1]);
                        System.out.println("Total de pedidos atendidos: " + pedidos);

                    }else System.out.println(response);
                }while (!response.equals("exit"));

            }else{
                //RECIBIR INT CON EL RPECIO DEL ALPISTE
                int kgAlpiste, kgNabina, kgAvena, kgPerilla, precio = in.readInt();
                int importe;

                System.out.println("Kg de Alpiste: (Precio actual " + precio +") " );
                kgAlpiste = sc.nextInt();
                out.writeUTF("Kilos de alpiste: " + kgAlpiste);
                importe = precio * kgAlpiste;

                precio = in.readInt();
                System.out.println("Kg de Nabina: (Precio actual " + precio +") " );
                kgNabina = sc.nextInt();
                out.writeUTF("Kilos de nabina: " + kgNabina);
                importe += precio * kgNabina;

                precio = in.readInt();
                System.out.println("Kg de Avena: (Precio actual " + precio +") " );
                kgAvena = sc.nextInt();
                out.writeUTF("Kilos de avena: " + kgAvena);
                importe += precio * kgAvena;

                precio = in.readInt();
                System.out.println("Kg de Perilla: (Precio actual " + precio +") " );
                kgPerilla = sc.nextInt();
                out.writeUTF("Kilos de perilla: " + kgPerilla);
                importe += precio * kgPerilla;
                double importeD = (double) importe/100;
                System.out.println("Importe total: " + importeD);
                guardarRegistro(kgAlpiste,kgNabina,kgAvena,kgPerilla,importeD);
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

    private static void guardarRegistro(int kgAlpiste, int kgNabina, int kgAvena, int kgPerilla, double importe) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            int numeroRegistro = 1;
            try {
                while (br.readLine()!=null){
                    numeroRegistro++;
                }
            } catch (EOFException e) {
                br.close();
                System.err.println("End of file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedWriter bw =  new BufferedWriter(new FileWriter(f));
            String registro = numeroRegistro + ";" + kgAlpiste + ";"+ kgNabina + ";"+ kgAvena + ";"+ kgPerilla + ";" +importe + "\n";
            bw.write(registro);
            bw.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
