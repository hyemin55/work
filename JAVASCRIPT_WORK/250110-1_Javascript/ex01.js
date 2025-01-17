//   "type": "commonjs" 일 때
// require('dotenv').config();

// import dotenv from "dotenv";

//   "type": "module" 일 때
import { config } from "dotenv";
config();
// console.log(dotenv);

// console.log("node");
// node <->
console.log(process.env.AA)
