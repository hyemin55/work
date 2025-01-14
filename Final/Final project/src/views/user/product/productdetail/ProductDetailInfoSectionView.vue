<script setup>
import { computed, ref, watchEffect } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { formatPrice } from '@/FormatData';
import _ProductDetailView from '@/views/user/product/productdetail/_ProductDetailView.vue';
import { getProductData, getReviewData } from '@/api/productDetailApi';
import ProductDetailSalseChartViewVue from '@/views/user/product/productdetail/ProductDetailSalseChartView.vue';
import { useCartStore } from '@/stores/CartStore';
import { useUserStore } from '@/stores/Login';
import SaleProductModal from '@/components/user/modal/SaleProductModal.vue';
import axios from 'axios';
import { GLOBAL_URL } from '@/api/util';
import { fetchMemeberCart, mergeMemberCart } from '@/api/cartApi';
import { itemWishClick } from '@/api/wishApi';
import { useWishStore } from '@/stores/WishStore';

const route = useRoute();
const router = useRouter();
const wishStore = useWishStore();
const cartStore = useCartStore();
const Modal = ref(false);
const productData = ref([]);
const reviewData = ref(null);
const productImages = ref([]);
const idx = ref(route.params.idx);
const wishHeart = ref(false);
const cartHeart = ref(false);

// 로그인 pinia
const userStore = useUserStore();
const userLogin = computed(() => userStore.loginCheck);
// const emit = defineEmits();

// 3. 옵션값을 클릭하면 watch에서 추적하는 idx값이 바뀌고 doLoad를 호출한다.
const doLoad = async () => {
  try {
    const productDataRes = await getProductData(idx.value);
    const reviewDataRes = await getReviewData(idx.value);

    productData.value = productDataRes.detailProductInfoDto;
    productImages.value = productDataRes.productImage;
    reviewData.value = reviewDataRes;
    cartAndWishView();
  } catch (err) {
    console.log('실패2' + err);
  }
};

const cartAndWishView = () => {
  if (cartStore.cartItems.find(cartItem => cartItem.usedProductId === productData.value.usedProductId)) {
    cartHeart.value = true;
  }
  if (wishStore.itemWishList.find(itemWishList => itemWishList === productData.value.usedProductId)) {
    wishHeart.value = true;
  } else {
    wishHeart.value = false;
  }
};

// 바로 판매하기 클릭 시 모달 창 열림
const SellNowOpenModal = () => {
  if (userStore.loginCheck) {
    Modal.value = true;
  } else {
    alert('로그인이 필요합니다.');
    router.push({ name: 'login2' });
  }
};
const closeModal = () => {
  Modal.value = false;
};

// 장바구니 추가

const addToCart = async () => {
  const data = {
    brandName: productData.value.brandName,
    nickName: sessionStorage.getItem('nickName'),
    productId: productData.value.productId,
    productName: productData.value.productName,
    productSize: productData.value.productSize,
    sellingPrice: productData.value.verifiedSellingPrice,
    usedOrNot: false,
    usedProductId: Number(idx.value),
    userSaleImages: productImages.value,
    verifiedSaleGradeType: productData.value.gradeType,
    quantity: 1,
  };
  cartStore.addItem(data);
  cartAndWishView();
  // alert('장바구니에 담았습니다.');
};

// 찜 클릭 이벤트
const addToWishlist = async () => {
  if (userLogin.value) {
    // DB통신(추가,삭제)
    await itemWishClick(productData.value.usedProductId);
    // Pinia(추가, 삭제)
    wishStore.itemMakeWishList(productData.value.usedProductId);
    cartAndWishView();
  } else {
    alert('로그인 후 사용이 가능합니다.');
    router.push({ path: '/login2' });
  }
};

// 리뷰별점평균을 소수점 1자리만 남긴다.
const Average = data => {
  data = data * 10;
  data = Math.round(data);
  data = data / 10;
  return data;
};

// 바로 결제하기
const doPayment = () => {
  if (sessionStorage.getItem('token')) {
    const purchaseProductDto = [
      {
        usedProductId: productData.value.usedProductId,
        quantity: 1,
        productName: productData.value.productName,
      },
    ];
    const data = {
      purchaseProductDtos: purchaseProductDto,
      totalPrice: productData.value.verifiedSellingPrice,
    };

    router.push({
      path: '/payment',
      query: { item: encodeURIComponent(JSON.stringify(data)) },
    });
  } else {
    alert('로그인 후 이용 가능합니다.');
    router.push({ path: '/login2' });
  }
};

watchEffect(() => {
  idx.value = route.params.idx;
  wishHeart.value;
  doLoad();
});
</script>

<template>
  <article id="productInfoSection">
    <ul id="productInfo">
      <li>{{ productData.brandName }}</li>
      <li>{{ productData.productName }}ㆍ{{ productData.size }} ml</li>
      <li v-if="reviewData">
        1,222찜 수
        <span style="color: orange"
          >★ {{ Average(reviewData.starAverage) }} ({{ reviewData.reviewCount }} reviews)</span
        >
      </li>
      <li>{{ formatPrice(productData.verifiedSellingPrice) }}</li>
      <li>판매용량 : {{ productData.productSize }} ml / {{ productData.usedOrNot ? '새상품' : '중고상품' }}</li>
    </ul>

    <!-- <div>
      <p>제조일자 : 2024-11-01</p>
      <p>유통기한 : 2029-11-01</p>
    </div> -->

    <div class="addButtonGroub">
      <button class="addToCart Sell​​Now" @click="SellNowOpenModal">바로 판매하기</button>
      <button class="addToCart BuyNow" @click="doPayment">바로 구매하기</button>
      <button class="icon_box" @click="addToCart" :class="{ active: cartHeart }">
        <img class="icon" src="@/assets/img/icon/free-icon-font-shopping-cart.svg" alt="" />
      </button>
      <button class="icon_box" :class="{ active: wishHeart }" @click.stop="addToWishlist">
        <img class="icon" src="@/assets/img/icon/free-icon-font-heart-line.svg" alt="" />
      </button>
    </div>

    <ProductDetailSalseChartViewVue v-if="productData" :size="productData.size" />
  </article>
  <article>
    <SaleProductModal v-if="Modal" @closeModal="closeModal" />
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
#productInfo li:nth-child(4),
#productInfo li:nth-child(5) {
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
@media (max-width: 630px) {
  #productInfoSection {
    width: 100%;
    margin: 0 auto;
    padding: 0 3%;
  }
}
</style>
