<script setup>
import { watchEffect } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const route = useRoute();

const kakaoLogin = () => {
  window.Kakao.Auth.authorize({
    redirectUri: "http://localhost:5173/kakaologin",
  });
};

watchEffect(() => {
  if (route.query.code) {
    axios.get("http://localhost:10000/kakao/login?code=" + route.query.code)
  .then((response) => {
    console.log(response.data);
  })
  .catch((error) => {
    console.log(error);
  });
  }
});

const kakaoLogout = () => {
  window.Kakao.Auth.logout((res) => {
    console.log(res);
  });
};
</script>

<template>
  <section id="login">
    <article id="login_box">
      <h1 class="login_box_h1">Sign in</h1>
      <div class="login_box_btn">
        <button id="kakao-login-btn" @click="kakaoLogin()">
          <img src="../img/btn_kakao.png" />카카오로 시작하기
        </button>
      </div>
        <!-- <div @click="kakaoLogout()"></div> -->
    </article>
  </section>
</template>

<style scoped>
#login {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
}
#login_box{
  background-color: antiquewhite;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;  
  width: 485px;
  height: 343px;
}
#kakao-login-btn img {
  width: 40px;
}
.login_box_h1{
  font-size: 1.7rem;
  padding: 20px;
}
.login_box_btn{
  display: flex;
  border: 1px solid var(--color-main-bloode);
  width: 350px;
  height: 45px;
  border-radius: 9px;
}
</style>
