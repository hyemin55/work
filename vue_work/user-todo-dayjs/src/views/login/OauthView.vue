<template>
  <div class="flex flex-col justify-center items-center min-height-80">
    <h1>Oauth</h1>
    <template v-if="loginCheck">
      <div>
        <h1>로그인에 성공하셨습니다.</h1>
        <router-link to="/">홈으로</router-link>
      </div>
    </template>
    <template v-else>
      <div>
        <h1>로그인 하셔야 이용가능합니다.</h1>
      </div>
    </template>
  </div>
</template>

<script setup>
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { login } from '@/api/loginApi';

const route = useRoute();
const loginCheck = ref(false);

watchEffect(async () => {
  console.log('code = ', route.query.code);
  // 값이 있으면 true, 값이 없으면(undefined) false
  if (route.query.code) {
    const data = await login(route.query.code);
    // console.log('data = ', data);
    localStorage.setItem('token', data);
    loginCheck.value = true;
  }
});
</script>

<style lang="scss" scoped></style>
