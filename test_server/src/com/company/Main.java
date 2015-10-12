package com.company;

import com.sun.imageio.plugins.common.BitFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends JFrame implements Runnable {
    private ServerSocket server;
    private Socket connection;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("part1");
                connection = server.accept();
                System.out.println("part2");
                out = new ObjectOutputStream(connection.getOutputStream());
                System.out.println("part3");
                in = new ObjectInputStream(connection.getInputStream());
                System.out.println("part4");
                saveFile((String)in.readObject(), (byte[])in.readObject());
                out.writeObject(true);
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch (ClassCastException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Main("Server")).start();
    }

    public void saveFile (String fileName, byte [] b){
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            fileOutputStream.write(b);
            System.out.println("File saved");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public Main (String name){
        super(name);
        try {
            server = new ServerSocket(1, 10);
        } catch (IOException e) {
        }
        setLayout(new FlowLayout());
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        final JTextField masage = new JTextField(10);
        final JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==ok){
                    sendData(masage.getText());
                }
            }
        });
        add(masage);
        add(ok);
    }

    private void sendData (Object object) {
        try {
            out.flush();
            out.writeObject(object);
        } catch (IOException e) {
        }
    }
}
