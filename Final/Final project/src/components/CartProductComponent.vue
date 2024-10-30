<script setup>
import { computed, onMounted, ref, watch } from 'vue'
import { GLOBAL_URL } from '@/api/util'
import { useCartStore } from '@/stores/CartStore'
import axios from 'axios';
import { useUserStore } from '@/stores/Login';

// 로그인 pinia
const userStore = useUserStore();
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
const cart_idx = ref(props.productInfo.productId) // 부모자로 보낼 idx
const cart_product_name = ref(props.productInfo.productName)
const cart_product_price = ref(props.productInfo.price) // 이것도
const cart_quantity = ref(props.productInfo.quantity)
const cartCheck = ref(props.isChecked)

onMounted(() => {
  makeCartCheckList(); 
});
watch(() => props.isChecked,
  newValue => {
    // props 변화 감지 => makecartCheckList 실행
    cartCheck.value = newValue
    makeCartCheckList()
  },
)
const makeCartCheckList = () => {
  // 배열에 추가
  if (cartCheck.value) {
    cartStore.cartCheckList.push({ productId: cart_idx.value, price: cart_product_price.value, quantity: cart_quantity });
  } 
  // 배열에 삭제
  else {
    cartStore.cartCheckList = cartStore.cartCheckList.filter(
      item => item.productId !== cart_idx.value,
    );
  }
}

// 부모에게 상태 전달
const emit = defineEmits(['update:isChecked'])
const handleCheckboxChange = () => {
  emit('update:isChecked', cartCheck.value)
}
watch(cartCheck, newValue => {
  emit('update:isChecked', newValue)
})


// 수량 변경
const upCount = async() => {
  cartStore.upQuantity(cart_idx.value)
  const data = {
    productId : cart_idx.value,
    quantity : 1,
    memberId : 1,
  }
  if(userLogin.value){
    await axios.post(`${GLOBAL_URL}/cartProduct/increment`, data,{
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
      }
    })
  }
};
const downCount = async() => {
  if(cart_quantity.value>1){
    cartStore.downQuantity(cart_idx.value)
    const data = {
      productId : cart_idx.value,
      quantity : 1,
      memberId : 1,
    }
    if(userLogin.value){
      await axios.post(`${GLOBAL_URL}/cartProduct/decrement`, data,{
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
        }
      })
    }
  }
};
watch(() => props.productInfo.quantity, (newValue) => {
    cart_quantity.value = newValue;
});

</script>


<template v-for="item in cart" :key="item.idx">
  <article id="cart_product_component_wrapper">
    <input
      v-model="cartCheck"
      type="checkbox"
      name="check"
      id="product_check"
      @change="handleCheckboxChange()"
    />
    <div class="img">
      <img
        :src="`${GLOBAL_URL}/api/file/download/${productInfo.images[0].filename}`"
        style="height: 90%"
      />
    </div>
    <div class="text">
      <div class="text_box">
        <p class="title">상품명 : {{ cart_product_name }}</p>
        <p class="price">{{ cart_product_price }}원</p>
      </div>
      <div class="count">
        <button @click="upCount">+</button>
        <p>수량 : {{ cart_quantity }}</p>
        <button @click="downCount">-</button>
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
  border: 1px solid black;
  margin: 14px 0;
}
#product_check {
  position: absolute;
  top: 0;
  left: 0;
}
/* 이미지 박스 설정 */
.img {
  width: 197px;
  height: 100%;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
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
  background-color: #fff;
}
.text_box p {
  margin: 10px 0;
}
.title {
  font-size: 1.6rem;
  font-weight: 400;
}
.price {
  font-weight: 600;
  font-size: 2.2rem;
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
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  padding: 0px 6px;
  cursor: pointer;
  font-size: 2rem;
}
</style>
