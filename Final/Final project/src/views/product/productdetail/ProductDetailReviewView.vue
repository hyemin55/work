<script setup>
import { ref } from 'vue'
import ReviewComponent from '@/components/ReviewComponent.vue'
import ProductDetailReviewSlide from './ProductDetailReviewSlideView.vue'
import { productDetailStore } from '@/stores/productDetailStore'

const SortStar = ref(true)
const Latest = ref(true)
const detailStore = productDetailStore
const idx = detailStore.productIdx
const size = detailStore.productSize

const SortStarHandle = () => {
  SortStar.value = !SortStar.value
}
const LatestHandle = () => {
  Latest.value = !Latest.value
}
</script>

<template>
  <article id="detailReview">
    <h1 id="detailReviewTitle">Photo Lists</h1>
    <ProductDetailReviewSlide />

    <h1 id="detailReviewTitle">Review Lists</h1>
    <ul id="ShowReview">
      <li>추천순</li>
      <div>
        <li v-show="SortStar" @click="SortStarHandle">별점 높은 순</li>
        <li v-show="!SortStar" @click="SortStarHandle">별점 낮은 순</li>
      </div>
      <div>
        <li v-show="Latest" @click="LatestHandle">최근 등록 순</li>
        <li v-show="!Latest" @click="LatestHandle">오래된 순</li>
      </div>
    </ul>

    <!-- mypage에서 component로 만들어서 재사용하기! -->
    <ReviewComponent />
  </article>
</template>

<style scoped>
#detailReview {
  height: 200px;
}
#detailReviewTitle {
  font-size: 2rem;
}
#ShowReview {
  height: 60px;
  display: flex;
  align-items: center;
  gap: 20px;
  width: 100%;
  /* background-color: aqua; */
}
#ShowReview li {
  font-size: 1.4rem;
  height: 35px;
  width: 100px;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  /* background-color: antiquewhite; */
  cursor: pointer;
}
#ShowReview li:hover {
  border: 0.5px solid var(--color-main-bloode);
  background-color: var(--color-main-bloode);
  color: white;
}
</style>
