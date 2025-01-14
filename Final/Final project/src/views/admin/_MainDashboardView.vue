<template>
  <section id="dashboard" v-if="joinCount && logInCount && orderList && pendingList && reviewList">
    <!-- 상단 왼쪽 / 구매 리스트 -->
    <article id="orderList">
      <div class="orderListTitle">
        <h1>구매 리스트</h1>
        <p class="underline"><router-link to="/orderManagement">더보기▸</router-link></p>
      </div>
      <ul class="orderListHeader">
        <li>No.</li>
        <li>카테고리</li>
        <li>상품명</li>
        <li>판매금액</li>
        <li>주문시간</li>
      </ul>
      <div v-if="orderList.orderCount > 0">
        <ul class="orderListContent" v-for="(item, index) in orderList.dashOrderListDtoList?.slice(0, 10)" :key="index">
          <li>{{ item.orderId }}</li>
          <li>{{ item.categoryName }}</li>
          <li>{{ item.productName }}</li>
          <li>{{ item.price.toLocaleString() }} 원</li>
          <li>{{ dateTimeFormat(item.createDate) }}</li>
        </ul>
      </div>
      <div v-else class="notList">
        <p>오늘 구매 내역이 없습니다.</p>
      </div>
    </article>

    <!-- 상단 오른쪽/ 오늘의 신규회원 및 방문자 수 -->
    <article id="today">
      <div class="todayCountTitle">
        <h1>Today</h1>
        <h1>{{ dateFormat(today) }}</h1>
      </div>
      <div class="todayCounts">
        <ul>
          <router-link to="/userManagement">
            <li>{{ joinCount.memberCount }}</li>
            <li>신규회원</li>
          </router-link>
        </ul>
        <ul>
          <router-link to="/userManagement">
            <li>{{ logInCount.memberCount }}</li>
            <li>방문자</li>
          </router-link>
        </ul>
        <ul>
          <router-link to="/orderManagement">
            <li>{{ orderList.orderCount }}</li>
            <li>구매</li>
          </router-link>
        </ul>
        <ul>
          <router-link to="/mainInspectionList">
            <li>{{ pendingList.pendingSaleCount }}</li>
            <li>판매신청</li>
          </router-link>
        </ul>
      </div>
      <div class="todayContent">
        <ul v-for="(joinMember, index) in joinCount.dashMemberListDtoJoinList?.slice(0, 5)" :key="index">
          <li>{{ joinMember.nickName }} 님이 가입했습니다.</li>
          <li>{{ timeFormat(joinMember.joinDate) }}</li>
        </ul>
        <ul v-for="(loginMember, index) in logInCount.dashMemberLogInListDtoList?.slice(0, 5)" :key="index">
          <li>{{ loginMember.nickName }} 님이 로그인했습니다.</li>
          <li>{{ timeFormat(loginMember.logInDate) }}</li>
        </ul>
      </div>
    </article>
    <!-- 중간 왼쪽 / 판매신청 리스트 -->
    <article id="pendingList">
      <div class="pendingListTitle">
        <h1>판매 리스트</h1>
        <p class="underline"><router-link to="/mainInspectionList">더보기▸</router-link></p>
      </div>
      <div>
        <ul class="pendingListHeader">
          <li>No.</li>
          <li>카테고리</li>
          <li>상품명</li>
          <li>닉네임</li>
          <li>신청시간</li>
        </ul>
      </div>
      <div v-if="pendingList.pendingSaleCount > 0">
        <ul
          class="pendingListContent"
          v-for="(item, index) in pendingList.dashPendingListDtoList?.slice(0, 10)"
          :key="index"
        >
          <li>{{ item.pendingSaleId }}</li>
          <li>{{ item.categoryName }}</li>
          <li>{{ item.productName }}</li>
          <li>{{ item.nickName }}</li>
          <li>{{ dateTimeFormat(item.createDate) }}</li>
        </ul>
      </div>
      <div v-else class="notList">
        <p>오늘 판매신청 내역이 없습니다.</p>
      </div>
    </article>
    <!-- 중간 오른쪽 공지사항 -->
    <article id="announcement">
      <div class="announcementTitle">
        <h1>공지사항</h1>
        <p class="underline"><router-link to="/announcement">더보기▸</router-link></p>
      </div>
      <div>
        <ul class="announcementHeader">
          <li>No.</li>
          <li>제목</li>
          <li>작성시간</li>
        </ul>
        <ul class="announcementContent" v-for="(item, index) in announcementData" :key="index">
          <li>{{ item.number }}</li>
          <li>{{ item.header }}</li>
          <li>{{ item.creationDate }}</li>
        </ul>
      </div>
    </article>
    <!--하단 왼쪽 / 리뷰리스트 -->
    <article id="reviewList">
      <div class="reviewListTitle">
        <h1>리뷰 리스트</h1>
        <p class="underline"><router-link to="/reviewManagement">더보기▸</router-link></p>
      </div>
      <ul class="reviewListHeader">
        <li>No.</li>
        <li>닉네임</li>
        <li>카테고리</li>
        <li>브랜드</li>
        <li>상품명</li>
        <li>용량</li>
      </ul>
      <div v-if="reviewList && reviewList.dashReviewListDtoList && reviewList.dashReviewListDtoList.length > 0">
        <div
          class="reviewListTotalContent"
          v-for="(item, index) in reviewList.dashReviewListDtoList.slice(0, 10)"
          :key="index"
        >
          <ul class="reviewListContentFirst">
            <li>{{ item.reviewId }}</li>
            <li>{{ item.nickName }}</li>
            <li>{{ item.categoryName }}</li>
            <li>{{ item.brandName }}</li>
            <li>{{ item.productName }}</li>
            <li>{{ item.productSize }} ml</li>
          </ul>
          <ul class="reviewListContentSecond">
            <li>{{ item.content }}</li>
            <li>{{ dateTimeFormat(item.reviewCreateDate) }}</li>
          </ul>
        </div>
      </div>
      <div v-else class="notList">
        <p>오늘 작성된 리뷰가 없습니다.</p>
      </div>
    </article>
    <div class="dashboardImg">
      <img src="@/assets/img/free-icon-polar-bear-18438786.png" alt="" />
    </div>
  </section>
