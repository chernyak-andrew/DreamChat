package com.dreamchat.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author eugene chapsky
 */
public class MainServer {
    private static int port = 1234;
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static int countOfClient=0;
    public static List<SendedMessage> listOfMessages = Collections.synchronizedList(new ArrayList());

        public static void init(int port) {
            listOfMessages.add(new SendedMessage("first", "admin"));
            listOfMessages.add(new SendedMessage("second", "admin"));
            System.out.println("***Init MainServer***");
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("ServerSocket opened  in port "+port);
                threadForPrintMessage();
                while (true) {
                    socket = serverSocket.accept();
                    socket.setSoTimeout(0);
                    new ServerPerClient(socket).start();
                    countOfClient++;
                    System.out.println("***Got a "+countOfClient+" client***");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) throws IOException {
        init(port);
    }


    public static void threadForPrintMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int size = listOfMessages.size();
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (listOfMessages.size()>size){
                        for (int i = size; i<listOfMessages.size(); i++){
                            SendedMessage msg=listOfMessages.get(i);
                            System.out.println(msg.getDateCreated()+":"+ msg.getUserName() +":    "+ msg.getText());
                        }
                        size=listOfMessages.size();
                    }
                }
            }
        }).start();
    }
}

