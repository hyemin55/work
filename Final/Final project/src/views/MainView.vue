<template>
  <section id="main_wrapper">
    <article id="main_video">
      <!-- <video autoplay muted loop src="../img/main.mp4"></video> -->
    </article>
    <article id="main_best">
      
    </article>
    <article id="main_product_list"></article>
    <article id="main_editor"></article>
    <article id="main_new"></article>
  </section>
</template>

<script setup></script>

<style scoped>
/* 1-section 설정 */
#main_video{
  width: 100%;
  height: 100vh;
  background-color: rgb(35, 138, 74);
}
#main_video video{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
