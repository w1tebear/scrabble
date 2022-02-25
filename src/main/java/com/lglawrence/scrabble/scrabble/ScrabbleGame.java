/**
 * 
 */
package com.lglawrence.scrabble.scrabble;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.lglawrence.scrabble.comms.WebSocketServer;

/**
 * @author lgl
 *
 */
public class ScrabbleGame implements Game {

    private boolean begun = false;
    Board board = new Board();
    private String id = UUID.randomUUID().toString();
    Set<Player> players = new HashSet<>();
    TilePile tilePile = new TilePile();

    public ScrabbleGame() {

    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Initialize the game. Accepted properties:
     * 
     * <pre>
     * name - the name of the player initializing the game.
     * </pre>
     */
    @Override
    public void init(WebSocketServer ws, Map<String, String> props) throws GameInputException {
        if (!props.containsKey("name")) {
            throw new GameInputException("A name is required to start a new game.");
        }
        players.add(new Player(props.get("name")));

    }

    public void start() {
        begun = true;
    }

}
