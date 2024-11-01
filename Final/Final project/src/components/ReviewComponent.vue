<script setup>
import { ref } from 'vue'

const totalPages = ref(10)
const pageNum = ref(20)

const setPageNum = num => {
  pageNum.value = num
}
</script>

<template>
  <div id="userReviewList" class="border">
    <p class="userReviewStar">★★★★★</p>
    <div class="userReviewImgs">
      <img src="@/assets/img/p_003.png" alt="" class="userReviewImg" />
    </div>
    <p class="userReviewText">리뷰 내용들 샬라샬라샬라</p>
    <p class="userReviewTime">2024-10-29 21:52:10</p>
    <ul class="userInfo">
      <li>
        <img src="@/assets/img/빵빵덕세안핑크.png" alt="" class="userInfoImg" />
      </li>
      <li class="userInfoNickname">닉네임</li>
    </ul>
  </div>

  <div id="userReviewList" class="border">
    <p class="userReviewStar">★★★★★</p>
    <div class="userReviewImgs">
      <img src="@/assets/img/p_003.png" alt="" class="userReviewImg" />
      <img src="@/assets/img/p_003.png" alt="" class="userReviewImg" />
      <img src="@/assets/img/p_003.png" alt="" class="userReviewImg" />
      <img src="@/assets/img/p_003.png" alt="" class="userReviewImg" />
      <img src="@/assets/img/p_003.png" alt="" class="userReviewImg" />
    </div>
    <p class="userReviewText">리뷰 내용들 샬라샬라샬라</p>
    <p class="userReviewTime">2024-10-29 21:52:10</p>
    <ul class="userInfo">
      <li>
        <img src="@/assets/img/빵빵덕세안핑크.png" alt="" class="userInfoImg" />
      </li>
      <li class="userInfoNickname">닉네임</li>
    </ul>
  </div>

  <div id="totalPages">
    <ul>
      <li
        class="totalPages"
        v-for="num in totalPages"
        v-bind:key="num"
        @click="setPageNum(num - 1)"
      >
        {{ num }}
      </li>
    </ul>
  </div>
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
  height: 100%;
  padding: 1px;
}
</style>
