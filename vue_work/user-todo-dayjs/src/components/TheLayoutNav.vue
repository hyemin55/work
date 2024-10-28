<template>
  <nav class="bg-gradient-to-r from-yellow-200 to-orange-400 fixed w-full z-10">
    <div class="container mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex justify-between items-center py-4">
        <!-- Logo -->
        <RouterLink to="/" class="text-2xl font-bold text-gray-800"
          >MyLogo</RouterLink
        >
        <!-- Hamburger Menu (for mobile) -->
        <div class="sm:hidden">
          <button
            @click="menuDisplay"
            id="menu-btn"
            class="text-gray-800 focus:outline-none"
          >
            <svg
              class="w-6 h-6"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M4 6h16M4 12h16m-7 6h7"
              ></path>
            </svg>
          </button>
        </div>
        <!-- Navigation Links (hidden on small screens) -->
        <div class="hidden sm:flex space-x-8 text-gray-800">
          <RouterLink to="/" class="hover:text-orange-500">Home</RouterLink>
          <RouterLink to="/about" class="hover:text-orange-500"
            >About</RouterLink
          >
          <RouterLink to="/month" class="hover:text-orange-500"
            >Month</RouterLink
          >
          <RouterLink to="/message" class="hover:text-orange-500"
            >Message</RouterLink
          >
          <template v-if="useStore.loginCheck">
            <div class="w-12">
              <router-link to="/myPage"
                ><img
                  :src="useStore.thumbnail"
                  class="rounded-full cursor-pointer"
              /></router-link>
            </div>
          </template>
          <template v-else>
            <RouterLink to="/login" class="hover:text-orange-500"
              >Login</RouterLink
            >
          </template>
        </div>
      </div>
      <template v-if="mobileMenu">
        <!-- Mobile Menu (hidden by default) -->
        <div id="mobile-menu" class="hidden sm:hidden bg-white">
          <RouterLink
            to="/"
            class="block px-4 py-2 text-gray-800 pt-3 hover:bg-gray-100"
            >Home</RouterLink
          >
          <RouterLink
            to="/about"
            class="block px-4 py-2 text-gray-800 pt-3 hover:bg-gray-100"
            >About</RouterLink
          >
          <RouterLink
            to="/month"
            class="block px-4 py-2 text-gray-800 pt-3 hover:bg-gray-100"
            >Month</RouterLink
          >
          <RouterLink
            to="/message"
            class="block px-4 py-2 text-gray-800 pt-3 hover:bg-gray-100"
            >Message</RouterLink
          >
          <template v-if="useStore.loginCheck">
            <div class="w-12">
              <img :src="useStore.thumbnail" alt="" class="rounded-full" />
            </div>
          </template>

          <template v-else>
            <div>
              <router-link to="/login" class="pt-3 hover:text-orange-500"
                >Login</router-link
              >
            </div>
          </template>
          <RouterLink
            to="/login"
            class="block px-4 py-2 text-gray-800 hover:bg-gray-100"
            >LOGIN</RouterLink
          >
        </div>
      </template>
    </div>
  </nav>
</template>

<script setup>
import { ref, watchEffect } from 'vue';
import { useUserStore } from '@/stores/user';
import { loginCheck } from '@/api/loginApi';
const mobileMenu = ref(false);

const menuDisplay = () => {
  mobileMenu.value = !mobileMenu.value;
};
const useStore = useUserStore();

watchEffect(async () => {
  if (!localStorage.getItem('token')) return;

  const res = await loginCheck();
  if (res.status.toString().startWith('2')) {
    // console.log('res = ' + res.data);
    useStore.login(res.data);
    // 통신 성공 했을 때 pinia 상태값 바꾸기s
  }
});
</script>

<style lang="scss" scoped></style>
