/**
 * 
 */
package com.lglawrence.scrabble.scrabble;

/**
 * Object representing a game player.
 *
 */
public class Player {
    private String name;

    public Player(String name) {
        setName(name);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
