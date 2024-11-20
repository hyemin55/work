<template>
  <div>
    <h1>안녕하세요</h1>
    <h2>카운트: {{ count }}</h2>
    <button @click="count++">카운트 증가</button>
    <br />
    <br />
    <div>
      refMessage = {{ refMessage }}<br />
      noRefMessage = {{ state.noRefMessage }}<br />
      <button @click="onRefMessage">ref Message</button><br />
      <button @click="NoOnRefMessage">no ref Message</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'

const count = ref(0)
const refMessage = ref('hello i am refMessage')
let noRefMessage = 'hello i am noRefmessage'
const onRefMessage = ref(null)

onRefMessage.value = () => {
  refMessage.value = refMessage.value + '!'
}

const state = reactive({ noRefMessage: noRefMessage })
const NoOnRefMessage = () =>{
    state.noRefMessage += '!';
}

onMounted(() => {
  console.log('마운트 됨')
})
</script>

<style lang="scss" scoped></style>
