<script setup>
import { addCartDatabase } from '@/api/cartApi';
import { GLOBAL_URL } from '@/api/util';
import { itemWishClick } from '@/api/wishApi';
import { useCartStore } from '@/stores/CartStore';
import { useUserStore } from '@/stores/Login';
import { useWishStore } from '@/stores/WishStore';
import { computed, onMounted, ref, watchEffect } from 'vue';
import { useRouter } from 'vue-router';

// 로그인 pinia
const userStore = useUserStore();
const userLogin = computed(() => userStore.loginCheck);

const props = defineProps({
  productInfo: {
    type: Object,
    required: false,
  },
  layoutType: {
    type: Boolean,
    required: true,
  },
});
watchEffect(() => {
  console.log('현재 모드:', props.layoutType ? 'true' : 'false');
});

const rank = ref(props.productInfo.verifiedSaleGradeType);
const price = ref(props.productInfo.sellingPrice);
const size = ref(props.productInfo.productSize);
const sales = ref(props.productInfo.nickName);
const imgURL = ref(props.productInfo.userSaleImages);
// 아직 들어오지 않은 값(찜, 조회수)
const wishCount = ref(props.productInfo.wishCount || 10);
const viewCount = ref(props.productInfo.viewCount || 10);

// 10ml당 가격 계산
const cost = ref('');
const detailPrice = () => {
  cost.value = Math.floor(price.value / (size.value / 10));
};
onMounted(detailPrice);

// 장바구니 추가 ###########################################
const cartStore = useCartStore();
const addToCart = () => {
  console.log('장바구니 추가시 들어가는 값', props.productInfo);
  cartStore.addItem(props.productInfo);
};

// 찜목록 추가 #############################################
const redHeart = ref(false);
const iconClick = ref(false); // 찜하트 css
const wishStore = useWishStore();

watchEffect(() => {
  const wishProduct = wishStore.itemWishList.find(item => item === props.productInfo.usedProductId);
  console.log(wishProduct);
  if (wishProduct) {
    redHeart.value = true;
    iconClick.value = true;
  } else {
    redHeart.value = false;
    iconClick.value = false;
  }
});

const addToWishlist = async () => {
  if (userLogin.value) {
    // DB통신(추가,삭제)
    await itemWishClick(props.productInfo.usedProductId);
    // Pinia(추가, 삭제)
    wishStore.itemMakeWishList(props.productInfo.usedProductId);
  } else {
    alert('로그인 후 사용이 가능합니다.');
    router.push({ path: '/login2' });
  }
};
// #########################################################

// 디테일페이지 이동
const router = useRouter();
const RouteDetailPage = () => {
  router.push({
    path: `/productsdetail/${props.productInfo.usedProductId}`,
    // query: { },
  });
};
</script>

<template>
  <article :class="{ imgStyle: props.layoutType, listStyle: !props.layoutType }" class="masonry_component_wrapper">
    <div class="masonry_img_box">
      <img
        @click="RouteDetailPage"
        class="back_img"
        :src="`${GLOBAL_URL}/api/file/download/${imgURL[0].filename}`"
        alt=""
      />
      <div class="status_ranking">{{ rank }} 등급</div>
      <ul @click.stop>
        <li class="cart_push" @click.stop="addToCart">
          <img class="icon" src="@/assets/img/icon/free-icon-font-shopping-cart.svg" alt="" />
        </li>
        <li class="wish_push" :class="{ active: redHeart }" @click.stop="addToWishlist">
          <img
            class="icon"
            src="@/assets/img/icon/free-icon-font-heart-line.svg"
            alt=""
            :style="{ display: iconClick ? 'none' : 'flex' }"
          />
          <img
            class="icon"
            src="@/assets/img/icon/free-icon-font-heart.svg"
            alt=""
            :style="{ display: iconClick ? 'flex' : 'none' }"
          />
        </li>
      </ul>
    </div>
    <div class="masonry_text_box">
      <ul>
        <li @click="RouteDetailPage">
          용량 : {{ size }}ml • 가격 : {{ price }}원 <span class="detail_price">(10ml당 {{ cost }}원)</span>
        </li>
        <li>[ {{ sales }} ]</li>
        <li>❤ {{ wishCount }} • 조회수 {{ viewCount }}</li>
      </ul>
    </div>
  </article>
