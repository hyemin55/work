<script setup>
import { GLOBAL_URL } from '@/api/util';
import CartProductComponent from '@/components/CartProductComponent.vue';
import { useCartStore } from '@/stores/CartStore';
import { useUserStore } from '@/stores/Login';
import axios from 'axios';
import { computed, ref, watchEffect, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { eventBus } from '@/eventBus';
import { usePayMentStore } from '@/stores/PayMentStore';
import { fetchMemeberCart, mergeMemberCart } from '@/api/cartApi';
// import { storeToRefs } from 'pinia'

// 전체 선택 체크박스 토글
const toggleAllCheck = () => {
  cartStore.toggleAllCheck(allChecked.value);
};

// 로그인 pinia
const userStore = useUserStore();
const userLogin = computed(() => userStore.loginCheck);

// 장바구니 pinia
const cartStore = useCartStore();
const cart = computed(() => cartStore.cartItems);
const checkList = computed(() => cartStore.cartCheckList);
const allChecked = ref(true);

// 장바구니 계산
const deliveryPrice = ref(0);
const total_amount = computed(() => {
  return Number(deliveryPrice.value + cartStore.totalPrice);
});

// 장바구니 삭제
const deleteToCart = async () => {
  cartStore.removeItem();
  if (userLogin.value) {
    await axios.delete(`${GLOBAL_URL}/cart/remove`, {
      data: cartStore.cartCheckList,
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    cartStore.cartCheckList = [];
  }
};

// 로그인 실행 (라우터가 이동하면 한번)
onMounted(async () => {
  await cartLogin();
});
// 로그아웃 시 세션 초기화
eventBus.on('logout', () => {
  console.log('로그아웃 처리');
  cartStore.logOutRemoveItem();
  sessionStorage.removeItem('isCartFetched');
});
// 로그인
const cartLogin = async () => {
  console.log('로그인 체크');
  const token = sessionStorage.getItem('token');
  if (!token) {
    console.log('토큰이 없습니다. 로그인 필요');
    return; // 토큰이 없으면 중단
  }
  // 이부분 대체 :
  const isCartFetched = sessionStorage.getItem('isCartFetched') === 'true'; // 장바구니 로딩 여부 확인
  if (isCartFetched) return; // 이미 불러왔다면 중단

  if (token) {
    console.log('로그인 여부 저장');
    const pushData = cart.value.map(item => ({
      productId: item.productId,
      quantity: item.quantity,
    }));
    await mergeMemberCart(pushData); // 장바구니 데이터 합치기
    const fetchRes = await fetchMemeberCart();
    sessionStorage.setItem('isCartFetched', 'true');
    // 스토어에서 장바구니 업데이트(store 랜더링)
    cartStore.updateCart(fetchRes.data);
  }
};

console.log('로그인 완료');

// const payMentStore = usePayMentStore();
// 결제 하러가기
const payRouter = useRouter();
const doPayment = () => {
  if (sessionStorage.getItem('token')) {
    if (checkList.value.length > 0) {
      const purchaseProducttDtos = checkList.value.map(item => ({
        productId: item.productId,
        quantity: item.quantity,
        name: item.productName,
      }));
      const data = {
        purchaseProductDtos: purchaseProducttDtos,
        totalPrice: total_amount.value,
      };
      // payMentStore.payProductScan(data)

      payRouter.push({
        path: '/payment',
        query: { item: encodeURIComponent(JSON.stringify(data)) },
      });
    } else {
      alert('선택된 상품이 없습니다.');
    }
  } else {
    alert('로그인 후 이용 가능합니다.');
    payRouter.push({ path: '/login2' });
  }
};

// watchEffect(() => {
//   checkList.value = route.path === '/';
// });
</script>

<template>
  <section id="cart_wrapper">
    <article class="if_cart" v-if="cart.length > 0">
      <article class="cart_product">
        <ul class="cart_ctroll">
          <li>
            <label class="product_check_container">
              <input @change="toggleAllCheck" v-model="allChecked" type="checkbox" name="allCheck" id="allCheck" class="product_check" />
              <span class="custom-checkmark"></span>
            </label>
            <label for="allCheck">전체 선택</label>
          </li>
          <li><button @click="deleteToCart">선택 삭제</button></li>
        </ul>

        <CartProductComponent v-for="item in cartStore.cartItems" :key="item.productId" :productInfo="item" :isChecked="item.isChecked" v-model:isChecked="item.isChecked" />
      </article>

      <article class="cart_total_price">
        <article>
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
                <li style="font-weight: 800">총 결제 금액</li>
                <li>{{ total_amount.toLocaleString() }}원</li>
              </ul>
            </div>
            <button class="payment" @click="doPayment">결제하기</button>
          </div>
        </article>
      </article>
    </article>

    <article class="else_cart" v-else>
      <img src="@/assets/img/else_cart.png" alt="" />
      <h1>장바구니에 담긴 물건이 없습니다.</h1>
    </article>
  </section>
</template>

<style scoped>
/* 장바구니 전체설정 */
#cart_wrapper {
  position: relative;
  margin: 0 auto;
  height: auto;
  min-height: calc(100vh - 100px);
  width: var(--main-max-width);
}
.if_cart {
  position: relative;
  display: flex;
  width: 100%;
  height: 100%;
}
.else_cart {
  position: absolute;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.else_cart img {
  width: 20%;
  height: auto;
  margin: -10rem 0 2rem 0;
  animation: cart 4s infinite;
}
.else_cart h1 {
  font-size: 3rem;
  font-weight: 400;
  color: var(--color-text-gray);
  opacity: 1;
}
@keyframes cart {
  0% {
    transform: translateY(0px) rotate(0deg);
  }
  25% {
    transform: translateY(7px) rotate(3deg);
  }
  50% {
    transform: translateY(2px) rotate(3deg);
  }
  75% {
    transform: translateY(11px) rotate(0deg);
  }
  100% {
    transform: translateY(0px) rotate(0deg);
  }
}

/* 장바구니 상품 설정 (좌측)*/
.cart_product {
  position: relative;
  height: 100%;
  /* background-color: var(--color-main-Lgray); */
  background-color: var(--color-main-pink);
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 768px;
  min-height: 100vh;
  padding-bottom: 50px;
  border-bottom-left-radius: 15px; /* 왼쪽 아래 모서리 */
  border-bottom-right-radius: 15px; /* 오른쪽 아래 모서리 */
  /* max-width: 768px;
  width: 60%; */
  /* justify-content: center; */
}
.cart_ctroll {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 630px;
  height: 80px;
  font-size: 1.1rem;
}
.cart_ctroll li {
  display: flex;
  align-items: center;
}
.cart_ctroll label {
  cursor: pointer;
}
.product_check_container input {
  margin-right: 8px;
}

/* 결제금액 설정 (우측)*/
.cart_total_price {
  position: relative;
  display: flex;
  justify-content: center;
  width: 512px;
  min-height: 100vh;
  /* padding-bottom: 174px; */
  padding-bottom: 230px;
}
.cart_total_price > article {
  position: relative;
  width: 312px;
  height: 100%;
}
.cart_total_price > article > div {
  position: sticky;
  top: 300px;
  width: 312px;
  height: 382px;
}
.cart_title {
  font-size: 2.4rem;
  font-weight: 800;
  padding: 2rem;
}
#calculate {
  border-top: 1.5px solid black;
  border-bottom: 1.5px solid black;
  padding: 10px 0;
}
.price_list {
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: auto;
}
.price_list li {
  font-size: 1.8rem;
  padding: 2rem;
}
.price_list li:nth-child(2) {
  font-weight: 800;
}
#total_amount {
  margin: 10px 0;
}
.payment {
  width: 100%;
  height: 62px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #101010;
  border: 2px solid #101010;
  color: #fff;
  border-radius: 5px;
  font-size: 16px;
}
/* .payment:hover{
  color: #030303;
  background-color: #f5f5f5;
  border: 2px solid #030303;
} */

/* 체크박스 설정 */
.product_check {
  position: relative;
  width: 18px;
  height: 18px;
  appearance: none;
  transition: all 0.1s;
  border: solid 1px #818181;
  border-radius: 0.4rem;
}
.product_check:checked {
  background-color: var(--color-main-bloode); /* 체크 시 배경색 */
  border: solid 1px var(--color-main-bloode);
}
.product_check:checked::before {
  content: '';
  position: absolute;
  top: 2.5px;
  left: 6.5px;
  width: 4px;
  height: 8px;
  border: solid white;
  border-width: 0 2px 2px 0;
  transform: rotate(45deg); /* 체크 모양을 위한 회전 */
}
</style>
