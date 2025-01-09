setTimeout(()=>{
console.log("3초 뒤에 출력된다.")
},3000)

const aa = setInterval(()=>{
    console.log("2초마다")
},2000)

setTimeout(()=>{
    clearInterval(aa)
},8000)

console.log("출력됩니다.")