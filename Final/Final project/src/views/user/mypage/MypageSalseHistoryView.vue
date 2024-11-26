<script setup>
import HistoryProduct from '@/components/user/HistoryProduct.vue';
import { ref } from 'vue';

const props = ref({
  text01: '판매신청',
  text02: '판매',
  text03: '판매',
});
</script>

<template>
  <div class="dfd">
    <h1 class="orderHistory_title">전체 판매 내역</h1>
    <h2 class="salesApplication">
      판매 신청하러 가기
      <img src="../../assets/img/wing_money.gif" alt="" />
    </h2>
  </div>
  <HistoryProduct :propstext="props" :showBtn="false"></HistoryProduct>
</template>

<style scoped>
.dfd {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 1px #000;
  padding: 20px 0;
  margin-top: 32px;
}
.orderHistory_title {
  font-size: 3.2rem;
  font-weight: 400;
}
.salesApplication {
  font-size: 2rem;
  font-weight: 600;
  color: var(--color-main-bloode);
  cursor: pointer;
  animation: blink 8s infinite;
  display: flex;
  align-items: center;
  overflow: hidden;
}
.salesApplication img {
  height: 36px;
  width: auto;
  margin-left: 6px;
}
.salesApplication:hover img {
  animation: ani1 0.7s;
}

/* 애니메이션을 정의 */
@keyframes blink {
  0% {
    opacity: 1;
  }
  80% {
    opacity: 1;
  }
  86% {
    opacity: 0;
  }
  90% {
    opacity: 1;
  }
  95% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
@keyframes ani1 {
  0% {
    transform: translate(0, 0);
  }
  50% {
    transform: translate(100px, -100px);
  }
  50.1% {
    transform: translate(-100px, 100px);
  }
  100% {
    transform: translate(0, 0);
  }
}
</style>
