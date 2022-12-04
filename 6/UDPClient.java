// simulation of DNS using UDP

import java.net.*;
import java.util.Scanner;

public class UDPClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while(true) {
            System.out.print("Enter host: ");
            String host = scanner.nextLine();
            byte[] data = host.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 4444);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);
            String ip = new String(packet.getData());
            System.out.println("IP: " + ip);
            socket.close();
        }
    }
}