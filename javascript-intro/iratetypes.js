// Do some of the same type tricks as funwithtypes on Irate and IrateSub
// .load irate.js, load iratesub.js, then funwithtypes.js, then this
getAllMethods(Irate.prototype);
//[ 'constructor', 'computeYield', 'yieldRate' ]
getAllMethods(IrateSub.prototype);
//[ 'constructor', 'printMe' ]
Object.getOwnPropertyNames(ir);
//[ 'cap', 'rt', 'DEFAULT_RATE' ]
Object.getOwnPropertyNames(ir2);
//[ 'cap', 'rt', 'DEFAULT_RATE' ]
typeof ir;
//'object'
ir instanceof Irate
//true
ir2 instanceof IrateSub
//true
ir2 instanceof Irate
//true
ir instanceof IrateSub
//false
typeof ir2;
//'object'
