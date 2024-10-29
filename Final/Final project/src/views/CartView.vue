<script setup>
import { GLOBAL_URL } from '@/api/util';
import CartProductComponent from '@/components/CartProductComponent.vue';
import router from '@/router';
import { useCartStore } from '@/stores/CartStore';
import axios from 'axios';
import { computed, ref, watchEffect } from 'vue';

const cartStore = useCartStore();
const cart = computed(() => cartStore.cartItems);
const allChecked = ref(true);

const deliveryPrice = ref(5000);
const total_amount = computed(() => {
  return Number(deliveryPrice.value + cartStore.totalPrice);
});


// 전체 선택 체크박스 토글
const toggleAllCheck = () => {
  cartStore.toggleAllCheck(allChecked.value);
};

// 장바구니 삭제
const deleteToCart = async() => {
  await axios.delete(`${GLOBAL_URL}/cart/remove`, 
  {
    data : cartStore.cartCheckList, 
    headers: {
      'Content-Type': 'application/json',
    },
  });
  cartStore.removeItem();
};


// 회원장바구니 불러오기
// watchEffect(async()=>{
//   console.log('회원장바구니 호출')
//   const res = await axios.get(`${GLOBAL_URL}/cartProduct/select?memberId=1`)
//   .then(res => {
//     cartStore.updateCart(res.data);
//   })
//   .catch(error => {
//       console.error(error);
//   });
// });

const fetchMemberCart = async () => {
  alert('로그인 성공')
  console.log('회원장바구니 호출');
  try {
    const res = await axios.get(`${GLOBAL_URL}/cartProduct/select?memberId=1`);
    // 스토어에서 장바구니 업데이트
    cartStore.updateCart(res.data);
  } catch (error) {
    console.error(error);
  }
};

// 결제하기
// const doPayment =()=>{
//   router.push()
// };

</script>

<template>
  <section id="cart_wrapper">

    <article class="if_cart" v-if="cart.length>0">
      <article class="cart_product">
        <ul class="cart_ctroll">
          <li><input 
            @change="toggleAllCheck" 
            v-model="allChecked" 
            type="checkbox" 
            name="allCheck" 
            id="allCheck">
            <label for="allCheck">전체 선택</label>
          </li>
          <li><button @click="deleteToCart">선택 삭제</button></li>
          <li><button @click="fetchMemberCart">로그인 장바구니</button></li>
        </ul>

        <CartProductComponent 
        v-for="(item, index) in cartStore.cartItems" 
        :key="item.productId" 
        :productInfo="item"
        :isChecked="item.isChecked"
        v-model:isChecked="item.isChecked"
        />
      </article>
      <article class="cart_total_price">
        <div>
          <h1 class="cart_title">장바구니</h1>
          <div id="calculate">
            <ul class="product_price price_list">
              <li>상품 금액</li>
              <li>{{ cartStore.totalPrice.toLocaleString() }}원</li>
            </ul>
            <ul class="delivery_pirce price_list">
              <li>배송비</li>
              <li>{{ deliveryPrice.toLocaleString() }}원</li>
            </ul>
          </div>
          <div id="total_amount">
            <ul class="price_list">
              <li style="font-weight: 800;">총 결제 금액</li>
              <li>{{ total_amount.toLocaleString() }}원</li>
            </ul>
          </div>        
          <button class="payment" @click="doPayment">결제하기</button>
        </div>
      </article>
    </article>

    <article class="else_cart" v-else>
      <img src="../img/else_cart.png" alt="">
      <h1>장바구니에 담긴 물건이 없습니다.</h1>
    </article>
  </section>
</template>


<style scoped>
/* 장바구니 전체설정 */
#cart_wrapper{
  position: relative;
  max-width: var(--main-max-width);
  width: 100%;
  margin: 0 auto;
  height: calc(100vh - 100px);
}
.if_cart{
  position: relative;
  display: flex;
  width: 100%;
  height: 100%;
}
.else_cart{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.else_cart img{
  width: 20%;
  height: auto;
  margin: -10rem 0 2rem 0;
  animation: cart 4s infinite;
}
.else_cart h1{
  font-size: 3rem;
  font-weight: 400;
  color: var(--color-text-gray);
  opacity: 1;
}
@keyframes cart{
  0%{
    transform: translateY(0px) rotate(0deg);
  }
  25%{
    transform: translateY(7px) rotate(3deg);
  }
  50%{
    transform: translateY(2px) rotate(3deg);
  }
  75%{
    transform: translateY(11px) rotate(0deg);
  }
  100%{
    transform: translateY(0px) rotate(0deg);
  }
}

/* 장바구니 상품 설정 */
.cart_product{
  position: relative;
  max-width: 768px;
  width: 60%;
  height: 100%;
  overflow-y: scroll;
  background-color: var(--color-main-Lgray);
  display: flex;
  flex-direction: column;
  align-items: center;
  /* justify-content: center; */
}
.cart_ctroll{
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 80px;
  padding: 20px 69px;
}
.cart_product::-webkit-scrollbar {width: 0.7rem;}
.cart_product::-webkit-scrollbar-track {background: transparent;}
.cart_product::-webkit-scrollbar-thumb {background: rgba(0, 0, 0, 0.2); border-radius: 10px;}
.cart_product::-webkit-scrollbar-thumb:hover {background: rgba(0, 0, 0, 0.5);}

/* 결제금액 설정 */
.cart_total_price{
  position: relative;
  max-width: 512px;
  width: 40%;
  height: 100%;
}
.cart_total_price>div{
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -241px;
  margin-left: -147.5px;
  width: 295px;
  height: 382px;
}
.cart_title{
  font-size: 2.4rem;
  font-weight: 800;
  padding: 2.0rem;
}
#calculate{
  border-top: 1.5px solid black;
  border-bottom: 1.5px solid black;
  padding: 10px 0;
}
.price_list{
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: auto;
}
.price_list li{
  font-size: 1.8rem;
  padding: 2.0rem;
}
.price_list li:nth-child(2){
  font-weight: 800;
}
#total_amount{
  margin: 10px 0;
}
.payment{
  width: 100%;
  height: 62px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px solid rgb(146, 146, 146);
  border-radius: 5px;
  font-size: 16px;
}
</style>
