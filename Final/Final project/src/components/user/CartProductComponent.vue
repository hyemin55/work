<script setup>
import { computed, onMounted, ref, watch } from 'vue'
import { GLOBAL_URL } from '@/api/util'
import { useCartStore } from '@/stores/CartStore'
import axios from 'axios'
import { useUserStore } from '@/stores/Login'
import { useRouter } from 'vue-router'

// 로그인 pinia
const userStore = useUserStore()
const userID = computed(() => userStore.userId)
const userLogin = computed(() => userStore.loginCheck)

const cartStore = useCartStore()

// 상품리스트에 출력
const props = defineProps({
  // 받아오는props의 정의 방법
  productInfo: {
    type: Object,
    required: true,
  },
  isChecked: {
    type: Boolean,
    required: false,
  },
})

const cart_idx = ref(props.productInfo.usedProductId) // 부모자로 보낼 idx
const cart_product_name = ref(props.productInfo.productName)
const cart_product_price = ref(props.productInfo.sellingPrice) // 이것도
const cart_size = ref(props.productInfo.productSize);
const cartCheck = ref(props.isChecked);
const imgURL = ref(props.productInfo.userSaleImages);


// 장바구니 check배열 
onMounted(() => {
  makeCartCheckList()
})
watch(() => props.isChecked, newValue => {
    // props 변화 감지 => makecartCheckList 실행
    cartCheck.value = newValue
    makeCartCheckList()
  },
)
const makeCartCheckList = () => {
  // 배열에 추가
  if (cartCheck.value) {
    cartStore.cartCheckList.push({
      usedProductId: cart_idx.value,
      sellingPrice: cart_product_price.value,
    })
  }
  // 배열에 삭제
  else {
    cartStore.cartCheckList = cartStore.cartCheckList.filter(
      item => item.usedProductId !== cart_idx.value,
    )
  }
}

// 부모에게 상태 전달
const emit = defineEmits(['update:isChecked'])
const handleCheckboxChange = item => {
  emit('update:isChecked', cartCheck.value)
}
watch(cartCheck, newValue => {
  emit('update:isChecked', newValue)
})

// 상품으로 이동
const router = useRouter()
const moveDetail = () => {
  router.push({
    path: `/productsdetail/${props.productInfo.usedProductId}`,
    query: {
      size: cart_size.value,
    },
  })
}
</script>

<template v-for="item in cart" :key="item.idx">
  <article
    id="cart_product_component_wrapper"
    :class="{ check: cartCheck, notCheck: !cartCheck }"
  >
    <label class="product_check-ccartCheckontainer">
      <input
        v-model="cartCheck"
        type="checkbox"
        name="check"
        class="product_check"
        @change="handleCheckboxChange"
      />
      <span class="custom-checkmark"></span>
    </label>
    <div class="img" v-if="imgURL != null">
      <img :src="`${GLOBAL_URL}/api/file/download/${imgURL[0].filename}`" @click="moveDetail"/>
    </div>
    <div class="text">
      <div class="text_box">
        <p class="title" @click="moveDetail">
          상품명 : {{ cart_product_name }}
        </p>
        <p class="contents">옵션 : {{ cart_size }}ml</p>
        <p class="price">{{ cart_product_price }}원</p>
      </div>
    </div>
  </article>
</template>

<style scoped>
/* 전체 설정 */
#cart_product_component_wrapper {
  position: relative;
  display: flex;
  width: 630px;
  height: 172px;
  margin: 14px 0;
  background-color: #fff;
  border-radius: 0.8rem;
}
/* 체크박스 설정 */
.product_check {
  position: absolute;
  margin: 10px;
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
/* 이미지 박스 설정 */
.img {
  width: 197px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.img img {
  cursor: pointer;
  height: 90%;
  border-radius: 1.5rem;
}
/* 텍스트 박스 설정 */
.text {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 15px;
  max-width: 433px;
  width: 100%;
  height: 100%;
  /* background-color: #fff; */
}
.text_box p {
  margin: 10px 0;
}
.title {
  font-size: 1.6rem;
  font-weight: 600;
  letter-spacing: -0.034rem;
  cursor: pointer;
}
.contents {
  font-size: 1.3rem;
  font-weight: 400;
  letter-spacing: -0.034rem;
}
.price {
  font-weight: 600;
  font-size: 2.2rem;
  letter-spacing: -0.034rem;
}
/* 버튼 설정 */
.count {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  font-size: 1.2rem;
}

button {
  background-color: var(--color-main-bloode);
  color: var(--color-main-gray);
  border: none;
  border-radius: 5px;
  width: 20px;
  height: 20px;

  display: flex;
  align-items: center;
  justify-content: center;

  cursor: pointer;
  font-size: 1.5rem;
}
.check {
  filter: grayscale(0);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  color: #0c0c0c;
}
.notCheck {
  filter: grayscale(85%);
  box-shadow: none;
  color: var(--color-main-gray);
}
</style>
