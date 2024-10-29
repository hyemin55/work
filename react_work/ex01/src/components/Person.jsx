// const Person = (props) => {
//     return (
//         <div>
//             <h1>Person</h1>
//             <p>This is a person component.</p>
//             <h2>이름은 = {props.name}</h2>
//             <h2>나이는 = {props.age}</h2>
//         </div>
//      );
// }

import { useState } from "react";

// export default Person;

// 위 아래 두가지 방법이 있다.
// 위는 Person 전체를 받는거고 아래는 부분적으로 받을 수 있다.

const Person = ({ name:personName, age:personAge }) => {
    const [name, setName] = useState(personName);
    const [age, setAge] = useState(personAge);
    
  const doName = () => {
    setName(name+"!");
    // name = "null";
  };
  const doAge = () => {
    setAge(age+1);
    // age = age + 1;
  };
  return (
    <div>
      <h1>Person</h1>
      <p>This is a person component.</p>
      <h2>이름은 = {name}</h2>
      <h2>나이는 = {age}</h2>
      <button onClick={doName}>이름증가</button>
      <button onClick={doAge}>나이증가</button>
    </div>
  );
};

export default Person;
