"use strict";

var main = (function() {
    var debugEnabled = false;
    const messageHandlers = {
        CHAT: chatPane.log
    };

    function debug(msg) {
        if (debugEnabled) {
            console.log(msg);
        }
    }

    function init() {
        ws.setMessageHandlers(messageHandlers);
        chatPane.init();
        document.getElementById('new_game').onclick = function() { game.newGame(); }
    }

    function statusMsg(msg) {
        chatPane.log('* ' + msg);
    }

    function warn(msg) {
        console.log('WARNING: ' + msg);
    }

    return {
        debug: debug,
        init: init,
        statusMsg: statusMsg,
        warn: warn
    }
})();