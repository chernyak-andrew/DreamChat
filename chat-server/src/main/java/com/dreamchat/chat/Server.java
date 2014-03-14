package com.dreamchat.chat;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author eugene chapsky
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome!");
        BufferedReader buffReader = null;
        PrintWriter printWriter = null;
        String messageStr;
        ServerSocket servers = null;
        Socket fromclient = null;
        try {
            servers = new ServerSocket(1234);
            System.out.print("Waiting for a client...");
            fromclient = servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }
        buffReader = new BufferedReader(new
                InputStreamReader(fromclient.getInputStream()));
        printWriter = new PrintWriter(fromclient.getOutputStream(), true);
        ObjectMapper mapper = new ObjectMapper();

        Message messageFromClient = null;
        try {
            while ((messageStr = buffReader.readLine()) != null) {
                if (messageStr.equalsIgnoreCase("exit")) break;
                messageFromClient = mapper.readValue(messageStr, Message.class);
                System.out.println(messageFromClient.getDateCreated()+":"+ messageFromClient.getSender() +":    "+ messageFromClient.getMessage());
                printWriter.print(messageStr);
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

