<script setup>
import { reactive } from 'vue';

const dropMenu = () => {};

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
      <p><i class="fi fi-rr-caret-down"></i></p>
    </div>
    <div class="input_check">
      <ul>
        <li v-for="(item, index) in filterInfo.content" :key="index">
          <input type="checkbox" id="" />
          <label for="">{{ item }}</label>
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
  cursor: pointer;
}
.title {
  background-color: bisque;
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1.7rem;
}
.title p {
  padding: 15px;
}
.input_check {
  width: 100%;
  height: auto;
}
.input_check ul {
  width: 100%;
  height: auto;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  font-size: 1.5rem;
  padding: 15px;
}
input[type='checkbox'] {
  margin-right: 5px;
}
</style>
