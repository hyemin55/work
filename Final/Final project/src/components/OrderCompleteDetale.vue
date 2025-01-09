<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios from 'axios';
import { ref } from 'vue';
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

// 계속 쇼핑하기
const router = useRouter();
const gohome = () => {
  router.push({ name: 'main' });
};

const buyerInfoRef = ref(null);

// 주무자 정보 받아오기
const route = useRoute();
const merchantUid = route.query.merchant_uid;
console.log('merchantUid:', merchantUid);

onMounted(async () => {
  const res = await axios.get(`${GLOBAL_URL}/api/payment/orderCompleted?merchant_uid=${merchantUid}`);
  buyerInfoRef.value = res.data;
  console.log(buyerInfoRef.value);
});
</script>

<template>
  <!-- <section class="order_complete_wrapper" v-if="buyerInfoRef">
    <article class="order_complete_title">
      <img src="/src/assets/img/shoppingBag.jpg" alt="">
      <ul>
        <li>상품 주문이 완료되었습니다.</li>
        <li>주문번호 : {{ buyerInfoRef.purchaseId }}</li>
        <li @click="gohome()">계속 쇼핑하기</li>
      </ul>
    </article>

    <article class="order_complete_delivery">
      <ul>
        <li>주문자 : {{ buyerInfoRef.buyerName }}</li>
        <li>배송지 : {{ buyerInfoRef.buyerAddr }} {{ buyerInfoRef.buyerPostcode }}</li>
      </ul>
    </article>

    <article class="order_complete_product">
      <p>상품정보</p>
      <ul v-for="item in buyerInfoRef.usedProductResDtos" :key="item.idx">
        <li>
          <img
            :src="`${GLOBAL_URL}/api/file/download/${item.userSaleImages[0].filename}`"
            alt=""
            class="userReviewImg"
          />
        </li>
        <li>상품명 : {{ item.productName }}</li>
        <li>브랜드 : {{ item.brandName }}</li>
        <li>사이즈 : {{ item.size }}</li>
        <li>등급 : {{ item.verifiedSaleGradeType }}</li>
      </ul>

      <p>결제정보</p>
      <ul>
        <li>주문금액 : {{ buyerInfoRef.amount }}</li>
        <li>배송비 : 0</li>
        <li>결제금액 : {{ buyerInfoRef.amount }}</li>
      </ul>
    </article>
  </section> -->
  <section class="order_complete_wrapper" v-if="buyerInfoRef">
    <article class="order_complete_title">
      <img src="/src/assets/img/shoppingBag.jpg" alt="">
      <ul>
        <li>상품 주문이 완료되었습니다.</li>
        <li>주문번호 : </li>
        <li @click="gohome()">계속 쇼핑하기</li>
      </ul>
    </article>

    <article class="order_complete_delivery">
      <ul>
        <li>주문자 : {{ buyerInfoRef.buyerName }} </li>
        <li>배송지 : {{ buyerInfoRef.buyerAddr }} {{ buyerInfoRef.buyerPostcode }}</li>
      </ul>
    </article>

    <article class="order_complete_product product">
      <p class="product_title">주문 상품</p>      
      <div class="product_component" v-for="item in buyerInfoRef.usedProductResDtos" :key="item.idx">
        <div class="img_box">
            <img
            :src="`${GLOBAL_URL}/api/file/download/${item.userSaleImages[0].filename}`"
            alt=""
            class="userReviewImg"
          />
        </div>
        <ul>
          <li>상품명 : {{ item.productName }}</li>
          <li>브랜드 : {{ item.brandName }}</li>
          <li>사이즈 : {{ item.size }}</li>
          <li>등급 : {{ item.verifiedSaleGradeType }}</li>
        </ul>
      </div>
    </article>

    <article class="order_complete_product pay">
      <ul>
        <li><span>주문금액 :</span> {{ buyerInfoRef.amount }}원</li>
        <li><span>배송비 :</span>0 원</li>
        <li><b>결제금액 :</b> {{ buyerInfoRef.amount }} 원</li>
      </ul>
    </article>

  </section>
</template>

<style scoped>
/* 전체설정 */
.order_complete_wrapper {
  max-width: 600px;
  width: 100%;
  margin: 0 auto;
  text-align: center;
}
/* 주문완료 타이틀 */
.order_complete_title {
  text-align: center;
  border-bottom: 2px solid #535353;
  padding: 20px 0;
  padding-bottom: 25px;
}
.order_complete_title img{width: 180px;}
.order_complete_title ul {margin-top: 20px;}
.order_complete_title ul li {margin: 5px 0;}
.order_complete_title ul li:nth-child(1) {font-size: 2.7rem; font-weight: 600;}
.order_complete_title ul li:nth-child(2) {font-size: 1.5rem;}
.order_complete_title ul li:nth-child(3) {
  font-size: 1.4rem;
  background-color: var(--color-main-bloode);
  color: #fff;
  display: inline-block;
  padding: 8px 10px;
  margin-top: 20px;
  border-radius: 1.5rem;
  cursor: pointer;
}
.order_complete_title ul li:nth-child(3):hover{
  background-color: #000;
  color: #fff;
}


/* 주문완료 배송지 */
.order_complete_delivery {
  margin: 0 auto;
  border-bottom: 2px solid #e2e2e2;
}
.order_complete_delivery ul {
  font-size: 2rem;
  margin: 0 auto;
  text-align: left;
  padding: 10px 10px;
}
.order_complete_delivery ul li{
  margin: 14px 0;
}


/* 주문완료 상품 */
.order_complete_product {
  margin: 0 auto;
}
.product{
  border-bottom: 2px solid #e2e2e2;
  padding: 0px 10px;
}
.product>p{
  font-size: 2rem;
}
.product_title{
  margin: 0 auto;
  margin-top: 20px;
  text-align: left;
}
.product_component{
  display: flex;
  height: 142px;
  margin: 20px auto;
  padding: 15px;
  background-color: #f5f5f5;
  border-radius: 10px;
}
.img_box{
  background-color: #fff;
  border-radius: 1rem;
  height: 100%;
  aspect-ratio: 1;
  object-fit: cover;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}
.product ul{
  font-size: 1.5rem;
  text-align: left;
  margin-left: 26px;
}
.order_complete_product ul li{
  margin: 10px 0;
}

/* 주문완료 금액 */
.pay ul{
  font-size: 2.2rem;
  margin: 0 auto;
  text-align: left;
  /* background-color: #e2e2e2; */
  padding: 10px 10px;
  border-radius: 1.5rem;
}
.pay ul li{
  margin: 17px 0px;
  display: flex;
  justify-content: space-between;
}

</style>
