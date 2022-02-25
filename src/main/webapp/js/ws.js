/**
 * Javascript to manage the websocket interface
 */
"use strict";

var ws = (function() {
    var socket;
    const sendQueue = [];
    const socketState = { CONNECTING: 0, OPEN: 1, CLOSING: 2, CLOSED: 3 };
    var messageHandlers;

    function connect(host) {
        main.debug("Connecting...");
        if ('WebSocket' in window) {
            socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            socket = new MozWebSocket(host);
        } else {
            alert('Error: WebSockets are not supported by this browser.');
            return;
        }

        socket.onopen = function() {
            main.debug("in onopen");
            sendQueueToServer();
            // chatPane.init(ws);
        };

        socket.onclose = function() {
            main.debug("In onclose");
            main.statusMsg('Info: Connection to server closed.');
        };

        socket.onmessage = function(message) {
            main.debug("in onmessage:");
            main.debug(message);
            /* 'msg' is the JSON version of the java bean OutgoingMsgBean */
            let msg = JSON.parse(message.data);
            let action = msg.action;
            if (action) {
                let handlerFunc = messageHandlers[action];
                if (handlerFunc) {
                    handlerFunc(msg);
                } else {
                    main.warn('Ignoring message - no messageHandler for action ' + action);
                }
            } else {
                main.warn('Ignoring message - no action found: ' + msg);
            }
        };
    }

    /* 
     * action = CHAT|INIT|JOIN
     * payload = an object with attributes appropriate for the action - see the java game definition.
     */
    function crOutgoingMessage(action, payload) {
        return { action: action, payload: payload };
    }

    function init() {
        if (window.location.protocol == 'http:') {
            connect('ws://' + window.location.host + '/scrabble/wsendpoint');
        } else {
            connect('wss://' + window.location.host + '/scrabble/wsendpoint');
        }
    }

    /*
     * outgoingMessage is of type returned by ws.crOutgoingMessage
     */
    function sendOutgoingMessage(outgoingMessage) {
        sendQueue.push(outgoingMessage);

        if (socket == null) {
            ws.init(); // will also result in call to socket.onopen()
        } else {
            if (socket.readyState == socketState.OPEN) {
                sendQueueToServer();
            } else if (socket.readyState != socketState.CONNECTING) {
                // if CONNECTING the sendQueue will be sent in socket.onopen()
                ws.init(); // will also result in call to socket.onopen()
            }
        }
    }

    function sendQueueToServer() {
        let outgoingMsg = sendQueue.shift();
        while (outgoingMsg != undefined) {
            socket.send(JSON.stringify(outgoingMsg));
            outgoingMsg = sendQueue.shift();
        }
    }

    /*
     * messageHandlers - an object whose keys are 'actions' as defined for the Java class
     * OutgoingMsgBean, and whose values are functions that should expect a single value,
     * that being the object created by parsing the JSON message.
     */
    function setMessageHandlers(newMessageHandlers) {
        messageHandlers = newMessageHandlers;
    }

    return {
        crOutgoingMessage: crOutgoingMessage,
        init: init,
        sendOutgoingMessage: sendOutgoingMessage,
        setMessageHandlers: setMessageHandlers
    };

})();







