package com.dreamchat.chat;

import java.text.SimpleDateFormat;

/**
 * Created by user on 28.03.2014.
 */
public abstract class AbstractMessage {
    protected String type;
    protected SimpleDateFormat dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
