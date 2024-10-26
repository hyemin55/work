<script setup>
import { useUserStore } from '@/stores/Login'
import { onMounted } from 'vue'

const user = useUserStore()
console.log(user.nickName)
console.log(user.profileImage)

onMounted(() => {})
</script>

<template>
  <section id="mypage">
    <article id="myInfoNav">
      <div class="nickname">
        <!-- <img src="@/img/빵빵덕세안.png" alt="" /> -->
        <img :src="`${user.profileImage}`" alt="" />
        <p>{{ user.nickName }}</p>
      </div>
      <ul id="myInfoNavList">
        <li>주문 내역 조회</li>
        <li>판매 내역 조회</li>
        <li>찜 목록</li>
        <li>내 리뷰 관리</li>
        <li>회원 정보 관리</li>
      </ul>
    </article>

    <div id="rightGroub">
      <article id="currentSituation"></article>

      <article id="myDetailInfo"></article>
    </div>
  </section>
</template>

<style scoped>
#mypage {
  display: flex;
  width: var(--main-max-width);
  margin: 0 auto;
}
#myInfoNav {
  width: 161px;
  height: 500px;
  /* background-color: antiquewhite; */
  padding: 30px 0;
  margin-top: 50px;
  position: fixed;
}
.nickname {
  /* position: absolute; */
  padding-bottom: 20px;
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
  /* width: 100%; */
  display: flex;
  flex-direction: column;
  font-size: 1.6rem;
  /* background-color: yellow; */
}
#myInfoNavList li {
  position: relative;
  margin-bottom: 50px;
  /* width: 100%; */
  padding-left: 8%;
  cursor: pointer;
}
#myInfoNavList li::after {
  position: absolute;
  content: '';
  left: 0;
  bottom: -10px;
  height: 0.1px;
  width: 100%;
  background-color: var(--color-main-bloode);
}
#myInfoNavList li:hover {
  color: var(--color-main-bloode);
  font-family: 'Pretendard-Bold';
}
#rightGroub {
  width: 70%;
  height: 300vh;
}
#currentSituation {
  /* background-color: aquamarine; */
  height: 400px;
}
#myDetailInfo {
  /* background-color: brown; */
}
</style>
