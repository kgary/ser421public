// Testing steps
let json1 = '{"tournament":{"name":"British Open","year":2001,"award":840000,"yardage":6905,"par":71,"round":1,"players":[{"lastname":"Montgomerie","firstinitial":"C","score":-3,"hole":17},{"lastname":"Fulke","firstinitial":"P","score":-5,"hole":"finished"},{"lastname":"Owen","firstinitial":"G","score":-1,"hole":"finished"},{"lastname":"Parnevik","firstinitial":"J","score":0,"hole":12},{"lastname":"Ogilvie","firstinitial":"J","score":-1,"hole":7},{"lastname":"Cejka","firstinitial":"A","score":-2,"hole":15},{"lastname":"Romero","firstinitial":"E","score":-4,"hole":11},{"lastname":"Fasth","firstinitial":"N","score":4,"hole":"finished"}]}}';

let json2 = '{"tournament":{"name":"British Open","year":2001,"award":840000,"yardage":6905,"par":71,"round":4,"players":[{"lastname":"Montgomerie","firstinitial":"C","score":-3,"hole":17},{"lastname":"Fulke","firstinitial":"P","score":-5,"hole":"finished"},{"lastname":"Owen","firstinitial":"G","score":-1,"hole":"finished"},{"lastname":"Parnevik","firstinitial":"J","score":0,"hole":17},{"lastname":"Ogilvie","firstinitial":"J","score":-1,"hole":17},{"lastname":"Cejka","firstinitial":"A","score":-2,"hole":17},{"lastname":"Romero","firstinitial":"E","score":-4,"hole":17},{"lastname":"Fasth","firstinitial":"N","score":4,"hole":"finished"}]}}';

let json3 = '{"tournament":{"name":"British Open","year":2001,"award":840000,"yardage":6905,"par":71,"round":4,"players":[{"lastname":"Montgomerie","firstinitial":"C","score":1,"hole":"finished"},{"lastname":"Fulke","firstinitial":"P","score":-2,"hole":"finished"},{"lastname":"Owen","firstinitial":"G","score":-1,"hole":"finished"},{"lastname":"Parnevik","firstinitial":"J","score":-3,"hole":"finished"},{"lastname":"Ogilvie","firstinitial":"J","score":1,"hole":"finished"},{"lastname":"Cejka","firstinitial":"A","score":-2,"hole":"finished"},{"lastname":"Romero","firstinitial":"E","score":0,"hole":"finished"},{"lastname":"Fasth","firstinitial":"N","score":-4,"hole":"finished"}]}}';

let json4 = '{"tournament":{"name":"British Open","year":2001,"award":840000,"yardage":6905,"par":71,"round":3,"players":[{"lastname":"Montgomerie","firstinitial":"C","score":-2,"hole":14},{"lastname":"Fulke","firstinitial":"P","score":3,"hole":"finished"},{"lastname":"Owen","firstinitial":"G","score":0,"hole":0},{"lastname":"Parnevik","firstinitial":"J","score":1,"hole":9},{"lastname":"Ogilvie","firstinitial":"J","score":-1,"hole":3},{"lastname":"Cejka","firstinitial":"A","score":2,"hole":17},{"lastname":"Romero","firstinitial":"E","score":-1,"hole":"finished"},{"lastname":"Fasth","firstinitial":"N","score":1,"hole":11}]}}';

let json5 = '{"tournament":{"name":"British Open","year":2001,"award":840000,"yardage":6905,"par":71,"round":2,"players":[{"lastname":"Montgomerie","firstinitial":"C","score":0,"hole":0},{"lastname":"Fulke","firstinitial":"P","score":0,"hole":0},{"lastname":"Owen","firstinitial":"G","score":0,"hole":0},{"lastname":"Parnevik","firstinitial":"J","score":0,"hole":0},{"lastname":"Ogilvie","firstinitial":"J","score":0,"hole":0},{"lastname":"Cejka","firstinitial":"A","score":0,"hole":0},{"lastname":"Romero","firstinitial":"E","score":-1,"hole":"finished"},{"lastname":"Fasth","firstinitial":"N","score":0,"hole":0}]}}';

// part a
// change the next line to use the json above you want to test
let tournamentInstance = new Tournament(json3);
console.log('tournament: ', JSON.stringify(tournamentInstance));
console.log("player: ", JSON.stringify(tournamentInstance.players[0]));

// part b
console.log('Leaderboard: ');
console.log(tournamentInstance.leaderboard());

// part c
console.log('projectScoreByIndividual, A.Cejka: ', tournamentInstance.projectScoreByIndividual("Cejka", "A"));

// part d
console.log('projectScoreByHole, A.Cejka: ', tournamentInstance.projectScoreByHole("Cejka", "A"));

// part e
console.log('ProjectedLeaderBoard - projectScoreByIndividual: ');
tournamentInstance.projectedLeaderBoard(tournamentInstance.projectScoreByIndividual);

// part f
tournamentInstance.printLeaderboard();

// part g
console.log('postScore:');
tournamentInstance.players[1].postScore(-1, tournamentInstance);
tournamentInstance.players[2].postScore(-2, tournamentInstance);
tournamentInstance.players[3].postScore(-3, tournamentInstance);
tournamentInstance.players[5].postScore(0, tournamentInstance);
tournamentInstance.players[6].postScore(1, tournamentInstance);

if (tournamentInstance.isTournamentComplete()) {
    console.log("The tournament is over!");
}

tournamentInstance.printLeaderboard();
