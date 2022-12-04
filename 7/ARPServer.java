// simulation of ARP protocol using TCP

import java.net.*;
import java.io.*;

public class ARPServer {
    
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
            String ip = new String(data);
            System.out.println("IP: " + ip);

            String mac = "IP not found";
            for(int i = 0; i < ips.length; i++) {
                if(ips[i].equals(ip.trim())) {
                    mac = macs[i];
                    break;
                }
            }

            out.write(mac.getBytes());
            serverSocket.close();
        }
    }
}
