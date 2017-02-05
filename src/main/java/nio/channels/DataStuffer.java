package nio.channels;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class DataStuffer {
    private static byte[] data = new byte[256];
    public static void main(String[] args) throws IOException {
        int port = 9000;
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            Thread stuffer = new StuffThread(socket);
            stuffer.start();
        }
    }
    private static class StuffThread extends Thread {
        private Socket socket;

        public StuffThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                OutputStream out = new BufferedOutputStream(socket.getOutputStream());
                while (!socket.isClosed()) {
                    out.write(data);
                }
            } catch (IOException e) {
                if (!socket.isClosed()) {
                    try {
                        socket.close();
                    } catch (IOException ex) {

                    }
                }
            }
        }
    }
}
