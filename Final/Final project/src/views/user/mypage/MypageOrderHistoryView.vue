<script setup>
import { GLOBAL_URL } from '@/api/util';
import PageNationComponent from '@/components/PageNationComponent.vue';
import HistoryProduct from '@/components/user/HistoryProduct.vue';
import MypageEmptyComponent from '@/components/user/MypageEmptyComponent.vue';
import axios from 'axios';
import { onMounted, ref, watch } from 'vue';

// 페이지네이션
const totalCount = ref(0);
const pageSize = ref(5);
const pageNum = ref(0);
const pageNationData = ref('');

// 주문리스트 통신
const orderList = ref([]);
const getOrderList = async(pageNum, size)=>{
  try{
    const res = await axios.get(`${GLOBAL_URL}/myPage/orderList`, {
      params:{
        pageNum : pageNum,
        size : size,
      },
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
        'Content-Type':'application/json'
      }
    })
    totalCount.value = res.data.totalElements;
    orderList.value = res.data.content;
    console.log("오더리스트", orderList.value);
  }catch(error){
    console.error(error)
  }
}

// 페이지 랜더링
watch([pageNum,totalCount], ([newPageNum, newTotalCount]) => {
  getOrderList(newPageNum, pageSize.value);
  pageNation(newTotalCount)
});
onMounted(()=>{
  getOrderList(pageNum.value, pageSize.value);
  pageNation();
})
const pageUpdate = pageNumer => {
  pageNum.value = pageNumer;
};

// 페이지컴포넌트 생성을 위해 보내는값(페이지수 생성)
const pageNation = () => {
  pageNationData.value = {
    totalCount: totalCount.value,
    pageSize: pageSize.value,
  };
};
</script>

<template>
  <h1 class="orderHistory_title">전체 주문 내역</h1>

  <article v-if="orderList.length > 0">
    <HistoryProduct :orderList="orderList" :type="'order'" :showBtn="true"></HistoryProduct>
    <PageNationComponent :pageNationData="pageNationData" @currentPage="pageUpdate" />
  </article>
  <article v-else>
    <MypageEmptyComponent></MypageEmptyComponent>
  </article>
</template>

<style scoped>
.orderHistory_title {
  font-size: 3.1rem;
  font-weight: 700;
  border-bottom: solid 1px #000;
  padding: 20px 0;
  margin-top: 32px;
  /* margin-bottom: 10px; */
}
</style>