<script setup>
import { ref } from 'vue'
import { Carousel, Pagination, Slide, Navigation } from 'vue3-carousel'
import home_1 from '@/assets/img/빵빵덕복숭아.png'
import home_2 from '@/assets/img/p_003.png'
import home_3 from '@/assets/img/빵빵덕세안.png'
import home_4 from '@/assets/img/빵빵덕토끼.png'

import 'vue3-carousel/dist/carousel.css'
import Queue from 'queue-fifo'

const slides = ref([home_1, home_2, home_3, home_4])

let queue = new Queue()
let image_list = []
// 1. img idx => q
for (let i = 0; i < slides.value.length; i++) {
  // console.log('ddd ' + i)
  queue.enqueue(i)
  image_list.push(slides.value[i])
}
// 2. 클릭 시 제일 앞으로
// 클릭 시 index 값을 받아오는 함수
const handleClick = index => {
  console.log('Clicked index:', index)
  // 클릭한 슬라이드의 index에 따른 로직 추가

  console.log('queuepeek', queue.peek())
  //
  if (queue.peek() == index) return
  while (queue.peek() != index) {
    queue.enqueue(queue.dequeue())
    console.log('queue : ' + queue.elements)
  }
  slides.value = []
  while (!queue.isEmpty()) {
    const index = queue.dequeue() // 큐에서 인덱스 값 추출
    console.log(index)
    if (index >= 0 && index < image_list.length) {
      // 유효한 인덱스인지 확인
      slides.value.push(image_list[index])
    } else {
      console.warn(`Invalid index: ${index}`)
    }
  }

  image_list = []
  for (let i = 0; i < slides.value.length; i++) {
    queue.enqueue(i)
    image_list.push(slides.value[i])
  }
}
</script>

<template>
  <div class="wrapper">
    <Carousel
      :items-to-show="3"
      :snapAlign="'start'"
      :wrap-around="true"
      :pause-autoplay-on-hover="true"
      :autoplay="0"
      :mouse-drag="false"
    >
      <Slide
        v-for="(slide, index) in slides"
        :key="index"
        class="Slidebox"
        @click="handleClick(index)"
      >
        <div class="carousel__item">
          <img
            :class="{
              firstslideImg: index === 0,
              firstslideImgOne: index === 1,
              firstslideImgTwo: index === 2,
            }"
            :src="slide"
          />
        </div>

        <div class="carousel__item_info">
          <p>브랜드명</p>
          <p>빵빵덕</p>
          <p>￦100,000</p>
        </div>
      </Slide>

      <template #addons>
        <!-- <Pagination /> -->
      </template>
      <Navigation />
    </Carousel>
  </div>
</template>

<style scoped>
.wrapper {
  padding: 10px;
  /* background-color: aquamarine; */
}

.Slidebox {
  padding: 20px;
  /* background-color: #5f5f5f; */
}

.carousel__item {
  /* background-color: rgb(194, 156, 105); */
  display: flex;
  align-items: flex-end;
  /* padding: 20px; */
  justify-content: center;
  position: relative;
  /* background-color: brown; */
  height: 500px;
  overflow: hidden;
  cursor: pointer;
  transition:
    opacity 0.3s ease,
    visibility 0s 0.3s;
}
.carousel__item_info {
  cursor: pointer;
  position: absolute;
  /* display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column; */
  bottom: 70px;
  font-size: 2.3rem;
  color: white;
  text-shadow: 2px 2px 5px #333;
  line-height: 1.5;
  /* height: 200px; */
  overflow: hidden;
  /* color: white; */
  transition:
    opacity 0.3s ease,
    visibility 0s 0.3s;
  visibility: hidden;
  opacity: 0;
}
.Slidebox:hover .carousel__item_info {
  opacity: 1; /* 호버 시 상세 정보 표시 */
  visibility: visible; /* 호버 시 보이도록 설정 */
  transition:
    opacity 0.3s ease,
    visibility 0s; /* 상세 정보가 바로 보이도록 설정 */
  /* background-color: aqua; */
}

.Slidebox:hover .carousel__item {
  transition:
    opacity 0.3s ease,
    visibility 0s; /* 상세 정보가 바로 보이도록 설정 */
  opacity: 0.5; /* 투명도 설정 (0은 완전 투명, 1은 불투명) */
  filter: saturate(0.5);
}
.Slidebox:hover .carousel__item > img {
  /* transition: opacity 0.3s ease, visibility 0s; 상세 정보가 바로 보이도록 설정 */
  background-color: rgba(0, 0, 0, 0.5);
}
.firstslideImg {
  width: 110%; /* 첫 번째 슬라이드 이미지의 너비 조정 */
  height: auto; /* 비율 유지 */
  background-color: var(--color-main-Lgray);
  padding: 3%;
  position: relative;
}

.firstslideImgOne {
  width: 70%; /* 나머지 슬라이드 이미지의 너비 조정 */
  height: auto; /* 비율 유지 */
  background-color: var(--color-main-Lgray);
  padding: 3%;
}
.firstslideImgTwo {
  width: 70%;
  background-color: var(--color-main-Lgray);
  padding: 3%;
}
</style>
