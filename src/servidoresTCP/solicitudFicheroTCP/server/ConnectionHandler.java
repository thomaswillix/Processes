package servidoresTCP.solicitudFicheroTCP.server;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable{
    private Socket client;
    public ConnectionHandler(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            String file = in.readUTF();
            File f = new File(file);
            if (f.exists()){
                readContent(f,file,out);
            } else{
                out.writeUTF("/error;El archivo que se ha solicitado no existe");
            }

            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readContent(File f, String file, DataOutputStream out) throws IOException {
        file = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            try {
                StringBuilder sb = new StringBuilder(file);
                while ((line = br.readLine())!=null){
                    sb.append(line);
                    sb.append("\n");
                }
                file = sb.toString();
            } catch (EOFException e) {
                System.err.println("End of file");
            }
        } catch (FileNotFoundException e) {
            //Ignore
        }
        out.writeUTF(file);
    }
}
