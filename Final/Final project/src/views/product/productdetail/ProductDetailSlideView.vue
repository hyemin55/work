<script setup>
import { onMounted, ref } from 'vue'
import { Carousel, Slide, Navigation } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import axios from 'axios'
import { GLOBAL_URL } from '@/api/util'
const currentSlide = ref(0)

const slideTo = nextSlide => (currentSlide.value = nextSlide)
const list = ref([])

const galleryConfig = {
  itemsToShow: 1,
  mouseDrag: false,
  touchDrag: false,
  wrapAround: false,
}

const thumbnailsConfig = {
  itemsToShow: 6,
  wrapAround: true,
}

const props = defineProps({
  productId: {
    type: Object,
    required: true,
  },
})

onMounted(async () => {
  try {
    const res = await axios.get(
      `${GLOBAL_URL}/detail/images/${props.productId}`,
    )
    console.log(res)
    if (res.status == 200) {
      list.value = res.data
      // console.log(list.value)
      // console.log('리스트파일이름' + list.value)
      // console.log('리스트파일이름' + list.value.images.length)
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
      <Slide v-for="(image, index) in list.images" :key="index">
        <img
          :src="`${GLOBAL_URL}/api/file/download/${image.filename}`"
          alt=""
          class="carousel_image"
        />
      </Slide>
    </Carousel>

    <Carousel id="thumbnails" v-bind="thumbnailsConfig" v-model="currentSlide">
      <Slide v-for="(image, index) in list.images" :key="index">
        <img
          :src="`${GLOBAL_URL}/api/file/download/${image.filename}`"
          class="carousel_thumbnail"
        />
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
  margin: 20px 1.5% 25px 0;
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
  /* background-color: antiquewhite; */
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel_image {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: auto;
  object-fit: cover;
  /* background-color: brown; */
  border-radius: 15px;
}
/* #thumbnails {
  width: 100%;
  background-color: antiquewhite;
} */
.carousel_thumbnail {
  /* background-color: rgb(153, 96, 22); */
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
