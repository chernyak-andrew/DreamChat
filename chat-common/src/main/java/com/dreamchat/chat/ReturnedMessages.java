package com.dreamchat.chat;

import java.util.List;

/**
 * Created by user on 28.03.2014.
 */
public class ReturnedMessages extends AbstractMessage{
    private int indexOfLastMessage;
    private List<SendedMessage> messages;

    public ReturnedMessages() {
    }

    public ReturnedMessages(int indexOfLastMessage, List messages) {
        this.indexOfLastMessage = indexOfLastMessage;
        this.messages = messages;

        super.type="RETURN_MSG";
    }

    public List<SendedMessage> getMessages() {
        return messages;
    }

    public int getIndexOfLastMessage() {
        return indexOfLastMessage;
    }
}
