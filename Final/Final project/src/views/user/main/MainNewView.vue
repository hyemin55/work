<script setup>
import MainNewSildeView from './MainNewSildeView.vue';
</script>

<template>
  <article id="main_new">
    <h1>NEW</h1>
    <MainNewSildeView></MainNewSildeView>
  </article>
</template>

<style scoped>
/* ==== main_new ==== */
#main_new {
  /* background-color: rgb(145, 127, 104); */
  max-width: var(--main-max-width);
  width: 100%;
  margin: 0 auto;
  height: 900px;
  overflow: hidden;
  justify-content: center;
  align-items: center;
}
#main_new > h1 {
  font-size: 4rem;
  font-family: var(--font-JacquesFrancois);
  display: flex;
  justify-content: center;
  padding: 40px 0;
}
@media (max-width: 630px) {
  #main_new {
    height: 650px;
  }
}
</style>
