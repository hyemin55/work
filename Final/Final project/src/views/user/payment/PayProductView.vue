<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios from 'axios';
import { ref, watchEffect } from 'vue';

const props = defineProps({
  productInfo: {
    type: Object,
    required: true,
  },
});
const payProductInfo = props.productInfo.purchaseProductDtos || '상품정보';
const payProductMoney = props.productInfo.totalPrice || '상품총금액';
// const payDelivery = ref('0')
// const payTotal = ref( Number(payProductMoney+payDelivery) )

const list = ref();
watchEffect(async () => {
  console.log(props.productInfo.purchaseProductDtos);
  console.log(props.productInfo.totalPrice);
  const data = payProductInfo.map(item => ({
    usedProductId: item.usedProductId,
    quantity: item.quantity,
  }));
  try {
    const res = await axios.post(`${GLOBAL_URL}/api/payment/cart-product-info`, data, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    if (res.status === 200) {
      console.log('res', res.data);
      list.value = res.data;
    } else {
      console.log('오류');
    }
    return res;
  } catch (error) {
    console.log(error);
  }
});
</script>

<template>
  <section id="pay_product">
    <article class="pay_product_set" v-for="item in list" :key="item.productId">
      <div class="pay_product_compnents">
        <div class="pay_product_img">
          <!-- {{ mainImage }} -->
          <img :src="`${GLOBAL_URL}/api/file/download/${item.mainImage}`" alt="" />
        </div>
        <div class="pay_product_content">
          <ul>
            <li>
              <h6>{{ item.productName }}</h6>
            </li>
            <li class="pro_content"> {{ item.content }}</li>
            <li>
              
              옵션 : {{ item.size }} / 수량 : {{ item.quantity }}개
            </li>
          </ul>
          <p>가격 : {{ item.price.toLocaleString() }}원</p>
        </div>
        <div class="line"></div>
      </div>
    </article>

    <article class="pay_product_total_prcie">
      <p>총 상품 금액 : {{ payProductMoney.toLocaleString() }} 원</p>
    </article>
  </section>
</template>

<style scoped>
/* 전체 설정 */
#pay_product {
  padding: 15px 0;
  height: auto;
  width: 100%;
  position: relative;
  border-radius: 5px;
  background-color: #F5F5F5;
}
/* 상품 컴포넌트 설정 */
.pay_product_compnents {
  margin-left: 30px;
  padding: 15px 0px;
  width: calc(100% - 60px);
  display: flex;
  margin-bottom: 15px;
  background: #fff;
}
.pay_product_compnents:last-child {margin-bottom: 0;}
.pay_product_img {
  width: 145px;
  height: 145px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 1rem;
  margin-left: 15px;
}
.pay_product_img img {
  height: 90%;
  border-radius: 1rem;
  width: auto;
}
.pay_product_content {
  width: 100%;
}
.pay_product_content ul {
  margin-left: 30px;
}
.pay_product_content ul li {
  font-size: 1.5rem;
  letter-spacing: -0.034rem;
  margin: 10px 0;
  font-weight: 400;
}
.pay_product_content ul li h6 {
  font-size: 2.2rem;
  letter-spacing: -0.034rem;
  margin: 10px 0;
  font-weight: 700;
}
.pay_product_content p {
  text-align: right;
  font-size: 1.8rem;
  letter-spacing: -0.034rem;
  font-weight: 600;
  margin-right: 30px;
}
.pro_content {
  max-width: 450px;
  /* width: 50%; */
  white-space: nowrap; /* 텍스트가 한 줄로 표시되도록 설정 */
  overflow: hidden; /* 넘치는 텍스트를 숨김 */
  text-overflow: ellipsis; /* 넘친 텍스트를 '...'으로 표시 */
  color: var(--color-text-gray);
}

/* 총가격 설정 */
.pay_product_total_prcie {
  font-size: 2rem;
  font-weight: 800;
  width: 100%;
  height: auto;
  text-align: end;
  padding: 3rem;
}
</style>
