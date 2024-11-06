<script setup>
import { RouterLink, RouterView, useRoute } from 'vue-router'
import Header from './components/layoutnav/Header.vue'
import Footer from './components/layoutnav/Footer.vue'

const route = useRoute()
</script>

<template>
  <Header></Header>

  <div class="min-height">
    <RouterView />
  </div>

  <Footer></Footer>
</template>

<style scoped>
.min-height {
  min-height: calc(100vh - 320px);
}
</style>
