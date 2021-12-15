package se320_finalassignment;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class clientP1 extends JFrame implements ActionListener {

    private JTextField jtf = new JTextField();
    private JTextArea jta = new JTextArea();
    private DataOutputStream outputToServer;
    private DataInputStream inputFromServer;

    public double weight;
    public double height;

    public static void main(String[] args){
        //set height and weight here
        new clientP1(77, 1.8);
            
    }

    public clientP1(double weightParam, double heightParam){
        weight = weightParam;
        height = heightParam;
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("Enter radius"), BorderLayout.WEST);
        p.add(jtf, BorderLayout.CENTER);
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(p, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);
        jtf.addActionListener(this);
        setTitle("Client");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        try{
            Socket socket = new Socket("locahost", 8000);
            inputFromServer = new DataInputStream(socket.getInputStream());
            outputToServer = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException e){
            jta.append(e.toString() + "\n");
        }



    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if(e.getSource() instanceof JTextField){
            try{
                double bmi = weight / (height * height);
                outputToServer.writeDouble(bmi);
                outputToServer.flush();

                String bmiStr = inputFromServer.readUTF();
                jta.append("Calculated BMI is " + bmi + "\n");
                jta.append("BMI received from server is: " + bmiStr + "\n");


            }
            catch(IOException ex){
                System.err.println(ex);
            }
        }
    }

}