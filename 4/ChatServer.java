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
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                String message = new String(buffer, 0, bytesRead);
                System.out.println("Client: " + message);
                System.out.print("Server: ");
                String response = scanner.nextLine();
                buffer = response.getBytes();
                out.write(buffer);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
