// program to send an image to the server

import java.io.*;
import java.net.*;
import javax.imageio.*;
import java.awt.image.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 4444);
        BufferedImage image = ImageIO.read(new File("image.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();
        new DataOutputStream(socket.getOutputStream()).writeInt(bytes.length);
        socket.getOutputStream().write(bytes);

        System.out.println("Image sent.");
        socket.close();
    }
}
