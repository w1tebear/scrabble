/**
 * Javascript to manage the websocket interface
 */
"use strict";

var Chat = {};

Chat.socket = null;

Chat.connect = (function(host) {
    if ('WebSocket' in window) {
        Chat.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        Chat.socket = new MozWebSocket(host);
    } else {
        ChatPane.log('Error: WebSocket is not supported by this browser.');
        return;
    }

    Chat.socket.onopen = function() {
        ChatPane.log('Info: WebSocket connection opened.');
        document.getElementById('chatInput').onkeydown = function(event) {
            if (event.keyCode == 13) {
                Chat.sendMessage();
            }
        };
    };

    Chat.socket.onclose = function() {
        document.getElementById('chatInput').onkeydown = null;
        ChatPane.log('Info: WebSocket closed.');
    };

    Chat.socket.onmessage = function(message) {
        /* 'msg' is the JSON version of the java bean OutgoingMsgBean */
        var msg = JSON.parse(message.data);
        if (msg.chatMessage) {
            ChatPane.log(msg.chatMessage);
        } 
    };
});

Chat.initialize = function() {
    if (window.location.protocol == 'http:') {
        Chat.connect('ws://' + window.location.host + '/scrabble/wsendpoint');
    } else {
        Chat.connect('wss://' + window.location.host + '/scrabble/wsendpoint');
    }
};

Chat.sendMessage = (function() {
    var message = document.getElementById('chatInput').value;
    if (message != '') {
        Chat.socket.send(message);
        document.getElementById('chatInput').value = '';
    }
});

var ChatPane = {};

ChatPane.log = (function(message) {
    var chatPane = document.getElementById('chatPane');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.innerHTML = message;
    chatPane.appendChild(p);
    while (chatPane.childNodes.length > 25) {
        chatPane.removeChild(chatPane.firstChild);
    }
    chatPane.scrollTop = chatPane.scrollHeight;
});

Chat.initialize();


