<!-- 슬라이드 공식 사이트 = https://ismail9k.github.io/vue3-carousel/getting-started.html -->
<script setup>
import { Carousel, Slide, Navigation, Icon, Pagination } from 'vue3-carousel';
import { ref, watchEffect } from 'vue';
import axios from 'axios';
import 'vue3-carousel/dist/carousel.css';
import { GLOBAL_URL } from '@/api/util';
import router from '@/router';
import { formatPrice } from '@/FormatPrice';

const slides = ref([]);
const pageNum = 0;
const size = 10;

//  '/category/:title/:idx'이거 받아오기!!!!!!!

const getNewList = async () => {
  try {
    const res = await axios.get(`${GLOBAL_URL}/api/products/new?pageNum=${pageNum}&size=${size}`);
    // console.log(res)
    if (res.status == 200) {
      // New_list.value = res.data
      // console.log('나와랏', res.data)
      // for (let i = 0; i < res.data.length; i++) {
      //   slides.value.push(res.data[i].images[0])
      // }
      slides.value = res.data;
      console.log(slides.value);
    }
  } catch (e) {
    console.log('리스트 못 받아오는 오류에요 = ' + e);
  }
};

watchEffect(() => {
  getNewList();
});
const navDetailProduct = (productId, size) => {
  console.log(productId);
  router.push({
    path: `/productsdetail/${productId}`,
    query: { size: size },
  });
};

// getNewList()
</script>

<template>
  <div class="wrapper">
    <Carousel :items-to-show="3.5" :autoplay="2000" :snapAlign="'center'" :wrap-around="true" :pause-autoplay-on-hover="true" :mouseDrag="false">
      <Slide v-for="(slide, index) in slides" :key="index">
        <!-- <div v-for="productDtail in New_list" :key="productDtail.productId"> -->
        <div class="carousel__item" @click="navDetailProduct(slide.productId, slide.size)">
          <p class="time_check">{{ slide.registerDate }} 등록상품</p>
          <img class="slideImg" :src="`${GLOBAL_URL}/api/file/download/${slide.images[0].filename}`" />
          <div class="item_info">
            <p>{{ slide.brandName }}</p>
            <p>{{ slide.productName }}</p>
            <p>￦ {{ slide.price.toLocaleString() }}</p>
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

.carousel__item img {
  position: absolute;
  width: 95%;
  max-height: auto;
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
.item_info p {
  font-size: 2.7rem;
  /* height: 200px; */
  padding: 5px 20px;
}
</style>
