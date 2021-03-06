package se320_finalassignment;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.awt.*;
import javax.swing.*;

/*
Final assignment Part 2
Server Client
Made by: Benigno Digon
Credit to Dr. Akbas for code templates and examples


*/

class serverP2 extends JFrame implements Runnable{
    
    private JTextArea jta = new JTextArea();
    public static void main(String[] args){
        


        new Thread(new serverP2()).start();
        new Thread(new serverP2()).start();
        new Thread(new serverP2()).start();
        new Thread(new serverP2()).start();

    }

    public void run(){
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("Server");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try{
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Server started at " + new Date() + "\n");
            Socket socket = serverSocket.accept();
            DataInputStream inputFromClient = new DataInputStream(
                socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(
                socket.getOutputStream());
            while(true){
                double bmi = inputFromClient.readDouble();
                outputToClient.writeUTF("" + bmi);
                jta.append("BMI received from client: " + bmi + "\n");
                jta.append("BMI sent to client: " + bmi + "\n");


            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    
}