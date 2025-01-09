const a = [1,2,3,4]

const [el1, el2, el3, el4, el5 = 10, el6 = null, el7] = a

console.log(`el1 = ${el1}`)
console.log(`el2 = ${el2}`)
console.log(`el3 = ${el3}`)
console.log(`el4 = ${el4}`)
console.log(`el5 = ${el5}`)
console.log(`el6 = ${el6}`)
console.log(`el7 = ${el7}`)

function aa([a,b,c]){
    console.log(`a = ${a}`)
    console.log(`b = ${b}`)
    console.log(`c = ${c}`)
}

aa([10,a,el3])