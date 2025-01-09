const arr1 = [
  { name: "홍길동", age: 20 },
  { name: "정길동", age: 15 },
  { name: "김길동", age: 30 },
];

arr1.forEach((obj) => {
  console.log(obj);
});

console.log("\n");
const arr2 = [...arr1, { name: "박길동", age: 40 }];
console.log(arr2);

const findObj = arr1.find((obj) => {
  if (obj.name == "홍길동" && obj.age === 20) {
    return obj;
  }
});

console.log("findObj = ", findObj);

const arr3 = [1, 2, 3, 4];
const findIndex = arr3.findIndex((index) => {
  return index === 2;
});

console.log("findIndex", findIndex);

const findIndex2 = arr1.findIndex((value) => {
  return value.name === "홍길동";
});
console.log("findIndex", findIndex);
