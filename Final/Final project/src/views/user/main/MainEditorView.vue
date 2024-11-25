<script setup>
import MainEditorPicksSlideView from './MainEditorPicksSlideView.vue'
</script>

<template>
  <article id="main_editor">
    <h1>Editor's Picks</h1>

    <!-- <ul id="main_editor_list">
        <li class="main_editor_img1">
          <img src="../../img/editor_front_img.png" alt="" />
        </li>
        <li class="main_editor_img2">
          <img src="../../img/editor_front_img.png" alt="" />
        </li>
      </ul> -->

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
</style>
