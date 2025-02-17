<script setup>
import { GLOBAL_URL } from '@/api/util';
import ProductComponent from '@/components/user/ProductComponent.vue';
import { useInfiniteQuery } from '@tanstack/vue-query';
import axios from 'axios';
import { computed, onMounted, ref, watch, watchEffect } from 'vue';
import { onBeforeRouteLeave, useRoute } from 'vue-router';
import ProductFilter from '@/views/user/product/productFilter.vue';

// const sortedList = ref([])  // 정렬된 리스트
const sortTitle = ref('추천순 ⇅');
const hiddenItem = ref(0);
const totalDataLength = ref(0);

const route = useRoute();
const categoryTitle = computed(() => route.params.title);
const categoryId = computed(() => route.params.idx);
const loadingUi = ref(null); // 로딩 UI지정

// 무한 스크롤 데이터 패칭 함수
const fetchCategoryData = async ({ pageParam = 0 }) => {
  const res = await axios.get(`${GLOBAL_URL}/api/categories/${categoryId.value}?pageNum=${pageParam}`);
  const data = res.data;
  return { data, nextPage: data.length > 0 ? pageParam + 1 : undefined };
};
// useInfiniteQuery로 무한 스크롤 쿼리 설정
const {
  data: list,
  fetchNextPage, // (호출시)자동으로 페이지를 증가시킨다. (이 부분 증가시 getNextPageParam 자동으로 작동)
  hasNextPage, // 다음페이지가 있는지 확인하는 변수
  isFetchingNextPage, // 로딩중인지 확인한는 변수
} = useInfiniteQuery(['categoryData', categoryId], fetchCategoryData, {
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

// 정렬 기준을 바꿀 때 호출되는 함수
const sortList = (order, index) => {
  hiddenItem.value = index;
  switch (order) {
    case 'latestDate':
      sortTitle.value = '최신순';
      break;
    case 'oldeDate':
      sortTitle.value = '오래된 순';
      break;
    case 'highPrice':
      sortTitle.value = '높은 가격순';
      break;
    case 'lowPrice':
      sortTitle.value = '낮은 가격순';
      break;
    case 'basic':
    default:
      sortTitle.value = '추천순 ⇅';
  }
};

const filterData = ref([]);
const setFilter = ()=>{
  filterData.value = [];
  if(categoryId.value === "3"){
    filterData.value = [{
      id: 1,
      title: '브랜드',
      content: ['Ferrari', 'Chanel', 'Versace', 'Jomalone', 'Cocodor', 'Diptyque', 'Santa Maria Novella', 'Tom Ford', 'Comme Des Garcons Parfum', 'Dior'],
    },
    {
      id: 2,
      title: '성별',
      content: ['남성용', '여성용'],
    },
    {
      id: 3,
      title: '용량',
      content: ['100ml 이상', '100ml ~ 50ml', '50ml 이하'],
    },
    {
      id: 4,
      title: '지속시간',
      content: ['파르푕', '오드파르푕', '오드뜨왈렛', '오드코롱'],
    }]
  }
  else if(categoryId.value === "2"){
    filterData.value = [{
      id: 5,
      title: '브랜드',
      content: ['Jomalone', 'Diptyque', 'Annunziata'],
    },
    {
      id: 6,
      title: '용도',
      content: ['실내용 ', '차량용', '휴대용', '욕실용'],
    },
    {
      id: 7,
      title: '용량',
      content: ['100ml 이상', '100ml ~ 50ml', '50ml 이하'],
    }]
  }
  else if(categoryId.value === "1"){
    filterData.value = [{
      id: 8,
      title: '브랜드',
      content: ['Yankee Candle', 'Dolce & Gabbana',],
    },
    {
      id: 9,
      title: '성별',
      content: ['남성용', '여성용'],
    },
    {
      id: 10,
      title: '용량',
      content: ['100g 이상', '100g ~ 50g', '50g 이하'],
    }]
  }
}
  
onMounted(()=>{
  setFilter();
})
watch(categoryId, (newValue, oldValue) =>{
  console.log("categoryId 값이 변경되었습니다:", newValue);
  // filterMode.value = false
  // filterLayout.value = true
  // ball.value = false;
  setFilter();
})


const ball = ref(false)
const filterLayout = ref(true) //상품 레이아웃 변경
const filterMode = ref(false) //필터 존재여부 변경
const chageMode = ()=>{
  filterMode.value = !filterMode.value;
  filterLayout.value = !filterMode.value;
  ball.value = !ball.value;
}
</script>

<template>
  <section id="product_wrapper" class="scroll-target">
    <article class="product_gnb">
      <h1 class="product_category_title">{{ categoryTitle }} {{ categoryId == 4 ? '검색결과' : undefined }}</h1>

      <div class="filter_btn">
        <p class="filter_btn_text">
          <img src="/src/assets/img/free-icon-font-filter.svg" alt="">
        </p>
        <div class="filter_btn_switch" @click="chageMode">
          <p class="filter_ball" :class="{'ball_x0' : !ball, 'ball_x24': ball}"></p>
          <!-- <p class="onoffText"><span>off</span><span>on</span></p> -->
        </div>
      </div>

      <div class="product_dropdown">
        <p class="product_mount">총 '{{ totalDataLength }}개' 제품</p>
        <div class="sort_container">
          <p class="sort_trigger">{{ sortTitle }}</p>
          <ul class="product_sort">
            <li @click="sortList('basic', 0)" :style="{ display: hiddenItem === 0 ? 'none' : '' }">추천순 ⇅</li>
            <li @click="sortList('latestDate', 1)" :style="{ display: hiddenItem === 1 ? 'none' : '' }">최신순</li>
            <li @click="sortList('oldeDate', 2)" :style="{ display: hiddenItem === 2 ? 'none' : '' }">오래된 순</li>
            <li @click="sortList('highPrice', 3)" :style="{ display: hiddenItem === 3 ? 'none' : '' }">높은 가격순</li>
            <li @click="sortList('lowPrice', 4)" :style="{ display: hiddenItem === 4 ? 'none' : '' }">낮은 가격순</li>
          </ul>
        </div>
      </div>
    </article>

    <article class="product_list" v-if="list && list.pages && list.pages[0] && categoryId.value !== 4 " >
      <div class="product_fillter" v-if="filterMode">
        <form class="product_fillter_form">
          <ProductFilter v-for="filterData in filterData" :key="filterData.id" :filterInfo="filterData"> </ProductFilter>
        </form>
      </div>

      <div class="product_list_grid" :class="{'onFilter': filterLayout, 'offFilter': !filterLayout}">
        <ProductComponent v-for="product in list.pages.flatMap(page => page.data)" :key="product.productId" :productInfo="product" />
      </div>
    </article>

    <h1 class="loadingUi" ref="loadingUi" v-if="hasNextPage">
      <img src="../../assets/img/icon/loading.gif" alt="" />
    </h1>
  </section>
</template>

<style scoped>
/* 전체설정 */
#product_wrapper {
  max-width: var(--main-max-width);
  width: 100%;
  margin: 0 auto;
}
/* 상품 gnd 설정 */
.product_gnb {
  position: relative;
  width: 100%;
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.product_category_title {
  font-family: var(--font-JacquesFrancois);
  font-size: 2.8rem;
}

/* dropdown 메뉴 설정 */
.product_dropdown {
  position: absolute;
  bottom: 0;
  right: 0;
  display: flex;
  align-items: center;
}
.product_mount {
  font-size: 1.2rem;
  margin-right: 1.2rem;
}
.sort_container {
  cursor: pointer;
}
.product_sort {
  display: none;
  position: absolute; /* 드롭다운 위치 조정 */
  top: 100%; /* 드롭다운을 바로 아래에 표시 */
  right: 0; /* 왼쪽 정렬 */
  background-color: white;
  border: 1px solid #ccc;
  z-index: 900;
  width: 75px;
  text-align: center;
}
.sort_trigger {
  width: 75px;
  height: 28px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
  background-color: #ccc;
}
.sort_container:hover .product_sort {
  display: block;
}
.product_sort li {
  padding: 10px;
}
.product_sort li:hover {
  background-color: #f0f0f0;
}

.filter_btn{
  position: absolute;
  bottom: 0;
  left: 0;
  width: 130px;
  height: 28px;
  display: flex;
  align-items: center;
}
.filter_btn_text{
  height: 100%;
  width: 30px;
  display: flex;
  align-items: center;
  font-size: 1.5rem;
}
.filter_btn_text img{
  height: 80%;
  margin-right: 2.5px;
}
.filter_btn_switch{
  position: relative;
  height: 100%;
  width: 52px;
  cursor: pointer;
  background-color: rgb(197, 33, 33);
  border-radius: 1.5rem;
  padding: 4px;
  box-shadow: inset 1px 1px 5px 2px var(--color-main-bloode);
}
.filter_ball{
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: aliceblue;
  transition: transform 0.5s;
  box-shadow: -1px -1px 4px #ffffff73, 2px 2px 3px rgba(94, 104, 121, .288);
}
.ball_x0{transform: translateX(0);}
.ball_x24{transform: translateX(24px);}

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

/* 상품 리스트 설정 */
.product_list {
  width: 100%;
  display: flex;
}
.product_fillter {
  position: relative;
  width: 325px;
  height: auto;
  margin-top: 10px;
}
.product_fillter_form {
  position: absolute;
  top: 0;
  left: 0;
  width: 285px;
  height: 700px;
  /* background-color: #e9e9e9; */
  /* background-color: indianred; */
}
.product_list_grid {
  height: auto;
  display: grid;
  gap: 20px;
}
.onFilter{
  width: calc(100%);
  grid-template-columns: repeat(4, 1fr);
}
.offFilter{
  width: calc(100% - 325px);
  grid-template-columns: repeat(3, 1fr);
}

/* 미디어쿼리 구간 */
@media (max-width: 630px) {
  .product_list {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
