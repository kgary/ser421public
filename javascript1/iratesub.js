// you have to .load irate.js first
class IrateSub extends Irate {
  constructor(c, r) { 
     super(c, r); 
  }
  printMe(y,r) { 
     console.log("Yield is "+this.computeYield(y,r)+" Yield rate is "+this.yieldRate(y,r)); 
  } 
}
var  ir2 = new IrateSub(1000, 0.1);
// IrateSub { cap: 1000, rt: 0.1, DEFAULT_RATE: 0.1 }
ir2.printMe(4, 0.1);
// Yield is 1464.1000000000001, Yield rate is 1.4641000000000002
