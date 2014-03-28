package com.dreamchat.chat;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author eugene chapsky
 */
public class Client{

    private StackTraceElement state; // connected, disconnected, TODO
    private int port;
    private Socket socket = null;
    private BufferedReader bufferedReader = null;
    private PrintWriter printWriter = null;
    private String login= null;
    private InetAddress ipAddress= null;


    public Client(String host, int port, String login) {
        try {
            ipAddress= InetAddress.getByName(host);
        } catch (UnknownHostException ex){
            ex.printStackTrace();
        }
        this.port = port;
        this.login = login;
        connect();
    }

    public Client(){
        this("localhost", 1234, "Default Login");
    }

    public void connect() {
       System.out.println("Connecting to... " + ipAddress);
        try{
            socket = new Socket(ipAddress, port);
            socket.setSoTimeout(0);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            socket.close();
            bufferedReader.close();
            printWriter.close();
        } catch (IOException ex){
            System.err.print("Cannot close Soket/BufferedReader/PrintWriter");
        }
    }

    public void sendMessage(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String strMsg = mapper.writeValueAsString(message);
            printWriter.println(strMsg);
//            String s = bufferedReader.readLine();
//            Message messageFromServer = mapper.readValue(s, Message.class);
//            System.out.println(messageFromServer.getDateCreated()+":"+ messageFromServer.getSender() +":    "+ messageFromServer.getMessage());
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public String getLogin(){
        return login;
    }

}
