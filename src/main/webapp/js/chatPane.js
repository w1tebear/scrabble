"use strict";

var chatPane = (function() {
    function sendChatMessage() {
        let message = document.getElementById('chatInput').value;
        if (message != '') {
            ws.sendOutgoingMessage(ws.crOutgoingMessage("CHAT", { message: message }));
            document.getElementById('chatInput').value = '';
        }
    }

    function init() {
        document.getElementById('chatInput').onkeydown = function(event) {
            if (event.key == 'Enter') {
                sendChatMessage();
            }
        };
    }

    function log(message) {
        var msg = message;
        if (typeof msg != 'string') {
            // then it is a full Java OutgoingMsgBean
            msg = message.chatMessage;
        }
        var chatPane = document.getElementById('chatPane');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.innerHTML = msg;
        chatPane.appendChild(p);
        while (chatPane.childNodes.length > 25) {
            chatPane.removeChild(chatPane.firstChild);
        }
        chatPane.scrollTop = chatPane.scrollHeight;
    }

    return {
        init: init,
        log: log
    };
})();




