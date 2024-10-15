<template>
  <div class="about">
    <button @click="handle">클릭하세요</button>
    <p v-if="con">클릭하셨어요 다시 클릭하면 없어져요</p>
    <p v-show="con">con값이 true이면 클릭했을때 보인다.</p>
    <p v-show="!con">con값이 false이면 바로 보인다.</p>
    <h1 class="h1message" v-cloak v-show="con">{{ message }}</h1>

    <table class="bor">
      <tbody>
        <tr>
          <td>tid</td>
          <td>tcolor</td>
          <td>ttext</td>
        </tr>
        <tr v-for="name in arr" :key="name.tid">
          <td>{{ name.tid }}</td>
          <td>{{ name.tcolor }}</td>
          <td>{{ name.ttext }}</td>
        </tr>
        <tr v-for="name in arr" :key="name.tid">
          <td>{{ name.tid }}</td>
          <td>{{ name.tcolor }}</td>
          <td>{{ name.ttext }}</td>
        </tr>
        <tr v-for="name in arr" :key="name.tid">
          <td>{{ name.tid }}</td>
          <td>{{ name.tcolor }}</td>
          <td>{{ name.ttext }}</td>
        </tr>
        <tr v-memo="[name2, gender, age]">
          <td>{{ name2 }}</td>
          <td>{{ gender }}</td>
          <td>{{ age }}</td>
        </tr>
        <tr>
          <td><button @click="name2 = '화요일'">이름변경</button></td>
          <td><button @click="gender = '여자'">성별변경</button></td>
          <td><button @click="incrementAge">나이변경</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const con = ref(false)
const message = ref('')

const arr = ref([{ tid: '1', tcolor: 'red', ttext: 'fjslfjklsflkjsd' }])

const handle = () => {
  alert('안녕')
  con.value = !con.value
  window.setTimeout(() => {
    message.value = '5초뒤에 보이기'
  }, 5000)
}

const name2 = ref('수요일')
const gender = ref('남자')
const age = ref(0)
const incrementAge=()=>{
  age.value += 1
}
</script>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
.about {
  display: table-column;
  align-content: center;
  justify-content: center;
}
[v-cloak] {
  display: none;
}
.bor {
  border: 1px solid rgb(95, 46, 10);
  text-align: center;
  border-collapse: collapse;
}
tr, td{
  border: 1px solid rgb(95, 46, 10);
  padding: 10px;
}
</style>
