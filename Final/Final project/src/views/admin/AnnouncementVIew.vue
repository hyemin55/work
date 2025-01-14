<template>
  <section>
    <article id="sortByAndSearch">
      <!-- <div id="search">
        <input
          type="search"
          @input="fetchSuggestions()"
          v-model="emailSearchKeyword"
          id="titleSearch"
          placeholder="제목 또는 내용 검색"
        />
        <img class="searchIcon" src="@/assets/img/icon/free-icon-font-search-3917132.png" alt="productSearch" />
      </div> -->
      <div class="save" @click="openWritingModal">글쓰기</div>
    </article>

    <article id="announcementData">
      <table class="announcementDataTitle">
        <thead>
          <tr>
            <th>No.</th>
            <th>제목</th>
            <th>작성시간</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody v-for="(item, index) in announcementData" :key="index">
          <tr class="tableBody" @click.stop="toggleContent(item, index)" :class="{ Titleactive: viewContents[index] }">
            <td>{{ index + 1 }}</td>
            <td class="Title">
              {{ item.header }}
            </td>
            <td>{{ item.creationDate }}</td>
            <td>{{ item.views.toLocaleString() }}</td>
          </tr>
          <tr v-if="viewContents[index]" class="contents" :class="{ contentactive: viewContents[index] }">
            <td colspan="4">{{ item.content }}</td>
          </tr>
        </tbody>
      </table>
    </article>
    <article>
      <AnnouncementWritingModalComponent
        v-if="writingModal"
        :announcementDataCount="announcementDataCount"
        @closeModal="closeModal"
      />
    </article>
  </section>
</template>

<script setup>
import AnnouncementWritingModalComponent from '@/components/admin/AnnouncementWritingModalComponent.vue';
import { ref } from 'vue';

const announcementData = ref([]);
const viewContents = ref([false]);
const writingModal = ref(false);
const announcementDataCount = ref(announcementData.value.length);
const toggleContent = (item, index) => {
  if (!viewContents.value[index]) {
    item.views++;
  }
  viewContents.value[index] = true;
  for (let i = 0; i < announcementData.value.length; i++) {
    if (i !== index) {
      viewContents.value[i] = false;
    }
  }
  // console.log('2', viewContents.value);
};

const openWritingModal = () => {
  // console.log(announcementData.value.length);
  announcementDataCount.value = announcementData.value.length;
  writingModal.value = true;
};

const closeModal = addAnnouncement => {
  if (addAnnouncement === false) {
    writingModal.value = false;
  } else {
    // console.log('announcementData', announcementData.value);
    // console.log('addAnnouncement', addAnnouncement);
    announcementData.value.push(addAnnouncement);
    writingModal.value = false;
  }
};

