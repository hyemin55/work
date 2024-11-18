<template>
  <section id="main_wrapper">
    <article id="main_video">
      <video autoplay muted loop src="@/assets/img/main.mp4"></video>
    </article>

    <MainBestView />
    <MainListView />
    <MainPromotionBannerView />
    <MainEditorView />
    <MainNewView />
  </section>
</template>

<script setup>
import { defineAsyncComponent } from 'vue';
import MainBestView from './MainBestView.vue';
import MainListView from './MainListView.vue';
import MainPromotionBannerView from './MainPromotionBannerView.vue';
import MainEditorView from './MainEditorView.vue';
import MainNewView from './MainNewView.vue';

// const MainEditorView = defineAsyncComponent(() => import('./MainEditorView.vue'));
// const MainNewView = defineAsyncComponent(() => import('./MainNewView.vue'));

// main_editor_list
// const editor_images = [
//   { src: require('../img/p_003.png') },
//   { src: require('../img/editor_front_img.png') },
//   { src: require('../img/editor_front_img.png') },
//   { src: require('../img/editor_front_img.png') },
//   { src: require('../img/editor_front_img.png') },
// ]
// const currentMainIndex = ref(0)
// const isAnimating = ref(false)

// const handleImageClick = index => {
//   if (index === currentMainIndex.value) {
//     return
//   } else {
//     isAnimating.value = true

//     setTimeout = () => {
//       currentMainIndex.value = index
//       isAnimating.value = false
//     }
//   }
// }
</script>

<style scoped>
/* 1-section 설정 */
#main_wrapper {
  position: relative;
}
#main_video {
  width: 100%;
  height: 100vh;
  /* background-color: rgb(35, 138, 74); */
}
#main_video video {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border: 1px solid white;
}
</style>
