package servidoresTCP.simulacionServidorAlpisteOrd.server;

import servidoresTCP.simulacionServidorAlpisteOrd.sharedData.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ConnectionHandler implements Runnable{
    private Socket cliente;
    private Data d;
    private double precioTotal = 0;
    private static int num;

    public ConnectionHandler(Socket cliente, Data datos) {
        this.cliente = cliente;
        this.d = datos;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            num = in.readInt();
            Server.usuarios.put(num,cliente);
            if (num==0){
                synchronized (d) {

                    out.writeUTF("Introduzca su contraseña: ");
                    String pass = in.readUTF();
                    if (pass.trim().equalsIgnoreCase("pericosegovia")) {
                        do {
                            out.writeUTF(menu().split(";")[1]);
                            num = in.readInt();
                            if (num != 5) {
                                try {
                                    modificarPrecio(num, out, in);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } while (num != 5);
                        d.notifyAll();
                        Server.usuarios.remove(0);
                    } else out.writeUTF("Contraseña incorrecta!");
                    cliente.close();
                }
            } else {
                for (Socket client : Server.usuarios.values()) {
                    if (client != Server.usuarios.get(0)) {
                        DataOutputStream out1 = new DataOutputStream(cliente.getOutputStream());
                        out1.writeUTF("El usuario admin puede estar haciendo cambios, en cuanto se pueda le aparecerá el menú");
                    }
                }
                synchronized (d) {
                   if (Server.usuarios.get(0) != null) {
                        try {
                            d.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                System.out.println(in.readUTF());
                do {
                    out.writeUTF(menu());
                    num = in.readInt();
                    if (num > 0 && num < 5) {
                        comprarSemillas(num, out, in);
                    }
                } while (num != 5);
                out.writeUTF("Precio total de las operaciones: " + precioTotal);
            }
            if(!cliente.isClosed()){
                cliente.close();
            }
        }catch (SocketException e){
            System.err.println("Client closed");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void comprarSemillas(int num, DataOutputStream out, DataInputStream in) throws IOException {
        int uds;
        switch (num){
            case 1:
                out.writeUTF("/compra;¿Cuánto alpiste quieres comprar?: ");
                uds = in.readInt();
                precioTotal+=(((double) d.getAlpiste() /100) * uds);
                break;
            case 2:
                out.writeUTF("/compra;¿Cuánta nabina quieres comprar?: ");
                uds = in.readInt();
                precioTotal+=(((double) d.getNabina() /100) * uds);
                break;
            case 3:
                out.writeUTF("/compra;¿Cuánto cañamón quieres comprar?: ");
                uds = in.readInt();
                precioTotal+=(((double) d.getCaniamon() /100) * uds);
                break;
            case 4:
                out.writeUTF("/compra;¿Cuánto negrillo quieres comprar?: ");
                uds = in.readInt();
                precioTotal+=(((double) d.getNegrillo() /100) * uds);
                break;
        }
    }

    private void modificarPrecio(int num, DataOutputStream out, DataInputStream in) throws InterruptedException, IOException {
        switch (num){
            case 1:
                out.writeUTF("/precio;¿A qué precio quieres vender alpiste? (en céntimos):");
                num = in.readInt();
                d.setAlpiste(num);
                break;
            case 2:
                out.writeUTF("/precio;¿A qué precio quieres vender nabina? (en céntimos):");
                num = in.readInt();
                d.setNabina(num);
                break;
            case 3:
                out.writeUTF("/precio;¿A qué precio quieres vender cañamón? (en céntimos):");
                num = in.readInt();
                d.setCaniamon(num);
                break;
            case 4:
                out.writeUTF("/precio;¿A qué precio quieres vender negrillo? (en céntimos):");
                num = in.readInt();
                d.setNegrillo(num);
                break;
        }

    }

    private String menu() {
        return "/menu;1- alpiste\n" +
                "2- nabina\n" +
                "3- cañamón\n" +
                "4- negrillo\n" +
                "5- terminar";
    }
}
