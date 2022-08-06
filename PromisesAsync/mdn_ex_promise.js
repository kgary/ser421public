 
  function doOperation() {
    doStep1(0, 1).then(function doStep2(v) {
        return v+2;
    }).then(function doStep3(v) {
        return v+3;
    })
  }
  function doStep1(init) {
    return new Promise(function (resolve) {
        resolve(init+1);
    });
  }

  doOperation();