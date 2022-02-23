/**
 * OutgoingMsgBean.java
 */
package com.lglawrence.scrabble.comms;

/**
 * This class is a bean describing the contents of messages sent from the WS
 * server to the client. When serialized to JSON, only populated parameters will
 * be included.
 */
public class OutgoingMsgBean {
    public String chatMessage;

    public OutgoingMsgBean() {
    }

    public OutgoingMsgBean(String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