</template>

<script setup>
import {
  getDashboardJoinCount,
  getDashboardLogInCount,
  getDashboardOrderList,
  getDashboardPendingList,
  getDashboardReviewList,
} from '@/api/AdminDashboardApi';
import { dateFormat, dateTimeFormat, timeFormat } from '@/FormatData';
import { ref } from 'vue';
const today = Date();
const joinCount = ref([]);
const logInCount = ref([]);
const orderList = ref([]);
const pendingList = ref([]);
const reviewList = ref([]);
const announcementData = ref([]);

const doload = async () => {
  const dashboardJoinCountRes = await getDashboardJoinCount();
  joinCount.value = dashboardJoinCountRes;
  const dashboardLogInCountRes = await getDashboardLogInCount();
  logInCount.value = dashboardLogInCountRes;
  const dashboardOrderListRes = await getDashboardOrderList();
  orderList.value = dashboardOrderListRes;
  const dashboardPendingListRes = await getDashboardPendingList();
  pendingList.value = dashboardPendingListRes;
  const dashboardReviewListRes = await getDashboardReviewList();
  reviewList.value = dashboardReviewListRes;

  // console.log('joinCount', joinCount.value);
  // console.log('logInCount', logInCount.value);
  // console.log('orderList', orderList.value);
  // console.log('pendingList', pendingList.value);
  // console.log('reviewList', reviewList.value);
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

doload();
</script>

<style scoped>
#dashboard {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
  padding: 15px;
  font-size: 1.2rem;
}
article {
  background-color: white;
  padding: 10px;
  box-shadow: inset -4px -4px 6px rgba(167, 180, 168, 0.5);
  border-bottom-right-radius: 10px;
}
.underline:hover {
  text-decoration: underline;
  text-underline-position: under;
}
h1 {
  font-size: 2rem;
}
.notList {
  text-align: center;
  height: 50%;
  align-content: center;
}
/* 각 블록의 타이틀 설정 */
.orderListTitle,
.todayCountTitle,
.pendingListTitle,
.announcementTitle,
.announcementTitle,
.reviewListTitle {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid var(--color-text-gray);
  line-height: 3.2rem;
}
/* 목록들 레이아웃 및 선 설정*/
.orderListHeader,
.pendingListHeader,
.announcementHeader,
.reviewListHeader {
  padding: 1.8% 0 1% 0;
  border-bottom: 0.5px solid var(--color-main-gray);
}
.orderListHeader,
.orderListContent,
.pendingListHeader,
.pendingListContent {
  display: grid;
  justify-items: center;
  grid-template-columns: 10% 15% 40% 10% 25%;
  align-items: center;
}
.announcementHeader,
.announcementContent {
  display: grid;
  justify-items: center;
  grid-template-columns: 10% 70% 20%;
  align-items: center;
}
.reviewListHeader,
.reviewListContentFirst {
  display: grid;
  grid-template-columns: 10% 10% 15% 20% 35% 10%;
  justify-items: center;
  align-items: center;
}
.orderListContent,
.pendingListContent,
.announcementContent,
.reviewListContentFirst {
  padding: 1% 0;
}
.orderListContent,
.pendingListContent,
.announcementContent {
  border-bottom: 0.5px solid var(--color-main-Lgray);
}

.pendingListContent:last-child,
.announcementContent:last-child {
  border-bottom: none;
}
.reviewListContentSecond {
  display: grid;
  grid-template-columns: 80% 20%;
  align-items: center;
  padding: 1%;
}
.reviewListContentFirst,
.reviewListContentSecond li:nth-child(1) {
  padding-left: 1%;
}
.reviewListTotalContent:nth-child(odd) {
  color: #636363;
}
.reviewListTotalContent:nth-child(even) {
  background-color: rgba(167, 180, 168, 0.15);
  color: #1a1a1a;
}
/* today */
.todayCounts {
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: center;
  font-size: 1.3rem;
  padding: 30px 5%;
}
.todayCounts ul {
  width: 23%;
  height: 100px;
  background-color: #e9f2f6;
  border-radius: 10px;
  align-content: center;
}
.todayCounts li:nth-child(1) {
  font-size: 4rem;
  color: orange;
}
.todayCounts ul:hover {
  background-color: #dceff7;
}
.todayContent {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 5%;
  padding: 0 5% 10px 5%;
}
.todayContent ul {
  display: flex;
  justify-content: space-between;
  padding: 5px 0;
  border-bottom: 0.5px dotted rgba(0, 0, 0, 0.2);
}

/* 이미지 */
.dashboardImg {
  justify-items: center;
  align-content: end;
  padding: 5%;
  transition: all 0.3s ease;
  animation: imgMove 2s infinite alternate linear;
  /* background-color: #636363; */
}
.dashboardImg img {
  width: 70%;
}
@keyframes imgMove {
  to {
    transform: translateX(-10px) rotate(-4deg);
  }
  from {
    transform: translateY(10px) rotate(4deg);
  }
}
@media (max-width: 1000px) {
  #dashboard {
    grid-template-columns: repeat(1, 1fr);
  }
  .dashboardImg img {
    width: 50%;
  }
}
</style>
