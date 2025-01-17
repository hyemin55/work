import { useState } from "react";
import "./App.css";

function App() {
  
  const doA = () => {
    return [
      10,
      () => {
        console.log("doA");
      },
    ];
  };
  
  const [bb,bbb] = doA()
  const [aa, setAA] = useState(10);
  
  const clickA = () => {
    console.log("push....");
    setAA(aa + 1);
  };

  console.log("랜더링")
  console.log(bb)
  console.log(bbb)
  return (
    <>
      <h1>깔깜</h1>
      <h2>{aa}</h2>
      <button onClick={clickA}>누르면 aa값 증가</button>
    </>
  );
}

export default App;
