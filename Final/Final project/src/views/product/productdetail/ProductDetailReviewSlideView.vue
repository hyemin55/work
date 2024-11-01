<script setup>
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import { ref } from 'vue'
import img1 from '@/assets/img/빵빵덕세안.png'
import img2 from '@/assets/img/빵빵덕세안핑크.png'

const slides = ref([
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
  img1,
  img2,
])

const config = {
  itemsToShow: 7.5,
  snapAlign: 'start',
  wrapAround: true,
}
</script>

<template>
  <Carousel v-bind="config" id="ReviewSlide">
    <Slide v-for="(slide, index) in slides" :key="index">
      <div class="carousel__item"><img :src="slide" alt="" /></div>
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
}
.carousel__item {
  /* background-color: aqua; */
  height: 150px;
  width: 150px;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 10px;
}
.carousel__item > img {
  padding: 3px;
  width: 150px;
  height: 150px;
  object-fit: cover;
  /* background-color: antiquewhite; */
}
</style>
