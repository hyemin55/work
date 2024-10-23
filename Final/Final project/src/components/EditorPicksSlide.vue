<template>
  <div class="wrapper">
    <Carousel :autoplay="3000" :wrap-around="true">
      <Slide v-for="slide in slides" :key="slide">
        <div class="carousel__item">
          <img class="slideImg" :src="slide" />
        </div>
      </Slide>

      <template #addons>
        <Pagination />
      </template>
    </Carousel>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { Carousel, Pagination, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'Autoplay',
  components: {
    Carousel,
    Slide,
    Pagination,
  },
  data() {
    return {
      slides: [
        { src: '../img/best_img.png' },
        { src: '../img/best_img.png' },
        { src: '../img/best_img.png' },
      ],
    }
  },
})
</script>

<style>
.carousel__item {
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  padding: 10px;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 5px solid white;
}
.slideImg {
  max-height: 740px;
  max-width: 100%;
}
.wrapper {
  margin-top: 3vh;
}
</style>
