<script setup>
import MyInformation from '@/components/mypage/MyInformation.vue'
import MyWishlist from '@/components/mypage/MyWishlist.vue'
import MypageOrderHistory from '@/components/mypage/MypageOrderHistory.vue'
import MypageReview from '@/components/mypage/MypageReview.vue'
import MypageSalseHistory from '@/components/mypage/MypageSalseHistory.vue'
import { computed, onMounted, ref } from 'vue'
import { useUserStore } from '../../stores/Login'
import CurrentSituation from './CurrentSituation.vue'

const user = useUserStore()
const NoprofileImage = ref(user.profileImage)

onMounted(() => {
  // if (!NoprofileImage.value) {
  //   user.profileImage = require('@/img/빵빵덕세안.png')
  // }
})

const selectpage = ref('myOrderHistory')
const componentMap = {
  myOrderHistory: MypageOrderHistory,
  mySalseHistory: MypageSalseHistory,
  myWishlist: MyWishlist,
  myReview: MypageReview,
  myInformation: MyInformation,
}
const currentComponent = computed(() => componentMap[selectpage.value])
</script>

<template>
  <section id="mypage">
    <article id="myInfoNav">
      <div class="nickname">
        <!-- <img src="@/img/빵빵덕세안.png" alt="" /> -->
        <img :src="user.profileImage" alt="" />
        <p>{{ user.nickName }}</p>
      </div>
      <ul id="myInfoNavList">
        <li @click="selectpage = 'myOrderHistory'">주문 내역 조회</li>
        <li @click="selectpage = 'mySalseHistory'">판매 내역 조회</li>
        <li @click="selectpage = 'myWishlist'">찜 목록</li>
        <li @click="selectpage = 'myReview'">내 리뷰 관리</li>
        <li @click="selectpage = 'myInformation'">회원 정보 관리</li>
      </ul>
    </article>

    <main id="rightGroub">
      <article id="currentSituation">
        <CurrentSituation />
      </article>

      <article id="myDetailInfo">
        <component :is="currentComponent"></component>
      </article>
    </main>
  </section>
</template>

<style scoped>
#mypage {
  position: relative;
  width: var(--main-max-width);
  margin: 0 auto;
  display: flex;
}
#myInfoNav {
  width: 15%;
  min-width: 120px;
  max-width: 150px;
  height: 500px;
  padding: 30px 0;
  position: sticky;
  top: 150px;
  left: 0;
  /* background-color: antiquewhite; */
}
.nickname {
  padding-bottom: 30px;
  font-size: 1.5rem;
  text-align: center;
  line-height: 3rem;
}
.nickname > img {
  width: 80px;
  height: 80px;
  /* border: 0.1px solid var(--color-main-Lgray); */
  border-radius: 100%;
  background-color: white;
  object-fit: cover;
}
#myInfoNavList {
  height: auto;
  display: flex;
  flex-direction: column;
  font-size: 1.6rem;
  /* background-color: yellow; */
}
#myInfoNavList li {
  position: relative;
  /* background-color: antiquewhite; */
  margin-top: 20px;
  padding: 9%;
  cursor: pointer;
}
#myInfoNavList li::after {
  position: absolute;
  content: '';
  left: 0;
  bottom: 0;
  height: 0.1px;
  width: 100%;
  background-color: var(--color-main-bloode);
}
#myInfoNavList li:hover {
  color: var(--color-main-bloode);
  font-family: 'Pretendard-Bold';
}
#rightGroub {
  /* position: absolute; */
  width: 100%;
  max-width: 1130px;
  right: 0;
}
#currentSituation {
  /* position: relative; */
  padding: 15px 3%;
  /* background-color: aquamarine; */
  height: 400px;
}
#myDetailInfo {
  /* position: relative; */
  padding: 15px 3%;
  /* background-color: brown; */
  height: 400px;
}
</style>