</template>

<style scoped>
/* 전체 설정 ##################################### */
.masonry_component_wrapper {
  /* max-width: 410px;
  width: 100%;
  max-height: 370px; */
  /* aspect-ratio: 410 / 370; */
  margin-top: 20px;
}
.imgStyle {
  max-width: 410px;
  width: 100%;
  max-height: 370px;
}
.listStyle {
  max-width: 100%;
  width: 100%;
  max-height: 370px;
  display: flex;
  padding-bottom: 20px;
  border-bottom: 1px solid #d6d6d6;
}
/* 이미지 박스 설정 ############################### */
.imgStyle .masonry_img_box {
  width: 100%;
  max-height: 280px;
  aspect-ratio: 410 / 280;
}
.listStyle .masonry_img_box {
  width: 325px;
  height: 100%;
  aspect-ratio: 325 / 222;
}
.masonry_img_box {
  position: relative;
  overflow: hidden;
  border-radius: 1.4rem;
  overflow: hidden;
}
.masonry_img_box > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  cursor: pointer;
  transition: all 0.5s;
}
.masonry_img_box:hover .back_img {
  transform: scale(1.05);
  /* animation: scale 10s linear forwards; */
}
.status_ranking {
  position: absolute;
  top: 11px;
  left: 12px;
  color: #222222;
  border-radius: 0.7rem;
  padding: 7px 10px;
  font-size: 1.4rem;
  font-weight: 700;
  /* padding: 5px 8px; */
  border-radius: 0.5rem;
  background-color: #fff;
}

@keyframes scale {
  0% {
    transform: scale(1);
  }
  100% {
    transform: scale(1.2);
  }
}
.masonry_img_box:hover > ul li {
  opacity: 1;
}
.masonry_img_box > ul {
  display: flex;
  position: absolute;
  bottom: 0;
  right: 0;
}
.masonry_img_box > ul > li {
  width: 35px;
  height: 35px;
  border: 2px solid rgba(0, 0, 0, 0.05);
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  margin: 12px;
  cursor: pointer;
  transition: background-color 0.2s;
  background-color: var(--color-main-Lgray);
  transition: opacity 0.2s;
  opacity: 0;
}
.masonry_img_box > ul > li:hover {
  background-color: var(--color-main-gray);
  /* background-color:#fdf4f1; */
  border: 2px solid rgba(0, 0, 0, 0.05);
}
.masonry_img_box > ul > li:nth-child(1) {
  margin-right: -7px;
}
.icon {
  width: 60%;
  height: auto;
  transition: filter 0.4s; /* 부드러운 전환 효과 */
}
.wish_push.active {
  background-color: var(--color-main-gray);
  border: 2px solid rgba(0, 0, 0, 0.05);
  opacity: 1;
}

/* 텍스트 박스 설정 ############################### */
.imgStyle .masonry_text_box {
  width: 100%;
  height: 90px;
  text-align: center;
  font-size: 1.5rem;
}
.listStyle .masonry_text_box {
  width: 100%;
  display: flex;
  align-items: center;
  font-size: 1.5rem;
}
.masonry_text_box {
}
.listStyle .masonry_text_box ul {
  margin-left: 50px;
}

.masonry_text_box ul li {
  margin: 10px 0;
  cursor: pointer;
  letter-spacing: -0.024rem;
}

.imgStyle .masonry_text_box ul li:nth-child(1) {
  font-size: 1.7rem;
  font-weight: 600;
}
.imgStyle .masonry_text_box ul li:nth-child(2) {
  font-size: 1.7rem;
}
.imgStyle .masonry_text_box ul li:nth-child(3) {
  font-size: 1.4rem;
  color: var(--color-text-gray);
}

.listStyle .masonry_text_box ul li:nth-child(1) {
  font-size: 2rem;
  font-weight: 600;
}
.listStyle .masonry_text_box ul li:nth-child(2) {
  font-size: 1.8rem;
}
.listStyle .masonry_text_box ul li:nth-child(3) {
  font-size: 1.6rem;
  color: var(--color-text-gray);
}

.detail_price {
  font-size: 1.3rem;
  color: var(--color-text-gray);
}

/* 타입 변경하기 ########################################## */
</style>
