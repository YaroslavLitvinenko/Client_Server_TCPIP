package com.company;

import java.io.*;
import java.net.URI;

/**
 * Created by ярослав on 27.07.2015.
 */
public class BytFile extends File {
    private byte [] data;

    public BytFile(String pathname) {
        super(pathname);
        try (FileInputStream fileInputStream = new FileInputStream(pathname)) {
            data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BytFile(String parent, String child) {
        super(parent, child);
    }

    public BytFile(File parent, String child) {
        super(parent, child);
    }

    public BytFile(URI uri) {
        super(uri);
    }

    public byte[] getData (){
        return data;
    }

    public String toString (){
        return getName();
    }
}
