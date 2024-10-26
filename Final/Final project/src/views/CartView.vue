<script setup>
import { GLOBAL_URL } from '@/api/util';
import CartProductComponent from '@/components/CartProductComponent.vue';
import { useCartStore } from '@/stores/CartStore';
import axios from 'axios';
import { ref, watchEffect } from 'vue';

const cartStore = useCartStore();
const cart = cartStore.cartItems;
const allChecked = ref(false);

// 전체 선택 체크박스 토글
const toggleAllCheck = () => {
  cartStore.toggleAllCheck(allChecked.value);
};

// 장바구니 삭제
const deleteToCart = () => {
  console.log(cartStore.deathNote.value)
  cartStore.removeItem();
  axios.delete(`${GLOBAL_URL}/cart/remove`, 
    {
      data : cartStore.deathNote, 
      headers: {
        'Content-Type': 'application/json',
      },
    });
};

const deleteAxios=(cartid)=>{
  console.log(cartid)
}

// 회원장바구니 불러오기
watchEffect(async()=>{
  console.log('회원장바구니 호출')

  const res = await axios.get(`${GLOBAL_URL}/cartProduct/select?memberId=1`)
  .then(res => {
    cartStore.updateCart(res.data);
    console.log(res);
    console.log("cart length : " + cart.length);
  })
  .catch(error => {
      console.error(error);
  });

})
</script>

<template>
  <section id="cart_wrapper">
    <article class="cart_product">
      <ul class="cart_ctroll">
        <li><input 
          @change="toggleAllCheck" 
          v-model="allChecked" 
          type="checkbox" 
          name="allCheck" 
          id="allCheck">
          <label for="allCheck">전체선택</label>
        </li>
        <li><button @click="deleteToCart">삭제</button></li>
      </ul>

      <CartProductComponent 
      v-for="(item, index) in cartStore.cartItems" 
      :key="index" 
      :productInfo="item"
      :isChecked="item.isChecked"
      v-model:isChecked="item.isChecked"
      @sendCartId="deleteAxios"
      />
    </article>

    <article class="cart_total_price">
      <div>
        <h1 class="cart_title">장바구니</h1>

        <div id="calculate">
          <ul class="product_price price_list">
            <li>상품 금액</li>
            <li>3,000원</li>
          </ul>
          <ul class="delivery_pirce price_list">
            <li>배송비</li>
            <li>5,000원</li>
          </ul>
        </div>
        
        <div id="total_amount">
          <ul class="price_list">
            <li style="font-weight: 800;">총 결제 금액</li>
            <li>8,000원</li>
          </ul>
        </div>        
        
        <button class="payment">결제하기</button>
      </div>
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
  display: flex;
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
