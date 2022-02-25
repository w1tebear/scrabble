/**
 * IncomingMsgBean.java
 */
package com.lglawrence.scrabble.comms;

import java.util.HashMap;
import java.util.Map;

import com.lglawrence.scrabble.scrabble.Game.Action;
import com.lglawrence.scrabble.scrabble.ScrabbleGame;

/**
 * This bean reflects the JSON object that will be received from the client.
 */
public class IncomingMsgBean {

    private ScrabbleGame.Action action;
    private Map<String, String> payload = new HashMap<>();

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @return the payload
     */
    public Map<String, String> getPayload() {
        return payload;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Map<String, String> payload) {
        this.payload = payload;
    }
}
