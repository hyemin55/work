
function aa(callback) {
  setTimeout(() => {
    console.log("aa콜백함수 호출합니다.");
    callback();
  }, 3000);
}

function bb(callback) {
  setTimeout(() => {
    console.log("bb콜백*2함수 호출합니다.");
    callback();
  }, 4000);
}

function cc(callback) {
  setTimeout(() => {
    console.log("cc콜백*3함수 호출합니다.");
    callback();
  }, 6000);
}

aa(() => {
  console.log("aaa");
  bb(() => {
    console.log("bbb");
    cc(() => {
      console.log("ccc");
    });
  });
});
