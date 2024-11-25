<script setup>
import { computed, ref, watchEffect } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { formatPrice } from '@/FormatPrice';
import _ProductDetailView from '@/views/user/product/productdetail/_ProductDetailView.vue';
import { getProductData, getReviewData } from '@/api/productDetailApi';
import ProductDetailSalseChartViewVue from '@/views/user/product/productdetail/ProductDetailSalseChartView.vue';
import { useCartStore } from '@/stores/CartStore';
import { useUserStore } from '@/stores/Login';

import axios from 'axios';
import { GLOBAL_URL } from '@/api/util';
import { fetchMemeberCart, mergeMemberCart } from '@/api/cartApi';
const route = useRoute();
const router = useRouter();

const productData = ref([]);
const reviewData = ref(null);
const productDataOk = ref([]);

const idx = ref(route.params.idx);
const size = ref(route.query.size);

const cartStore = useCartStore();

// 로그인 pinia
const userStore = useUserStore();
const userLogin = computed(() => userStore.loginCheck);
const emit = defineEmits();
// const emit = defineEmits();

// 1. 클릭한 옵션값을 idx에 담아준다.
const productOptionSelect = item => {
  // console.log('item', item.productId);
  // console.log('item', item.size);
  router.push({
    name: 'productsdetail',
    params: { idx: item.productId },
    query: { size: item.size },
  });
};

// 3. 옵션값을 클릭하면 watch에서 추적하는 idx값이 바뀌고 doLoad를 호출한다.
const doLoad = async () => {
  // console.log(`doLoad = ${idx.value}`);
  try {
    productData.value = await getProductData(idx.value);
    reviewData.value = await getReviewData(idx.value);

    // console.log('productData 값 : ', productData.value.data[0].productId);
    // console.log('reviewData 값 : ', reviewData.value.length);

    if (productData.value.status === 200 && reviewData.value.status === 200) {
      // console.log('productData.value.status === 200', productData.value);
      for (let i = 0; i < productData.value.data.length; i++) {
        // console.log('조건에 맞는 아이는? ', productData.value.data[i].size);
        if (productData.value.data[i].productId == idx.value && productData.value.data[i].size == size.value) {
          productDataOk.value = productData.value.data[i];
          // console.log('데이터내용들', productDataOk.value);
        }
      }
      console.log('reviewData.value', reviewData.value);

      // const newStatus = true;
      // emit('onProductInfoLoaded', newStatus);
    } else if (productData.value.status == 500) {
      console.log(productData.value.status);
      router.push({ name: 'main' });
    } else {
      console.log('실패1');
    }
  } catch (err) {
    console.log('실패2' + err);
  }
};
const BuyNow = () => {};

// console.log(productDataOk);

