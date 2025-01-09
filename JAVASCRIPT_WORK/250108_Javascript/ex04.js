
const person = {
  age: 20,
  name: "홍길동",
  gender: "남자",
};

// 호이스팅 -> 변수와 함수가 선언되어있으면 먼저 정의 후 값을 넣기때문에
// 에러없이 undefined로 출력이 된다.
// var와 같다.
function fuc({ age, name, gender }) {
  console.log(`나이는 ${age}`);
  console.log(`이름은 ${name}`);
  console.log(`성별은 ${gender}`);
}

// 위와 같은 이유로 var를 사용했을때 데이터 오류가 생길 가능성이 높아
// let 또는 const 를 이후에 만들었고 이걸 사용하길 권장한다.
const fuc = ({ age, name, gender }) => {
  console.log(`나이는 ${age}`);
  console.log(`이름은 ${name}`);
  console.log(`성별은 ${gender}`);
};

fuc(person);
