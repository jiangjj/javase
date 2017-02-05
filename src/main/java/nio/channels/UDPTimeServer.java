package nio.channels;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.Date;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class UDPTimeServer {
    public final static int DEFAULT_PORT = 37;
    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[1]);
                if (port <=0 || port > 65535)
                    port = DEFAULT_PORT;
            } catch (Exception e) {

            }
        }
        ByteBuffer in = ByteBuffer.allocate(8192);
        ByteBuffer out = ByteBuffer.allocate(8);
        out.order(ByteOrder.BIG_ENDIAN);
        SocketAddress address = new InetSocketAddress(port);
        DatagramChannel channel = DatagramChannel.open();
        DatagramSocket socket = channel.socket();
        socket.bind(address);
        System.out.println("bound to " + address);
        while (true) {
            try {
                in.clear();
                SocketAddress client = channel.receive(in);
                System.out.println(client);
                long secondSince1900 = getTime();
                out.clear();
                out.putLong(secondSince1900);
                out.flip();
                out.position(4);
                channel.send(out, client);
            }catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    private static long getTime( ) {
        long differenceBetweenEpochs = 2208988800L;
        Date now = new Date();
        long secondsSince1970 = now.getTime() / 1000;
        long secondsSince1900 = secondsSince1970 + differenceBetweenEpochs;
        return secondsSince1900;
    }
}
