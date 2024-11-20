<template>
  <div>
    <h1>{{ myComputed }}</h1>
    <h1>{{ myNoComputed() }}</h1>
    <h1>
      {{ myChangeData }}
    </h1>
    <button @click="reloadPage">새로고침</button>
    <h1>fullName = {{ fullName }}</h1>
    <h1>fullNameMethod = {{ fullNameMethod() }}</h1>
    <button @click="change">바꾸기</button>
    <h1>getSetFullName = {{ getSetFullName }}</h1>
  </div>
  <div>
    <h1><h1 ref="inputRef">Hello, ref DOM 반응</h1></h1>
  </div>
</template>

<script setup>
import { reactive, computed, ref } from 'vue'

const myComputed = computed(() => {
  console.log('호출 myComputed')
  return 'myComputed'
})
const myNoComputed = () => {
  console.log('호출 myNoComputed')
  return 'myNoComputed'
}

const myChangeData = ref('myChangeData')

const reloadPage = () => {
  myChangeData.value = myChangeData.value + '!'
}

const 홍길동 = reactive({
  firstName: '홍',
  lastName: '길동'
})

const 박길동 = reactive({
  firstName: '박',
  lastName: '길동'
})

const fullName = computed(() => {
  console.log('fullName 호출')
  return 홍길동.firstName + ' ' + 홍길동.lastName
})

const fullNameMethod = () => {
  console.log('fullNameMethod')
  return 홍길동.firstName + ' ' + 홍길동.lastName
}

const getSetFullName = computed({
  get() {
    console.log('get 함수 호출')
    return 박길동.firstName + ' ' + 박길동.lastName
  },
  set(value) {
    console.log('set 함수 호출')
    ;[박길동.firstName, 박길동.lastName] = value.split(' ')
    박길동.firstName = 박길동.firstName + '!'
  }
})

const change = () => {
  //홍길동 바꾸기
  fullName.value = fullName.value + '!'
  //박길동 바꾸기
  getSetFullName.value = getSetFullName.value + '!'
  console.log('getSetFullName.value' + getSetFullName.value)
}

const inputRef = ref(null);
setTimeout(()=>{
    console.log("2초뒤에 콘솔에 출력된다.")
    console.log(inputRef.value)
    console.log(inputRef.value.innerText)
}, 2000)
</script>

<style lang="scss" scoped></style>
