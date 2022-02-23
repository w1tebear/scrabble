/**
 * 
 */
package com.lglawrence.scrabble.comms;

/**
 * @author lgl
 *
 */

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.lglawrence.scrabble.util.HTMLString;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/wsendpoint")
public class WebSocketServer {
	private static final Logger log = LogManager.getLogger(WebSocketServer.class);
	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	private static final Set<WebSocketServer> connections = new CopyOnWriteArraySet<>();

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

	private final String nickname;

	private Session session;

	public WebSocketServer() {
		log.traceEntry();
		log.warn("STarting!!!");
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
		// Never trust the client
		String filteredMessage = String.format("%s: %s", nickname, HTMLString.encodeHtml(message.toString()));
		sendOutgoingMsg(new OutgoingMsgBean(filteredMessage));
	}
	public void sendOutgoingMsg(OutgoingMsgBean msgBean) {
	    Gson gson = new Gson();
	    String msg = gson.toJson(msgBean);
	    broadcast(msg);
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		log.traceEntry();
		log.error("Chat Error: " + t.toString(), t);
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
