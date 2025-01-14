<script setup>
import MainEditorPicksSlideView from './MainEditorPicksSlideView.vue';
</script>

<template>
  <article id="main_editor">
    <h1>Editor's Pick of the Month</h1>
    <MainEditorPicksSlideView></MainEditorPicksSlideView>
  </article>
</template>

<style scoped>
/* ==== main_editor ==== */
#main_editor {
  width: var(--main-max-width);
  margin: 0 auto;
  height: 800px;
  overflow: hidden;
  /* background-color: #f3eed9; */
}
#main_editor > h1 {
  font-size: 4rem;
  font-family: var(--font-JacquesFrancois);
  text-align: center;
  padding-top: 40px;
  margin-bottom: 20px;
}
@media (max-width: 630px) {
  #main_editor {
    height: 600px;
  }
}
</style>
