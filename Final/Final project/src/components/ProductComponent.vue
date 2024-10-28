<script setup>
import { GLOBAL_URL } from '@/api/util'
import { useRouter } from 'vue-router'
import { ref } from 'vue'
import { useCartStore } from '@/stores/CartStore'
import axios from 'axios'

// 장바구니 추가
const cartStore = useCartStore()
const addToCart = () => {
  console.log('장바구니 추가')
  // alert("장바구니에 담았습니다.")

  cartStore.addItem(props.productInfo)

  // axios통신 부분
  const data = {
    memberId: 1,
    productId: props.productInfo.productId,
    quantity: 1,
  }
  try {
    const res = axios.post(`${GLOBAL_URL}/cart/add`, data)

    console.log(res)
  } catch (e) {
    console.log(e)
  }
}
// 찜목록 추가
const addToWishlist = () => {
  // alert("༼ つ ◕_◕ ༽つ 찜~")
  console.log('찜목록 추가')
}

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
console.log(props.productInfo)

// 3. defineProps으로 정의한 명령어를 변수로 정리하는 부분입니다.
const productName = ref(props.productInfo.productName || '상품이름')
const content = ref(props.productInfo.content || '상품설명')
const price = ref(props.productInfo.price || '가격')
// const review_avr = ref('평점');
const reviewCount = ref(props.productInfo.reviewCount || '0')

// useNavigator
// 4. 클릭 이벤트에 반응하여 경로이동을 한다.
// 경로 이동을 하는데 클릭한 해당 이미지의 id를 가지고 페이지로 이동을 하는겁니다.
// 이게 전부입니다.
// 그런데 어떻게 다 다른 페이지로 이동하느냐고요???
// 그게 아니죠 다 같은 페이지로 가는게 맞습니다.
// 같은 페이지로 이동해서 가지고간 다른 id를 가지고 그곳에서 통신을해서
// 각자 다른 상품들을 불러오는 같은 페이지가 되는 겁니다.
const router = useRouter()
const navDetailProduct = () => {
  router.push(`/productsdetail/${props.productInfo.productId}`)
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
            src="../img/icon/free-icon-font-shopping-cart.svg"
            alt=""
          />
        </li>
        <li class="wish_push" @click.stop="addToWishlist">
          <img
            class="icon"
            src="../img/icon/free-icon-font-heart-line.svg"
            alt=""
          />
        </li>
      </ul>
    </div>
    <div class="product_text">
      <ul>
        <li @click="navDetailProduct" class="product_title">
          {{ productName }}
        </li>
        <li class="product_content">{{ content }}</li>
      </ul>
      <ul>
        <li class="product_price">￦{{ price }}</li>
        <li class="product_review">
          <span>
            <img
              class="star"
              src="../img/icon/free-icon-font-star.svg"
              alt=""
            />
            별점
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
  height: 390px;
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
  background-color: rgba(20, 20, 20, 0.8);
  border: 2px solid rgba(255, 255, 255, 0.6);
}
.product_img > ul > li:nth-child(1) {
  margin-right: -7px;
}
.cart_push:hover .icon {
  filter: brightness(0) saturate(100%) invert(1); /* 흰색으로 변경 */
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
  height: 85px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 0 10px;
}
.product_text ul:nth-child(1) {
  width: 100%;
  height: 45px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.product_text ul:nth-child(1) > li {
  margin-top: 4px;
}
.product_text ul:nth-child(2) {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 40px;
  width: 100%;
}
.product_text li {
  cursor: pointer;
}
.product_title {
  font-weight: 400;
  font-size: 16px;
}
.product_content {
  font-size: 1rem;
}
.product_price {
  font-weight: bold;
  font-size: 19px;
}
</style>
