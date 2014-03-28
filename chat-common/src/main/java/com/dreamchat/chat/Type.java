package com.dreamchat.chat;

/**
 * Created by user on 28.03.2014.
 */
public enum Type {
    GET_MSG("GET_MSG"),
    SENDED_MSG("SENDED_MSG");

    private String typeValue;

    private Type(String typeValue) {
        this.typeValue=typeValue;
    }

    static public Type getType(String typeOfMessage) {
        for (Type type : Type.values()) {
            if (type.getTypeValue().equals(typeOfMessage)) {
                return type;
            }
        }
        throw new RuntimeException("unknown type");
    }

    private String getTypeValue() {
        return typeValue;
    }
}
