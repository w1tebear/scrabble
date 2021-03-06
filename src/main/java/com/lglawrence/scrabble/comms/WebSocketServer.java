/**
 * 
 */
package com.lglawrence.scrabble.comms;

/**
 * @author lgl
 *
 */

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.lglawrence.scrabble.scrabble.Game;
import com.lglawrence.scrabble.scrabble.Game.Action;
import com.lglawrence.scrabble.scrabble.GameInputException;
import com.lglawrence.scrabble.scrabble.ScrabbleGame;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/wsendpoint")
public class WebSocketServer {
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<WebSocketServer> connections = new CopyOnWriteArraySet<>();
    private static Gson gson = new Gson();
    private static final String GUEST_PREFIX = "Guest";
    private static final Logger log = LogManager.getLogger(WebSocketServer.class);

    private static void broadcast(String msg) {
        log.traceEntry();
        for (WebSocketServer client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s", client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }

    private Game game = null;
    private String nickname;
    private Session session;

    public WebSocketServer() {
        log.traceEntry();
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }

    @OnClose
    public void end() {
        log.traceEntry();
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "has disconnected.");
        sendOutgoingMsg(new OutgoingMsgBean(message));
    }

    @OnMessage
    public void incoming(String message) {
        log.traceEntry("message: {}", message);
        IncomingMsgBean imb = gson.fromJson(message, IncomingMsgBean.class);
        Action action = imb.getAction();
        if (action == null) {
            sendOutgoingMsg(new OutgoingMsgBean("* unable to perform action - no action supplied"));
        }

        try {
            Map<String, String> props = imb.getPayload();
            if (action.equals(Action.INIT)) {
                game = new ScrabbleGame();
                if (props.containsKey("name")) {
                    this.nickname = props.get("name");
                }
                game.init(this, props);

            } else if (action.equals(Action.CHAT)) {
                if (props.containsKey("message")) {
                    String filteredMessage = String.format("%s: %s", nickname, props.get("message").toString());
                    sendOutgoingMsg(new OutgoingMsgBean(filteredMessage));
                }
            }
        } catch (GameInputException e) {
            sendOutgoingMsg(new OutgoingMsgBean("* error - " + e.getMessage()));
        }
        sendOutgoingMsg(new OutgoingMsgBean("* action " + action.toString() + " complete"));

    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        log.traceEntry();
        log.error("Chat Error: " + t.toString(), t);
    }

    public void sendOutgoingMsg(OutgoingMsgBean msgBean) {
        String msg = gson.toJson(msgBean);
        broadcast(msg);
    }

    @OnOpen
    public void start(Session session) {
        log.traceEntry();
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "has joined.");
        sendOutgoingMsg(new OutgoingMsgBean(message));
    }
}
