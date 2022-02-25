/**
 * Game.java
 */
package com.lglawrence.scrabble.scrabble;

import java.util.Map;

import com.lglawrence.scrabble.comms.WebSocketServer;

/**
 * Interface all games must implement.
 */
public interface Game {
    public static enum Action {
        CHAT, END, INIT, MAKE_PLAY, START
    }

    /**
     * Initialize the game. The game will not actually start until start() is
     * called.
     * 
     * @param ws    the WebSocketServer to which all communications should be sent.
     * @param props the properties appropriate to initialize the game.
     * @throws GameInputException should the properties be lacking.
     */
    public void init(WebSocketServer ws, Map<String, String> props) throws GameInputException;

}
