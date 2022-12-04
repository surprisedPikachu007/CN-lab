// simulation of DNS using UDP

import java.net.*;

public class UDPServer {

    static String[] hosts = {"github.com", "bitbucket.org", "gitlab.com", "sourceforge.net"};
    static String[] ips = {"68.180.206.184", "18.205.93.4", "162.243.85.113", "185.70.42.12"};

    public static void main(String[] args) throws Exception {
        while(true) {
            DatagramSocket socket = new DatagramSocket(4444);
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);
            String host = new String(packet.getData());
            System.out.println("Request for " + host);

            int index = -1;
            for(int i = 0; i < hosts.length; i++) {
                if(hosts[i].equals(host.trim())) {
                    index = i;
                    break;
                }
            }

            String ip = "Host not found";
            if(index != -1) {
                ip = ips[index];
            }

            byte[] data = ip.getBytes();
            packet = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
            socket.send(packet);
            socket.close();
        }
    }
}
