package io.filterstreams;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class MonitoredSourceViewer {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                URL u = new URL(args[0]);
                URLConnection uc = u.openConnection();
                InputStream in = uc.getInputStream();
                ProgressMonitorInputStream pin = new ProgressMonitorInputStream(null, u.toString(), in);
                ProgressMonitor pm = pin.getProgressMonitor();
                pm.setMaximum(uc.getContentLength());
                for (int c = pin.read(); c != -1; c = pin.read()) {
                    System.out.print(c);
                }
                pin.close();
            } catch (MalformedURLException ex) {

            } catch (IOException ex) {
                System.err.println(ex);
            }

        }
        System.exit(0);
    }
}
