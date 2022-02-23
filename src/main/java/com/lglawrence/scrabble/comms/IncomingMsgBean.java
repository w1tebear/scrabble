/**
 * IncomingMsgBean.java
 */
package com.lglawrence.scrabble.comms;

import com.lglawrence.scrabble.scrabble.Game;

/**
 * This bean reflects the JSON object that will be received from the client.
 */
public class IncomingMsgBean {
    public String chatMessage;
    public Game.Action action;
}
