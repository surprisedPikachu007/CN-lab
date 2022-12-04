// echo server using TCP sockets

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            Socket socket = serverSocket.accept();

            while(true) {
                String message = new DataInputStream(socket.getInputStream()).readUTF();
                new DataOutputStream(socket.getOutputStream()).writeUTF(message);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
