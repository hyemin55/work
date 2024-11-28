<template>
  <article>
    <nav id="header">
      <img src="@/assets/img/logo_text_white.png" alt="" />
      <ul class="header_nav">
        <li><a href="/" target="_blank" rel="noopener noreferrer">사이트로 이동</a></li>
        <li><img src="@/assets/img/icon/free-icon-font-refresh-3917293.svg" alt="" /></li>
        <li @click="logout">LOGOUT</li>
      </ul>
    </nav>
  </article>
</template>

<script setup>
import { useUserStore } from '@/stores/Login';
import { onBeforeMount, onMounted, onUpdated } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const useStore = useUserStore();

const logout = () => {
  useStore.logout();
  router.push({ name: 'main' });
};
// onMounted(() => {
//   useStore.nickName === '민이♡';
//   console.log(useStore.nickName);
// });

onMounted(() => {
  console.log('AdminHeader', useStore.nickName);
});
</script>

<style scoped>
#header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: fixed;
  padding: 1%;
  font-size: 1.7rem;
  width: 100%;
  height: 70px;
  background-color: var(--color-main-bloode);
}
#header img {
  width: 150px;
}
#header li img {
  width: 100%;
  height: auto;
}
.header_nav {
  display: flex;
  align-items: center;
  justify-content: right;
  width: 50%;
  height: auto;
  gap: 2%;
}
.header_nav li {
  padding: 8px;
}
.header_nav li a:nth-child(1) {
  text-decoration: underline;
  text-underline-position: under;
  /* border-bottom: 1px solid white; */
  color: white;
}
.header_nav li:nth-child(2) {
  color: white;
  width: 37px;
  cursor: pointer;
}
.header_nav li:nth-child(2):hover {
  animation: spin 2s linear infinite;
}
.header_nav li:nth-child(3) {
  background-color: var(--color-main-pink);
  border-radius: 10px;
}

@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>
