// program to accept an image from the client

import java.io.*;
import java.net.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

public class Server {
    public static void main(String[] args) throws Exception {
        
        System.out.println("Waiting for image...");

        ServerSocket serverSocket = new ServerSocket(4444);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();

        int length = new DataInputStream(is).readInt();
        BufferedImage image = ImageIO.read(is);

        if(image != null) {
            System.out.println("Image received.");
            System.out.println("Image size: " + length/1024 + " KB");

            JFrame frame = new JFrame("Server");
            frame.getContentPane().add(new JLabel(new ImageIcon(image)));
            frame.pack();
            frame.setVisible(true);
        }

        socket.close();
        serverSocket.close();
    }
}