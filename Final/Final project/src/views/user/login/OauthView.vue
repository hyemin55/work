<script setup>
import { watchEffect } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { login, loginCheck } from '@/api/KakaoLoginApi';
import { useUserStore } from '@/stores/Login';
import LodingView from '@/views/loding/LodingView.vue';

const route = useRoute();
const router = useRouter();
const useStore = useUserStore();

// 받은 인가코드로 토큰받아오기
watchEffect(async () => {
  // console.log('code = ', route.query.code)
  if (route.query.code) {
    let res = await login(route.query.code);
    if (!res.status.toString().startsWith('2')) return;
    res = await loginCheck();
    if (res.status.toString().startsWith('2')) {
      console.log(res.data);
      await useStore.login(res.data);
      console.log(res.data);
    }
    // if사용해 role 권한이 admin이면 관리자페이지로 푸시
    if (res.data.nickName === '민이♡') {
      await router.push({ name: 'mainadmin' });
    } else if (res.data.role === '검수자') {
      await router.push({ name: 'main' });
    } else {
      await router.push({ name: 'main' });
    }
  }
});

// 프론트에서 모두 통신할때 사용한다.
//   watchEffect(async () => {
// if (route.query.code) {
//   res.value = await axios.post('https://kauth.kakao.com/oauth/token', null, {
//     params: {
//       grant_type: 'authorization_code',
//       client_id: '3729987ab56f48c56116ec21b049a78e', // 카카오 REST API 키
//       redirect_uri: 'http://localhost:5173/oauth',
//       client_secret: 'rvxajLH92o1Lz9Joj69mju2A0pD5lVgi', // 리다이렉트 URI
//       code: route.query.code, // 받은 인가 코드
//     },
//   })

// console.log(res.value.padStart.access_token)
// localStorage.setItem('kakaoToken', res.value.data.access_token)

// 카카오에 user의 사용자정보 받아오기
// const accessToken = res.value.data.access_token;
// const userInfo = await axios.get("https://kapi.kakao.com/v2/user/me", {
//     headers: {
//         Authorization: `Bearer ${accessToken}`,
//         'Content-Type':'application/x-www-form-urlencoded;charset=utf-8'
//     },
// });
// console.log(userInfo);
//   }
// })
</script>

<template>
  <div>
    <LodingView />
  </div>
</template>

<style scoped></style>
