<template>
  <section id="main_wrapper">
    <article id="main_video">
      <!-- <video autoplay muted loop src="../img/main.mp4"></video> -->
    </article>

    <MainBestView></MainBestView>
    <MainListView></MainListView>

    <article id="promotion_banner">
      <div class="promotion_banner_size">
        <img class="promotion_banner_logo" src="../img/logo_text.png" alt="" />
        <p class="promotion_banner_text">
          지난 추억의 잔향을 나누고,
          <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          새 이야기의 향기를 품어보세요
        </p>
        <!-- to 나중에 판매페이지로 바꿔야함 -->
        <RouterLink to="/productList" class="promotion_banner_link"
          >바로 판매하기 →</RouterLink
        >
      </div>
    </article>

    <article id="main_editor">
      <h1>Editor's Picks</h1>

      <ul id="main_editor_list">
        <li class="main_editor_img1">
          <img src="../img/editor_front_img.png" alt="" />
        </li>
        <li class="main_editor_img2">
          <img src="../img/editor_front_img.png" alt="" />
        </li>
      </ul>

      <EditorPicksSlide></EditorPicksSlide>
    </article>

    <article id="main_new">
      <h1>NEW</h1>
      <div class="main_new_box">
        <ul>
          <li>00:00:30 등록상품</li>
          <li><img src="../img/p_003.png" alt="" /></li>
          <li>Dior</li>
          <li>미스 디올 오 드 퍼품</li>
          <li>\ 130,000</li>
        </ul>
      </div>
    </article>
  </section>
</template>

<script setup>
import MainBestView from './mainpage/MainBestView.vue'
import MainListView from './mainpage/MainListView.vue'
import EditorPicksSlide from '@/components/EditorPicksSlide.vue'

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
  font-size: 3.6rem;
  font-family: var(--font-family-pretendard-bold);
  color: var(--color-main-Lgray);
}
.promotion_banner_link {
  position: absolute;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
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

/* ==== main_editor ==== */
#main_editor {
  width: var(--main-max-width);
  margin: 0 auto;
  height: 700px;
  background-color: #f3eed9;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}
#main_editor > h1 {
  font-size: 4rem;
  font-family: var(--font-JacquesFrancois);
  display: flex;
  justify-content: center;
  padding: 40px 0;
}
#main_editor_list {
  display: flex;
  gap: 1%;
  justify-content: space-around;
  align-items: end;
}
.main_editor_img1 {
  width: 49%;
  height: auto;
  background-color: var(--color-main-Lgray);
  /* border: 0.5pt solid #333; */
  border-radius: 10%;
}
.main_editor_img1 > img {
  width: 100%;
  height: auto;
  background-color: #411420;
}
.main_editor_img2,
.main_editor_img3,
.main_editor_img4,
.main_editor_img5 {
  width: 27%;
  height: auto;
}
.main_editor_img2 > img {
  width: 100%;
  height: auto;
}

/* ==== main_new ==== */
#main_new {
  background-color: rgb(145, 127, 104);
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
  padding: 50px 0;
  /* background-color: red; */
}
.main_new_box {
  position: relative;
  background-color: #f3eed9;
  width: 31%;
  height: 700px;
  border: 0.5pt solid var(--color-main-bloode);
  border-radius: 12px;
}
.main_new_box img {
  width: 80%;
}
</style>
