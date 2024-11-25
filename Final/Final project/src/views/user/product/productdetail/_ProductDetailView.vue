<script setup>
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import ProductSlide from '@/views/user/product/productdetail/ProductDetailSlideView.vue';
import ProductDescription from '@/views/user/product/productdetail/ProductDescriptionView.vue';
import ProductDetailReview from '@/views/user/product/productdetail/ProductDetailReviewView.vue';
import ProductInfoSection from '@/views/user/product/productdetail/ProductDetailInfoSectionView.vue';

const route = useRoute();

const productId = ref(route.params.idx);
const productSize = ref(route.query.size);

const isProductInfoLoaded = ref(false); // 상태 변수

// ProductInfoSection이 로드 완료된 후 호출되는 메소드
const handleProductInfoLoaded = newStatus => {
  isProductInfoLoaded.value = newStatus;
  console.log(isProductInfoLoaded.value);
};

watchEffect(() => {
  productId.value = route.params.idx;
  productSize.value = route.query.size;
});
</script>

<template>
  <!-- <nav class="productRoute">{{ productId }}번 상품 상세페이지 경로</nav> -->

  <section id="product">
    <main id="productMain">
      <ProductSlide :productId="productId" />
      <!-- ProductInfoSection이 로드되었을 때 나머지 컴포넌트를 렌더링 -->
      <ProductInfoSection @onProductInfoLoaded="handleProductInfoLoaded" />
    </main>
    <!-- v-if="isProductInfoLoaded" id="ProductDescription" -->
    <!-- 조건부 렌더링: ProductInfoSection이 로드되면 나머지 컴포넌트를 렌더링 -->
    <div class="sectionLine"></div>
    <ProductDescription />
    <div class="sectionLine"></div>
    <ProductDetailReview />
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
.sectionLine::after {
  position: absolute;
  content: '';
  border: 0.5px solid var(--color-main-Lgray);
  height: 0.1px;
  width: var(--main-max-width);
  display: flex;
  margin-top: -20px;
}
</style>
