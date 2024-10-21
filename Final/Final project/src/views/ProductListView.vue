<script setup>
import ProductComponent from '@/components/ProductComponent.vue';
import { computed} from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const categoryTitle = computed(() => route.params.title); 

</script>

<template>
  <section id="product_wrapper">
    <article class="product_gnb">
      <h1 class="product_category_title">{{categoryTitle}}</h1>
      <ul class="product_category">
        <li><router-link to="/">카테고리1</router-link></li>
        <li><router-link to="/">카테고리2</router-link></li>
        <li><router-link to="/">카테고리3</router-link></li>
        <li><router-link to="/">카테고리4</router-link></li>
      </ul>
    </article>
    <article class="product_list">
      <ProductComponent/>

    </article>
  </section>
</template>


<style scoped>
/* 전체설정 */
#product_wrapper{
  max-width: var(--main-max-width);
  width: 100%;
  margin: 0 auto;
}
/* 상품 gnd 설정 */
.product_gnb{
  width: 100%;
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: end;
  background-color: rgb(255, 249, 241);
}
.product_category_title{
  font-size: 30px;
}
.product_category{
  display: flex;
}
.product_nav li{
  margin: 0 10px;
}
/* 상품 리스트 설정 */
.product_list{
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}
</style>
