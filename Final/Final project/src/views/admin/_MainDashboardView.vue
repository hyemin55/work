<template>
  <article>
    <h1>MainAdmin</h1>
  </article>
</template>

<script setup>
import { useUserStore } from '@/stores/Login';

const useStore = useUserStore();
console.log('MainDashboard', useStore.role);
</script>

<style scoped></style>
