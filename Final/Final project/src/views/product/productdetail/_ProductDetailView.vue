<script setup>
import { computed, watchEffect } from 'vue'
import { useRoute } from 'vue-router'
import ProductSlide from '@/views/product/productdetail/ProductDetailSlideView.vue'
import ProductDescription from '@/views/product/productdetail/ProductDescriptionView.vue'
import ProductDetailReview from '@/views/product/productdetail/ProductDetailReviewView.vue'
import ProductInfoSection from '@/views/product/productdetail/ProductDetailInfoSectionView.vue'
import { productDetailStore } from '@/stores/productDetailStore'

const detailStore = productDetailStore()
const route = useRoute()
const productId = computed(() => route.params.idx)
const productSize = computed(() => route.query.size)

// console.log('메인디테일페이지의 productId = ', productId.value)
// console.log('메인디테일페이지의 productSize = ', productSize.value)
watchEffect(() => {
  detailStore.setIdx(productId.value, productSize.value)
  // console.log('제품아이디 = ', DetailStore.productIdx)
})
</script>

<template>
  <nav class="productRoute">{{ productId }}번 상품 상세페이지 경로</nav>

  <section id="product">
    <main id="productMain">
      <ProductSlide :productId="productId" />
      <ProductInfoSection />
    </main>
    <ProductDescription id="ProductDescription" />
    <ProductDetailReview id="ProductDetailReview" />
  </section>
</template>

<style scoped>
.productRoute {
  padding: 20px 0 0 0;
  margin: 0 auto;
  color: var(--color-text-gray);
  width: var(--main-max-width);
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
#ProductDescription::before {
  position: absolute;
  content: '';
  border: 0.5px solid var(--color-main-Lgray);
  height: 0.1px;
  width: var(--main-max-width);
  display: flex;
  margin-top: -20px;
}
#ProductDetailReview::before {
  position: absolute;
  content: '';
  border: 0.5px solid var(--color-main-Lgray);
  height: 0.1px;
  width: var(--main-max-width);
  display: flex;
  margin-top: -20px;
}
</style>
