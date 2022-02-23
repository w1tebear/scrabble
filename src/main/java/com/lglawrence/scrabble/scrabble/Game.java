/**
 * 
 */
package com.lglawrence.scrabble.scrabble;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author lgl
 *
 */
public class Game {

    public static enum Action {
        INIT, START, END, PLAY
    }

    Set<Player> players = new HashSet<>();
    TilePile tilePile = new TilePile();
    Board board = new Board();
    private boolean begun = false;
    private String id = UUID.randomUUID().toString();

    public Game() {

    }

    public void start() {
        begun = true;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

}
