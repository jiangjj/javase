package io.networkstream;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/1/27.
 */
public class URLTyper {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java URLTyper url");
            return;
        }
        InputStream in = null;
        try {
            URL u = new URL(args[0]);
            in = u.openStream();
            for (int c = in.read(); c != -1; c = in.read()) {
                System.out.write(c);
            }
        }catch (MalformedURLException ex) {
            System.err.println(args[0] + " is not a URL Java understands.");
        } finally {
            if (in != null)
                in.close();
        }
    }
}
