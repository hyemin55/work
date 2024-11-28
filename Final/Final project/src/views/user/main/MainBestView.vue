<script setup>
import { getBestProducts } from '@/api/mainApi';
import { GLOBAL_URL } from '@/api/util';
import { onBeforeUnmount, onMounted, ref, watchEffect } from 'vue';
import router from '@/router';

const bestListRef = ref([]);
const bestListImagesRef = ref([]);
const bestCartegoryRef = ref(['Perfume', 'Diffuser', 'Candle']);
const bestCartegoryColorRef = ref(['112, 56, 129, 0.5', '30, 145, 153, 0.5', '0, 96, 16, 0.5']);
const currentIdxRef = ref(0);
let intervalId = null;

onMounted(async () => {
  bestListRef.value = await getBestProducts();
  console.log(bestListRef.value);
  bestListRef.value.forEach(best_product => {
    bestListImagesRef.value.push(best_product.mainImage.filename);
  });
});

const changeIdx = setInterval(() => {
  currentIdxRef.value = (currentIdxRef.value + 1) % 3;
}, 10000);

const nextchangeIdx = () => {
  currentIdxRef.value = (currentIdxRef.value + 1) % 3;
};
const prevchangeIdx = () => {
  currentIdxRef.value = (currentIdxRef.value - 1 + 3) % 3;
};
const navDetailProduct = (productId, size) => {
  console.log(productId, size);
  router.push({
    path: `/productsdetail/${productId}`,
    query: { size: size },
  });
};

watchEffect(() => {
  changeIdx;
});
onBeforeUnmount(() => {
  if (intervalId) {
    clearInterval(intervalId);
    console.log('setInterval 끝났음');
  }
});
</script>

<template>
  <article id="main_best">
    <h1>BEST</h1>

    <div id="best_position">
      <div class="best_left_box">
        <div class="best_product_banner" :style="`background-color: rgba(${bestCartegoryColorRef[currentIdxRef]})`">
          <p class="best_product_category">{{ bestCartegoryRef[currentIdxRef] }}</p>
          <div class="best_product_page">
            <div class="page_btn">
              <button @click="prevchangeIdx">&lt;</button>
              {{ currentIdxRef + 1 }} / {{ bestListRef.length }}
              <button @click="nextchangeIdx">&gt;</button>
            </div>
          </div>
        </div>

        <div class="best_product">
          <img
            class="best_product_img"
            @click="navDetailProduct(bestListRef[currentIdxRef].productId, bestListRef[currentIdxRef].size)"
            :src="`${GLOBAL_URL}/api/file/download/${bestListImagesRef[currentIdxRef]}`"
            alt=""
          />
          <ul class="best_left_text">
            <li class="best_brand_name" v-if="bestListRef.length > 0">{{ bestListRef[currentIdxRef].brandName }}</li>
            <li class="best_product_name" v-if="bestListRef.length > 0">{{ bestListRef[currentIdxRef].productName }}</li>
            <li v-else>Loading...</li>
          </ul>
        </div>
      </div>

      <div class="best_right_box">
        <p class="best_right_text">피렌체의 소중한 기억을 간직한, <br />&nbsp;&nbsp;&nbsp;&nbsp; 아름다운 꽃과 식물들의 이야기</p>
        <img class="best_promotion_img" src="@/assets/img/best_img.png" alt="" />
      </div>
    </div>
  </article>
</template>

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
/* 좌측설정 1 */
.best_left_box {
  /* background-color: rgb(250, 183, 183); */
  position: relative;
  width: 57%;
  height: 450px;
  left: 5%;
}
.best_product_banner {
  position: absolute;
  background-color: rgba(112, 56, 129, 0.5);
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
.best_product_page button {
  padding: 10px;
}
/* 좌측설정 2 */
.best_product {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 10%;
}
.best_product_img {
  width: 100%;
  height: auto;
  cursor: pointer;
}
.best_left_text {
  text-align: center;
  letter-spacing: -0.034rem;
}
.best_brand_name {
  font-size: 1.9rem;
  font-weight: 600;
  color: var(--color-main-bloode);
}
.best_product_name {
  font-size: 2.8rem;
  margin-top: 3px;
}

/* 우측설정 */
.best_right_box {
  width: 47.5%;
  position: relative;
  top: 45px;
  right: 5%;
  /* background-color: antiquewhite; */
}
.best_promotion_img {
  width: 100%;
  height: auto;
  margin-top: 60px;
}
.best_right_text {
  margin-top: -20px;
  margin-left: 24%;
  font-size: 2rem;
  line-height: 24px;
}
</style>
