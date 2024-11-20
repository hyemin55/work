<script setup>
import { computed, ref, watchEffect } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { formatPrice } from '@/FormatPrice';
import _ProductDetailView from '@/views/product/productdetail/_ProductDetailView.vue';
import { getProductData, getReviewData } from '@/api/productDetailApi';
import ProductDetailSalseChartViewVue from './ProductDetailSalseChartView.vue';
// import { useCartStore } from '@/stores/CartStore';
// import { useUserStore } from '@/stores/Login';

const route = useRoute();
const router = useRouter();

const productData = ref([]);
const reviewData = ref(null);
const productDataOk = ref([]);

const idx = ref(route.params.idx);
const size = ref(route.query.size);

// const cartStore = useCartStore();

// 로그인 pinia
// const userStore = useUserStore();
// const userLogin = computed(() => userStore.loginCheck);
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
      // console.log('reviewData.value', reviewData.value);

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
// const addToCart = async () => {
//   cartStore.addItem(productDataOk);
//   console.log('찍었다.', productDataOk);
//   alert('장바구니에 담았습니다.');

//   if (userLogin.value) {
//     const data = {
//       productId: idx.value,
//       quantity: 1,
//     };
//     try {
//       const res = axios.post(`${GLOBAL_URL}/cart/add`, data, {
//         headers: {
//           Authorization: `Bearer ${sessionStorage.getItem('token')}`,
//         },
//       });
//       console.log(res);
//     } catch (e) {
//       console.log(e);
//     }
//   }
// };

// 찜 클릭 이벤트
const redHeart = ref(false);
const addToWishlist = () => {
  redHeart.value = !redHeart.value;
  if (redHeart.value == true) alert('༼ つ ◕_◕ ༽つ 찜~');
};

// URL 공유 클릭 이벤트
const urlShare = () => {
  const url = window.location.href;
  navigator.clipboard
    .writeText(url)
    .then(() => {
      alert('URL이 클립보드에 복사되었습니다.');
    })
    .catch(err => {
      console.error('URL 복사를 실패했어요ㅠㅡㅠ', err);
    });
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
      <button class="addToCart BuyNow" @click="BuyNow">바로 구매하기</button>
      <!-- <button class="addToCart" @click="addToCart"> -->
      <button class="addToCart">
        <img src="@/assets/img/icon/free-icon-font-shopping-cart.svg" alt="" />
        장바구니 추가
      </button>
      <button class="wish_push" :class="{ active: redHeart }" @click.stop="addToWishlist">
        <img class="icon" src="@/assets/img/icon/free-icon-font-heart-line.svg" alt="" />
      </button>
      <button class="wish_push" @click="urlShare">
        <img src="@/assets/img/icon/free-icon-font-share-3917574.png" class="icon" alt="" />
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
  margin: 20px 0 25px 30px;
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
  align-items: center;
  gap: 25px;
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
  justify-content: space-between;
  margin: 25px 0;
  height: 45px;
  width: 80%;
  /* background-color: brown; */
  gap: 2%;
}
.addToCart {
  width: 35%;
  height: 100%;
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--color-main-gray);
  font-size: 1.6rem;
}
.BuyNow {
  background-color: var(--color-main-bloode);
  color: white;
  font-family: 'Pretendard-SemiBold';
}
.addToCart > img {
  width: 15%;
  margin-right: 5px;
  /* background-color: antiquewhite; */
}
.wish_push {
  height: 100%;
  width: 9%;
  /* background-color: rgb(173, 143, 104); */
  border-radius: 50%;
  border: 0.5px solid var(--color-main-gray);
}
.icon {
  width: 100%;
  height: auto;
  /* border-radius: 50%; */
  padding: 15%;
  transition: filter 0.4s;
}
.wish_push.active {
  background-color: var(--color-main-bloode);
  border: 0.5px solid var(-color-main-bloode);
}
.wish_push.active .icon {
  filter: brightness(0) saturate(100%) invert(1);
}
</style>
