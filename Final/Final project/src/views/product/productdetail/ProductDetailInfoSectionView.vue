<script setup>
import { GLOBAL_URL } from '@/api/util'
import router from '@/router'
import SalseChart from '@/views/product/productdetail/SalseChart.vue'
import axios from 'axios'
import { onMounted, ref, watchEffect } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const idx = ref(route.params.idx)
const size = ref(route.query.size)
// console.log('사이즈', size.value)
const productData = ref([])
const reviewData = ref(0)
const productDataOk = ref([])

watchEffect(async () => {
  try {
    const res = await axios.get(
      `${GLOBAL_URL}/detail/detailProductInfo/${idx.value}`,
    )
    const res2 = await axios.get(
      `${GLOBAL_URL}/detail/detailReviewInfo/${idx.value}`,
    )
    productData.value = res.data
    reviewData.value = res2.data
    if (res.status === 200 && res2.status === 200) {
      // console.log(productData.value.length)

      for (let i = 0; i < productData.value.length; i++) {
        if (
          productData.value[i].productId == idx.value &&
          productData.value[i].size == size.value
        ) {
          // 여기닷! 수정수정필요
          console.log('조건에 맞는 아이는? ', productData.value[i])
          productDataOk.value = productData.value[i]

          // console.log('데이터내용들', productData.value)
        }
      }
    } else {
      console.log('실패')
    }
  } catch (err) {
    console.log('실패' + err)
  }
})

const productOptions = ref(['30ml', '50ml', '100ml'])

const productOptionselec = selectedOption => {
  console.log(selectedOption.productId)
  // console.log(props.productId)
  // console.log(props.size)

  router.push({
    path: `/productsdetail/${selectedOption.productId}?size=${selectedOption.size}`,
  })
}

const BuyNow = () => {}

const redHeart = ref(false)
const addToWishlist = () => {
  alert('༼ つ ◕_◕ ༽つ 찜~')
  redHeart.value = !redHeart.value
}

const urlShare = () => {
  const url = window.location.href
  navigator.clipboard
    .writeText(url)
    .then(() => {
      alert('URL이 클립보드에 복사되었습니다.')
    })
    .catch(err => {
      console.error('URL 복사를 실패했어요ㅠㅡㅠ', err)
    })
}
const Average = data => {
  data = data * 10
  data = Math.round(data)
  data = data / 10
  return data
}
</script>

<template>
  <article id="productInfoSection">
    <ul id="productInfo">
      <li>{{ productDataOk.brandName }}</li>
      <li>{{ productDataOk.productName }}</li>
      <li>
        1,222찜 수
        <span style="color: orange"
          >★ {{ Average(reviewData.starAverage) }} ({{
            reviewData.reviewCount
          }}
          reviews)</span
        >
      </li>
      <li>￦ {{ productDataOk.price }}</li>
    </ul>

    <p class="OptionSelect">옵션선택</p>
    <div id="productOption">
      <button
        @click="productOptionselec(size)"
        v-for="(size, index) in productData"
        :key="index"
      >
        {{ size.size }} ml
      </button>
    </div>
    <div>
      <p>제조일자 : 2024-11-01</p>
      <p>유통기한 : 2029-11-01</p>
    </div>

    <div class="addButtonGroub">
      <button class="addToCart BuyNow" @click="BuyNow">바로 구매하기</button>
      <button class="addToCart">
        <img src="@/assets/img/icon/free-icon-font-shopping-cart.svg" alt="" />
        장바구니 추가
      </button>
      <button
        class="wish_push"
        :class="{ active: redHeart }"
        @click.stop="addToWishlist"
      >
        <img
          class="icon"
          src="@/assets/img/icon/free-icon-font-heart-line.svg"
          alt=""
        />
      </button>
      <button class="wish_push" @click="urlShare">
        <img
          src="@/assets/img/icon/free-icon-font-share-3917574.png"
          class="icon"
          alt=""
        />
      </button>
    </div>

    <SalseChart />
  </article>
</template>

<style scoped>
/* 오른쪽 상품정보 구역 */
#productInfoSection {
  /* background-color: aquamarine; */
  width: 50%;
  margin: 20px 0 25px 0;
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
#productOption button:hover {
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
