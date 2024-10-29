<script setup>
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide, Navigation } from 'vue3-carousel'

const config = {
  autoplay: 2000,
  wrapAround: true,
  pauseAutoplayOnHover: true,
}
</script>

<template>
  <article id="detailReview">
    <h1>Photo Lists</h1>

    <template>
      <Carousel v-bind="config">
        <Slide v-for="slide in 10" :key="slide">
          <div class="carousel__item">{{ slide }}</div>
        </Slide>

        <template #addons>
          <Navigation />
        </template>
      </Carousel>
    </template>

    <h1>Review Lists</h1>
    <ul>
      <template>
        <li v-if="Recommended">추천순</li>
        <li v-else>추천순</li>
      </template>
      <template>
        <li v-if="SortStar">별점순</li>
        <li v-else>별점순</li>
      </template>
      <template>
        <li v-if="Latest">최신순</li>
        <li v-else>최신순</li>
      </template>
    </ul>

    <div>
      <p>★★★★★</p>
      <img src="../../img/p_003.png" alt="" />
      <p>리뷰 내용들 샬라샬라샬라</p>
      <p>2024-10-29 21:52:10</p>
      <ul>
        <li><img src="../../img/빵빵덕토끼.png" alt="" /></li>
        <li>닉네임</li>
      </ul>
    </div>
  </article>
</template>

<style scoped>
#detailReview {
  height: 200px;
}
</style>
