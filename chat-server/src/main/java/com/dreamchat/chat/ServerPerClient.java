package com.dreamchat.chat;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
            try {
                while (true) {
                    String messageString = buffReader.readLine();
                    if (messageString.equalsIgnoreCase("exit")) break;
                    String typeOfMessage = getTypeOfMessage(messageString);
                    Type enumOfType = Type.getType(typeOfMessage);
                    switch (enumOfType){
                        case GET_MSG: returnMessages(messageString);
                        case SENDED_MSG: addMessageToCollection(messageString);
                    }
                }
            } catch (IOException ex){
                System.out.println("User leave");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

    private void addMessageToCollection(String messageString) {
        ObjectMapper mapper = new ObjectMapper();
        SendedMessage messageFromClient = null;
        try {
            messageFromClient = mapper.readValue(messageString, SendedMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainServer.listOfMessages.add(messageFromClient);
    }

    private void returnMessages(String messageString) {
        ObjectMapper mapper = new ObjectMapper();
        GetMessages getMsg = null;
        try {
            getMsg = mapper.readValue(messageString, GetMessages.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<SendedMessage> listOfSendedMessage = new ArrayList<SendedMessage>();
        int curentSize = MainServer.listOfMessages.size();
        for (int i=getMsg.getLastRecievedMessageId(); i<curentSize; i++){
            SendedMessage msg = MainServer.listOfMessages.get(i);
            listOfSendedMessage.add(msg);
        }
        ReturnedMessages messagesForReturn = new ReturnedMessages(curentSize, listOfSendedMessage);
        String strMsg = null;
        try {
            strMsg = mapper.writeValueAsString(messagesForReturn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println(strMsg);

    }

    private String getTypeOfMessage(String messageString) {
        return "GET_MSG";
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

