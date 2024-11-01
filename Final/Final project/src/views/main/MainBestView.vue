<template>
  <article id="main_best">
    <h1>BEST</h1>
    <div id="best_position">
      <div class="best_left_box">
        <div class="best_product_banner">
          <p class="best_product_category">Perfume</p>
          <p class="best_product_page">
            <button @click="best_page_left">&lt;</button>
            1 / 3
            <button @click="best_page_right">&gt;</button>
          </p>
        </div>
        <div class="best_product">
          <img class="best_product_img" src="@/assets/img/p_003.png" alt="" />
          <ul class="best_left_text">
            <li>Dior</li>
            <li>향수제품명자리니조금길게적어볼게요</li>
          </ul>
        </div>
      </div>
      <div class="best_right_box">
        <p class="best_right_text">
          가장 인기 많은 향수<br />&nbsp;&nbsp;&nbsp;&nbsp;시원한 오이향에
          빠져보세요*^.^*
        </p>
        <img
          class="best_promotion_img"
          src="@/assets/img/best_img.png"
          alt=""
        />
      </div>
    </div>
  </article>
</template>

<script setup></script>

<style scoped>
/* ====BEST===== */
#main_best {
  /* background-color: blanchedalmond; */
  height: 760px;
  margin: 0 auto;
  width: var(--main-max-width);
}
#main_best > h1 {
  font-size: 4rem;
  font-family: var(--font-JacquesFrancois);
  display: flex;
  justify-content: center;
  padding: 55px 0;
}
#best_position {
  display: flex;
  height: 467px;
}
.best_left_box {
  /* background-color: rgb(250, 183, 183); */
  position: relative;
  width: 57%;
  height: 450px;
  left: 5%;
}
.best_product_banner {
  position: absolute;
  background-color: rgba(96, 0, 0, 0.5);
  width: 100%;
  height: 275px;
}
.best_product_category {
  transform: rotate(-90deg);
  transform-origin: left bottom;
  position: absolute;
  font-size: 5rem;
  top: 62%;
  left: 15%;
  opacity: 0.5;
  color: var(--color-purewhite);
  font-family: var(--font-JacquesFrancois);
}
.best_product_page {
  position: absolute;
  right: 2%;
  top: 10px;
  font-size: 2rem;
  color: var(--color-main-Lgray);
}
.best_product {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 10%;
}
.best_product_img {
  width: 100%;
  height: auto;
}
.best_left_text {
  text-align: center;
  font-size: 2rem;
}

.best_right_box {
  width: 47.5%;
  position: relative;
  top: 20px;
  font-size: 2rem;
  right: 5%;
}
.best_promotion_img {
  width: 100%;
  height: auto;
  margin-top: 100px;
}
.best_right_text {
  margin-left: 24%;
}
</style>
