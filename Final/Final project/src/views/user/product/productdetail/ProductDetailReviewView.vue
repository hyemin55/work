<script setup>
import { ref, watchEffect } from 'vue';
import ReviewComponent from '@/components/user/ReviewComponent.vue';
import ProductDetailReviewSlide from '@/views/user/product/productdetail/ProductDetailReviewSlideView.vue';
import { useRoute } from 'vue-router';
import { getstarCounting } from '@/api/productDetailApi';

const route = useRoute();
const SortStar = ref(true);
const Latest = ref(true);
const idx = ref(route.params.idx);
const reviewCount = ref(0);
const starCounts = ref([0, 0, 0, 0, 0]);
const starCountData = ref({});

const SortStarHandle = () => {
  SortStar.value = !SortStar.value;
};
const LatestHandle = () => {
  Latest.value = !Latest.value;
};

// 리뷰 평균 점수 관리
const starAverage = ref(0);
const circumference = 2 * Math.PI * 45; // 원 둘레 (r = 45)

const fullStars = ref(0);
const hasHalfStar = ref(0);
const emptyStars = () => {
  const totalStars = fullStars.value + (hasHalfStar.value ? 1 : 0);
  return Math.max(5 - totalStars, 0); // 최소 0으로 설정
}; // 빈 별 개수

// 별점별 리뷰수 계산
const starCounting = async () => {
  const starCountingData = await getstarCounting(idx.value);
  starCountData.value = starCountingData.data;
  reviewCount.value = starCountData.value.reviewCount;
  starAverage.value = starCountData.value.starAverage;
  starCounts.value = [
    starCountData.value.fiveStarCount || 0,
    starCountData.value.fourStarCount || 0,
    starCountData.value.threeStarCount || 0,
    starCountData.value.twoStarCount || 0,
    starCountData.value.oneStarCount || 0,
  ];
  // 총 별 개수 계산
  fullStars.value = Math.floor(starAverage.value); // 정수 별 개수
  hasHalfStar.value = starAverage.value % 1 !== 0; // 소수점이 있을 때 반 별 표시 여부
  emptyStars();
};

const Average = data => {
  data = data * 10;
  data = Math.round(data);
  data = data / 10;
  return data;
};

watchEffect(() => {
  idx.value = route.params.idx;
  starCounting();
});
</script>

<template>
  <article id="detailReview">
    <h1 id="detailReviewTitle">Reviews</h1>
    <div id="TotalReviewWrapper">
      <div class="progress-wrapper">
        <svg width="100" height="100" viewBox="0 0 100 100">
          <!-- 배경 원 -->
          <circle cx="50" cy="50" r="45" stroke="#ddd" stroke-width="5" fill="none" />
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
            :stroke-dashoffset="circumference - circumference * (starAverage / 5)"
            style="transition: stroke-dashoffset 0.5s ease"
          />
        </svg>
        <div class="progress-text">{{ Average(starAverage) }}</div>
      </div>

      <div class="starAverage">
        <div>
          <span v-for="n in fullStars" :key="'full' + n">★</span>
          <span v-if="hasHalfStar">☆</span>
          <span v-for="n in emptyStars" :key="'empty' + n">☆</span>
        </div>
        <p>from {{ reviewCount }} reviews</p>
      </div>

      <div id="starCounting">
        <span v-for="(count, index) in starCounts" :key="index">
          ★ {{ 5 - index }}.0
          <span class="bar-container">
            <div class="bar" :style="{ width: `${(count / reviewCount) * 100}%` }"></div>
          </span>
          <span>{{ count }} reviews</span>
        </span>
      </div>
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
    <ReviewComponent v-if="reviewCount || reviewCount == 0" :reviewCount="reviewCount" />
  </article>
</template>

<style scoped>
#detailReview {
  /* height: 200px; */
  margin: 0 auto;
}
#detailReviewTitle {
  font-size: 2rem;
}
#TotalReviewWrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 20px 0 50px 0;
  /* background-color: #ffa500; */
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
.starAverage {
  width: 20%;
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
  width: 70%;
  line-height: 30px;
  font-size: 1.4rem;
}
#starCounting > span {
  display: flex;
  align-items: center;
  justify-content: space-around;
  gap: 5px;
}

.bar-container {
  width: 75%;
  background-color: #ddd;
  border-radius: 5px;
  overflow: hidden;
  height: 8px;
}

.bar {
  height: 100%;
  border-radius: 5px;
  background-color: #333;
  transition: width 0.3s ease;
}

/* Review Lists section */
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

@media (max-width: 630px) {
  #detailReview{
    padding: 0 3%;
  }
#TotalReviewWrapper {
  flex-direction: column;
}
#starCounting {
  width: 88%;
}
}
</style>
