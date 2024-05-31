package simulacionServidorAlpiste.client;

import simulacionServidorAlpiste.sharedData.Datos;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class InputHandler implements Runnable{
    private Socket socket;
    private static Scanner sc = new Scanner(System.in);
    private File f = new File("files/pedidos.csv");
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
            double precio;
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
                    }
                    if (response.equals("/precio")){
                            System.out.println("¿Qué precio le quiere dar? (en céntimos)");
                            operacion = sc.nextInt();
                            out.writeInt(operacion);
                            System.out.println(in.readUTF());
                    }
                    if(response.startsWith("/recaudacion")){
                            recaudacion = Double.parseDouble(response.split(" ")[1]);
                            System.out.println("la recaudación total es de: " + recaudacion);
                    }
                    if (response.startsWith("/pedidosAtendidos")){
                            pedidos = Integer.parseInt(response.split(" ")[1]);
                            System.out.println("Total de pedidos atendidos: " + pedidos);
                    }
                }while (!response.equals("exit"));

            }else{
                System.out.println("Kg de Alpiste: (Precio actual " + Datos.getPrecioAlpiste() +") " );
                kgAlpiste = sc.nextInt();

                System.out.println("Kg de Nabina: (Precio actual " + Datos.getPrecioNabina() +") " );
                kgNabina = sc.nextInt();

                System.out.println("Kg de Avena: (Precio actual " + Datos.getPrecioAvena() +") " );
                kgAvena = sc.nextInt();

                System.out.println("Kg de Perilla: (Precio actual " + Datos.getPrecioPerilla() +") " );
                kgPerilla = sc.nextInt();

                out.writeUTF(kgAlpiste + " " + kgNabina + " " + kgAvena + " " + kgPerilla);
                int importe;
                importe = in.readInt();
                guardarRegistro(kgAlpiste,kgNabina,kgAvena,kgPerilla,importe);
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

    private void guardarRegistro(int kgAlpiste, int kgNabina, int kgAvena, int kgPerilla, int importe) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            int numeroRegistro = 1;
            try {
                String line;
                while ((line = br.readLine())!=null){
                    numeroRegistro++;
                }
            } catch (EOFException e) {
                br.close();
                System.err.println("End of file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedWriter bw =  new BufferedWriter(new FileWriter(f));
            double importeD = importe/100;
            String registro = numeroRegistro + ";" + kgAlpiste + ";"+ kgNabina + ";"+ kgAvena + ";"+ kgPerilla + ";" +importeD + "\n";
            bw.write(registro);
            bw.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
