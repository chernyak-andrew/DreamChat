package com.dreamchat.chat;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author eugene chapsky
 */
public class Client {

    private StackTraceElement state; // connected, disconnected,
    private static String host;
    private static int port;
    private static Socket soket = null;
    private BufferedReader bufferedReader = null;
    private PrintWriter printWriter = null;
    private String strMsg;
    private String login;

    public Client(String host, int port, String login) {
        this.host = host;
        this.port = port;
        this.login = login;
        connect();
    }

    public Client(){
        this("localhost", 1234, "Default Login");
    }

    public void connect() {
       System.out.println("Connecting to... " + host);
        try{
            soket = new Socket(host, port);
            bufferedReader = new BufferedReader(new InputStreamReader(soket.getInputStream()));
            printWriter = new PrintWriter(soket.getOutputStream(), true);
        } catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            soket.close();
            bufferedReader.close();
            printWriter.close();
        } catch (IOException ex){
            System.err.print("Cannot close Soket/BufferedReader/PrintWriter");
        }
    }

    public void sendMessage(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            strMsg = mapper.writeValueAsString(message);
            printWriter.println(strMsg);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public String getLogin(){
        return login;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Client side");

        Socket fromserver = null;
        String host="localhost";
        System.out.println("Connecting to... " + host);

        fromserver = new Socket(host, 1234);
        BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
        PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);
        BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
        String fuser, fserver;
        while((fuser = inu.readLine()) != null) {
            out.println(fuser);
            fserver = in.readLine();
            System.out.println(fserver);
            if (fuser.equalsIgnoreCase("close")) break;
            if (fuser.equalsIgnoreCase("exit")) break;
        }

        out.close();
        in.close();
        inu.close();
        fromserver.close();
    }
}
