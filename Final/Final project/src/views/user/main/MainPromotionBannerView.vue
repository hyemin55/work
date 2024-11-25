<script setup>
import Aos from 'aos';
import 'aos/dist/aos.css';
import { onMounted, watchEffect } from 'vue';

onMounted(()=>{
    Aos.init({
      duration: 1000, 
      once: true,  
    });
  })
</script>

<template>
  <article id="promotion_banner">
    <div class="promotion_banner_size">
      <img 
        class="promotion_banner_logo"
        src="@/assets/img/logo_text.png"
        alt=""
      />
      <p class="promotion_banner_text">
        <span data-aos="fade-right">지난 추억의 잔향을 나누고,</span>
        <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span data-aos="fade-left">새 이야기의 향기를 품어보세요</span>
      </p>
      <!-- to 나중에 판매페이지로 바꿔야함 -->
      <RouterLink to="/" class="promotion_banner_link" 
      data-aos="fade-right" 
      data-aos-offset="50"
      data-aos-delay="600"
        >바로 판매하기 →</RouterLink
      >
    </div>
  </article>
</template>


<style scoped>
/* ====promotion_banner==== */
#promotion_banner {
  background: linear-gradient(to right, #a85d71, #f3eed9);
  margin: 0 auto;
  height: 189px;
  display: flex;
  margin: 10% 0;
}
.promotion_banner_size {
  position: relative;
  width: var(--main-max-width);
  margin: 0 auto;
}
.promotion_banner_logo {
  position: absolute;
  /* width: var(--main-max-width); */
  height: 188px;
  opacity: 0.15;
  margin: 0 auto;
}
.promotion_banner_text {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left: 0; 
}
.promotion_banner_text span{
  display: inline-block;
  font-size: 3.6rem;
  font-family: var(--font-family-pretendard-bold);
  color: var(--color-main-Lgray);
}
.promotion_banner_link {
  position: absolute;
  right: 0;
  top: 50%;
  margin-top: -27px;
  /* transform: translateY(-50%); */
  color: var(--color-main-bloode);
  font-family: var(--font-family-pretendard-bold);
  font-size: 2rem;
  padding: 15px 0 15px 15px;
}
@media (max-width: 300px) {
  .promotion_banner_text {
    display: none;
  }
}
</style>
