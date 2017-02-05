package io.networkStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

/**
 * Created by jiangjiajie on 2017/1/27.
 */
public class SocketTyper {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java SocketTyper url1");
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
        if (file == null) {
            file = "/";
        }
        if (port <= 0) {
            //default port
            port = 80;
        }
        Socket s = null;
        try {
            s = new Socket(host, port);
            String request = "GET " + file + " HTTP/1.1\r\n"
                    + "User-Agent: SocketType\r\n"
                    + "Accept: text/*\r\n"
                    + "Host: " + host + "\r\n"
                    + "\r\n";
            byte[] b = request.getBytes("US-ASCII");
            OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();
            out.write(b);
            out.flush();
            for (int c = in.read(); c != -1; c++) {
                System.out.write(c);
            }
        } finally {
            if (s != null && s.isConnected())
                s.close();
        }
    }
}
