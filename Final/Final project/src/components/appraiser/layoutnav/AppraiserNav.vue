<template>
  <article id="Adminnav">
    <ul>
      <RouterLink to="/mainDashboard"
        ><li><img src="@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg" alt="" />판매신청목록</li></RouterLink
      >
      <RouterLink to="/reviewManagement"
        ><li><img src="@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg" alt="" />승인목록</li></RouterLink
      >
      <RouterLink to="/orderManagement"
        ><li><img src="@/assets/img/icon/free-icon-font-clipboard-list-7857488.svg" alt="" />반려목록</li></RouterLink
      >
    </ul>
  </article>
</template>

<script setup>
import { useUserStore } from '@/stores/Login';
import { watch } from 'vue';

const useStore = useUserStore();
console.log('AppraiserNav', useStore.nickName);
watch(useStore.nickName, _new => {
  console.log(_new);
});
</script>

<style scoped>
#Adminnav {
  position: fixed;
  width: 200px;
  height: calc(100vh - 70px);
  background-color: var(--color-main-pink);
  font-size: 1.6rem;
  margin-top: 70px;
}
li {
  width: 100%;
  height: 50px;
  cursor: pointer;
  align-content: center;
  padding-left: 20px;
}
li:hover {
  background-color: aqua;
}
li img {
  width: 10%;
  margin-right: 10px;
}
</style>
