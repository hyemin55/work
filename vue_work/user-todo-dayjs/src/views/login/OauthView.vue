<template>
  <div>
    <h1>Oauth</h1>
  </div>
</template>

<script setup>
import { watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { login } from '@/api/loginApi';

const route = useRoute();

watchEffect(async () => {
  console.log('code = ', route.query.code);
  // 값이 있으면 true, 값이 없으면(undefined) false
  if (route.query.code) {
    const data = await login(route.query.code);
    console.log('data = ', data);
  }
});
</script>

<style lang="scss" scoped></style>
