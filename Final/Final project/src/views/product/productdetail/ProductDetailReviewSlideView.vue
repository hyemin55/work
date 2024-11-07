<script setup>
import 'vue3-carousel/dist/carousel.css';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';
import { ref, watch, watchEffect } from 'vue';
import { productDetailStore } from '@/stores/ProductDetailStore';
import axios from 'axios';
import { GLOBAL_URL } from '@/api/util';

const detailStore = productDetailStore();
const idx = ref(detailStore.productIdx);
const slides = ref([]);

const reviewImgsData = async () => {
  console.log(idx.value);
  const reviewImageList = await axios.get(`${GLOBAL_URL}/detail/reviewImageList/${idx.value}`);
  // console.log('reviewImgsData', reviewImageList.data);
  // console.log('reviewImgsData', reviewImageList.data.length);
  slides.value = reviewImageList.data;
};
watchEffect(() => {
  idx;
  reviewImgsData();
});

const config = {
  itemsToShow: 7.5,
  snapAlign: 'start',
  wrapAround: true,
};
</script>

<template>
  <Carousel v-bind="config" id="ReviewSlide">
    <Slide v-for="(slide, index) in slides" :key="index">
      <div class="carousel__item"><img :src="`${GLOBAL_URL}/api/file/download/${slide.filename}`" alt="" /></div>
    </Slide>

    <template #addons>
      <Navigation />
    </template>
  </Carousel>
</template>

<style scoped>
#ReviewSlide {
  width: 100%;
  height: 180px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
  /* background-color: aqua; */
}
.carousel__item {
  /* background-color: aqua; */
  height: 150px;
  width: 150px;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 10px;
  gap: 10px;
}
.carousel__item > img {
  padding: 3px;
  width: 150px;
  height: 150px;
  object-fit: cover;
  /* background-color: antiquewhite; */
}
</style>
