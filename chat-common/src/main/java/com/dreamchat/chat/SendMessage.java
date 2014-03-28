package com.dreamchat.chat;

/**
 * Created by user on 28.03.2014.
 */
public class SendMessage extends AbstractMessage {
    private int id;
    private String text;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
