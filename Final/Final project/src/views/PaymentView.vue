<script setup>
import { useRoute } from 'vue-router';
const route = useRoute();
const cartItems = route.params.item;

console.log(cartItems);
</script>

<template>
  <div>
    <h1></h1>
  </div>
</template>

<style scoped></style>
