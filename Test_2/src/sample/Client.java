package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ярослав on 27.07.2015.
 */
public class Client implements Runnable {
    private Socket connection;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    Client (){
        connection = null;
        out = null;
        in = null;
    }

    public void sendData (File file){
        byte []b;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            String s = file.getName();
            out.flush();
            out.writeObject(s);
            out.writeObject(b);
            System.out.println("finish");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close (){
        try {
            out.flush();
            out.writeObject(false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                processConnection();
                System.out.println("Connection accept");
                out = new ObjectOutputStream(connection.getOutputStream());
                System.out.println("part2");
                in = new ObjectInputStream(connection.getInputStream());
                System.out.println("part3");
                in.readObject();
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    private void processConnection (){
        boolean connect = false;
        while (!connect){
            try {
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 1);
                //connection = new Socket(InetAddress.getByName("192.168.1.6"), 1);
                connect = true;
            } catch (IOException e) {
            }
        }
    }
}
