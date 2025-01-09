const doA = () => {
  const promis = new Promise((resolve, reject) => {
    setTimeout(() => {
      // resolve("잘됨");
      reject("못됨");
    }, 3000);
});
return promis;
};


doA()
  .then((result) => {
    console.log(result);
  })
  .catch((error) => {
    console.log(error);
  });
