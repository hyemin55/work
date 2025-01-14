<template>
  <section>
    <article>
      <AnnouncementComponent />
    </article>
    <article id="sortByAndSearch">
      <select name="sortBy" id="sortBy">
        <option value="total">전체</option>
        <!-- <option value="PaymentCompleted">결제완료</option>
        <option value="InDelivery">배송중</option>
        <option value="DeliveryCompleted">배송완료</option>
        <option value="PurchaseConfirmation">구매확정</option>
        <option value="CancelPurchase">구매취소</option> -->
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
            <th colspan="2">상품명</th>
            <th>옵션</th>
            <th>판매가</th>
            <th>카테고리</th>
            <th>상태</th>
            <th>수량</th>
            <th>주문일</th>
            <th>배송완료일</th>
            <th>구매확정</th>
            <th>구매자</th>
            <th>정산</th>
          </tr>
        </thead>
        <tbody v-for="(item, index) in orderList" :key="index">
          <tr class="TableBody">
            <td>{{ item.purchaseProductId }}</td>
            <td><img class="productImages" :src="`${GLOBAL_URL}/api/file/download/${item.filename}`" alt="" /></td>
            <td>{{ item.productName }}</td>
            <td>{{ item.productSize }} ml</td>
            <td>{{ item.price.toLocaleString() }} 원</td>
            <td>{{ item.category }}</td>
            <td>{{ item.purchaseStatus }}</td>
            <td>1</td>
            <td>{{ dateTimeFormat(item.purchaseCreationDate) }}</td>
            <td>{{ item.deliveredDate === null ? '-' : dateTimeFormat(item.deliveredDate) }}</td>
            <td>{{ item.deliveredDate === null ? '-' : dateTimeFormat(item.deliveredDate) }}</td>
            <td>{{ item.nickName }}</td>
            <td>N</td>
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
import { getOrderManagementList } from '@/api/AdministratorModeApi';
import { GLOBAL_URL } from '@/api/util';
import PageNationComponent from '@/components/PageNationComponent.vue';
import AnnouncementComponent from '@/components/admin/AnnouncementComponent.vue';
import { dateTimeFormat } from '@/FormatData';
import { ref } from 'vue';

const orderList = ref([]);
const totalCount = ref(0);
const selectState = ref([]);
const size = ref(20);
const pageNationData = ref();
const currentPage = ref(0);
const stateOption = [
  { value: 'PaymentCompleted', name: '결제완료' },
  { value: 'InDelivery', name: '배송중' },
  { value: 'DeliveryCompleted', name: '배송완료' },
  { value: 'PurchaseConfirmation', name: '구매확정' },
  { value: 'CancelPurchase', name: '구매취소' },
];

const updatePage = selectPage => {
  currentPage.value = selectPage;
};

const dolode = async () => {
  selectState.value = stateOption[0];
  const OrderManagementListRes = await getOrderManagementList();
  orderList.value = OrderManagementListRes.content;
  totalCount.value = OrderManagementListRes.totalElements;
  // console.log('orderList', orderList.value);
  // console.log('totalCount', totalCount.value);
  pageNationData.value = {
    totalCount: totalCount.value,
    pageSize: size.value,
  };
};
dolode();
</script>

<style scoped>
#Inspection {
  background-color: white;
  width: 100%;
  height: auto;
  border-radius: 15px;
  padding: 5px 5px 15px 5px;
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

table {
  width: 100%;
  font-size: 1.4rem;
}
td {
  height: 50px;
  padding: 0 1%;
  border-bottom: 0.5px solid var(--color-main-gray);
}
th {
  border-bottom: 2px solid var(--color-main-gray);
  height: 40px;
}
.TableBody:hover {
  background-color: var(--color-main-Lgray);
}
.TableBody > td:nth-child(3) {
  text-align: left;
}
.productImages {
  width: 45px;
  height: 45px;
  padding: 2px;
  background-color: white;
  border: 0.5px solid var(--color-main-Lgray);
  border-radius: 5px;
}
.selectState {
  background-color: var(--color-main-pink);
  border-radius: 10px;
  padding: 5px;
}
</style>
