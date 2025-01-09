const aa = {
  a: 10,
  b: "b는 20입니다.",
  c: 30,
};
const { a, b, c } = aa;

console.log(`a = ${a}`);
console.log(`b = ${b}`);
console.log(`c = ${c}`);
console.log(aa)

console.log(JSON.stringify(aa))
const aaa = JSON.stringify(aa)
console.log(JSON.parse(aaa))

// fetch는 react, typescript, next.js를 사용할 때 
// axios는 사용할 수 없기에 fetch를 사용한다.