<script setup>
import { reactive, ref } from 'vue';

const render = ref(true)
const close = ref(true)
const dropMenu = () => {
  render.value = !render.value
  close.value = !close.value
};

const props = defineProps({
  filterInfo: {
    type: Object,
    required: true,
    default: () => ({
      title: '필터',
      content: [],
    }),
  },
});
const filterInfo = reactive(props.filterInfo);
</script>

<template>
  <div class="filter_box">
    <div class="title" @click="dropMenu">
      <p>{{ filterInfo.title }}</p>
      <p>
        <img v-if="close" src="/src/assets/img/free-icon-font-minus.svg" alt="">
        <img v-else src="/src/assets/img/free-icon-font-plus.svg" alt="">
      </p>
    </div>
    <div class="input_check">
      <ul :class="{'display': render, 'nonDisplay': !render}">
        <li v-for="(item, index) in filterInfo.content" :key="item.index">
          <input type="checkbox" :id="item" />
          <label :for="item">{{ item }}</label>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
@import url('https://cdn-uicons.flaticon.com/2.6.0/uicons-regular-rounded/css/uicons-regular-rounded.css');
.filter_box {
  width: 100%;
  height: auto;
  position: relative;
}
.title {
  /* background-color: var(--color-main-pink); */
  background-color: rgb(147, 33, 33);
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1.7rem;
  cursor: pointer;
  border-bottom: 1px solid #cccccc;
}
.title p {
  color: #eeeeee;
  padding: 15px;
}
.title p img{
  width: 17px;
}
.input_check {
  width: 100%;
  height: auto;
}
.input_check ul {
  width: 100%;
  height: auto;
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  gap: 20px;
  font-size: 1.5rem;
  background-color: #fcfbfb;
  box-shadow: 0px 2px 2px inset #dbdbdb;
}
.input_check ul li{
  margin-left: 5px;
  align-items: center;
}
input[type='checkbox'] {
  margin-right: 5px;
}
.input_check li label{
  cursor: pointer;
}
.input_check li label:hover{
  color: var(--color-main-bloode);
}

/* 필터 여닫기 */
.display{padding: 15px;}
.display li{display: flex;} 
.nonDisplay{padding: none;}
.nonDisplay li{display: none;}

/* checkBox 커스텀 */
input[type="checkbox"] {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  width: 20px;
  height: 20px;
  border: 2px solid #ccc;
  border-radius: 50%;
  position: relative;
  cursor: pointer;
  background-color: #fcfbfb;
  transition: background-color 0.2s, border-color 0.2s;
}
input[type="checkbox"]:checked {
  background-color: var(--color-main-bloode); 
  border-color: var(--color-main-bloode); 
}
input[type="checkbox"]:checked::before {
  content: "●";
  font-size: 10.5px;
  color: #fcfbfb;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
