<script setup>
import { ref } from 'vue'
import { Carousel, Pagination, Slide, Navigation } from 'vue3-carousel'
import home_1 from '@/img/editor_front_img.png'
import home_2 from '@/img/p_003.png'
import home_3 from '@/img/빵빵덕세안.png'
import home_4 from '@/img/빵빵덕세안.png'

import 'vue3-carousel/dist/carousel.css'
import Queue from 'queue-fifo'

const slides = ref([home_1, home_2, home_3,home_4])

let queue = new Queue()
let image_list = []
// 1. img idx => q
for (let i = 0; i < slides.value.length; i++) {
  console.log('ddd ' + i)
  queue.enqueue(i)
  image_list.push(slides.value[i])
}
// 2. 클릭 시 제일 앞으로
// 클릭 시 index 값을 받아오는 함수
const handleClick = index => {
  console.log('Clicked index:', index)
  // 클릭한 슬라이드의 index에 따른 로직 추가

  console.log(queue.peek())
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
          <img :class="{firstslideImg: index === 0, dfdfdf : index === 1, dfdfdff : index === 2, dfdfdfff : index === 3 }" :src="slide"></img>
          <!-- <img class="firstslideImg" :src="slide" /> -->
          <!-- <img :class="{lideImg: index === 2}" :src="slide" /> -->
        </div>
      </Slide>

      <template #addons>
        <Pagination />
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
  padding: 10px;
  /* background-color: #5f5f5f; */
  
}

.carousel__item {
  /* background-color: rgb(194, 156, 105); */
  display: flex;
  align-items: flex-end;
  padding: 20px;
  /* justify-content: space-around; */
  /* position: relative;

  /* background-color: brown; */
  height: 500px;
}

.carousel__item > p {
  position: absolute;
  bottom: 10px; /* 바닥에서의 거리 설정 */
  left: 10px; /* 왼쪽에서의 거리 설정 */
  color: white; /* 글자 색상 설정 */
  text-align: left;
  font-size: 2.3rem;
  /* text-shadow: 1px 1px 2px #5f5f5f; */
}

.firstslideImg {
  width: 400px; /* 첫 번째 슬라이드 이미지의 너비 조정 */
  height: auto; /* 비율 유지 */
}

.dfdfdf  {
  width: 250px; /* 나머지 슬라이드 이미지의 너비 조정 */
  height: auto; /* 비율 유지 */
  /* background-color: black; */
}
.dfdfdff{
  width: 250px ;
}
.dfdfdfff{
  
  width: 250px ;
}
</style>