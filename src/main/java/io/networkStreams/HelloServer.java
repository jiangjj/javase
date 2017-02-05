package io.networkStreams;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class HelloServer {
    public static void main(String[] args) throws IOException {
        int port = 2345;
        ServerSocket ss = new ServerSocket(port);
        try {
            Socket s = ss.accept();
            String response = "Hello";
            OutputStream out = s.getOutputStream();
            out.write(response.getBytes("US-ASCII"));
            out.flush();
            s.close();
        } catch (IOException ex) {

        }
    }
}
