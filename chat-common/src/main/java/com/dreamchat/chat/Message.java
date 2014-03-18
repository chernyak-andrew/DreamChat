package com.dreamchat.chat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 11.03.14
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private String message;
    private String sender;
    private Date dateCreated = new Date();

    public Message(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public Message() {
        this("No message","No sender");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDateCreated() {
        return dateCreated;
    }




}
