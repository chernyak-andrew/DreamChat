package com.dreamchat.chat;

/**
 * Created by user on 28.03.2014.
 */
public class GetMessages extends AbstractMessage {
    private int lastRecievedMessageId;
//    private String type = "GET_MSG";


    public GetMessages(int lastRecievedMessageId) {
        this.lastRecievedMessageId = lastRecievedMessageId;
        super.type = "GET_MSG";
    }
    public GetMessages(){
        this(0);
    }

    public int getLastRecievedMessageId() {
        return lastRecievedMessageId;
    }

    public void setLastRecievedMessageId(int lastRecievedMessageId) {
        this.lastRecievedMessageId = lastRecievedMessageId;
    }
}
