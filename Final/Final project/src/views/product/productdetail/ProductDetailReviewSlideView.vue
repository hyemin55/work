<script setup>
import 'vue3-carousel/dist/carousel.css';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { getReviewImageList } from '@/api/productDetailApi';
import { GLOBAL_URL } from '@/api/util';
import ProductPhotoModalView from './ProductPhotoModalView.vue';

const route = useRoute();
const idx = ref(route.params.idx);
const slides = ref([]);

const showModal = ref(false);
const selectedImage = ref('');

function openModal(filename) {
  selectedImage.value = `${GLOBAL_URL}/api/file/download/${filename}`;
  showModal.value = true;
}

function closeModal() {
  showModal.value = false;
}

const reviewImgsData = async () => {
  const reviewImageList = await getReviewImageList(idx.value);
  slides.value = reviewImageList;
  console.log(slides.value);
};

const config = {
  itemsToShow: 7.5,
  snapAlign: 'start',
  wrapAround: true,
};

watchEffect(() => {
  idx.value;
  reviewImgsData();
});
</script>

<template>
  <Carousel v-bind="config" id="ReviewSlide">
    <template v-if="slides.length == 0 || slides.length == null">
      <div class="carousel__item"><img src="" alt="" /></div>
      <p>아직 등록된 사진이 없어요ㅠㅡㅠ</p>
    </template>
    <template v-else>
      <Slide v-for="(slide, index) in slides" :key="index">
        <div class="carousel__item"><img :src="`${GLOBAL_URL}/api/file/download/${slide.filename}`" alt="" @click="openModal(slide.filename)" /></div>
      </Slide>
    </template>
    <template #addons>
      <Navigation />
    </template>
  </Carousel>
  <productPhotoModalView v-if="showModal" :image="selectedImage" @close="closeModal" />
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
  cursor: pointer;
}
.carousel__item > img {
  padding: 3px;
  width: 150px;
  height: 150px;
  object-fit: cover;
  /* background-color: antiquewhite; */
}
</style>
