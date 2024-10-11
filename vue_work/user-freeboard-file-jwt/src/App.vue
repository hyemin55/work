<script setup>
import { RouterView } from 'vue-router'
import LayoutHeader from './layout/LayoutHeader.vue';
</script>

<template>
  <div>
    <LayoutHeader/>
    <RouterView/>
  </div>
</template>

<style scoped></style>
