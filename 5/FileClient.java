// transfer file using TCP socket

import java.net.*;
import java.io.*;

public class FileClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost",4444);

        FileOutputStream fos = new FileOutputStream("file2.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = socket.getInputStream();

        byte[] buffer = new byte[128];
        int bytesRead = 0;
        while((bytesRead = is.read(buffer)) != -1) {
            bos.write(buffer,0,bytesRead);
        }

        bos.close();
        socket.close();
        System.out.println("File received.");
    }
}
