package nio.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class NewIOHelloServer {
    public final static int PORT = 2345;
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress port = new InetSocketAddress(PORT);
        serverSocketChannel.socket().bind(port);
        while (true) {
            try {
                SocketChannel clientChannel = serverSocketChannel.accept();
                String response = "Hello "
                        + clientChannel.socket().getInetAddress( ) + " on port "
                        + clientChannel.socket().getPort( ) + "\r\n";
                response += "This is " + serverSocketChannel.socket( ) + " on port "
                        + serverSocketChannel.socket().getLocalPort( ) + "\r\n";
                byte[] data = response.getBytes("UTF-8");
                ByteBuffer buffer = ByteBuffer.wrap(data);
                while (buffer.hasRemaining()) {
                    clientChannel.write(buffer);
                }
                clientChannel.close();
            } catch (IOException e) {

            }
        }
    }
}
