<template>
  <section id="main_wrapper">
    <article id="main_video">
      <!-- <video autoplay muted loop src="../img/main.mp4"></video> -->
    </article>

    <article id="main_best">
      <h1>BEST</h1>
      <div class="best_left_box">
        <div class="best_product_banner">
          <button @click="best_page_left">&lt;</button>
          1/3
          <button @click="best_page_right">&gt;</button>
        </div>
        <div class="best_product">
          <img class="best_img" src="../img/빵빵덕세안.png" alt="" />
          <ul class="best_left_text">
            <li>Dior</li>
            <li>향수 설명글</li>
          </ul>
        </div>
      </div>
    </article>

    <article id="main_product_list"></article>
    <article id="main_editor"></article>
    <article id="main_new"></article>
  </section>
</template>

<script setup>
import { ref } from 'vue'
</script>

<style scoped>
/* 1-section 설정 */
#main_video {
  width: 100%;
  height: 200vh;
  background-color: rgb(35, 138, 74);
}
#main_video video {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* ====BEST===== */
#main_best {
  background-color: blanchedalmond;
  height: 760px;
  width: var(--main-max-width);
}
#main_best > h1 {
  font-size: 4rem;
  font-family: var(---font-JacquesFrancois);
  display: flex;
  justify-content: center;
}
.best_left_box {
  background-color: rgb(250, 183, 183);
  position: relative;
  width: 710px;
  height: 450px;
  left: 5%;
}
.best_product_banner {
  position: absolute;
  background-color: brown;
  width: 710px;
  height: 275px;
}
.best_product{
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 10%;
}
.best_img {
  width: 350px;
  height: 350px;
}
.best_left_text {
  text-align: center;
  font-size: 2rem;
}
</style>
