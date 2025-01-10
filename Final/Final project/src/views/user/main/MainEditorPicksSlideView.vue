<script setup>
import { onMounted, ref } from 'vue';
import Queue from 'queue-fifo';
import router from '@/router';
import { getEditorPicks } from '@/api/mainApi';
import { GLOBAL_URL } from '@/api/util';

const slides = ref([]);

let queue = new Queue();
let image_list = [];
// 2. 클릭 시 제일 앞으로
// 클릭 시 index 값을 받아오는 함수
const handleClick = index => {
  0.0;
  // console.log('Clicked index:', index);
  // 클릭한 슬라이드의 index에 따른 로직 추가

  // console.log('queuepeek: ', queue.peek());
  let maxIterations = queue.size();
  if (queue.peek() == index) return;
  while (queue.peek() != index && maxIterations > 0) {
    queue.enqueue(queue.dequeue());
    maxIterations--;
    // console.log('queue : ' + queue.elements);
  }
  if (maxIterations === 0) {
    console.warn('Infinite loop detected. Exiting...');
    return; // 무한 루프 방지
  }
  slides.value = [];
  while (!queue.isEmpty()) {
    const index = queue.dequeue(); // 큐에서 인덱스 값 추출
    // console.log(index);
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

// 자세히보기를 클릭했을 때 넘겨줄 idx와 size이다.
const navDetailProduct = (productId, size) => {
  // console.log(productId);
  router.push({
    path: `/masonry/${productId}`,
  });
};

// 렌더링 시 한번 실행
const dolode = async () => {
  const pickRes = await getEditorPicks();
  slides.value = pickRes;
  // console.log(slides.value);
  // 1. img idx => q
  for (let i = 0; i < slides.value.length; i++) {
    // console.log('ddd ' + i)
    queue.enqueue(i);
    image_list.push(slides.value[i]);
  }
};

onMounted(() => {
  dolode();
});
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
      <img :src="`${GLOBAL_URL}/api/file/download/${slide.fileName}`" />
      <div class="slides_info">
        <p>{{ slide.brandName }}</p>
        <p>{{ slide.productName }}</p>
        <p>￦ {{ slide.minPrice.toLocaleString() }} ~ {{ slide.maxPrice.toLocaleString() }}</p>
        <p>재고 : {{ slide.usedProductCount.toLocaleString() }}</p>
      </div>
      <div v-if="index === 0" class="extra_content">
        <p>{{ slide.productName }}ㆍ{{ slide.size }} ml</p>
        <p>{{ slide.content }}</p>
        <p @click="navDetailProduct(slide.productId, slide.size)">자세히 보러가기 →</p>
        <!-- <p @click="navDetailProduct(slide)">자세히 보러가기 →</p> -->
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
  margin-top: 60px;
  gap: 3%; /* 슬라이드 간격 */
  /* background-color: rgb(121, 121, 121); */
}
.Slidebox {
  position: relative;
  object-fit: cover;

  background-color: var(--color-main-Lgray);
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
  font-size: 3.5rem;
}
.secondSlideImg,
.thirdSlideImg {
  width: 18%;
  height: auto;
  padding: 1%;
  font-size: 2.5rem;
  cursor: pointer;
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
  padding: 1%;
  text-align: center;
  /* text-shadow: 2px 2px 5px #333; */
  line-height: 1.5;
  transition: all 0.3s ease;
  visibility: hidden;
  opacity: 0;
}
.slides_info p {
  transform: translateY(50px);
  /* transition: all 0.3s ease; */
}
.slides_info p:nth-child(1) {
  font-family: var(--font-JacquesFrancois);
  transition: all 0.3s ease;
}
.slides_info p:nth-child(2) {
  border-bottom: 1px solid white;
  transition: all 0.3s ease;
  font-size: 2rem;
}
.slides_info p:nth-child(3) {
  line-height: 300%;
  transition: all 0.6s ease;
}
.Slidebox:hover .slides_info p {
  transform: translateY(30px);
}
.Slidebox:hover .slides_info {
  background-color: rgba(48, 48, 48, 0.7);
  opacity: 1;
  visibility: visible;
}
.Slidebox:hover img {
  filter: saturate(0.5);
}
.extra_content {
  position: absolute;
  /* background-color: antiquewhite; */
  height: auto;
  max-height: 30%;
  line-height: 2.3rem;
  width: 116%;
  top: 0;
  right: 0;
  cursor: default;
  transform: translateX(105%);
  /* font-family: 'Pretendard-Thin'; */
}

.extra_content p:nth-child(1) {
  font-size: 1.8rem;
  font-family: 'Pretendard-Bold';
  border-bottom: 0.5px solid #333;
}
.extra_content p:nth-child(2) {
  margin-top: 10px;
  margin-bottom: 20px;
  font-size: 1.8rem;
}
.extra_content p:nth-child(3) {
  cursor: pointer;
  font-size: 1.6rem;
  font-family: 'Pretendard-SemiBold';
  color: var(--color-main-bloode);
}

.firstSlideImg:hover .extra_content p {
  text-shadow: 2px 2px 1px rgba(0, 0, 0, 0.3);
  transition: all 0.3s ease-in-out;
}
@media (max-width: 630px) {
  .slides_info {
    padding: 3%;
    line-height: 1.3;
  }
  .extra_content p:nth-child(1),
  .extra_content p:nth-child(2) {
    display: none;
  }
  .firstSlideImg {
    font-size: 1.8rem;
  }
  .secondSlideImg,
  .thirdSlideImg {
    font-size: 1.2rem;
  }
  .slides_info p:nth-child(2) {
    font-size: 1.4rem;
  }
  .Slidebox:hover .slides_info p {
    transform: translateY(15px);
  }
}
</style>
