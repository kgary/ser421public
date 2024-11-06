"use strict";

function Irate(cap,rt) {
    this.cap = cap;
    this.rt = rt;
    this.DEFAULT_RATE = 0.1;

    this.computeYield = function(years, rate) {
        return this.cap * this.yieldRate(years, rate);
    };
    this.yieldRate = function(years, rate) {
        if (years <= 1) {
            return 1 + rate;
        } else {
            let rt = this.yieldRate(years - 1, rate);
            let crate = rt + rt * rate;
            return crate;
        }
    };
}

let ir3 = new Irate(10000, 0.1);
console.log(ir3.constructor.name);

console.log("yield: " + ir3.computeYield(4, 0.1));
console.log("yield rate: " + ir3.yieldRate(4, 0.1));
