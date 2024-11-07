<script setup>
import { nextTick, ref, watch, watchEffect } from 'vue';
import ReviewComponent from '@/components/ReviewComponent.vue';
import ProductDetailReviewSlide from './ProductDetailReviewSlideView.vue';
import { productDetailStore } from '@/stores/ProductDetailStore';

const SortStar = ref(true);
const Latest = ref(true);
const detailStore = productDetailStore();
const idx = ref(detailStore.productIdx);
const reviewCount = ref(0);

const SortStarHandle = () => {
  SortStar.value = !SortStar.value;
};
const LatestHandle = () => {
  Latest.value = !Latest.value;
};

// rating, reviewCount
const ratingData = () => {};

// 리뷰 평균 점수 관리
const rating = ref(3.7); // 예: 소수점 첫째 자리까지의 평균 점수
const newRating = ref(detailStore.starAverage);
console.log('newRating = ', newRating.value);

const circumference = 2 * Math.PI * 45; // 원 둘레 (r = 45)

// 별 개수 계산
const fullStars = Math.floor(rating.value); // 정수 별 개수
const hasHalfStar = rating.value % 1 !== 0; // 소수점이 있을 때 반 별 표시 여부
const emptyStars = () => {
  const totalStars = fullStars.value + (hasHalfStar.value ? 1 : 0);
  return Math.max(5 - totalStars, 0); // 최소 0으로 설정
}; // 빈 별 개수

watchEffect(async () => {
  reviewCount.value = detailStore.reviewCount;
  await nextTick();
});
</script>

<template>
  <article id="detailReview">
    <h1 id="detailReviewTitle">Reviews</h1>
    <div id="TotalReviewWrapper">
      <div class="progress-wrapper">
        <svg width="100" height="100" viewBox="0 0 100 100">
          <!-- 배경 원 -->
          <circle cx="50" cy="50" r="45" stroke="#ddd" stroke-width="05" fill="none" />
          <!-- 진행률 원 -->
          <!-- stroke="#FFD700"  별점 색상 설정 -->
          <circle
            cx="50"
            cy="50"
            r="45"
            stroke="#FFD700"
            stroke-width="5"
            fill="none"
            stroke-dasharray="283"
            :stroke-dashoffset="circumference - circumference * (rating / 5)"
            style="transition: stroke-dashoffset 0.5s ease"
          />
        </svg>
        <div class="progress-text">{{ rating.toFixed(1) }}</div>
      </div>

      <div class="starRating">
        <div>
          <span v-for="n in fullStars" :key="'full' + n">★</span>
          <span v-if="hasHalfStar">☆</span>
          <span v-for="n in emptyStars" :key="'empty' + n">☆</span>
        </div>
        <p>from {{ reviewCount }} reviews</p>
      </div>

      <ul id="starCounting">
        <li>
          5.0 <span>★</span>
          <div>bar</div>
          별점별리뷰수
        </li>
        <li>
          4.0 <span>★</span>
          <div>bar</div>
          별점별리뷰수
        </li>
        <li>
          3.0 <span>★</span>
          <div>bar</div>
          별점별리뷰수
        </li>
        <li>
          2.0 <span>★</span>
          <div>bar</div>
          별점별리뷰수
        </li>
        <li>
          1.0 <span>★</span>
          <div>bar</div>
          별점별리뷰수
        </li>
      </ul>
    </div>

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
  margin: 0 auto;
}
#detailReviewTitle {
  font-size: 2rem;
}
#TotalReviewWrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
/* 원형차트 */
.progress-wrapper {
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 10%;
  /* background-color: aqua; */
}
.progress-text {
  position: absolute;
  /* background-color: antiquewhite; */
  font-size: 2em;
  color: #333;
}

/* 총 리뷰수 & 별점 */
.starRating {
  width: 15%;
  /* background-color: antiquewhite; */
  font-size: 1.5em;
  color: #ffa500;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 5px;
}
#starCounting {
  width: 75%;
}
#starCounting > li {
  display: flex;
  align-items: center;
  justify-content: left;
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
