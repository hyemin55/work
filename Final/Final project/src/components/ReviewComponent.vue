<script setup>
import { GLOBAL_URL } from '@/api/util';
import { productDetailStore } from '@/stores/ProductDetailStore';
import axios from 'axios';
import { nextTick, onMounted, ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const ReviewList = ref([]);
const detailStore = productDetailStore();
const idx = ref(detailStore.productIdx);
const star_list = ['★', '★★', '★★★', '★★★★', '★★★★★'];

let flag = 0;
const totalPages = ref(10);
const totalPageGroup = ref(0);
const pageSize = 5;
const currentPage = ref(1);
const currentPageGroup = ref(0);
const startPage = ref(0);
const endPage = ref(0);
const reviewCount = ref(detailStore.reviewCount);

watchEffect(() => {
  reviewCount.value = detailStore.reviewCount;
});

onMounted(async () => {
  const reviewsData = await axios.get(`${GLOBAL_URL}/detail/review/${idx.value}`);
  // console.log('순서시작', reviewsData.data.length)
  // console.log('순서시작', reviewsData.data)
  totalPages.value = Math.ceil(reviewCount.value / pageSize);
  totalPageGroup.value = Math.floor(totalPages.value / 10);
  viewCurrentPage();
});
console.log(reviewCount.value);

// 이전페이지
const backPage = async () => {
  currentPage.value = startPage.value - 10;
  if (currentPageGroup.value <= 0) {
    console.log('첫페이지입니다.');
    alert('첫페이지입니다.');
    return;
  }
  viewCurrentPage();
};

// 다음페이지
const nextPage = async () => {
  currentPage.value = endPage.value + 1;
  console.log('현재페이지그룹', currentPageGroup.value);
  if (currentPageGroup.value >= totalPageGroup.value) {
    console.log('마지막페이지입니다.');
    alert('마지막페이지입니다.');
    return;
  }
  viewCurrentPage();
};

// 선택페이지
const goToPage = page => {
  if (currentPage.value == page) {
    console.log('현재페이지입니다.');
    return;
  }
  currentPage.value = page;
  viewCurrentPage();
};

// 현재페이지
const viewCurrentPage = async () => {
  currentPageGroup.value = Math.floor((currentPage.value - 1) / 10);
  // console.log('현재페이지', currentPage.value)
  // console.log('현재페이지그룹', currentPageGroup.value)

  if (currentPageGroup.value == currentPage.value - 1 && flag) {
    flag = true;
    return;
  } else {
    const reviewsData = await axios.get(`${GLOBAL_URL}/detail/review/${idx.value}?pageNum=${currentPage.value - 1}`);
    // console.log('리뷰리스트', res.data)
    ReviewList.value = reviewsData.data;
    startPage.value = currentPageGroup.value * 10 + 1;
    endPage.value = Math.min(startPage.value + 9, totalPages.value);
  }
};

// 선택된 페이지번호에 CSS 설정
const activePage = pageNum => {
  if (currentPageGroup.value <= 0) {
    return currentPage.value === pageNum;
  } else {
    return currentPage.value - 1 - currentPageGroup.value * 10 === pageNum - 1;
  }
};
</script>

<template>
  <div id="userReviewList" class="border" v-for="(list, index) in ReviewList" :key="index">
    <p class="userReviewStar">{{ star_list[list.star - 1] }}</p>
    <div class="userReviewImgs">
      <img :src="`${GLOBAL_URL}/api/file/download/${list.reviewImageResDto.filename}`" alt="" class="userReviewImg" />
    </div>
    <p class="userReviewText">{{ list.content }}</p>
    <p class="userReviewTime">
      {{ list.reviewCreationDate }}
    </p>
    <ul class="userInfo">
      <li>
        <img :src="`${list.memberDetailReviewResDto.profileImage}`" alt="" class="userInfoImg" />
      </li>
      <li class="userInfoNickname">
        {{ list.memberDetailReviewResDto.nickName }}
      </li>
    </ul>
  </div>

  <div id="userReviewList" class="border noUserReviewList" v-if="reviewCount == 0 || reviewCount == null">
    <img src="@/assets/img/free-icon-font-note-sticky-9798415.svg" alt="" />
    <p>아직 리뷰가 등록되지 않았어요 ㅠㅡㅠ</p>
  </div>

  <ul id="totalPages">
    <li @click="backPage">이전</li>
    <li class="totalPages" v-for="pageNum in endPage - startPage + 1" v-bind:key="pageNum" @click="goToPage(startPage + pageNum - 1)" :class="{ active: activePage(pageNum) }">
      {{ startPage + pageNum - 1 }}
    </li>
    <li @click="nextPage">다음</li>
  </ul>
</template>

<style scoped>
#userReviewList {
  line-height: 35px;
}
#userReviewList::after {
  position: absolute;
  content: '';
  border: 0.5px dashed var(--color-main-Lgray);
  height: 0.1px;
  width: var(--main-max-width);
  display: flex;
}
.noUserReviewList > img {
  width: 100px;
  color: var(--color-main-Lgray);
  filter: grayscale(100%);
  margin-top: 20px;
}
.noUserReviewList::before {
  position: absolute;
  content: '';
  border: 0.5px dashed var(--color-main-Lgray);
  height: 0.1px;
  width: var(--main-max-width);
  display: flex;
  /* top: 20px; */
}
.noUserReviewList {
  text-align: center;
  height: 150px;
  background-color: antiquewhite;
}
.userReviewStar {
  font-size: 2rem;
  color: orange;
  margin-top: 20px;
}
.userReviewImgs {
  display: flex;
  align-items: center;
  justify-content: start;
  width: 100%;
  height: auto;
  /* background-color: antiquewhite; */
}
.userReviewImg {
  width: 8%;
  height: auto;
  margin-right: 1%;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 10px;
}
.userReviewImg:last-child {
  margin-right: 0;
}
.userReviewText,
.userInfoNickname {
  font-size: 1.4rem;
  /* background-color: bisque; */
}
.userReviewTime {
  font-size: 1.2rem;
  margin-top: -15px;
  color: var(--color-text-gray);
}
.userInfo {
  display: flex;
  align-items: center;
  justify-content: left;
  height: 40px;
  width: auto;
  gap: 10px;
  margin-bottom: 10px;
  /* background-color: aqua; */
}
.userInfoImg {
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 50%;
  width: 40px;
  height: 40px;
  padding: 1px;
  object-fit: cover;
}
#totalPages {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  margin-top: 30px;
  /* background-color: rgb(161, 160, 158); */
}
#totalPages li {
  cursor: pointer;
}
.totalPages {
  /* background-color: rgb(236, 207, 172); */
  display: flex;
  align-items: center;
  justify-content: center;
  /* width: 10%; */
  /* margin: 0 1%; */
  padding: 1%;
}
.totalPages.active {
  color: var(--color-main-bloode);
  font-weight: 600;
  text-decoration: underline;
}
</style>
