package guava.basicutils;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by jiangjiajie on 2017/2/2.
 */
@Deprecated
public class ThrowablesDemo {
    public static void main(String[] args) {
        try {
            throw new IOException();
        } catch (IOException e) {

        } catch (Throwable t) {
            Throwables.getRootCause(t);
        }
    }
}
