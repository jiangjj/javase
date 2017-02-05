package io.objectstreams;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class SerializableList extends ArrayList implements Externalizable{

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(size());
        for (int i = 0; i < size(); i++) {
            if (get(i) instanceof Serializable) {
                out.writeObject(get(i));
            } else {
                out.writeObject(null);
            }
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int elementCount = in.readInt();
        this.ensureCapacity(elementCount);
        for (int i = 0; i < elementCount; i++) {
            add(in.readObject());
        }
    }

    public static void main(String[] args) throws Exception {
        SerializableList list = new SerializableList();
        list.add("Element 1");
        list.add(new Integer(9));
        list.add(new Socket("www.baidu.com", 80));
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream temp = new ObjectOutputStream(bout);
        temp.writeObject(list);
        temp.close();
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oin = new ObjectInputStream(bin);
        List out = (List) oin.readObject();
        Iterator iterator = out.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
