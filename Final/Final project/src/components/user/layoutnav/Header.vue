<script setup>
import { useUserStore } from '@/stores/Login';
import { ref, watchEffect, onMounted, onUpdated, nextTick } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { eventBus } from '@/eventBus';
import { useCartStore } from '@/stores/CartStore';

const cartStore = useCartStore();
const route = useRoute();
const router = useRouter();
const HeaderMode = ref(false);

const useStore = useUserStore();

const loginCheck = ref(false);
const token = ref(false);
watchEffect(() => {
  HeaderMode.value = route.path === '/';
  cartStore.cartCheckList = []; // 계산 중첩 방지
});

const kakaoLogout = async () => {
  // await logout(sessionStorage.getItem('token'))
  useStore.logout();
  // console.log('로그아웃 성공')
  // console.log(token.value)
  // token.value = false
  eventBus.emit('logout');
  router.push({ name: 'login2' });
};

onMounted(() => {
  const savedToken = sessionStorage.getItem('token');
  if (savedToken) {
    token.value = true;
    useStore.loginCheck = true; // 스토어에 로그인 상태 설정
  } else {
    useStore.loginCheck = false;
  }
});
// onMounted(() => {
//   token.value = localStorage.getItem('token')
// })
const categories = [
  { title: 'Perfume', path: '/category/Perfume/3' },
  { title: 'Diffuser', path: '/category/Diffuser/2' },
  { title: 'Candle', path: '/category/Candle/1' },
];

const isTransparent = ref(true);
const handleScroll = () => {
  if (window.scrollY >= window.innerHeight) {
    isTransparent.value = false;
  } else {
    isTransparent.value = true;
  }
};

onMounted(() => {
  if (route.path === '/') {
    HeaderMode.value = true;
    window.addEventListener('scroll', handleScroll);
  } else {
    HeaderMode.value = false;
  }
});

const isSearchVisible = ref(false); // 검색창의 표시 여부
const searchInput = ref(null); // 검색창 참조
const searchQuery = ref(''); // 검색어 저장
const searchStyle = ref({ width: '0px' });
// 검색창 토글 함수
const toggleSearch = () => {
  isSearchVisible.value = !isSearchVisible.value;
  nextTick(() => {
    if (isSearchVisible.value) {
      searchInput.value.focus();
      searchStyle.value = { width: '200px' };
    } else {
      searchInput.value.blur();
      searchStyle.value = { width: '0px' };
    }
  });
};
</script>

<template>
  <section
    id="header"
    :class="[
      { sticky: !HeaderMode, fixed: HeaderMode },
      { transparent: !isTransparent, transparentX: isTransparent },
    ]"
  >
    <article id="header_top">
      <p>Where people linger, a fragrance remains</p>
      <template v-if="useStore.loginCheck">
        <ul class="login">
          <li @click="kakaoLogout()">로그아웃</li>
          <li class="noCursor">&nbsp;|&nbsp;</li>
          <li><router-link to="/mypage">마이페이지</router-link></li>
          <li class="noCursor">&nbsp;|&nbsp;</li>
          <li>고객센터</li>
          <li class="noCursor">&nbsp;|&nbsp;</li>
          <li><router-link to="/mainadmin">관리자</router-link></li>
        </ul>
      </template>
      <template v-else>
        <ul class="login">
          <li><RouterLink to="/login2">로그인</RouterLink></li>
          <li class="noCursor">&nbsp;|&nbsp;</li>
          <li>고객센터</li>
        </ul>
      </template>
    </article>

    <article id="header_bottom">
      <RouterLink class="logo" to="/">
        <img src="@/assets/img/logo_text.png" alt="" />
      </RouterLink>

      <ul class="gnb01">
        <li v-for="(category, index) in categories" :key="index">
          <RouterLink :to="category.path" class="link_title">{{ category.title }}</RouterLink>
        </li>
      </ul>
      <ul class="gnb02">
        <li>
          <input v-show="isSearchVisible" v-bind:style="searchStyle" type="text" placeholder="검색어를 입력해주세요." class="search-input" v-model="searchQuery" ref="searchInput" />
          <img @click="toggleSearch" class="icon" src="@/assets/img/icon/free-icon-font-search-3917132.png" alt="" />
        </li>
        <li>
          <RouterLink to="/cart">
            <img class="icon" src="@/assets/img/icon/free-icon-font-basket-shopping-simple-9768421.png" alt="" />
          </RouterLink>
        </li>
      </ul>
    </article>
  </section>
</template>

<style scoped>
/* header 전체설정 */
#header {
  width: 100%;
  height: 100px;
  display: flex;
  flex-direction: column;
  /* position: fixed; */
  z-index: 900;
  top: 0;
  left: 0;
  transition: background-color 0.2s ease;
  /* 순서대로 : x축 y축 블러 색상 */
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.08);
}
.sticky {
  position: sticky;
  background-color: rgb(255, 255, 255, 1);
}
.fixed {
  position: fixed;
  background-color: rgb(255, 255, 255, 0.2);
}
.transparent {
  background-color: white;
}
.fixed:not(.transparent) {
  background-color: rgb(255, 255, 255, 0.4);
}
#header > article {
  max-width: var(--main-max-width);
  width: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#header > article:nth-child(1) {
  height: 30px;
}
#header > article:nth-child(2) {
  height: 70px;
}
#header > article > ul {
  display: flex;
}

/* header_top 설정 */
#header_top {
  font-size: 1.2rem;
}
#header_top > p {
  font-family: 'PRISTINA';
}
.login li {
  margin-left: 0.4rem;
  cursor: pointer;
}
.noCursor {
  cursor: default !important;
}
#header_top::after {
  content: '';
  position: absolute;
  left: 0;
  top: 30px;
  width: 100%;
  height: 0.2px;
  background-color: var(--color-main-bloode);
}

/* header_bottom 설정 */
.logo img {
  width: 95px;
  height: auto;
}
.gnb01 li {
  margin: 0 2.4rem;
}
.link_title {
  font-size: 1.8rem;
  font-weight: 500;
  font-family: 'JacquesFrancois-Regular';
  color: rgb(19, 19, 19);
  padding: 15%;
  /* background-color: antiquewhite; */
}
.gnb02 li {
  position: relative;
  font-size: 1.5rem;
  margin-left: 1.8rem;
}
.icon {
  width: 2rem;
  height: auto;
}

/* 검색창 설정 */
.icon {
  cursor: pointer;
}
.search-input {
  z-index: 999;
  position: absolute;
  right: 2.5rem;
  height: 2rem;
  transition: width 0.3s ease;
  border: 1px solid #ccc;
  margin-left: 10px;
  transition: width 0.3s ease;
}
.search-input:focus {
  width: 200px;
}
.search-input {
  width: 0;
}

/* 반응현 구간 설정 */
/* @media (max-width: 1280px) {} */
</style>
