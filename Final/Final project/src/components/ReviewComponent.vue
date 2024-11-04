<script setup>
import { GLOBAL_URL } from '@/api/util'
import axios from 'axios'
import { computed, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const idx = ref(route.params.idx)
const ReviewList = ref([])
const reviewcount = ref(0)

const totalPages = ref(10)

const star_list = ['★', '★★', '★★★', '★★★★', '★★★★★']

onMounted(async () => {
  const res = await axios.get(`${GLOBAL_URL}/detail/review/1`)
  // const res = await axios.get(`${GLOBAL_URL}/detail/review/${idx}`)
  ReviewList.value = res.data
  reviewcount.value = res.data.list
  console.log(reviewcount.value)
  // console.log('별이 5개 맞아?', ReviewList.value[0].star)

  const res2 = await axios.get(`${GLOBAL_URL}/detail/detailReviewInfo/1`)
  totalPages = res2.data.reviewCount
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

  <!-- <ul id="totalPages">
    <li @click="setPageNum(pageNum.value - 1)" v-if="pageNum.value > 0">
      이전
    </li>
    <li
      class="totalPages"
      v-for="num in totalPages"
      v-bind:key="num"
      @click="setPageNum(num - 1)"
      :class="{ active: pageNum.value === num - 1 }"
    >
      {{ num }}
    </li>
    <li
      @click="setPageNum(pageNum.value + 1)"
      v-if="pageNum.value < totalPages - 1"
    >
      다음
    </li>
  </ul> -->
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
  width: 30%;
  margin-top: 30px;
  background-color: rgb(161, 160, 158);
}
.totalPages {
  background-color: rgb(236, 207, 172);
  display: flex;
  align-items: center;
  justify-content: center;
  width: 10%;
}
</style>
