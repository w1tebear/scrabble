"use strict"

var game = (function() {
    var myName = null;

    function newGame() {
        myName = prompt("Please enter your name:", "");
        if (newGame == null || newGame == "") {
            alert("Your name is required to start a new game.");
        } else {
            alert("Starting new Scrabble game!!");
            ws.sendOutgoingMessage(ws.crOutgoingMessage("INIT", { name: myName }));
        }
    }
    
    return {
        newGame: newGame
    }
})();

