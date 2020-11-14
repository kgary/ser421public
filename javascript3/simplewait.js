function wait(ms) {
  return new Promise(resolve => setTimeout(() => resolve(), ms));
}

async function test(ms) {
  for (let i = 0; i < 5; ++i) {
    await wait(ms);
    console.log(ms * (i + 1));
  }
}

// These two function calls will actually run in parallel
test(70);
test(130);
