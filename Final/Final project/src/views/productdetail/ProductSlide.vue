<script setup>
import { onMounted, ref, defineProps } from 'vue'
import { Carousel, Slide, Navigation } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import axios from 'axios'
import img1 from '@/img/빵빵덕유치원.png'
import img2 from '@/img/빵빵덕세안.png'
import img3 from '@/img/빵빵덕캐릭터회색.png'
import img4 from '@/img/빵빵덕토끼.png'
import img5 from '@/img/빵빵덕유치원.png'
import img6 from '@/img/빵빵덕세안핑크.png'
import img7 from '@/img/빵빵덕캐릭터회색.png'
import img8 from '@/img/빵빵덕토끼.png'
import img9 from '@/img/빵빵덕세안.png'
import img10 from '@/img/빵빵덕복숭아.png'
import { GLOBAL_URL } from '@/api/util'
const currentSlide = ref(0)

const slideTo = nextSlide => (currentSlide.value = nextSlide)

const galleryConfig = {
  itemsToShow: 1,
  mouseDrag: false,
  touchDrag: false,
  wrapAround: false,
}

const thumbnailsConfig = {
  itemsToShow: 5.5,
  wrapAround: true,
}

const props = defineProps({
  productId: {
    type: Object,
    required: true,
  },
})
// const slides = ref([
//   img1,
//   img2,
//   img3,
//   img4,
//   img5,
//   img6,
//   img7,
//   img8,
//   img9,
//   img10,
// ])
const list = ref([])
onMounted(async () => {
  try {
    const res = await axios.get(
      `${GLOBAL_URL}/detail/images/${props.productId}`,
    )
    console.log(res)
    if (res.status == 200) {
      list.value = res.data
      console.log(list.value)
      console.log(list.value.images)
    }
    return res
  } catch (e) {
    console.error('실패', e)
  }
})
</script>

<template>
  <article id="productSlide">
    <Carousel id="gallery" v-bind="galleryConfig" v-model="currentSlide">
      <Slide v-for="(image, index) in list" :key="index">
        <img
          :src="`${GLOBAL_URL}/api/file/download/${image[0].filename}`"
          alt=""
          class="carousel_image"
        />
      </Slide>
    </Carousel>

    <Carousel id="thumbnails" v-bind="thumbnailsConfig" v-model="currentSlide">
      <Slide v-for="(image, index) in list" :key="index">
        <img :src="`${GLOBAL_URL}/api/file/download/${image[0].filename}`" />
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
  margin: 25px 1.5% 25px 2%;
  width: 50%;
  height: 700px;
  text-align: center;
}
#gallery {
  width: 100%;
  height: 550px;
  padding: 15px 0;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 20px;
  background-color: antiquewhite;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel_image {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80%;
  height: auto;
  object-fit: cover;
  background-color: brown;
  border-radius: 15px;
}
.carousel_thumbnail {
  cursor: pointer;
  object-fit: cover;
  width: 90%;
  height: 90px;
  margin: 20px 0;
  padding: 5%;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 20px;
}
</style>
