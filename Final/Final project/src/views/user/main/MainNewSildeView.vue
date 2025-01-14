<!-- 슬라이드 공식 사이트 = https://ismail9k.github.io/vue3-carousel/getting-started.html -->
<script setup>
import { Carousel, Slide, Navigation, Icon, Pagination } from 'vue3-carousel';
import { ref, watchEffect } from 'vue';
import 'vue3-carousel/dist/carousel.css';
import { GLOBAL_URL } from '@/api/util';
import router from '@/router';
import { getNewSildes } from '@/api/mainApi';

const slides = ref([]);
const pageNum = 0;
const size = 10;

//  '/category/:title/:idx'이거 받아오기!!!!!!!

const getNewList = async () => {
  try {
    const newSildesRes = await getNewSildes();
    slides.value = newSildesRes;
  } catch (e) {
    console.log('리스트 못 받아오는 오류에요 = ' + e);
  }
};

watchEffect(() => {
  getNewList();
});
const navDetailProduct = usedProductId => {
  router.push({
    path: `/productsdetail/${usedProductId}`,
  });
};

// getNewList()
</script>

<template>
  <div class="wrapper">
    <Carousel
      :items-to-show="3.5"
      :autoplay="2000"
      :snapAlign="'center'"
      :wrap-around="true"
      :pause-autoplay-on-hover="true"
      :mouseDrag="false"
    >
      <Slide v-for="(slide, index) in slides" :key="index">
        <!-- <div v-for="productDtail in New_list" :key="productDtail.productId"> -->
        <div class="carousel__item" @click="navDetailProduct(slide.usedProductId)">
          <p class="time_check">{{ slide.registerDate }} 등록상품</p>
          <p class="gradeType">{{ slide.gradeType }}등급</p>
          <img class="slideImg" :src="`${GLOBAL_URL}/api/file/download/${slide.mainImage}`" />
          <div class="item_info">
            <p>{{ slide.brandName }}</p>
            <p>이름{{ slide.productName }}ㆍ{{ slide.size }} ml</p>
            <p>￦ {{ Number(slide.price).toLocaleString() }}</p>
            <p></p>
          </div>
        </div>
        <!-- </div> -->
      </Slide>

      <template #addons>
        <Navigation />
      </template>
    </Carousel>
  </div>
</template>

<style scoped>
.carousel__item {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  position: relative;
  overflow: hidden;
  width: 95%;
  height: 700px;
  border-radius: 10px;
  color: black;
  background-color: var(--color-main-Lgray);
  /* border: 0.5px solid var(--color-main-bloode); */
  cursor: pointer;
}
.carousel__item:hover {
  background-color: rgb(225, 225, 225);
}
.carousel__item img {
  position: absolute;
  width: 330px;
  height: 330px;
  object-fit: cover;
  border-radius: 10px;
  background-color: white;
  top: 20%;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 5px solid white;
}

.wrapper {
  margin-top: 3vh;
}
.time_check {
  font-size: 2rem;
  position: absolute;
  top: 30px;
}
.gradeType {
  position: absolute;
  top: 70px;
  font-size: 2rem;
  color: orange;
  padding: 5px 8px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  background-color: rgb(247, 247, 247);
  box-shadow:
    inset -3px -3px 3px #ffffff73,
    inset 1px 1px 3px rgba(94, 104, 121, 0.288);
}

.item_info {
  position: absolute;
  display: flex;
  flex-direction: column;
  text-align: left;
  justify-content: space-between;
  bottom: 20px;
  left: 10px;
}
.item_info p:nth-child(1) {
  font-size: 2rem;
  color: var(--color-main-bloode);
}
.item_info p:nth-child(2) {
  font-size: 2.3rem;
}
.item_info p {
  font-size: 2.7rem;
  /* height: 200px; */
  padding: 5px 20px;
}
@media (max-width: 630px) {
  .carousel__item {
    height: 440px;
    border-radius: 10px;
  }
  .time_check {
    font-size: 1.6rem;
    top: 20px;
  }
  .carousel__item img {
    width: 180px;
  }
  .item_info p:nth-child(1) {
    font-size: 1.8rem;
  }
  .item_info p {
    font-size: 2rem;
    padding: 5px 5px;
  }
}
</style>
