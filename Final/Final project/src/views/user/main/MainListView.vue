<template>
  <article id="main_product_list" :class="{ expanded: product_see_more }">
    <div>
      <div class="main_product_Com">
        <ProductComponent v-for="product in random_list.slice(0, 4)" :key="product.productId" :productInfo="product" />
      </div>
      <div class="main_add_product_Com" v-if="product_see_more">
        <ProductComponent v-for="product in random_list.slice(4, 8)" :key="product.productId" :productInfo="product" />
      </div>
    </div>
    <div class="main_product_list_btn_box">
      <button class="main_product_list_btn" @click="see_handle" v-show="!product_see_more">더보기 ▽</button>
      <button class="main_product_list_btn" @click="see_handle" v-show="product_see_more">닫기 △</button>
    </div>
  </article>
</template>

<script setup>
import { GLOBAL_URL } from '@/api/util';
import ProductComponent from '@/components/user/ProductComponent.vue';
import axios from 'axios';
import { ref, watchEffect } from 'vue';

// main_product_list
const product_see_more = ref(false);
const random_list = ref([]);
const displayed_products = ref([]); // 현재 표시할 제품 리스트

const see_handle = () => {
  product_see_more.value = !product_see_more.value;

  if (product_see_more.value) {
    // "더보기"를 클릭하면 나머지 4개를 보여줍니다
    // displayed_products.value = res.data.slice(4, 8);
  }
};

const getRandomList = async (pageNum, size) => {
  try {
    const res = await axios.get(`${GLOBAL_URL}/api/products/random?pageNum=${pageNum}&size=${size}`);
    if (res.status == 200) {
      random_list.value = res.data; // 전체 제품 리스트
      displayed_products.value = res.data.slice(0, 4); // 처음 4개 제품 표시
    }
  } catch (e) {
    console.log(e);
  }
};

watchEffect(() => {
  getRandomList(0, 8);
});
</script>

<style scoped>
/* ==== main_product_list ===== */
#main_product_list::before {
  content: '';
  position: absolute;
  width: 70vw;
  left: 0;
  top: -50px;
  height: 0.2px;
  background-color: var(--color-main-bloode);
}
#main_product_list::after {
  content: '';
  position: absolute;
  width: 70vw;
  right: 0;
  height: 0.2px;
  bottom: -50px;
  background-color: var(--color-main-bloode);
}
#main_product_list {
  /* background-color: #F3EED9; */
  position: relative;
  margin: 0 auto;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.expanded {
  height: auto;
  /* height: 1060px !important; */
}
.main_product_list_btn_box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  /* background-color: aqua; */
}
.main_product_list_btn {
  height: 45px;
  width: 13%;
  min-width: 100px;
  font-size: 1.5rem;
  margin: 10px 0;
  padding: 1%;
  border: 0.5px solid var(--color-main-gray);
  border-radius: 50px;
  background-color: white;
  text-align: center;
  line-height: 50%;
}
.main_product_Com,
.main_add_product_Com {
  width: var(--main-max-width);
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 2%;
  padding: 15px 0;
}
@media (max-width: 630px) {
  .main_product_Com,
.main_add_product_Com {
    grid-template-columns: repeat(2, 1fr);
    height: auto;
  }
}
</style>
