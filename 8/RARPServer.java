// simulation of RARP protocol using TCP

import java.net.*;
import java.io.*;

public class RARPServer {
        
    static String[] macs = {"6F:1A:80:76:12:2D", "9E:1A:80:76:12:2D", "7F:1A:80:76:12:2D", "8F:1A:80:76:12:2D"};
    static String[] ips = {"165.165.80.80", "165.165.79.1", "132.132.67.41", "186.117.121.11"};

    public static void main(String[] args) throws Exception {
        while(true) {
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            byte[] data = new byte[1024];
            in.read(data);
            String mac = new String(data);
            System.out.println("MAC: " + mac);

            String ip = "MAC not found";
            for(int i = 0; i < macs.length; i++) {
                if(macs[i].equals(mac.trim())) {
                    ip = ips[i];
                    break;
                }
            }

            out.write(ip.getBytes());
            serverSocket.close();
        }
    }
}
