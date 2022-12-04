// simulation of ARP protocol using TCP

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ARPClient {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while(true) {
            System.out.print("IP address: ");
            String host = scanner.nextLine();
            
            Socket socket = new Socket("localhost", 4444);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.write(host.getBytes());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            byte[] data = new byte[1024];
            in.read(data);
            String mac = new String(data);
            System.out.println("MAC: " + mac);
            
            socket.close();
        }
    }
}
