<script setup>
import axios from 'axios';


const kakaoLogin = () => {
  // window.Kakao.Auth.authorize({
    // redirect_uri: "http://localhost:5173/oauth",
  // });

  // axios.get('');


  console.log(route.query.code)
};

const kakaoLogout = () => {
	window.Kakao.Auth.logout();

	alert('로그아웃 성공');
};


</script>

<template>
  <section id="login">
    <article id="login_box">
      <h1 class="login_box_h1">Sign in</h1>
      <div class="login_box_btn">
        <a id="kakao-login-btn"
         href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=3729987ab56f48c56116ec21b049a78e&redirect_uri=http://localhost:5173/oauth">
          <img src="../img/btn_kakao.svg" />카카오로 시작하기
        </a>
      </div>
        <div @click="kakaoLogout()">로그아웃</div>
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
  /* background-color: antiquewhite; */
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;  
  width: 485px;
  height: 343px;
}
#kakao-login-btn img {
  width: 30px;
  margin: 0 10px;
}
#kakao-login-btn{
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--color-text-gray);
}
.login_box_h1{
  font-size: 1.7rem;
  padding: 20px;
}
.login_box_btn{
  display: flex;
  border: 0.5pt solid var(--color-main-bloode);
  width: 350px;
  height: 45px;
  border-radius: 9px;
}
</style>
