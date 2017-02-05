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
public class ConnectedTimeClient {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        SocketAddress address = new InetSocketAddress(0);
        DatagramSocket socket = channel.socket();
        socket.bind(address);
        SocketAddress server = new InetSocketAddress("time-a.nist.gov", 37);
        channel.connect(server);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte) 0);
        buffer.flip();
        channel.write(buffer);
        buffer.clear();
        buffer.putInt(0);
        channel.read(buffer);
        buffer.flip();
        long secondsSince1900 = buffer.getLong( );
        long differenceBetweenEpochs = 2208988800L;
        long secondsSince1970
                = secondsSince1900 - differenceBetweenEpochs;
        long msSince1970 = secondsSince1970 * 1000;
        Date time = new Date(msSince1970);
        System.out.println(time);
    }
}
