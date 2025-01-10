<template>
  <section>
    <article>
      <AnnouncementComponent />
    </article>
    <article id="sortByAndSearch">
      <select name="sortBy" id="sortBy" v-model="reviewSortBy">
        <option value="">전체</option>
        <option value="category">배송중</option>
        <option value="productName">배송완료</option>
      </select>
      <select name="pageSize" id="pageSize" v-model="pageSize">
        <option value="5">5개씩 보기</option>
        <option value="10">10개씩 보기</option>
        <option value="20">20개씩 보기</option>
      </select>
    </article>
    <template v-if="InspectionList.length > 0">
      <article id="Inspection" v-for="(item, index) in InspectionList" :key="index">
        <h1>검수상품 : {{ index + 1 }}</h1>
        <MainInspectionItemView :item="item" @dataUpdate="dataUpdate" />
      </article>
    </template>
    <template v-else>
      <article id="notList">
        <p>판매 신청 목록이 없습니다.</p>
        <img src="@/assets/img/icon/free-animated-icon-note-6172546.gif" alt="" />
      </article>
    </template>
    <article>
      <PageNationComponent v-if="totalCount > 0" :pageNationData="pageNationData" @currentPage="pageUpdate" />
    </article>
  </section>
</template>

<script setup>
import { GLOBAL_URL } from '@/api/util';
import AnnouncementComponent from '@/components/admin/AnnouncementComponent.vue';
import axios from 'axios';
import { reactive, ref, watchEffect } from 'vue';
import PageNationComponent from '@/components/PageNationComponent.vue';
import MainInspectionItemView from './MainInspectionItemView.vue';
import { getInspectionList, getInspectionListCount } from '@/api/InspectionListApi';

const totalCount = ref(0);
const pageNumber = ref(0);
const InspectionList = ref([]);
const pageSize = ref(5);
const reviewSortBy = ref('');

// 부모 컴포넌트의 반응형 변수가 바뀌면 자식 컴포넌트는 재랜더링이 된다.

const pageNationData = reactive({
  totalCount: totalCount.value,
  pageSize: pageSize.value,
});

// 페이지 넘버가 바뀌면 watchEffect에서 dolode를 실행한다.
const pageUpdate = pageNum => {
  pageNumber.value = pageNum;
  InspectionList.value = [];
};

const dataUpdate = () => {
  dolode();
};

const dolode = async () => {
  const inspectionListCountRes = await getInspectionListCount();
  totalCount.value = inspectionListCountRes.data;
  pageNationData.totalCount = totalCount.value;

  try {
    // 판매 신청 리스트
    const InspectionListRes = await getInspectionList(pageNumber.value, pageSize.value);

    InspectionList.value = InspectionListRes.data.map(item => ({
      ...item,
      brandKeyword: '',
      productKeyword: '',
      brandSuggestions: [],
      productSuggestions: [],
      selectedBrand: '',
      selectedProduct: [],
      productNameInput: '',
      brandNameInput: '',
      categoryId: 0,
      inspectionSize: 0,
      inspectionSellingPrice: 5000,
      TestResult: '',
      PassGrade: [],
      FailReason: [],
      Content: '',
    }));
    // console.log('InspectionList.value', InspectionList.value);
  } catch (error) {
    console.error('Error loading inspection list:', error);
  }
};
watchEffect(() => {
  pageNationData.pageSize = pageSize.value;
  pageNumber.value;
  dolode();
});
</script>

<style scoped>
#Inspection {
  background-color: white;
  width: 100%;
  height: auto;
  text-align: center;
  padding: 20px;
  border-radius: 15px;
  border: 3px solid #8f9d8d;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
  margin: 1% 0;
}
#Inspection > h1 {
  font-size: 2rem;
  padding-bottom: 5px;
}
#notList {
  background-color: white;
  width: 100%;
  border-radius: 15px;
  height: auto;
  text-align: center;
  padding: 10% 5%;
  align-content: center;
}
#notList > img {
  width: 40%;
  border-radius: 15px;
  height: auto;
  filter: opacity(0.7);
  align-content: center;
}
#notList > p {
  font-size: 2rem;
  color: var(--color-text-gray);
}
input,
select,
option {
  border: none;
  background-color: unset;
}
input:focus,
select:focus,
option:focus {
  outline: none;
}
#sortByAndSearch {
  display: flex;
  justify-content: left;
  gap: 1%;
  width: 100%;
  height: 30px;
  margin: 20px 0 10px 0;
}
#sortBy,
#pageSize {
  background-color: white;
  border-radius: 10px;
  padding: 0 10px 0 5px;
  align-items: center;
  min-width: 10%;
}
</style>
