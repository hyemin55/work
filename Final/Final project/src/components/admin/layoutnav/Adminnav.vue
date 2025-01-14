<template>
  <article id="AdminNav" v-if="role === 'ADMIN'" @mouseenter="handleMouseEnter" @mouseleave="handleMouseLeave">
    <ul>
      <Router-link v-for="(item) in adminMenu" :key="item.route" :to="item.route">
        <li class="NavMenu" :class="{ active: activeIndex === item.route }" @click="setActive()">
          <img :src="item.icon" alt="" /><span class="NavMenuText">{{ item.label }}</span>
        </li>
      </Router-link>
    </ul>
  </article>

  <article id="AdminNav" v-if="role === 'APPRAISER'">
    <ul>
      <RouterLink :to="item.route" v-for="(item) in appraiserMenu" :key="item.route">
        <li class="NavMenu" :class="{ active: activeIndex === item.route }" @click="setActive()">
          <img :src="item.icon" alt="" /><span class="NavMenuText">{{ item.label }}</span>
        </li>
      </RouterLink>
    </ul>
  </article>
</template>

<script setup>
import { useUserStore } from '@/stores/Login';
import { watch, ref } from 'vue';
import { useRoute } from 'vue-router';

const useStore = useUserStore();
const route = useRoute();
const role = useStore.role;
const activeIndex = ref(0);
const emit = defineEmits();

const isHovered = ref(false);
const handleMouseEnter = () => {
  isHovered.value = true;
  emit('MouseHovered', isHovered.value);
};
const handleMouseLeave = () => {
  isHovered.value = false;
  emit('MouseHovered', isHovered.value);
};
// 메뉴 항목 데이터
const adminMenu = [
  {
    route: '/mainDashboard',
    icon: new URL('@/assets/img/icon/free-icon-font-apps-3917482.svg', import.meta.url).href,
    label: '대시보드',
  },
  {
    route: '/categoryManagement',
    icon: new URL('@/assets/img/icon/free-icon-font-boxes-7857446.svg', import.meta.url).href,
    label: '카테고리관리',
  },
  {
    route: '/productManagement',
    icon: new URL('@//assets/img/icon/free-icon-font-air-freshener-7857379.svg', import.meta.url).href,
    label: '상품관리',
  },
  {
    route: '/orderManagement',
    icon: new URL('@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg', import.meta.url).href,
    label: '주문관리',
  },
  {
    route: '/userManagement',
    icon: new URL('@/assets/img/icon/free-icon-font-user-3917559.svg', import.meta.url).href,
    label: '사용자관리',
  },
  {
    route: '/reviewManagement',
    icon: new URL('@/assets/img/icon/free-icon-font-comment-3916638.svg', import.meta.url).href,
    label: '리뷰관리',
  },
  {
    route: '/announcement',
    icon: new URL('@/assets/img/icon/free-icon-font-megaphone-3914404.svg', import.meta.url).href,
    label: '공지사항',
  },
  {
    route: '/mainInspectionList',
    icon: new URL('@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg', import.meta.url).href,
    label: '판매신청목록',
  },
  {
    route: '/approvedList',
    icon: new URL('@/assets/img/icon/free-icon-font-smile-beam-6275928.svg', import.meta.url).href,
    label: '승인목록',
  },
  {
    route: '/petList',
    icon: new URL('@/assets/img/icon/free-icon-font-face-anguished-9844296.svg', import.meta.url).href,
    label: '반려목록',
  },
  {
    route: '/statistics',
    icon: new URL('@/assets/img/icon/free-icon-font-chart-histogram-5528038.svg', import.meta.url).href,
    label: '통계',
  },
];

const appraiserMenu = [
  {
    route: '/mainInspectionList',
    icon: new URL('@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg', import.meta.url).href,
    label: '판매신청목록',
  },
  {
    route: '/approvedList',
    icon: new URL('@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg', import.meta.url).href,
    label: '승인목록',
  },
  {
    route: '/petList',
    icon: new URL('@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg', import.meta.url).href,
    label: '반려목록',
  },
];

const setActive = () => {
  activeIndex.value = route.fullPath;
};
watch(setActive)
</script>

<style scoped>
.NavMenu {
  width: 100%;
  height: 50px;
  cursor: pointer;
  align-content: center;
  display: flex;
  align-items: center;
  padding-left: 20px;
}
.NavMenu:hover,
.NavMenu.active {
  /* background-color: var(--color-main-bloode); */
  background-color: #627c85;
  color: white;
  transition: all 0.3s ease;
}
.NavMenu img {
  width: 20px;
  margin-right: 10px;
}
.NavMenu:hover img,
.NavMenu.active img {
  filter: invert(100%) brightness(200%);
}

@media (max-width: 1000px) {
  .NavMenu {
    padding-left: 10px;
  }
  .NavMenuText {
    display: none;
    font-size: 0rem;
    width: 80px;
  }
  #AdminNav:hover .NavMenuText {
    display: block;
    font-size: 1.2rem;
    width: 80px;
  }
}
</style>
