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
          <button id="menu-btn" class="text-gray-800 focus:outline-none">
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
          <RouterLink to="/login" class="hover:text-orange-500"
            >Login</RouterLink
          >
        </div>
      </div>
      <template v-if="mobileMenu">
        <!-- Mobile Menu (hidden by default) -->
        <div id="mobile-menu" class="hidden sm:hidden bg-white">
          <RouterLink
            to="/"
            class="block px-4 py-2 text-gray-800 hover:bg-gray-100"
            >Home</RouterLink
          >
          <RouterLink
            to="/about"
            class="block px-4 py-2 text-gray-800 hover:bg-gray-100"
            >About</RouterLink
          >
          <RouterLink
            to="/month"
            class="block px-4 py-2 text-gray-800 hover:bg-gray-100"
            >Month</RouterLink
          >
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
import { ref } from 'vue';
const mobileMenu = ref(false);

const menuDisplay = () => {
  mobileMenu.value = !mobileMenu.value;
};
</script>

<style lang="scss" scoped></style>
