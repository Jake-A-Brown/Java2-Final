package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ReadThread1 implements Runnable {
    ServerController controller;

    public static boolean exitPressed;

    public ReadThread1(ServerController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(ServerController.s1.getInputStream()));
            while (true) {
                String inputString = br.readLine();
                controller.incomingText.setText(inputString);
                if (exitPressed == true) {
                    break;
                }
            }

            br.close();
        } catch (SocketException se) {
            System.out.println("Socket closed");
            try {
                ServerChat.stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException ie) {
        }
    }
}