<!--
props = 부모 컴포넌트에서 pageNationData에 아래의 데이터 내용을 넣고 props로 보내준다.
  pageNationData.value = {
    totalCount: totalCount.value,
    pageSize: pageSize.value,
  };

emit = 아래의 이름으로 부모컴포넌트에서 emit을 받아주고 페이지번호를 수정해 서버와 재통신한다.
  'currentPage'
 -->
<template>
  <article>
    <ul id="totalPages" v-if="pageNationData.totalCount > 0">
      <li @click="backPage">이전</li>
      <li
        class="totalPages"
        v-for="pageNum in endPage - startPage + 1"
        v-bind:key="pageNum"
        @click="goToPage(startPage + pageNum - 1)"
        :class="{ active: activePage(pageNum) }"
      >
        {{ pageNum + startPage - 1 }}
      </li>
      <li @click="nextPage">다음</li>
    </ul>
  </article>
</template>

<script setup>
import { ref, watchEffect } from 'vue';

const props = defineProps({
  pageNationData: {
    type: Object,
    required: true,
  },
});

const emit = defineEmits(['currentPage']);
const pageSize = ref(0);
const totalCount = ref(0);
const currentPage = ref(1);
const totalPages = ref(10);
const currentPageGroup = ref(0);
let flag = 0;
const totalPageGroup = ref(0);
const startPage = ref(0);
const endPage = ref(0);

// 이전페이지
const backPage = () => {
  if (currentPageGroup.value <= 0) {
    console.log('첫페이지입니다.');
    alert('첫페이지입니다.');
    return;
  }
  currentPage.value = startPage.value - 10;
  viewCurrentPage();
};

// 다음페이지
const nextPage = () => {
  if (currentPageGroup.value >= totalPageGroup.value) {
    console.log('마지막페이지입니다.');
    alert('마지막페이지입니다.');
    return;
  }
  currentPage.value = endPage.value + 1;
  viewCurrentPage();
};

// 선택페이지
const goToPage = page => {
  console.log('page', page);
  if (currentPage.value == page) {
    console.log('현재페이지입니다.');
    return;
  }
  currentPage.value = page;
  viewCurrentPage();
};

// 현재페이지
const viewCurrentPage = () => {
  currentPageGroup.value = Math.floor((currentPage.value - 1) / 10);
  if (currentPageGroup.value == currentPage.value - 1 && flag) {
    flag = true;
    return;
  } else {
    totalPages.value = Math.ceil(totalCount.value / pageSize.value);
    totalPageGroup.value = Math.floor(totalPages.value / 10);
    startPage.value = currentPageGroup.value * 10 + 1;
    endPage.value = Math.min(startPage.value + 9, totalPages.value);
    // console.log(totalCount);
    emit('currentPage', currentPage.value - 1);
  }
};

// 선택된 페이지번호에 CSS 설정
const activePage = pageNum => {
  if (currentPageGroup.value <= 0) {
    return currentPage.value === pageNum;
  } else {
    return currentPage.value - 1 - currentPageGroup.value * 10 === pageNum - 1;
  }
};
const dolode = () => {
  totalCount.value = props.pageNationData.totalCount;
  console.log('totalCount', totalCount.value);
  pageSize.value = props.pageNationData.pageSize;
  viewCurrentPage();
};
watchEffect(() => {
  dolode();
  console.log(props.pageNationData);
});
</script>

<style scoped>
#totalPages {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 20px;
  /* background-color: rgb(161, 160, 158); */
  font-size: 1.3rem;
  /* gap: 1%; */
}
#totalPages li {
  cursor: pointer;
  padding: 1%;
}
.totalPages {
  /* background-color: rgb(236, 207, 172); */
  display: flex;
  align-items: center;
  justify-content: center;
  /* width: 10%; */
  /* margin: 0 1%; */
}
.totalPages.active {
  color: var(--color-main-bloode);
  font-weight: 600;
  text-decoration: underline;
}
</style>
