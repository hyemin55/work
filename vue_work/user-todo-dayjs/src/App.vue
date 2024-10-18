<script setup>
import { RouterView } from 'vue-router';
import TheLayoutNavVue from './components/TheLayoutNav.vue';
</script>

<template>
  <TheLayoutNavVue></TheLayoutNavVue>
  <RouterView class="pt-20" />
</template>

<style scoped></style>
