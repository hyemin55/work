<template>
  <section id="main_wrapper">
    <article id="main_video">
      <video autoplay muted loop src="@/assets/img/main.mp4"></video>
      <article id="IntroductionModal" v-if="eventModal">
        <div>
          <h1 class="title">향수 전문 중고거래 쇼핑몰 CHERRISH</h1>
          <img src="@/assets/img/shutterstock_1346355935.jpg" alt="" />
          <h1 class="subTitle">
            꼼꼼한 검수 과정을 통해 <br />
            믿고 구매하실 수 있습니다.
          </h1>
          <div class="content">
            <p>(중고상품) 향수 검수 특이사항</p>
            <p>향수 거래 시 아래 사유로 검수 불합격 및 거래 취소될 수 있으므로 반드시 확인 부탁드립니다.</p>
            <p>01. 국내 정식 수입된 상품이 아닌 병행 수입 상품</p>
            <p>02. 체결일로부터 남은 사용기한이 16개월 이하인 상품</p>
            <p>03. 일반 상품에 리미티드 에디션 상품을 판매하거나, 리미티드 에디션 상품에 일반 상품을 판매하는 경우</p>
          </div>
          <div class="closeModalButtons">
            <button @click="ondDayCloseModal">오늘 하루 보지 않기×</button>
            <button @click="closeModal">닫기×</button>
          </div>
        </div>
      </article>
    </article>

    <MainBestView />
    <MainListView />
    <MainPromotionBannerView />
    <MainEditorView />
    <MainNewView />
  </section>
</template>

<script setup>
import { defineAsyncComponent, ref } from 'vue';
import MainBestView from '@/views/user/main/MainBestView.vue';
import MainListView from '@/views/user/main/MainListView.vue';
import MainPromotionBannerView from '@/views/user/main/MainPromotionBannerView.vue';
import MainEditorView from '@/views/user/main/MainEditorView.vue';
import MainNewView from '@/views/user/main/MainNewView.vue';

const eventModal = ref(true);

const ondDayCloseModal = () => {
  eventModal.value = false;
  localStorage.setItem('eventModal', eventModal.value);
  setTimeout(() => {
    eventModal.value = true;
    localStorage.removeItem('eventModal');
  }, 86400000);
};

const closeModal = () => {
  eventModal.value = false;
};

const modalState = () => {
  if (localStorage.getItem('eventModal')) {
    eventModal.value = false;
  }
};
modalState();
// const MainEditorView = defineAsyncComponent(() => import('./MainEditorView.vue'));
// const MainNewView = defineAsyncComponent(() => import('./MainNewView.vue'));
</script>

<style scoped>
/* 1-section 설정 */
#main_wrapper {
  position: relative;
}
#main_video {
  width: 100%;
  height: 100vh;
  /* background-color: rgb(35, 138, 74); */
}
#main_video video {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border: 1px solid white;
}
#IntroductionModal {
  position: fixed;
  width: 350px;
  top: 130px;
  left: 10%;
  text-align: center;
  line-height: 2rem;
  background-color: white;
  box-shadow:
    2px 2px 6px rgba(0, 0, 0, 0.5),
    inset 2px 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 50;
}
.title {
  font-size: 2.7rem;
  padding: 10px 0;
  line-height: 3.5rem;
  color: var(--color-main-bloode);
}
#IntroductionModal img {
  width: 340px;
  height: auto;
}
.subTitle {
  padding-top: 15px;
  font-size: 2rem;
  font-weight: 600;
}
.content {
  font-size: 1.3rem;
  text-align: left;
  padding: 10px 15px;
  color: #555;
}
.content p:nth-child(1) {
  font-size: 1.5rem;
  font-weight: 600;
}
.closeModalButtons {
  font-size: 1.1rem;
  color: white;
  padding: 0 10px;
  background-color: #333;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.closeModalButtons Button:hover {
  text-decoration: underline;
  text-underline-position: under;
}
</style>
