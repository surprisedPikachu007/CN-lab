// simulation of RARP protocol using TCP

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class RARPClient {
        
        static Scanner scanner = new Scanner(System.in);
    
        public static void main(String[] args) throws Exception {
            while(true) {
                System.out.print("MAC address: ");
                String host = scanner.nextLine();
                
                Socket socket = new Socket("localhost", 4444);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.write(host.getBytes());
    
                DataInputStream in = new DataInputStream(socket.getInputStream());
                byte[] data = new byte[1024];
                in.read(data);
                String ip = new String(data);
                System.out.println("IP: " + ip);
                
                socket.close();
            }
        }
}
