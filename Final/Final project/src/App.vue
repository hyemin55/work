<script setup>
import { RouterLink, RouterView, useRoute } from 'vue-router'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

const route = useRoute();

</script>

<template>
  <Header></Header>

  <RouterView />

  <Footer></Footer>
</template>

<style scoped></style>
