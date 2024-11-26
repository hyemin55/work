<script setup>
import HistoryProduct from '@/components/user/HistoryProduct.vue';
import { ref } from 'vue';

const props = ref({
  text01: '주문',
  text02: '주문',
  text03: '배송',
});
</script>

<template>
  <h1 class="orderHistory_title">전체 주문 내역</h1>
  <HistoryProduct :propstext="props" :showBtn="true"></HistoryProduct>
</template>

<style scoped>
.orderHistory_title {
  font-size: 3.2rem;
  font-weight: 400;
  border-bottom: solid 1px #000;
  padding: 20px 0;
  margin-top: 32px;
}
</style>
