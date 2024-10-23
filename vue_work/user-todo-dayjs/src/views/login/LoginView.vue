<template>
  <div>
    <div class="flex flex-col items-center gap-4 p-10 rounded-lg">
      <a
        id="custom-login-btn"
        @click="kakaoLogin()"
        class="transition-transform transform cursor-pointer"
      >
        <img
          src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
          width="222"
          alt="카카오 로그인 버튼"
          class="rounded-lg shadow-md"
        />
      </a>
      <div
        @click="kakaoLogout()"
        class="px-4 py-2 text-sm text-white bg-red-500 rounded-full cursor-pointer hover:bg-red-600 transition-all"
      >
        로그아웃
      </div>
    </div>
  </div>
</template>

<script setup>
const kakaoLogin = () => {
  window.Kakao.Auth.authorize({
    redirectUri: 'http://localhost:5173/oauth',
    // scope: 'account_email,friends,talk_calendar,talk_calendar_task',
  });
};
const kakaoLogout = () => {
  localStorage.removeItem('token');
  alert('로그아웃 성공');
};
</script>

<style lang="scss" scoped></style>
