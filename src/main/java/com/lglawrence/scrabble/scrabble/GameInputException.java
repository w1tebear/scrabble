/**
 * GameException.java
 */
package com.lglawrence.scrabble.scrabble;

/**
 * An unfixable problem occurred due to user input. The message associated with
 * the Exception should be displayed to the user.
 */
@SuppressWarnings("serial")
public class GameInputException extends Exception {
    public GameInputException(String msg) {
        super(msg);
    }
}
