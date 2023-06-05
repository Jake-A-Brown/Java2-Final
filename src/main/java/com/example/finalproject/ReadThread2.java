package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;

public class ReadThread2 implements Runnable {
    ClientController controller;
    public static boolean exitPressed;
    public ReadThread2(ClientController controller ){this.controller = controller;}

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(controller.s1.getInputStream()));

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
                ClientChat.stage.close();
            } catch (Exception e) {
                //
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }