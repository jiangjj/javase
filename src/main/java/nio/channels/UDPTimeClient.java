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
public class UDPTimeClient {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            SocketAddress address = new InetSocketAddress(0);
            DatagramSocket socket = channel.socket();
            socket.setSoTimeout(5000);
            socket.bind(address);
            SocketAddress server = new InetSocketAddress("time.nist.gov", 37);
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            buffer.order(ByteOrder.BIG_ENDIAN);
            buffer.put((byte) 65);
            buffer.flip();
            channel.send(buffer, server);
            buffer.clear();
            buffer.put((byte) 0).put((byte) 0).put((byte) 0).put((byte) 0);
            channel.receive(buffer);
            buffer.flip();
            long secondsSince1900 = buffer.getLong();
            long differenceBetweenEpochs = 2208988800L;
            long secondsSince1970
                    = secondsSince1900 - differenceBetweenEpochs;
            long msSince1970 = secondsSince1970 * 1000;
            Date time = new Date(msSince1970);
            System.out.println(time);
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (channel != null)
                channel.close();
        }
    }
}
