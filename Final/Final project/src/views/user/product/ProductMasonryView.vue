<script setup>
import { GLOBAL_URL } from '@/api/util';
import MasonryComponent from '@/components/user/MasonryComponent.vue';
import { useInfiniteQuery } from '@tanstack/vue-query';
import axios from 'axios';
import { onMounted, ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const productId = route.params.idx
const title = route.query.title;
const brand = route.query.brand;

// 여기서 상품 카테고리에 id를 받아 통신 -> 통신으로 productData 값을 props로 전달.
// const productData = ref([])
// const fetchProductData = async()=>{
//   try{
//     const res = await axios.get(`${GLOBAL_URL}/api/used-product/list/${productId}`, {
//       headers:{
//         'Content-Type': 'application/json'
//       }
//     })
//     console.log(res.data)
//     productData.value = res.data
//   }catch(error){
//     console.error(error)
//   } 
// }
// onMounted(()=>{
//   fetchProductData();
// })
const totalDataLength = ref(0);
let mode = ref(true);
const changeMode = () => {
  mode.value = !mode.value
}
// // 무한 스크롤
const loadingUi = ref(null); // 로딩 UI지정
// 무한 스크롤 데이터 패칭 함수
const fetchItemData = async ({ pageParam = 0 }) => {
  const res = await axios.get(`${GLOBAL_URL}/api/used-product/list/${productId}?pageNum=${pageParam}`, {
      headers:{
        'Content-Type': 'application/json'
      }
  })
  const data = res.data;
  return { data, nextPage: data.length > 0 ? pageParam + 1 : undefined };
};
// useInfiniteQuery로 무한 스크롤 쿼리 설정
const {
  data: list,
  fetchNextPage, // (호출시)자동으로 페이지를 증가시킨다. (이 부분 증가시 getNextPageParam 자동으로 작동)
  hasNextPage, // 다음페이지가 있는지 확인하는 변수
  isFetchingNextPage, // 로딩중인지 확인한는 변수
} = useInfiniteQuery(['itemData', productId], fetchItemData, {
  getNextPageParam: lastPage => lastPage.nextPage,
  refetchOnWindowFocus: false,
  cacheTime: 1000 * 60 * 10, // 데이터 캐싱 시간 설정
});
watchEffect(() => {
  if (list.value && list.value.pages && list.value.pages.length > 0) {
    totalDataLength.value = list.value.pages.reduce((total, page) => total + page.data.length, 0);
  }
});
// IntersectionObserver로 ui가 뷰포트에 걸리시 페이지 증가
watchEffect(() => {
  const observer = new IntersectionObserver(entries => {
    const firstEntry = entries[0];
    if (firstEntry.isIntersecting && hasNextPage.value && !isFetchingNextPage.value) {
      fetchNextPage();
    }
  });
  if (loadingUi.value) {
    observer.observe(loadingUi.value);
  }
  return () => {
    if (loadingUi.value) {
      observer.unobserve(loadingUi.value);
    }
  };
});
</script>

<template>
    <section v-if="totalDataLength>0" class="masonry_wrapper_section">
      <article class="masonry_filter">
          <div class="title">
            <p class="title_brand">{{ brand }}</p>
            <p class="title_title">{{ title }}</p>
          </div>
          <div class="custom-select">
              <div>
                <select>
                  <option value="" disabled selected>정렬</option>
                  <option value="1">최신순</option>
                  <option value="2">낮은 가격순</option>
                  <option value="3">높은 가격순</option>
                </select>
                <select>
                  <option value="" disabled selected>상품상태</option>
                  <option value="1">A 등급</option>
                  <option value="2">B 등급</option>
                  <option value="3">C 등급</option>
                  <option value="3">D 등급</option>
                  <option value="3">E 등급</option>
                </select>
                <select>
                  <option value="" disabled selected>용량</option>
                  <option value="1">옵션 1</option>
                  <option value="2">옵션 2</option>
                  <option value="3">옵션 3</option>
                </select>
              </div>
              <div class="layout_style" @click="changeMode">
                보기형식 : {{ mode ? '큰 이미지' : '리스트' }}
                <img v-if="mode" src="/src/assets/img/free-icon-font-iconType.svg" alt="">
                <img v-else src="/src/assets/img/free-icon-font-listType.svg" alt="">
              </div>
            </div>
        </article>

        <article class="masonry_layout" :class="{'iconStyle':mode , 'listStyle':!mode}">
            <!-- <MasonryComponent v-for="data in productData" :key="data.usedProductId" :productInfo="data" :layoutType="mode"></MasonryComponent> -->
            <MasonryComponent v-for="data in list.pages.flatMap(page => page.data)" :key="data.usedProductId" :productInfo="data" :layoutType="mode"></MasonryComponent>
        </article>

        <h1 class="loadingUi" ref="loadingUi" v-if="hasNextPage">
          <img src="/src/assets/img/icon/loading.gif" alt="" />
        </h1>
    </section>

    <section v-else class="tungtung_section">
      <img src="/src/assets/img/perfum_tung.png" alt="">
      <h2>판매중인 상품이 없습니다.</h2>
    </section>

</template>

<style scoped>
/* 전체설정 */
.masonry_wrapper_section {
  max-width: var(--main-max-width);   /* 1280 */
  width: 100%;
  margin: 0 auto;
}
.layout_style{
  font-size: 1.7rem;
  font-weight: 800;
  cursor: pointer;
}
.layout_style img{
  margin-left: 3px;
  width: 22px;
}
.layout_style:hover{
  color: var(--color-main-bloode);
}

/* 필터 설정 */
.masonry_filter{
    width: 100%;
    padding: 20px 0;
}
.title{
  display: flex;
  align-items: center;
  letter-spacing: -0.034rem;
  padding: 22px 0;
  border-bottom: 1px solid #9e9e9e;
}
.title>p{
  margin-right: 0.4rem;
}
.title_title{
  font-size: 3rem;
  font-weight: 600;
  padding: 10px;
}
.title_brand{
  /* background-color: black; */
  background-color: var(--color-main-bloode);
  font-size: 1.5rem;
  color: #fff;
  padding: 10px;
  display: inline;
}
/* Select 컨테이너 스타일 */
.custom-select{
  display: flex;
  align-items: end;
  justify-content: space-between;
}
.custom-select select {
  width: 200px;               /* 드롭다운 너비 */
  height: 40px;               /* 드롭다운 높이 */
  padding: 5px 10px;          /* 내부 여백 */
  font-size: 16px;            /* 글꼴 크기 */
  color: #333;                /* 텍스트 색상 */
  background-color: #f9f9f9;  /* 배경색 */
  border: 1px solid #ccc;     /* 테두리 */
  border-radius: 5px;         /* 모서리 둥글게 */
  /* appearance: none; */
  outline: none;              /* 포커스 시 외곽선 제거 */
  cursor: pointer;            /* 마우스 커서 변경 */
  margin-right: 10px;
  margin-top: 25px;
}
.custom-select select:focus {
  border-color: var(--color-main-bloode); 
  box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
}

/* 레이아웃 설정 */
.masonry_layout{
  width: 100%;
}
.iconStyle{
  display: grid;
  gap: 25px;
  grid-template-columns: repeat(3, 1fr)
}
/* .listStyle{} */


.tungtung_section{
  width: 100%;
  height: calc(100vh - 100px);
  display: flex;
  align-items: center;
  justify-content: center;
}
.tungtung_section img{
  width: 220px;
  height: auto;
}
.tungtung_section h2{
  font-size: 4rem;
  color: var(--color-text-gray);
}

/* 로딩 UI설정 */
.loadingUi {
  width: 100%;
  height: 65px;
  text-align: center;
  margin-top: 50px;
}
.loadingUi img {
  height: 100%;
  width: auto;
}
</style>