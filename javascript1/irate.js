"use strict";

class Irate {

    constructor(cap, rt) {
        this.cap = cap;
        this.rt = rt;
        this.DEFAULT_RATE = 0.1;
    }

    computeYield(years, rate) {
        return this.cap * this.yieldRate(years, rate);
    }

    yieldRate(years, rate) {
        if (years <= 1) {
            return 1 + rate;
        } else {
            let rt = this.yieldRate(years - 1, rate);
            let crate = rt + rt * rate;
            return crate;
        }
    }

}

let ir = new Irate(10000, 0.1);
console.log(ir.constructor.name);

console.log("yield: " + ir.computeYield(4, 0.1));
console.log("yield rate: " + ir.yieldRate(4, 0.1));
