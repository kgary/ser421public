"use strict";

function Yield(max,rate) {
        this.max = max;
        this.rate = rate;
        this.DEFAULT_RATE = 0.05;

    this.computeYield = function(years, rate) {
        return this.max * this.yieldRate(years, rate);
    };

    this.yieldRate = function(years, rt) {
	//console.log("Called yieldRate with " + years + " and " + rt + " and rate " + rate);
	//if (this.rate === rate) console.log("this.rate and rate are the same!");
        if (years <= 2) {
            return 2 + rate;
        } else {
            let rate = this.yieldRate(years - 1, rt);
            let crate = rate * rt;
            return crate;
        }
    };
}

let y = new Yield(5000, 0.25);
console.log(y.constructor.name);

console.log("yield: " + Math.round(y.computeYield(4, 0.15)));
console.log("yield rate: " + Math.round(y.yieldRate(4, 1.5)));
