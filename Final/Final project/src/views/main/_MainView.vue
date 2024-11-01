<template>
  <section id="main_wrapper">
    <article id="main_video">
      <!-- <video autoplay muted loop src="../../img/main.mp4"></video> -->
    </article>

    <MainBestView></MainBestView>
    <MainListView></MainListView>
    <MainPromotionBannerView></MainPromotionBannerView>
    <MainEditorView></MainEditorView>
    <MainNewView></MainNewView>
  </section>
</template>

<script setup>
import MainBestView from './MainBestView.vue'
import MainEditorView from './MainEditorView.vue'
import MainListView from './MainListView.vue'
import MainNewView from './MainNewView.vue'
import MainPromotionBannerView from './MainPromotionBannerView.vue'

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
  background-color: rgb(35, 138, 74);
}
#main_video video {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
