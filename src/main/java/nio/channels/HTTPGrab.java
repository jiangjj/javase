package nio.channels;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class HTTPGrab {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java HTTPGrab url filename");
            return;
        }
        URL u = new URL(args[0]);
        if (!u.getProtocol().equalsIgnoreCase("http")) {
            System.err.println("Sorry, " + u.getProtocol() + " is not supported");
            return;
        }
        String host = u.getHost();
        int port = u.getPort();
        String file = u.getFile();
        if (file == null)
            file = "/";
        if (port <= 0)
            port = 80;
        SocketAddress remote = new InetSocketAddress(host, port);
        SocketChannel channel = SocketChannel.open(remote);
        FileOutputStream out = new FileOutputStream(args[1]);
        FileChannel localFile = out.getChannel();
        String request = "GET " + file + " HTTP/1.1\r\n"
                + "User-Agent: HTTPGrab\r\n"
                + "Connection: close\r\n"
                + "Host: " + host + "\r\n"
                + "\r\n";
        ByteBuffer header = ByteBuffer.wrap(request.getBytes("US-ASCII"));
        channel.write(header);
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            localFile.write(buffer);
            buffer.clear();
        }
        localFile.close();
        channel.close();
    }
}
