<script setup>
import { RouterView, useRoute } from 'vue-router';
import Header from '@/components/user/layoutnav/Header.vue';
import Footer from '@/components/user/layoutnav/Footer.vue';
import { onBeforeMount, onBeforeUnmount, onMounted, ref } from 'vue';
import AdminHeader from '@/components/admin/layoutnav/AdminHeader.vue';
import { useUserStore } from '@/stores/Login';
import Adminnav from '@/components/admin/layoutnav/Adminnav.vue';

const data = useUserStore();
// console.log(role.nickName);
const route = useRoute();

// window height의 높이에서 scrollTop 을 뺀 값을 변수로 잡아서
// 20000 px 있는곳에 넣어라

const isScrolled = ref(false);
const scrollHeight = () => {
  isScrolled.value = window.scrollY >= 500;
};
const pageUp = () => {
  window.scrollTo({ top: 0, behavior: 'smooth' });
};
const pageDown = () => {
  window.scrollTo({ top: 30000, behavior: 'smooth' });
};

onMounted(() => {
  window.addEventListener('scroll', scrollHeight);
});
onBeforeUnmount(() => {
  window.removeEventListener('scroll', scrollHeight);
});
</script>

<template>
  <div>
    <div v-if="data.nickName === '민이♡'">
      <div>
        <AdminHeader></AdminHeader>
        <div class="adminLayout">
          <Adminnav></Adminnav>
          <RouterView class="MainAdmin" />
        </div>
      </div>
    </div>

    <div v-else>
      <Header></Header>

      <div class="min-height">
        <RouterView />
      </div>

      <Footer></Footer>
    </div>

    <div class="scroll_btn pagaUp" v-if="isScrolled" @click="pageUp">
      <img src="@/assets/img/icon/up.svg" alt="" />
    </div>
    <div class="scroll_btn pagaDown" v-if="isScrolled" @click="pageDown">
      <img src="@/assets/img/icon/up.svg" alt="" />
    </div>
  </div>
</template>

<style scoped>
.adminLayout {
  display: flex;
}
.min-height {
  min-height: calc(100vh - 320px);
}
.scroll_btn {
  position: fixed;
  right: 30px;
  width: 40px;
  height: 40px;
  background-color: white;
  border: 0.5px solid var(--color-main-gray);
  border-radius: 50%;
  cursor: pointer;
  overflow: hidden;
  z-index: 999;
}
.pagaUp {
  bottom: 100px;
}
.pagaDown {
  bottom: 50px;
}
.pagaDown img {
  transform: rotate(180deg);
}
.pagaUp img {
  width: 100%;
  height: auto;
}
</style>
