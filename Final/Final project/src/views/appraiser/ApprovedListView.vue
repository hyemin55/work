<template>
  <section>
    <article>
      <AnnouncementComponent />
    </article>
    <template v-if="acceptedList.length === 0">
      <article id="notList">
        <p>판매 신청 목록이 없습니다.</p>
        <img src="@/assets/img/icon/free-animated-icon-note-6172546.gif" alt="" />
      </article>
    </template>
    <template v-else>
      <article id="Inspection" v-for="(item, index) in acceptedList" :key="index">
        <table>
          <thead>
            <tr>
              <th>No.</th>
              <th>판매 신청자</th>
              <th>판매신청날짜</th>
              <th>배송도착일</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ item.pendingSaleId }}</td>
              <td>{{ item.seller }}</td>
              <td>{{ dateTimeFormat(item.registerDate) }}</td>
              <td>{{ dateTimeFormat(item.shippedDate) }}</td>
            </tr>
          </tbody>
          <thead>
            <tr>
              <th>카테고리</th>
              <th>브랜드</th>
              <th>상품명</th>
              <th>용량 (ml)</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ item.verifiedCategory }}</td>
              <td>{{ item.verifiedBrand }}</td>
              <td>{{ item.productName }}</td>
              <td>{{ item.verifiedSize.toLocaleString() }} ml</td>
            </tr>
          </tbody>
          <thead>
            <tr>
              <th>희망판매가격</th>
              <th>권장판매가격</th>
              <th colspan="2">판매자 상세설명</th>
            </tr>
          </thead>

          <tbody>
            <tr>
              <td>{{ item.userPrice.toLocaleString() }} 원</td>
              <td>{{ item.verifiedPrice.toLocaleString() }} 원</td>
              <td colspan="2" v-if="item.productDescription === ''">-</td>
              <td colspan="2" v-else>{{ item.productDescription }}</td>
            </tr>
          </tbody>
          <thead>
            <tr>
              <th>사용 유무</th>
              <th>판매 등급</th>
              <th colspan="2">검수결과 참고사항</th>
            </tr>
          </thead>

          <tbody>
            <tr>
              <td>{{ item.verifiedUsedOrNot ? '중고상품' : '새상품' }}</td>
              <td>{{ item.gradeType }}</td>
              <td colspan="2" v-if="item.inspectionDescription === ''">-</td>
              <td colspan="2" v-else>{{ item.inspectionDescription }}</td>
            </tr>
          </tbody>
          <thead>
            <tr>
              <th>대표사진</th>
              <th colspan="2">등록사진</th>
              <th>판매현황</th>
            </tr>
          </thead>

          <tbody>
            <tr class="images">
              <td><img :src="`${GLOBAL_URL}/api/file/download/${item.representativeImage}`" alt="" /></td>
              <td colspan="2">
                <img
                  v-for="(userImage, index) in item.userImages"
                  :key="index"
                  :src="`${GLOBAL_URL}/api/file/download/${userImage.filename}`"
                  alt=""
                />
                <img
                  v-for="(verifiedImage, index) in verifiedImages"
                  :key="index"
                  :src="`${GLOBAL_URL}/api/file/download/${verifiedImage.filename}`"
                  alt=""
                />
              </td>
              <td>{{ statusMap[item.inspectionStatus] }}</td>
            </tr>
          </tbody>
        </table>
      </article>
    </template>
    <article class="PageNation" v-if="acceptedList.length > 0">
      <PageNationComponent :pageNationData="pageNationData" @currentPage="pageUpdate" />
    </article>
  </section>
</template>

<script setup>
import { getAcceptedList } from '@/api/InspectionListApi';
import { GLOBAL_URL } from '@/api/util';
import AnnouncementComponent from '@/components/admin/AnnouncementComponent.vue';
import PageNationComponent from '@/components/PageNationComponent.vue';
import { dateTimeFormat } from '@/FormatData';
import { ref, watchEffect } from 'vue';

const acceptedList = ref([]);
const pageNationData = ref();
const pageNumber = ref(0);
const totalCount = ref(5);
const pageSize = ref(5);

const pageUpdate = pageNum => {
  pageNumber.value = pageNum;
};

const pageNation = () => {
  pageNationData.value = {
    totalCount: totalCount.value,
    pageSize: pageSize.value,
  };
};

const statusMap = {
  WAITING: '판매대기',
  INSPECTING: '검수 중',
  ACCEPTED: '검수통과',
  REJECTED: '검수반려',
  SELLING: '판매 중',
  SOLD: '판매완료',
};

const dolode = async () => {
  const acceptedListRes = await getAcceptedList();
  acceptedList.value = acceptedListRes;
  pageNation();
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
  text-align: center;
  padding: 20px;
  margin: 30px 0 0 0;
  border-radius: 15px;
  border: 3px solid #8f9d8d;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
}
#notList {
  background-color: white;
  width: 100%;
  border-radius: 15px;
  height: auto;
  text-align: center;
  padding: 10% 5%;
  align-content: center;
  margin-top: 30px;
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
table {
  width: 100%;
  font-size: 14px;
  color: #333;
}
th {
  width: 15%;
  padding: 10px;
  background-color: var(--color-main-Lgray);
  /* background-color: #a7b4a8 ; */
  /* border-bottom: 0.5px solid #e5e5e5; */
  border-right: 2px solid white;
}
th:last-child{
  border-right:none
}
td {
  width: 15%;
  text-align: center;
  padding: 20px 10px;
  margin: 5px 0;
  border-right: 2px solid var(--color-main-Lgray);
}
td:last-child{
  border-right:none
}
img {
  width: 100px;
}
.PageNation {
  padding-bottom: 30px;
}
</style>
