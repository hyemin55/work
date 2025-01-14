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
      <!-- <div id="search">
        <input type="search" id="productSearch" placeholder="상품명 검색" />
        <img class="searchIcon" src="@/assets/img/icon/free-icon-font-search-3917132.png" alt="productSearch" />
      </div> -->
    </article>
    <article id="Inspection">
      <table>
        <thead>
          <tr class="TableHeader">
            <th>No.</th>
            <th>카테고리</th>
            <th>브랜드</th>
            <th colspan="2">상품명</th>
            <th>옵션</th>
            <th>판매가</th>
            <th>상태</th>
            <th>재고</th>
            <th>등록일</th>
            <th>수정일</th>
          </tr>
        </thead>
        <tbody>
          <tr class="TableBody" v-for="(item, index) in productList" :key="index" >
            <td class="usedProductId">{{ item.usedProductId }}</td>
            <td>{{ item.category }}</td>
            <td>{{ item.brandName }}</td>
            <td colspan="2" class="productName" @click="goProductsDetail(item)">
              <span
                ><img class="productImages" :src="`${GLOBAL_URL}/api/file/download/${item.filename}`" alt="" />
                {{ item.productName }}</span
              >
            </td>
            <td>{{ item.productSize }} ml</td>
            <td>{{ item.productPrice.toLocaleString() }} 원</td>
            <td>
              {{ stateOption[stateOptionNumber(item.status)].name }}
              <!-- <select name="state" id="" v-model="selectState[item.status]" class="selectState">
                <option :value="list[index]?.name" v-for="(list, index) in stateOption" :key="index">
                  {{ list.name }}
                </option>
              </select> -->
            </td>
            <td>1</td>
            <td>{{ dateTimeFormat(item.createdDate) }}</td>
            <td v-if="item.modifiedDate">{{ dateTimeFormat(item.modifiedDate) }}</td>
            <td v-else>-</td>
          </tr>
        </tbody>
      </table>
    </article>
    <article>
      <PageNationComponent :pageNationData="pageNationData" @currentPage="updatePage" />
    </article>
  </section>
</template>

<script setup>
import { getProductManagementList } from '@/api/AdministratorModeApi';
import AnnouncementComponent from '@/components/admin/AnnouncementComponent.vue';
import PageNationComponent from '@/components/PageNationComponent.vue';
import { GLOBAL_URL } from '@/api/util';
import { ref, watchEffect } from 'vue';
import { dateTimeFormat } from '@/FormatData';
import { useRouter } from 'vue-router';

const router = useRouter()
const productList = ref([]);
const totalCount = ref(0);
const size = ref(20);
const currentPage = ref(0);
const selectState = ref([]);
const pageNationData = ref();
const stateOption = [
  { name: '판매중', value: 'SELLING' },
  { name: '품절', value: 'SOLD' },
  { name: '숨김', value: 'hiding' },
  { name: '판매대기', value: 'WAITING' },
  { name: '판매완료', value: 'saleCompleted' },
];

// item의 상태값에따라 동적키 매칭하여 이름 할당
const stateOptionNumber = value => {
  for (let i = 0; stateOption.length > i; i++) {
    if (stateOption[i].value === value) {
      // console.log(i);
      return i;
    } else {
      // console.log('매칭되는 상태값이 없습니다.');
    }
  }
};

const updatePage = selectPage => {
  currentPage.value = selectPage;
};

const goProductsDetail = (item)=>{
router.push({path:`/productsdetail/${item.usedProductId}`})
}

const dolode = async () => {
  selectState.value = stateOption[0];
  const productManagementListRes = await getProductManagementList();
  productList.value = productManagementListRes.productManageDtos.content;
  totalCount.value = productManagementListRes.productCount;
  // console.log('productList', totalCount.value, productList.value);

  pageNationData.value = {
    totalCount: totalCount.value,
    pageSize: size.value,
  };
};

watchEffect(() => {
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
/* 정렬, 검색 설정 */
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

/* 테이블 설정 */
table {
  width: 100%;
  font-size: 1.4rem;
}
tr {
  width: 100%;
}
th {
  border-bottom: 2px solid var(--color-main-gray);
  /* border-right: 0.5px solid var(--color-main-gray); */
  padding: 0 1%;
  height: 40px;
}
.TableHeader th:last-child {
  border-right: none;
}
td {
  width: auto;
  height: 50px;
  padding: 0 1%;
  border-bottom: 0.5px solid var(--color-main-gray);

  /* border-right: 0.5px solid var(--color-main-gray); */
}
.TableBody td:last-child {
  border-right: none;
}
.TableBody:hover {
  background-color: var(--color-main-Lgray);
}
.productName {
  text-align: left;
  cursor: pointer;
}
.productName span {
  display: flex;
  align-items: center;
  gap: 5px;
}
.productImages {
  width: 45px;
  height: 45px;
  padding: 2px;
  background-color: white;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 5px;
}
/* .selectState {
  background-color: var(--color-main-pink);
  border-radius: 10px;
  padding: 5px;
} */
</style>
