package com.dreamchat.chat;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author eugene chapsky
 */
public class Server extends Thread {
    private static int port = 1234;
    private static String messageString;
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static PrintWriter printWriter=null;
    private static BufferedReader buffReader=null;

    public static void init(int port) {
        System.out.println("***Init Server***");
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            socket.setSoTimeout(0);
            buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("***Got a client***");
        } catch (IOException e) {
            e.printStackTrace();
            stopServer();
        }
    }

    public static void startWaitingMessages(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            while ((messageString = buffReader.readLine()) != null) {
                if (messageString.equalsIgnoreCase("exit")) break;
                printWriter.print(messageString);
                Message messageFromClient = mapper.readValue(messageString, Message.class);
                System.out.println(messageFromClient.getDateCreated()+":"+ messageFromClient.getSender() +":    "+ messageFromClient.getMessage());
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void stopServer(){
      if (socket!=null){
          try {
              socket.close();
          } catch (IOException ex){
              ex.printStackTrace();
          }
      }
      if (buffReader!=null){
          try {
              buffReader.close();
          } catch (IOException ex){
              ex.printStackTrace();
          }
      }
      if (printWriter!=null){
              printWriter.close();
      }
    }

    public static void sendBroadcastMessage(String message){

    }

    public static void main(String[] args) throws IOException {
        init(port);
        startWaitingMessages();
    }
}

