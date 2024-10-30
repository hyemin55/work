<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import ProductInfoSection from './ProductInfoSection.vue'
import ProductSlide from './ProductSlide.vue'
import ProductDescription from './ProductDescription.vue'
import ProductDetailReview from './ProductDetailReview.vue'

const route = useRoute()
const productId = computed(() => route.params.idx)
</script>

<template>
  <nav class="productRoute">{{ productId }}번 상품 상세페이지 경로</nav>

  <section id="product">
    <main id="productMain">
      <ProductSlide :productId="productId" />
      <ProductInfoSection />
    </main>
    <ProductDescription />
    <ProductDetailReview />
  </section>
</template>

<style scoped>
nav {
  padding: 10px 0;
  color: var(--color-text-gray);
}
.productRoute {
  width: var(--main-max-width);
  margin: 0 auto;
}
#product {
  width: var(--main-max-width);
  margin: 0 auto;
}
/* 상단 구역 */
#productMain {
  display: flex;
  /* justify-content: center; */
  height: auto;
  /* background-color: antiquewhite; */
}
</style>
