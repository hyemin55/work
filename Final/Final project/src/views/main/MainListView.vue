<template>
  <article id="main_product_list" :class="{ expanded: product_see_more }">
    <div>
      <div class="main_product_Com">
        <ProductComponent
          v-for="product in random_list.slice(0, 4)"
          :key="product.productId"
          :productInfo="product"
        />
      </div>
      <div class="main_add_product_Com" v-if="product_see_more">
        <ProductComponent
          v-for="product in random_list.slice(4, 8)"
          :key="product.productId"
          :productInfo="product"
        />
      </div>
    </div>
    <button
      class="main_product_list_btn"
      @click="see_handle"
      v-show="!product_see_more"
    >
      더보기 ▽
    </button>
    <button
      class="main_product_list_btn"
      @click="see_handle"
      v-show="product_see_more"
    >
      닫기 △
    </button>
  </article>
</template>

<script setup>
import { GLOBAL_URL } from '@/api/util'
import ProductComponent from '@/components/ProductComponent.vue'
import axios from 'axios'
import { ref, watchEffect } from 'vue'

// main_product_list
const product_see_more = ref(false)
const random_list = ref([])
const displayed_products = ref([]) // 현재 표시할 제품 리스트

const see_handle = () => {
  product_see_more.value = !product_see_more.value

  if (product_see_more.value) {
    // "더보기"를 클릭하면 나머지 4개를 보여줍니다
    // displayed_products.value = res.data.slice(4, 8);
  }
}

const getRandomList = async (pageNum, size) => {
  try {
    const res = await axios.get(
      `${GLOBAL_URL}/api/products/random?pageNum=${pageNum}&size=${size}`,
    )
    if (res.status == 200) {
      random_list.value = res.data // 전체 제품 리스트
      displayed_products.value = res.data.slice(0, 4) // 처음 4개 제품 표시
    }
  } catch (e) {
    console.log(e)
  }
}

watchEffect(() => {
  getRandomList(0, 8)
})
</script>

<style scoped>
/* ==== main_product_list ===== */
#main_product_list::before {
  content: '';
  position: absolute;
  width: 70vw;
  left: 0;
  top: 0;
  height: 0.2px;
  background-color: var(--color-main-bloode);
}
#main_product_list::after {
  content: '';
  position: absolute;
  width: 70vw;
  right: 0;
  height: 0.2px;
  bottom: 0;
  background-color: var(--color-main-bloode);
}
#main_product_list {
  /* background-color: #F3EED9; */
  position: relative;
  margin: 0 auto;
  height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.expanded {
  height: 1020px !important;
}
.main_product_list_btn {
  font-size: 1.5rem;
  margin: 10px 0;
  color: var(--color-text-gray);
}
.main_product_Com,
.main_add_product_Com {
  width: var(--main-max-width);
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  padding: 15px 0;
}
</style>
