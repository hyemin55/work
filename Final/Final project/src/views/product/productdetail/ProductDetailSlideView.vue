<script setup>
import { onMounted, ref } from 'vue';
import { Carousel, Slide, Navigation } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';
import { useRoute } from 'vue-router';
import { getSlideImages } from '@/api/productDetailApi';
import { GLOBAL_URL } from '@/api/util';

const route = useRoute();
const idx = ref(route.params.idx);
const currentSlide = ref(0);

const slideTo = nextSlide => (currentSlide.value = nextSlide);
const list = ref([]);

const galleryConfig = {
  itemsToShow: 1,
  mouseDrag: false,
  touchDrag: false,
  wrapAround: false,
};

const thumbnailsConfig = {
  itemsToShow: 6,
  wrapAround: true,
};

onMounted(async () => {
  try {
    const slideImages = await getSlideImages(idx.value);
    // console.log('slideImages', slideImages);
    if (slideImages.status == 200) {
      list.value = slideImages.data;
      // console.log('list.value', list.value);
      // console.log('리스트파일이름' + list.value)
      // console.log('리스트파일이름' + list.value.images.length)
    }
    return slideImages;
  } catch (e) {
    console.error('실패', e);
  }
});
</script>
0

<template>
  <article id="productSlide">
    <Carousel id="gallery" v-bind="galleryConfig" v-model="currentSlide" :autoplay="3000">
      <Slide v-for="(image, index) in list.images" :key="index">
        <img :src="`${GLOBAL_URL}/api/file/download/${image.filename}`" alt="" class="carousel_image" />
      </Slide>
    </Carousel>
    <!-- :autoplay="2000" -->
    <Carousel id="thumbnails" v-bind="thumbnailsConfig" v-model="currentSlide" :pause-autoplay-on-hover="true" :mouse-drag="false">
      <Slide v-for="(image, index) in list.images" :key="index">
        <img :src="`${GLOBAL_URL}/api/file/download/${image.filename}`" class="carousel_thumbnail" />
      </Slide>

      <template #addons>
        <Navigation />
      </template>
    </Carousel>
  </article>
</template>

<style scoped>
/* 왼쪽 슬라이드 구역 */
#productSlide {
  width: 50%;
  height: 700px;
  margin: 20px 0px 25px 0;
  text-align: center;
}
/* 보여지는 슬라이드의 메인사진 1장 */
#gallery {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 20px;
  /* background-color: antiquewhite; */
}

.carousel_image {
  padding: 1%;
  width: 100%;
  height: 550px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 20px;
  object-fit: cover;
  /* background-color: brown; */
}

/* 메인아래 보여지는 슬라이드의 사진 여러장 */
#thumbnails {
  width: 100%;
  height: auto;
  /* background-color: antiquewhite; */
}

.carousel_thumbnail {
  width: 90%;
  height: 90px;
  margin: 20px 0;
  padding: 5%;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 20px;
  object-fit: cover;
  cursor: pointer;
  /* background-color: rgb(153, 96, 22); */
}
</style>
