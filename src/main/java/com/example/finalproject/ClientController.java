package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientController {

    public static Socket s1;
    public static PrintStream pout;

    @FXML
    public TextArea incomingText;
    public TextField outgoingText;

    public void connectClicked(ActionEvent event) {
        try {
            s1 = new Socket("localhost", 7777);
            System.out.println("Client Connected");
            ReadThread2 readThread = new ReadThread2(this);
            Thread rt = new Thread(readThread);
            rt.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPressed(ActionEvent event) {
        try {
            pout = new PrintStream(s1.getOutputStream());
            String strSend = outgoingText.getText();
            pout.println(strSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitPressed(ActionEvent event) {
        try {
            ServerChat.stage.close();
            pout.close();
            ReadThread1.exitPressed = true;
            s1.close();
            ClientChat.stage.close();
        } catch (Exception e) {
            ClientChat.stage.close();
        }
    }
}
