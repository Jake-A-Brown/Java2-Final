package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    public static ServerSocket ss;
    public static Socket s1;
    public static PrintStream pout;

    @FXML
    public TextArea incomingText;
    public TextField outgoingText;

    public void sendClicked(ActionEvent event) {
        try {
            pout = new PrintStream(s1.getOutputStream());
            String strSend = outgoingText.getText();
            pout.println(strSend);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void openSocketClicked(ActionEvent event) {
        try {
            ss = new ServerSocket(7777);
            System.out.println("Server waiting...");
            s1 = ss.accept();
            ReadThread1 readThread = new ReadThread1(this);
            Thread rt = new Thread(readThread);
            rt.start();
        }catch(IOException e) {
            System.out.println("Error in socket");
        }
    }

    public void exitClicked(ActionEvent event){
        try {
            ServerChat.stage.close();
            pout.close();
            ReadThread1.exitPressed = true;
            s1.close();
            ss.close();
        }catch(Exception e) {}
    }
}
