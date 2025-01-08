<template>
  <section>
    <article>
      <AnnouncementComponent />
    </article>
    <article id="sortByAndSearch">
      <select name="sortBy" id="sortBy">
        <option value="total">전체</option>
        <!-- <option value="onSale">판매중</option>
        <option value="soldOut">품절</option>
        <option value="hiding">숨김</option>
        <option value="waitingForSale">판매대기</option>
        <option value="saleCompleted">판매완료</option> -->
      </select>
      <div id="search">
        <input type="search" id="productSearch" placeholder="상품명 검색" />
        <img class="searchIcon" src="@/assets/img/icon/free-icon-font-search-3917132.png" alt="productSearch" />
      </div>
    </article>
    <article id="Inspection">
      <table>
        <thead>
          <tr class="TableHeader">
            <th>No.</th>
            <th>카테고리</th>
            <th>브랜드명</th>
            <th colspan="2">상품명</th>
            <th>옵션</th>
            <th>재고수량</th>
          </tr>
        </thead>
        <tbody>
          <tr class="TableBody" v-for="(item, index) in categoryList" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ category[item.dtype] }}</td>
            <td>{{ item.brandName }}</td>
            <td><img class="productImages" :src="`${GLOBAL_URL}/api/file/download/${item.filename}`" alt="" /></td>
            <td class="productName">{{ item.productId }}. {{ item.productName }}</td>
            <td>{{ item.size.toLocaleString() }} ml</td>
            <td>{{ item.usedProductCount.toLocaleString() }}</td>
          </tr>
        </tbody>
      </table>
    </article>
    <article class="PageNation">
      <PageNationComponent :pageNationData="pageNationData" @currentPage="updatePage" />
    </article>
  </section>
</template>

<script setup>
import { getCategoryManagementList } from '@/api/AdministratorModeApi';
import { GLOBAL_URL } from '@/api/util';
import AnnouncementComponent from '@/components/admin/AnnouncementComponent.vue';
import PageNationComponent from '@/components/PageNationComponent.vue';
import { ref, watchEffect } from 'vue';

const category = {
  P: 'Perfume',
  C: 'Candle',
  D: 'Diffuser',
};

const categoryList = ref([]);
const totalCount = ref(0);
const size = ref(20);
const currentPage = ref(0);
const selectState = ref([]);
const pageNationData = ref();
const stateOption = [
  { name: '판매중', value: 'onSale' },
  { name: '품절', value: 'soldOut' },
  { name: '숨김', value: 'hiding' },
  { name: '판매대기', value: 'waitingForSale' },
  { name: '판매완료', value: 'saleCompleted' },
];

const updatePage = selectPage => {
  currentPage.value = selectPage;
};

const dolode = async () => {
  selectState.value = stateOption[0];
  const CategoryManagementListRes = await getCategoryManagementList(currentPage.value, size.value);
  categoryList.value = CategoryManagementListRes.categoryManageDtoPage;
  totalCount.value = CategoryManagementListRes.count;
  console.log('categoryList', totalCount.value, categoryList.value);

  pageNationData.value = {
    totalCount: totalCount.value,
    pageSize: size.value,
  };
};
watchEffect(() => {
  currentPage.value;
  dolode();
});
</script>

<style scoped>
#Inspection {
  background-color: white;
  width: 100%;
  height: auto;
  border-radius: 15px;
  padding: 5px;
  text-align: center;
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
  justify-content: space-between;
  width: 100%;
  height: 30px;
  margin: 20px 0 10px 0;
}
#sortBy,
#search {
  background-color: white;
  border-radius: 10px;
  padding: 0 10px 0 5px;
  align-items: center;
}
#sortBy {
  width: 150px;
}
#search {
  width: 30%;
  display: flex;
  justify-content: space-between;
}
#productSearch {
  width: 100%;
}
.searchIcon {
  width: 20px;
  height: 20px;
  cursor: pointer;
}
.TableHeader {
}
.productImages {
  width: 45px;
  height: 45px;
  padding: 2px;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 5px;
}
table {
  width: 100%;
  font-size: 1.4rem;
}
td {
  height: 50px;
  border-bottom: 0.5px solid var(--color-main-gray);
}
th {
  border-bottom: 2px solid var(--color-main-gray);
  height: 40px;
}
.TableBody > td:nth-child(4) {
  text-align: left;
}
.selectState {
  background-color: var(--color-main-pink);
  border-radius: 10px;
  padding: 5px;
}
.productName {
  text-align: left;
}
.PageNation {
}
</style>
