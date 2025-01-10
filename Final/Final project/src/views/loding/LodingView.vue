<script setup></script>

<template>
  <div id="loginCheck">
    <img src="@/assets/img/p_003.png" alt="" />
    <!-- <h1>{{ useStore.nickName }}</h1> -->
  </div>
</template>

<style scoped>
#loginCheck {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100%;
}
img {
  width: 15%;
  height: auto;
  animation: rotate 4s infinite linear;
}
@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg); /* 360도 회전 */
  }
}
</style>
