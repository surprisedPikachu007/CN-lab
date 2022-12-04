// echo client using TCP sockets

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class EchoClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 4444)) {
            while(true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();
                new DataOutputStream(socket.getOutputStream()).writeUTF(message);
                String response = new DataInputStream(socket.getInputStream()).readUTF();
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
