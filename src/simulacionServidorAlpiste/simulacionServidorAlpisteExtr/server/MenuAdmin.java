package simulacionServidorAlpiste.simulacionServidorAlpisteExtr.server;

import simulacionServidorAlpiste.simulacionServidorAlpisteExtr.sharedData.Datos;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class MenuAdmin implements Runnable{
    private Scanner sc = new Scanner(System.in);
    private Datos d;
    private static int num;
    private static HashMap<Integer, Socket> usuarios = new HashMap<>();
    private static File f = new File("files/pedidos.csv");

    public MenuAdmin(Datos datos){
        this.d = datos;
    }
    @Override
    public void run() {
        try {
            do {
                System.out.println(menuAdmin());
                num = sc.nextInt();
                if (num < 0 || num > 6) {
                    System.err.println("codigo mal introducido");
                } else {
                    switch (num) {
                        case 0:
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4: //precio.
                            cambiarPrecio(num);
                            System.out.println("Operación realizada con éxito");
                            break;
                        case 5://recaudacion.
                            verRecaucadionPedidos();
                            break;
                        case 6: //pedidosAtendidos.
                            numeroPedidosAtendidos();
                            break;
                    }
                }
            } while (num != 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.err.println("exit menu");
    }
    private void numeroPedidosAtendidos() throws IOException {
        int lineastotales = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            try {
                while (br.readLine()!=null){
                    lineastotales++;
                }
            } catch (EOFException e) {
                System.err.println("End of file");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total de pedidos atendidos: " + lineastotales);
    }
    private void verRecaucadionPedidos() throws IOException {
        File f = new File("files/pedidos.csv");
        double recaudacionTotal = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            try {
                String line;
                while ((line = br.readLine())!=null){
                    String[] datos = line.split(";");
                    recaudacionTotal += Double.parseDouble(datos[5]);
                }
            } catch (EOFException e) {
                System.err.println("End of file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("la recaudación total es de: " + recaudacionTotal);
    }

    private void cambiarPrecio(int cod) throws IOException {
        synchronized (d){
            System.out.println("¿Qué precio le quiere dar? (en céntimos)");
            switch (cod){
                case 1:
                    d.setPrecioAlpiste(sc.nextInt());
                    break;
                case 2:
                    d.setPrecioNabina(sc.nextInt());
                    break;
                case 3:
                    d.setPrecioAvena(sc.nextInt());
                    break;
                case 4:
                    d.setPrecioPerilla(sc.nextInt());
                    break;
                default:
                    //ignore
                    break;
            }
        }

    }

    public static String  menuAdmin(){
        return ("Administración del servidor:\n" +
                "1.- Cambiar precio alpiste\n" +
                "2.- Cambiar precio nabina\n" +
                "3.- Cambiar precio avena\n" +
                "4.- Cambiar precio perilla\n" +
                "5.- Ver recaudación de los pedidos\n" +
                "6.- Numero de pedidos atendidos\n" +
                "0.- Finalizar servicio\n" +
                "Elige la opción:");

    }

}
