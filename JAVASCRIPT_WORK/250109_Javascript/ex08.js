const getData = async () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      //   resolve({
      //     name: "홍길동",
      //     age: 20,
      //     gender: "남자",
      //   });
      reject({
        error: "에러에러!",
        code: 500,
      });
    }, 3000);
  });
};

const printData = async () => {
  // await과 then 두가지중 아무거나 사용해도 된다.
  try {
    const res = await getData();
    console.log(res);
  } catch (e) {
    console.log(e);
    console.error("통신실패");
  }
  //   getData().then((result) => {
  //     console.log(result);
  //   });
};

printData();
