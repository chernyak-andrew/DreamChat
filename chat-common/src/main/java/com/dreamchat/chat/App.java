package com.dreamchat.chat;


import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Message message = new Message("Hello! This is my first mesage", "Alexey");
        ObjectMapper mapper = new ObjectMapper();
       try {
           mapper.writeValue(new File("c:\\user.json"), message);
       } catch (JsonProcessingException ex) {
           ex.printStackTrace();
       } catch (IOException ex){
           ex.printStackTrace();
       }


        ObjectMapper mapper2 = new ObjectMapper();
        Message msg = null;
        try {
             msg = mapper2.readValue(new File("c:\\user.json"), Message.class);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }



        System.out.println(msg.getMessage());
        System.out.println(msg.getSender());
        System.out.println(msg.getDateCreated());


    }
}