announcementData.value = [
  {
    number: 1,
    header: '2025-02-01 ~ 2025-02-05 설연휴 배송 안내',
    content: '설연휴 기간 동안 배송이 지연될 수 있습니다. 고객님들의 양해 부탁드립니다.',
    creationDate: '2024-12-20. 오전 2:30',
    views: 150,
  },
  {
    number: 2,
    header: '새로운 디퓨저 브랜드 입점 안내',
    content: '프랑스 명품 디퓨저 브랜드가 새롭게 입점하였습니다. 특별 할인 이벤트를 확인하세요!',
    creationDate: '2024-12-21. 오전 8:50',
    views: 120,
  },
  {
    number: 3,
    header: '향수 샘플 무료 제공 이벤트',
    content: '구매 후 리뷰를 남기시면 다양한 향수 샘플을 무료로 제공합니다.',
    creationDate: '2024-12-22. 오전 9:50',
    views: 300,
  },
  {
    number: 4,
    header: '캔들 워크샵 신청 안내',
    content: '나만의 향기 캔들을 직접 만들어볼 수 있는 워크샵이 열립니다. 선착순 모집 중!',
    creationDate: '2024-12-23. 오전 8:10',
    views: 250,
  },
  {
    number: 5,
    header: '겨울 한정 디퓨저 출시',
    content: '겨울 감성을 담은 한정판 디퓨저가 출시되었습니다. 수량이 한정되어 있으니 서둘러주세요!',
    creationDate: '2024-12-24. 오전 8:25',
    views: 500,
  },
  {
    number: 6,
    header: '배송비 무료 프로모션 안내',
    content: '모든 주문에 대해 무료 배송 혜택을 제공하는 이벤트가 진행 중입니다.',
    creationDate: '2024-12-25. 오전 9:32',
    views: 200,
  },
  {
    number: 7,
    header: '향수 리필 서비스 시작',
    content: '사용 중인 향수를 리필할 수 있는 서비스가 추가되었습니다. 더 경제적으로 이용하세요!',
    creationDate: '2024-12-26. 오후 7:32',
    views: 180,
  },
  {
    number: 8,
    header: '정기 구독 서비스 오픈',
    content: '매달 새로운 향수를 받아볼 수 있는 정기 구독 서비스가 오픈되었습니다.',
    creationDate: '2024-12-27. 오후 2:32',
    views: 270,
  },
  {
    number: 9,
    header: '캔들 사용 주의사항 안내',
    content: '캔들 사용 시 안전을 위해 불꽃 관리 및 환기에 신경 써주세요. 자세한 내용은 공지사항을 확인하세요.',
    creationDate: '2024-12-28. 오전 2:00',
    views: 100,
  },
  {
    number: 10,
    header: '연말연시 할인 이벤트!',
    content: '향수, 캔들, 디퓨저 전 제품 최대 50% 할인! 2025년 1월 5일까지 진행됩니다.',
    creationDate: '2024-12-29. 오전 2:32',
    views: 600,
  },
];
</script>

<style scoped>
#sortByAndSearch {
  width: 100%;
}
input {
  border: none;
  background-color: unset;
}
input:focus {
  outline: none;
}

#sortByAndSearch {
  display: flex;
  justify-content: right;
  width: 100%;
  height: 30px;
  margin: 20px 0 10px 0;
  gap: 1%;
}
#search {
  background-color: white;
  border-radius: 10px;
  padding: 0 10px 0 5px;
  align-items: center;
  width: 30%;
  display: flex;
  justify-content: space-between;
}
#titleSearch {
  width: 100%;
}
.searchIcon {
  width: 20px;
  height: 20px;
  cursor: pointer;
}
.save {
  width: 10%;
  text-align: center;
  align-content: center;
  font-size: 1.8rem;
  color: white;
  border-radius: 10px;
  background-color: #8f9d8d;
  box-shadow: inset 2px 2px 4px rgb(255, 255, 255);
  cursor: pointer;
  transition: all 0.3s ease;
}
.save:hover {
  background-color: #627c85;
  box-shadow: inset 2px 2px 4px rgba(0, 0, 0, 0.2);
  transform: translateY(2px);
}
/* 공지사항 내용들 */
#announcementData {
  background-color: white;
  width: 100%;
  height: auto;
  border-radius: 20px;
  padding: 1%;
  min-height: calc(100vh - 150px);
}
.announcementDataTitle {
  width: 100%;
  height: auto;
  font-size: 1.4rem;
  text-align: center;
}
thead,
tbody {
  width: 100%;
}
th {
  border-bottom: 2px solid var(--color-main-gray);
  /* width: 100%; */
  height: 40px;
}
td {
  height: 50px;
  padding: 1%;
  border-bottom: 0.5px solid var(--color-main-gray);
}
.tableBody {
  cursor: pointer;
  z-index: 20;
  background-color: white;
}
.tableBody > td:nth-child(1),
.tableBody > td:nth-child(4) {
  width: 5%;
}
.tableBody > td:nth-child(2) {
  width: 75%;
}
.tableBody > td:nth-child(3) {
  width: 15%;
}
.tableBody:hover {
  background-color: #e9f2f6;
}
.Title,
.contents {
  text-align: left;
}
.contents {
  width: 100%;
  z-index: 10;
  background-color: white;
}
.Titleactive {
  border-top: 2px solid #507383;
  background-color: #e9f2f6;
}
.contentactive {
  border-bottom: 2px solid #507383;
  background-color: #e9f2f6;
}
</style>
