package com.dreamchat.chat;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.Socket;

/**
 * @author eugene chapsky
 */
public class ServerPerClient extends Thread {
    private  Socket socket = null;
    private  PrintWriter printWriter=null;
    private  BufferedReader buffReader=null;


    ServerPerClient(Socket socket){
       this.socket=socket;
    }

    public void run () {
        try {
            buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            ObjectMapper mapper = new ObjectMapper();
            try {
                while (true) {
                    String messageString = buffReader.readLine();
                    if (messageString.equalsIgnoreCase("exit")) break;
                    Message messageFromClient = mapper.readValue(messageString, Message.class);
                    MainServer.listOfMessages.add(messageFromClient);
                }
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            } catch (IOException ex){
                System.out.println("User leave");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

    private void stopServer(){
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
}

