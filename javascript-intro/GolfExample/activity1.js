'use strict';

// part a
// Tournament Type
function Tournament(jsonString){
    let jsonObject = JSON.parse(jsonString);

    this.name = jsonObject.tournament.name;
    this.year = jsonObject.tournament.year;
    this.award = jsonObject.tournament.award;
    this.yardage = jsonObject.tournament.yardage;
    this.par = jsonObject.tournament.par;
    this.round = jsonObject.tournament.round;
    this.players = [];

    // populating the players array
    for (let i=0; i< jsonObject.tournament.players.length; i++) {
        this.players.push(new Player(jsonObject.tournament.players[i].lastname,
            jsonObject.tournament.players[i].firstinitial, jsonObject.tournament.players[i].score,
            jsonObject.tournament.players[i].hole));
    }

    this.isTournamentComplete();
}

// Player Type
function Player (lastName, firstInitial, score, hole){
    this.lastname = lastName;
    this.firstinitial = firstInitial;
    this.score = score;
    this.hole = hole;
}

Player.prototype.getHole = function(){
    if (this.hole === "finished"){
        return 18;
    }

    return this.hole;
};

// part b
// function returns the tournament object with players sorted
Tournament.prototype.leaderboard = function(){
    // sort the players based on the lowest score first and then  by hole (later holes first)
    return JSON.stringify(this.sortPlayersByScore());
};

// part c
Tournament.prototype.projectScoreByIndividual = function(lastName, firstInitial){
    let totalHoles = 18;
    let foundPlayer = this.searchPlayer(lastName, firstInitial);
    let projectedScore = null;

    if (foundPlayer) {
        let scoreAsPar = (foundPlayer.score / foundPlayer.getHole()) * (totalHoles - foundPlayer.getHole()) +
            foundPlayer.score;
        projectedScore = Math.round(scoreAsPar);
    } else {
        console.log('No Player with name - '+ lastName + ', ' + firstInitial + 'was found');
    }

    return projectedScore;
};

// part d
Tournament.prototype.projectScoreByHole = function(lastName, firstInitial){
    let projectedScoreByHole = null;

    // get the average score per hole of all the players in the tournament
    let collectiveRateOfProgress = this.getAverageScorePerHole(lastName, firstInitial);
    //console.log("collectiveRateOfProgress: ", collectiveRateOfProgress);
    let foundPlayer = this.searchPlayer(lastName, firstInitial);

    if (foundPlayer) {
        let totalHoles = 18;

        let value = foundPlayer.score + ((totalHoles - foundPlayer.getHole()) * collectiveRateOfProgress);
        projectedScoreByHole = Math.round(value);
    } else {
        console.log('No Player with name - '+ lastName + ', ' + firstInitial + 'was found');
    }

    return projectedScoreByHole;
};

// part e
Tournament.prototype.projectedLeaderBoard = function(projectedScoreByXXX){
    return JSON.stringify(this.sortPlayersByScore(projectedScoreByXXX.bind(this), true));
};

// part f
Tournament.prototype.printLeaderboard = function(){
    let sortedList = this.sortPlayersByScore();

    console.log('---  LEADERBOARD  ---');
    console.log('Player | Score | Hole');
    console.log('_____________________');

    sortedList.forEach(function(player){
        console.log(player.firstinitial + "." + player.lastname + ", Score: " + player.score +
            ", hole: "+ player.hole);
    });

    console.log('_____________________');
};

// part g
Player.prototype.postScore = function(s,t){
    // update the score properties
    this.score += s;

    // update the hole properties
    if (this.hole < 17) {
        this.hole += 1;
    } else if (this.hole === 17){
        this.hole = "finished";
    }
    return t.isTournamentComplete();
};

// part g
Tournament.prototype.isTournamentComplete = function(){
    let allPlayersRoundFinished = this.players.every(function(player){
        return player.hole === "finished";
    });

    return this.round === 4 && allPlayersRoundFinished;
}

// utilities function
Tournament.prototype.sortPlayersByScore = function(scoringFunction, isProjecting){
    let sortedPlayers = [];

    if (isProjecting === undefined) {
        isProjecting = false;
    }

    if (!scoringFunction) {
        scoringFunction = (function(lastname, firstinitial){
            let foundPlayer = this.searchPlayer(lastname, firstinitial);

            return foundPlayer.score;
        }).bind(this);
    }

    if (isProjecting){
        for (let i =0; i< this.players.length; i++) {
            let playerInstanceCopy = new Player(this.players[i].lastname, this.players[i].firstinitial,
                this.players[i].score, this.players[i].hole);

            playerInstanceCopy.score = scoringFunction(playerInstanceCopy.lastname, playerInstanceCopy.firstinitial);
            sortedPlayers.push(playerInstanceCopy);
        }
    } else {
        sortedPlayers = Array.from(this.players);
    }

    sortedPlayers.sort(function(a, b){
        a.score = scoringFunction(a.lastname, a.firstinitial);
        b.score = scoringFunction(b.lastname, b.firstinitial);

        if (a.score === b.score) {
            return b.getHole() - a.getHole();
        }

        return a.score - b.score;
    });

    return sortedPlayers;
};

Tournament.prototype.searchPlayer = function(lastName, firstInitial){
    // function which searches player with given lastName and firstInitial,
    // please note that the first occurrence will be returned, in case of multiple copies
    let found = false;
    let foundPlayer = null;

    for (let i=0; i<this.players.length; i++){
        if (!found){
            if (this.players[i].lastname.toLowerCase() === lastName.toLowerCase()
                && this.players[i].firstinitial.toLowerCase() === firstInitial.toLowerCase()) {

                foundPlayer = this.players[i];
                found = true;
            }
        }
    }

    return foundPlayer;
};

Tournament.prototype.getAverageScorePerHole = function(lastName, firstInitial){
    // calculate the average score per hole all the players in the tournament,
    // we need to account the fact that we would not include the current player
    let totalSum = 0;

    for (let i=0; i< this.players.length; i++) {
        // account all the players except for the passed player's information in function parameters
        if (this.players[i].lastname.toLowerCase() !== lastName.toLowerCase()
            && this.players[i].firstinitial.toLowerCase() !== firstInitial.toLowerCase()) {

            let totalHolesPlayed = 18 * this.round + this.players[i].getHole();
            totalSum += this.players[i].score / totalHolesPlayed;
        }
    }

    return parseFloat(totalSum/(this.players.length - 1)).toFixed(2);
};
