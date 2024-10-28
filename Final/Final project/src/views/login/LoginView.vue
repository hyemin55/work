<script setup>
const kakaoLogin = () => {
  window.Kakao.Auth.authorize({
    redirectUri: 'http://localhost:5173/login',
  })
}

// const kakaoLogout = () => {
//   localStorage.removeItem('token')
//   alert('로그아웃 성공')
// }
</script>

<template>
  <section id="login">
    <article id="login_box">
      <h1 class="login_box_h1">Sign in</h1>
      <div class="login_box_btn" @click="kakaoLogin()">
        <a id="kakao-login-btn" >
          <img src="../../img/btn_kakao.svg" />카카오로 시작하기
        </a>
      </div>
      <div class="login_box_btn" @click="kakaoLogin()">
        <a id="kakao-login-btn" >
          <img src="../../img/btn_naver.svg" />네이버로 시작하기
        </a>
      </div>
      <div class="login_box_btn" @click="kakaoLogin()">
        <a id="kakao-login-btn" >
          <img src="../../img/btn_google.svg" />구글로 시작하기
        </a>
      </div>
    </article>
  </section>
</template>

<style scoped>
#login {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 80vh;
}
#login_box {
  /* background-color: antiquewhite; */
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: 485px;
  height: 343px;
  transform: translateY(-15%);
  /* background-color: rgb(240, 240, 240);
  border-radius: 50px; */
}
.login_box_h1 {
  font-size: 1.7rem;
  padding: 20px;
  margin-top: -20px;
}
.login_box_btn {
  display: flex;
  border: 0.5px solid var(--color-main-bloode);
  width: 350px;
  height: 45px;
  border-radius: 9px;
  cursor: pointer;
  margin: 10px 0;
}
#kakao-login-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--color-text-gray);
  font-size: 1.2rem;
}
#kakao-login-btn img {
  width: 30px;
  margin: 0 10px;
}
</style>
