console.log("ㅋ.ㅋ");

// 1 false 값
const f1 = undefined;
const f2 = null;
const f3 = 0;
const f4 = -0;
const f5 = NaN;
const f6 = "";

const result = f2 || "result";
console.log("result = ", result);

class A {
  a;
  constructor(a) {
    this.a = a;
  }
}

// 2 truthy 값
const t1 = "result";
const t2 = 123;
const t3 = [];
const t4 = {};
const t5 = () => {};
const t6 = new A(20);
const t7 = { a: 20 };

const result2 = t1 && "result2222";
console.log("result2 = ", result2);
console.log("t6 = ", t6);
console.log("t7 = ", t7);

function printName(test) {
  if (test) {
    console.log(`${test} 출력된다`);
  } else {
    console.log(`${test} 출력안된다`);
  }
}
printName(t7);

function aa(a) {
  if (a) {
    console.log(a);
  }
}
aa({ a: 10, b: 20 });
