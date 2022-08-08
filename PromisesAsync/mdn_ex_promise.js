  function doOperation() {
    doStep1(0).then(function doStep2(v) {
        console.log("In DS2" + (v+2));
        return v+2;
    }).then(function doStep3(v) {
        console.log("In DS3 " + (v+3));
        return v+3;
    }).then((v) => console.log(v));
  }
  function doStep1(init) {
    return new Promise(function (resolve) {
        resolve(init+1);
    });
  }

doOperation();