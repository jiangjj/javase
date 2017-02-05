package io.objectstreams;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class Person implements Serializable, ObjectInputValidation{
    static Map thePeople = new HashMap();
    private String name;
    private String ss;
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.registerValidation(this, 5);
        in.defaultReadObject();
    }
    public void validateObject() throws InvalidObjectException {
        if (thePeople.containsKey(ss)) {
            throw new InvalidObjectException(this.name + " already exists");
        } else {
            thePeople.put(ss, name);
        }
    }
}
