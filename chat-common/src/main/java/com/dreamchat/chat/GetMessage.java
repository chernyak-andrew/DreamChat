package com.dreamchat.chat;

/**
 * Created by user on 28.03.2014.
 */
public class GetMessage extends AbstractMessage {
    private int lastRecievedMessageId;

    public int getLastRecievedMessageId() {
        return lastRecievedMessageId;
    }

    public void setLastRecievedMessageId(int lastRecievedMessageId) {
        this.lastRecievedMessageId = lastRecievedMessageId;
    }
}