// 장바구니 추가
const addToCart = async () => {
  const data = {
    productId: Number(idx.value),
    productName: productDataOk.value.productName,
    price: productDataOk.value.price,
    brandName: productDataOk.value.brandName,
    size: productDataOk.value.size,
    images: [productDataOk.value.mainImage],
    mainImage: productDataOk.value.mainImage,
    quantity: 1,
  };

  cartStore.addItem(data);
  alert('장바구니에 담았습니다.');

  if (userLogin.value) {
    try {
      const res = axios.post(`${GLOBAL_URL}/cart/add`, data, {
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem('token')}`,
        },
      });
      console.log(res);
      const pushData = cart.value.map(item => ({
        productId: item.productId,
        quantity: item.quantity,
      }));
      await mergeMemberCart(pushData);
      const fetchRes = await fetchMemeberCart();
      // 스토어에서 장바구니 업데이트(store 랜더링)
      cartStore.updateCart(fetchRes.data);
    } catch (e) {
      console.log(e);
    }
  }
};

// 찜 클릭 이벤트
const redHeart = ref(false);
const addToWishlist = () => {
  redHeart.value = !redHeart.value;
  if (redHeart.value == true) alert('༼ つ ◕_◕ ༽つ 찜~');
};

const isselectedSize = size => {
  return route.query.size === size.size.toString() && route.params.idx === size.productId.toString();
};
// 리뷰별점평균을 소수점 1자리만 남긴다.
const Average = data => {
  data = data * 10;
  data = Math.round(data);
  data = data / 10;
  return data;
};

watchEffect(() => {
  idx.value = route.params.idx;
  size.value = route.query.size;
  doLoad();
});
</script>

<template>
  <article id="productInfoSection">
    <ul id="productInfo">
      <li>{{ productDataOk.brandName }}</li>
      <li>{{ productDataOk.productName }}</li>
      <li v-if="reviewData">
        1,222찜 수
        <span style="color: orange">★ {{ Average(reviewData.data.starAverage) }} ({{ reviewData.data.reviewCount }} reviews)</span>
      </li>
      <li>{{ formatPrice(productDataOk.price) }}</li>
    </ul>

    <p class="OptionSelect">옵션선택</p>
    <div id="productOption">
      <button @click="productOptionSelect(size)" v-for="(size, index) in productData.data" :key="index" :class="{ selectedSize: isselectedSize(size) }">{{ size.size }} ml</button>
    </div>
    <!-- <div>
      <p>제조일자 : 2024-11-01</p>
      <p>유통기한 : 2029-11-01</p>
    </div> -->

    <div class="addButtonGroub">
      <button class="addToCart Sell​​Now">바로 판매하기</button>
      <button class="addToCart BuyNow" @click="BuyNow">바로 구매하기</button>
      <!-- <button class="addToCart" @click="addToCart"> -->
      <button class="icon_box" @click="addToCart">
        <img class="icon" src="@/assets/img/icon/free-icon-font-shopping-cart.svg" alt="" />
      </button>
      <button class="icon_box" :class="{ active: redHeart }" @click.stop="addToWishlist">
        <img class="icon" src="@/assets/img/icon/free-icon-font-heart-line.svg" alt="" />
      </button>
    </div>

    <ProductDetailSalseChartViewVue />
  </article>
</template>

<style scoped>
/* 오른쪽 상품정보 구역 */
#productInfoSection {
  /* background-color: aquamarine; */
  width: 50%;
  margin: 20px -10px 25px 30px;
  /* background-color: yellow; */
}
#productInfo {
  text-align: left;
}
#productInfo li:nth-child(1) {
  font-size: 1.4rem;
}
#productInfo li:nth-child(2) {
  font-size: 3.4rem;
  margin-top: 20px;
}
#productInfo li:nth-child(3) {
  font-size: 1.4rem;
  margin-top: 20px;
}
#productInfo li:nth-child(4) {
  font-size: 2rem;
  margin-top: 20px;
}
.OptionSelect {
  font-size: 1.6rem;
  margin: 25px 0 10px 0;
}
#productOption {
  display: flex;
  justify-content: left;
  align-items: center;
  gap: 2%;
  /* width: 70%; */
  font-size: 1.4rem;
  /* background-color: aquamarine; */
}
#productOption button {
  border: 0.5px solid var(--color-main-gray);
  padding: 10px 7%;
  border-radius: 10px;
  cursor: pointer;
}
button.selectedSize {
  background-color: var(--color-main-bloode);
  color: white;
  border: 0.5px solid var(--color-main-bloode);
}
/* #productOption button {
  background-color: var(--color-main-bloode);
  color: white;
  border: 0.5px solid var(--color-main-bloode);
} */
.addButtonGroub {
  display: flex;
  align-items: flex-end;
  justify-content: left;
  margin: 15px 0;
  height: 45px;
  width: 100%;
  /* background-color: brown; */
  gap: 2%;
}
.addToCart {
  /* background-color: antiquewhite; */
  width: 70%;
  height: 100%;
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.6rem;
  font-family: 'Pretendard-SemiBold';
  color: white;
}
.Sell​​Now {
  width: 100%;
  background-color: orange;
}
.BuyNow {
  width: 100%;
  background-color: var(--color-main-bloode);
}
.icon_box {
  width: 85px;
  height: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 0.5px solid var(--color-main-gray);
  border-radius: 50%;
}
.icon {
  text-align: center;
  width: 80%;
  height: auto;
  padding: 10%;
  transition: filter 0.4s;
}
.icon_box.active {
  background-color: orange;
  border: 0.5px solid var(-color-main-bloode);
}
</style>
