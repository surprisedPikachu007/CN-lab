// chat server using TCP sockets

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatServer {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            Socket socket = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while(true) {
                String message = in.readUTF();
                System.out.println("Client: " + message);
                System.out.print("Server: ");
                String response = scanner.nextLine();
                out.writeUTF(response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
