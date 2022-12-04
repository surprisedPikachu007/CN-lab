// transfer file using TCP socket

import java.net.*;
import java.io.*;

public class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);
        Socket socket = serverSocket.accept();

        File file = new File("file.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = socket.getOutputStream();

        byte[] buffer;
        long fileLength = file.length();
        long bytesRead = 0;

        System.out.println("Sending file...");
        while(bytesRead < fileLength) {
            int size = 128;
            
            if(fileLength - bytesRead >= 128) {
                bytesRead += size;
            } else {
                size = (int)(fileLength - bytesRead);
                bytesRead = fileLength;
            }

            buffer = new byte[size];
            bis.read(buffer,0,size);
            os.write(buffer);
            System.out.println(bytesRead*100/fileLength + "%");
        }

        bis.close();
        os.flush();
        socket.close();
        serverSocket.close();
        System.out.println("File sent.");
    }
}
