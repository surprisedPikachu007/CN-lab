// echo client using TCP sockets

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class EchoClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 4444)) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while(true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();
                byte[] buffer = message.getBytes();
                out.write(buffer);
                int bytesRead = in.read(buffer);
                String response = new String(buffer, 0, bytesRead);
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
