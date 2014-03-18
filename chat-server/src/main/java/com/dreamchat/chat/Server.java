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
    private  static int port = 1234;

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome!");
        String messageString;
        ServerSocket serverSoket = null;
        Socket soket = null;
        try {
            serverSoket = new ServerSocket(port);
            System.out.print("Waiting for a client...");
            soket = serverSoket.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept server soket");
            System.exit(-1);
        }
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(soket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(soket.getOutputStream(), true);
        ObjectMapper mapper = new ObjectMapper();
        Message messageFromClient = null;
        try {
            while ((messageString = buffReader.readLine()) != null) {
                if (messageString.equalsIgnoreCase("exit")) break;
                messageFromClient = mapper.readValue(messageString, Message.class);
                System.out.println(messageFromClient.getDateCreated()+":"+ messageFromClient.getSender() +":    "+ messageFromClient.getMessage());
                printWriter.print(messageString);
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

