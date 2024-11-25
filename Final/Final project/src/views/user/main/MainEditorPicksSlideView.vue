<script setup>
import { ref } from 'vue';
import home_1 from '@/assets/img/빵빵덕복숭아.png';
import home_2 from '@/assets/img/p_003.png';
import home_3 from '@/assets/img/빵빵덕세안.png';
import home_4 from '@/assets/img/빵빵덕토끼.png';
import home_5 from '@/assets/img/빵빵덕세안.png';

import Queue from 'queue-fifo';

const slides = ref([home_1, home_2, home_3, home_4, home_5]);

let queue = new Queue();
let image_list = [];
// 1. img idx => q
for (let i = 0; i < slides.value.length; i++) {
  // console.log('ddd ' + i)
  queue.enqueue(i);
  image_list.push(slides.value[i]);
}
// 2. 클릭 시 제일 앞으로
// 클릭 시 index 값을 받아오는 함수
const handleClick = index => {
  0.0;
  console.log('Clicked index:', index);
  // 클릭한 슬라이드의 index에 따른 로직 추가

  console.log('queuepeek', queue.peek());
  //
  if (queue.peek() == index) return;
  while (queue.peek() != index) {
    queue.enqueue(queue.dequeue());
    console.log('queue : ' + queue.elements);
  }
  slides.value = [];
  while (!queue.isEmpty()) {
    const index = queue.dequeue(); // 큐에서 인덱스 값 추출
    console.log(index);
    if (index >= 0 && index < image_list.length) {
      // 유효한 인덱스인지 확인
      slides.value.push(image_list[index]);
    } else {
      console.warn(`Invalid index: ${index}`);
    }
  }

  image_list = [];
  for (let i = 0; i < slides.value.length; i++) {
    queue.enqueue(i);
    image_list.push(slides.value[i]);
  }
};
</script>

<template>
  <div class="wrapper">
    <div
      v-for="(slide, index) in slides"
      :key="index"
      class="Slidebox"
      @click="handleClick(index)"
      :class="{
        firstSlideImg: index === 0,
        secondSlideImg: index === 1,
        thirdSlideImg: index === 2,
        otherSlideImg: index >= 3,
      }"
    >
      <img :src="slide" />
      <div class="slides_info">
        <p>브랜드명</p>
        <p>빵빵덕</p>
        <p>￦100,000</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.wrapper {
  display: flex;
  align-items: flex-end;
  justify-content: flex-start;
  width: 150%;
  height: auto;
  margin-top: 40px;
  gap: 1.5%; /* 슬라이드 간격 */
  /* background-color: rgb(121, 121, 121); */
}
.Slidebox {
  position: relative;
  cursor: pointer;
  object-fit: cover;
  transition:
    opacity 0.3s ease,
    visibility 0s 0.3s;
}
.Slidebox > img {
  width: 100%;
  height: auto;
}
.firstSlideImg {
  width: 30%;
  height: auto;
  padding: 1%;
  background-color: var(--color-main-Lgray);
  font-size: 3.5rem;
}
.secondSlideImg,
.thirdSlideImg {
  width: 18%;
  height: auto;
  background-color: var(--color-main-Lgray);
  padding: 1%;
  font-size: 2.5rem;
}
.otherSlideImg {
  width: 20%;
  padding: 1%;
  display: none;
}
.slides_info {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  color: white;
  text-shadow: 2px 2px 5px #333;
  line-height: 1.5;

  transition: all 0.3s ease;
  visibility: hidden;
  opacity: 0;
}
.slides_info p {
  transition: all 0.3s ease;
  transform: translateY(20px);
}

.Slidebox:hover .slides_info p {
  transform: translateY(0px);
}
.Slidebox:hover .slides_info {
  opacity: 1;
  visibility: visible;
}
.Slidebox:hover img {
  filter: saturate(0.5);
}
</style>
