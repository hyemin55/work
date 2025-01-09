// 배열 map => 반환되는 값이 있는 경우, forEach => 반환되는 값이 없는 경우

const arr1 = [1, 2, 3];

// 바로 리턴을 할 땐, 중괄호 생략가능하다.
const mapArr1 = arr1.map((item) => item * 3);

// 여러줄을 출력해야할 땐 중괄호를 넣어야한다.
// const mapArr1 = arr1.map((item) => {
//     return item * 3;
//   });

const foreachArr1 = arr1.forEach((item) => {
  item * 5;
});

console.log("mapArr1 = ", mapArr1);
console.log("foreachArr1 = ", foreachArr1);
