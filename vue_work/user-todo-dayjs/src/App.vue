<script setup>
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <header class="flex space-x-8">
    <RouterLink to="/">HOME</RouterLink>
    <RouterLink to="/month">MONTH</RouterLink>
    <RouterLink to="/about">ABOUT</RouterLink>
    <RouterLink to="/test">TEST</RouterLink>
    
  </header>

  <RouterView />
</template>

<style scoped></style>
