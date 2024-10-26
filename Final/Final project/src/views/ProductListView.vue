<script setup>
import { GLOBAL_URL } from '@/api/util'
import ProductComponent from '@/components/ProductComponent.vue'
import axios from 'axios'
import { computed, ref, watchEffect } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const categoryTitle = computed(() => route.params.title)
const categoryId = computed(() => route.params.idx)
const list = ref([])

watchEffect(async () => {
  try {
    const res = await axios.get(
      `${GLOBAL_URL}/api/categories/${categoryId.value}`,
    )
    console.log(res)
    if (res.status == 200) {
      list.value = res.data
    }
    return res
  } catch (e) {
    console.error('실패', e)
  }
})
</script>

<template>
  <section id="product_wrapper">
    <article class="product_gnb">
      <h1 class="product_category_title">{{ categoryTitle }}</h1>
      <ul class="product_category">
        <li><router-link to="/">전체상품</router-link></li>
        <li><router-link to="/">남성향수</router-link></li>
        <li><router-link to="/">여성향수</router-link></li>
        <li><router-link to="/">등등</router-link></li>
      </ul>

      <div class="product_dropdown">
        <p class="product_mount">총 1개</p>

        <div class="sort_container">
          <p class="sort_trigger">추천순⇅</p>
          <ul class="product_sort">
            <li>최신순↑</li>
            <li>최신순↓</li>
            <li>가격순↑</li>
            <li>가격순↓</li>
          </ul>
        </div>
      </div>
    </article>

    <article class="product_list">
      <ProductComponent
        v-for="product in list"
        :key="product.productId"
        :productInfo="product"
      />
      <!-- props -->
    </article>
  </section>
</template>

<style scoped>
/* 전체설정 */
#product_wrapper {
  max-width: var(--main-max-width);
  width: 100%;
  margin: 0 auto;
}
/* 상품 gnd 설정 */
.product_gnb {
  position: relative;
  width: 100%;
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.product_category_title {
  font-family: var(--font-JacquesFrancois);
  font-size: 2.8rem;
}
.product_category {
  display: flex;
  margin-top: 2rem;
}
.product_category li {
  font-size: 1.4rem;
  margin: 0 1rem;
}

/* dropdown 메뉴 설정 */
.product_dropdown {
  position: absolute;
  bottom: 0;
  right: 0;
  display: flex;
}
.sort_container {
  cursor: pointer;
}
.product_sort {
  display: none;
  position: absolute; /* 드롭다운 위치 조정 */
  top: 100%; /* 드롭다운을 바로 아래에 표시 */
  right: 0; /* 왼쪽 정렬 */
  background-color: white;
  border: 1px solid #ccc;
  z-index: 900;
  width: 75px;
  text-align: center;
}
.sort_trigger {
  width: 75px;
  height: 28px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
  background-color: #ccc;
}
.sort_container:hover .product_sort {
  display: block;
}
.product_sort li {
  padding: 10px;
}
.product_sort li:hover {
  background-color: #f0f0f0;
}

/* 상품 리스트 설정 */
.product_list {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

/* 미디어쿼리 구간 */
@media (max-width: 630px) {
  .product_list {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
