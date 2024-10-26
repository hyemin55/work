<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const productId = computed(() => route.params.idx)
</script>

<template>
  <div>
    <h1>{{ productId }}번 상품 상세페이지</h1>
  </div>
</template>

<style scoped></style>
