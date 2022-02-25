/**
 * OutgoingMsgBean.java
 */
package com.lglawrence.scrabble.comms;

import com.lglawrence.scrabble.util.HTMLString;

/**
 * This class is a bean describing the contents of messages sent from the WS
 * server to the client. When serialized to JSON, only populated parameters will
 * be included.
 */
public class OutgoingMsgBean {
    public static enum Action { CHAT }
    private Action action;
    private String chatMessage;

    /**
     * Constructor.
     */
    public OutgoingMsgBean() {
    }

    /**
     * Constructor - shortcut for creating just a chat message output bean.
     * @param chatMessage
     */
    public OutgoingMsgBean(String chatMessage) {
        setChatMessage(chatMessage);
        setAction(Action.CHAT);
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @return the chatMessage
     */
    public String getChatMessage() {
        return chatMessage;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @param chatMessage the chatMessage to set - this message will be suitably
     *                    escaped for HTML inclusion.
     */
    public void setChatMessage(String chatMessage) {
        this.chatMessage = HTMLString.encodeHtml(chatMessage);
    }
}
