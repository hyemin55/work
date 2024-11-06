<script setup>
import { GLOBAL_URL } from '@/api/util'
import { useRouter } from 'vue-router'
import { computed, ref, watch } from 'vue'
import { useCartStore } from '@/stores/CartStore'
import axios from 'axios'
import { useUserStore } from '@/stores/Login'

// 로그인 pinia
const userStore = useUserStore()
const userLogin = computed(() => userStore.loginCheck)

// 장바구니 추가
const cartStore = useCartStore()
const addToCart = () => {
  console.log('장바구니 추가')
  // alert("장바구니에 담았습니다.")
  cartStore.addItem(props.productInfo)

  if (userLogin.value) {
    const data = {
      memberId: 1,
      productId: props.productInfo.productId,
      quantity: 1,
    }
    try {
      const res = axios.post(`${GLOBAL_URL}/cart/add`, data, {
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem('token')}`,
        },
      })
      console.log(res)
    } catch (e) {
      console.log(e)
    }
  }
}

// 찜목록 추가
const redHeart = ref(false)
const iconClick = ref(false) // 찜하트 css

const addToWishlist = () => {
  redHeart.value = !redHeart.value
  iconClick.value = !iconClick.value // 찜하트 css
}

// 단위 변경
// const unit = ref('ml');
// watch(() => categoryTitle.value, (newTitle) => {
//   if (newTitle === 'Candle') {unit.value = 'g';}
//   else {unit.value = 'ml';}
//   });

// 1. productList에서 axios 통신을 통해 데이테이베이스에서 온 정보, for구문으로 받아온다.
// 2. defineProps 는 받아온 정보를 사용하는 명령어, router로 오는 정보를 useRoute로 받아오는거랑 같은 맥락이다.
// 상품리스트에 출력
const props = defineProps({
  // 받아오는props정의
  productInfo: {
    type: Object,
    required: true,
  },
})

// 3. defineProps으로 정의한 명령어를 변수로 정리하는 부분입니다.
const productName = ref(props.productInfo.productName || '상품이름')
const content = ref(props.productInfo.content || '상품설명')
const price = ref(props.productInfo.price || '가격')
const size = ref(props.productInfo.size || '사이즈')
// const review_avr = ref('평점');
const reviewCount = ref(props.productInfo.reviewCount || '0')

// useNavigator
const router = useRouter()
const navDetailProduct = () => {
  console.log('사이즈 값', size.value)
  router.push({
    path: `/productsdetail/${props.productInfo.productId}`,
    query: {
      size: size.value,
    },
  })
}
</script>

<template>
  <article class="products">
    <div class="product_img" @click="navDetailProduct">
      <img
        :src="`${GLOBAL_URL}/api/file/download/${productInfo.images[0].filename}`"
        style="height: 90%"
      />
      <ul @click.stop>
        <li class="cart_push" @click.stop="addToCart">
          <img
            class="icon"
            src="@/assets/img/icon/free-icon-font-shopping-cart.svg"
            alt=""
          />
        </li>
        <li
          class="wish_push"
          :class="{ active: redHeart }"
          @click.stop="addToWishlist"
        >
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
    <div class="product_text">
      <ul>
        <li @click="navDetailProduct" class="product_title">
          {{ productName }}ㆍ<span class="size">{{ size }}<span>ml</span></span>
        </li>
        <li class="product_content">{{ content }}</li>
      </ul>
      <ul>
        <li class="product_price">￦ {{ price.toLocaleString() }}</li>
        <li class="product_review">
          <span>
            <img
              class="star"
              src="@/assets/img/icon/free-icon-font-star.svg"
              alt=""
            />
            4.5
          </span>
          (<span>{{ reviewCount }}</span
          >)
        </li>
      </ul>
    </div>
  </article>
</template>

<style scoped>
/* 전체설정 */
.products {
  max-width: 305px;
  width: 100%;
  /* height: 400px; */
  border: solid rgba(0, 0, 0, 0.1) 1px;
  border-radius: 6px;
  overflow: hidden;
  margin: 10px 0;
}
/* 상단_이미지 설정 */
.product_img {
  position: relative;
  width: 100%;
  height: 305px;
  background-color: var(--color-main-Lgray);
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}
.product_img:hover > ul li {
  opacity: 1;
}
.product_img > ul {
  display: flex;
  position: absolute;
  bottom: 0;
  right: 0;
}
.product_img > ul > li {
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
.product_img > ul > li:hover {
  background-color: var(--color-main-gray);
  /* background-color:#fdf4f1; */
  border: 2px solid rgba(0, 0, 0, 0.05);
}
.product_img > ul > li:nth-child(1) {
  margin-right: -7px;
}

.wish_push.active {
  background-color: var(--color-main-gray);
  border: 2px solid rgba(0, 0, 0, 0.05);
  opacity: 1;
}

.icon {
  width: 60%;
  height: auto;
  transition: filter 0.4s; /* 부드러운 전환 효과 */
}
.star {
  width: 11px;
  height: auto;
}

/* 하단_텍스트 설정 */
.product_text {
  width: 100%;
  /* height: 85px; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 10px;
}
.product_text ul:nth-child(1) {
  width: 100%;
  /* height: 45px; */
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.product_text ul:nth-child(2) {
  display: flex;
  justify-content: space-between;
  align-items: center;
  /* height: 40px; */
  width: 100%;
}
.product_text li {
  cursor: pointer;
}
.product_title {
  font-weight: 500;
  font-size: 1.65rem;
  font-weight: 600;
  letter-spacing: -0.34px;
  color: #1f1f1f;
}
.product_content {
  font-size: 1rem;
  letter-spacing: -0.034rem;
  line-height: 1.4rem;
  margin: 7px 0 5px 0;
  color: var(--color-text-gray);
}
.product_price {
  letter-spacing: -0.034rem;
  font-weight: bold;
  font-size: 1.85rem;
  color: #1f1f1f;
}
.size {
  /* font-weight: 400; */
}
</style>
