<script setup>
import { GLOBAL_URL } from '@/api/util'
import axios from 'axios'
import { onMounted, ref, watchEffect } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const idx = ref(route.params.idx)
const ReviewList = ref([])

const star_list = ['★', '★★', '★★★', '★★★★', '★★★★★']
// onMounted(async () => {
//   const res = await axios.get(`${GLOBAL_URL}/detail/review/${idx.value}`)
// const res = await axios.get(`${GLOBAL_URL}/detail/review/${idx}`)
// ReviewList.value = res.data
// console.log('별이 5개 맞아?', ReviewList.value[0].star)
// })

let flag = 0
const reviewCount = 61
const totalPages = ref(10)
const pageSize = 5
const currPage = ref(1)
const currentPageGroup = ref(0)
const startPage = ref(0)
const endPage = ref(0)

totalPages.value = Math.ceil(reviewCount / pageSize)
// console.log('totalPages = ', totalPages.value)
const totalPageGroup = Math.floor(totalPages.value / 10)
// console.log('totalPageGroup = ', totalPageGroup)

startPage.value = currentPageGroup.value * 10 + 1
console.log('startPage = ', startPage.value)

// 토탈페이지그룹과 현재페이지 중 작은수를 작은 수를 출력
endPage.value = Math.min(currentPageGroup.value * 10 + 10, totalPages.value)
// console.log('endPage = ', endPage.value)

// 이전페이지
const backPage = async currentPage => {
  console.log(currentPage)
  currentPageGroup.value = Math.floor((currentPage - 1) / 10)
  if (currentPageGroup.value > 0) {
    currentPageGroup.value -= 1
    console.log('이전페이지 = ', currentPageGroup.value)
    startPage.value = currentPageGroup.value * 10 + 1
    endPage.value = Math.min(startPage.value + 9, totalPages.value)
    const res = await axios.get(
      `${GLOBAL_URL}/detail/review/${idx.value}?pageNum=${currentPage - 1}`,
    )
    ReviewList.value = res.data
  } else {
    console.log('첫페이지입니다.')
    alert('첫페이지입니다.')
  }
}

// 다음페이지
const nextPage = async currentPage => {
  currentPageGroup.value = Math.floor((currentPage - 1) / 10)
  console.log(currentPageGroup.value)
  if (currentPageGroup.value < totalPageGroup) {
    currentPageGroup.value += 1
    // console.log('currentPageGroup', currentPageGroup.value)
    startPage.value = currentPageGroup.value * 10 + 1
    // console.log('startPage', startPage.value)
    endPage.value = Math.min(startPage.value + 9, totalPages.value)
    // console.log('endPage', endPage.value)
    // console.log('다음페이지 = ', currentPageGroup.value)
    const res = await axios.get(
      `${GLOBAL_URL}/detail/review/${idx.value}?pageNum=${currentPage - 1}`,
    )
    // console.log(res.data)
    ReviewList.value = res.data
    // console.log('currentPageGroup 후', currentPageGroup.value)
    // console.log('startPage 후', startPage.value)
    // viewCurrentPage(currentPage + 1)
  } else {
    console.log('마지막페이지입니다.')
    alert('마지막페이지입니다.')
  }
}
const viewCurrentPage = async currentPage => {
  // console.log(currentPage - 1)
  currentPageGroup.value = Math.floor((currentPage - 1) / 10)
  console.log('현재페이지', currPage.value)
  if (currentPageGroup.value == currentPage - 1 && flag) {
    flag = true
    console.log('처음이라..')
    return
  } else {
    const res = await axios.get(
      `${GLOBAL_URL}/detail/review/${idx.value}?pageNum=${currentPage - 1}`,
    )
    console.log(res.data)
    ReviewList.value = res.data
    // startPage.value = currentPageGroup.value * 10 + 1
  }
}
watchEffect(() => {
  viewCurrentPage(currPage.value)
})
</script>

<template>
  <div
    id="userReviewList"
    class="border"
    v-for="(list, index) in ReviewList"
    :key="index"
  >
    <p class="userReviewStar">{{ star_list[list.star - 1] }}</p>
    <div class="userReviewImgs">
      <img src="@/assets/img/p_003.png" alt="" class="userReviewImg" />
    </div>
    <p class="userReviewText">{{ list.content }}</p>
    <p class="userReviewTime">
      {{ list.reviewCreationDate }}
    </p>
    <ul class="userInfo">
      <li>
        <img
          :src="`${list.memberDetailReviewResDto.profileImage}`"
          alt=""
          class="userInfoImg"
        />
      </li>
      <li class="userInfoNickname">
        {{ list.memberDetailReviewResDto.nickName }}
      </li>
    </ul>
  </div>

  <ul id="totalPages">
    <li @click="backPage(startPage)">이전</li>
    <li
      class="totalPages"
      v-for="pageNum in endPage - startPage + 1"
      v-bind:key="pageNum"
      @click="currPage = pageNum"
      :class="{ active: viewCurrentPage === pageNum - 1 }"
    >
      {{ startPage + pageNum - 1 }}
    </li>
    <li @click="nextPage(endPage)">다음</li>
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
.totalPages {
  /* background-color: rgb(236, 207, 172); */
  display: flex;
  align-items: center;
  justify-content: center;
  /* width: 10%; */
  /* margin: 0 1%; */
  cursor: pointer;
  padding: 1%;
}
.clickpageNum {
  color: red;
  font-size: large;
}
</style>
