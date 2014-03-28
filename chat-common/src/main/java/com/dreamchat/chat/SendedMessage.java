package com.dreamchat.chat;

import java.text.SimpleDateFormat;

/**
 * Created by user on 28.03.2014.
 */
public class SendedMessage extends AbstractMessage {
    private String text;
    private String userName;

    public SendedMessage(String text, String userName) {
        this.text = text;
        this.userName = userName;
        super.type="SENDED_MSG";
    }

    public SendedMessage() {
        this("","");
    }

    public String getUserName() {
        return userName;
    }

    public SimpleDateFormat getDateCreated() {
        return super.dateCreated;
    }

    public String getText() {
        return text;
    }

    public String getType(){
        return super.type;
    }




}


