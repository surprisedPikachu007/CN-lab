// chat client using TCP sockets

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient {

    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4444)) {
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while(true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();
                out.writeUTF(message);
                String response = in.readUTF();
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
