<script setup>
import { addCartDatabase } from '@/api/cartApi';
import { GLOBAL_URL } from '@/api/util';
import { categoryWishClick, categoryWishList, itemWishClick, itemWishList } from '@/api/wishApi';
import MypageEmptyComponent from '@/components/user/MypageEmptyComponent.vue';
import { useCartStore } from '@/stores/CartStore';
import { useUserStore } from '@/stores/Login';
import { useWishStore } from '@/stores/WishStore';
import { computed, onMounted, ref, watch, watchEffect } from 'vue';
import { useRouter } from 'vue-router';

// pinia
const userStore = useUserStore();
const userLogin = computed(() => userStore.loginCheck);
const wishStore = useWishStore();
const cartStore = useCartStore();

const clickBtn = ref(true);
// 모드 변경
const mode = ref('categoryMode')
const categoryMode = ()=>{
  mode.value = 'categoryMode'
  console.log(mode.value)
  clickBtn.value = true
}
const itemMode = ()=>{
  mode.value = 'itemMode'
  console.log(mode.value)
  clickBtn.value = false
}

// 전체석택
let allChecked = ref(false);
let allCheckedList = ref([]);

const allCheck = () => {
  if (allChecked.value == true) {
    data.value.forEach(product => {
      product.isChecked = allChecked.value;
      if (product.isChecked) {
        allCheckedList.value.push(product.wishListCategoryDto.id); // 카테고리 아이디를 삭제배열에 추가
                        
      }
    });
  } else {
    data.value.forEach(product => {
      product.isChecked = allChecked.value;
    });
    allCheckedList.value = [];
  }
};

// 찜(선택) 전체삭제
const allCheckDelete = () => {
  // 추후에 왜 배열로 보내지 않고 값 하나하나 보냈나 의문을 가질 수 있는데,
  // 벡에서 받는 데이터를 배열로 받지 않아서 그냥 풀어서 보낸거다.
  const normalArray = [...allCheckedList.value];
  if (normalArray.length === 0) {
    alert('선택된 제품이 없습니다.');
    return;
  }
  normalArray.forEach((productId) => {
    wishDelete(productId, true);
  });
};


// 찜 목록 정보를 DB에서 가져옴.
const data = ref([]);
const LoadingwishList = async () => {
  if(mode.value === 'categoryMode'){
    const wishListData = await categoryWishList();
    data.value = wishListData.map(product => ({
      ...product,
      isChecked: false,
    }));
    console.log('카테고리 목록 데이터', data.value);
  }
  else if(mode.value === 'itemMode'){
    const wishListData = await itemWishList();
    data.value = wishListData.map(product => ({
      ...product,
      isChecked: false,
    }));
    console.log('상품 목록 데이터', data.value);
  }
};


// 화면 랜더링
onMounted(()=>{
  LoadingwishList();
})
watch(mode, () => {
  LoadingwishList();
});


// 찜상품(개별) 삭제
const wishDelete = async (productId, check) => {
  if (check == true) {
    if(mode.value === 'categoryMode'){
      await categoryWishClick(productId);
      console.log('찜목록 삭제');
      wishStore.makeWishList(productId);
    }
    else if(mode.value === 'itemMode'){
      await itemWishClick(productId);
      console.log('찜목록 삭제');
      wishStore.itemMakeWishList(productId);
    }
    await LoadingwishList();
  }
  else {
    alert('선택된 제품이 없습니다.');
  }
};


// 장바구니 담기 (수정 필요)
// const addCart = (productId, check) => {
//   if(mode.value = 'itemMode'){
//     const data = {
//       productId: productId,
//       quantity: 1,
//     };
//     const cartData = {
//       productId: productId,
//       brandName : "",
//       content : "",
//       dtype : "",
//       images : [],
//       mainImage : {filename: 'p_011.png', path: 'images/file/\\p_011.png', fileDesc: 'perfume11'},
//       price : 100800,
//       productId : 37,
//       productName : "소바쥬 오 드 뚜왈렛",
//       registerDate : "2024-10-23 06:49:37",
//       reviewCount : 0,
//       size : 30   
//     };
//     if (userLogin.value && check == true) {
//       cartStore.addItem(cartData);
//       addCartDatabase(data);
//       alert('장바구니에 담았습니다.');
//     } else {
//       alert('선택된 제품이 없습니다.');
//     }
//   }
//   else{
//     console.log('장바구니')
//   }
// };


// 상품으로 이동
const router = useRouter();
const moveDetail = (productId, productName, brand) => {
  if(mode.value === 'categoryMode'){
    router.push({path: `/masonry/${productId}`,
      query: {
        title: productName,
        brand: brand
      },
    });
  }
  else if(mode.value === 'itemMode'){
    router.push({path: `/productsdetail/${productId}`,
      query: {
        title: productName,
        brand: brand
      },
    });
  }
};
</script>

<template>
  <div>
    <h1 class="wishlist_title">찜 목록
      <ul class="wishlist_mode">
        <li @click="categoryMode" :class="{'click_btn':clickBtn, 'none_click_btn':!clickBtn}">카테고리</li>
        <li @click="itemMode" :class="{'click_btn':!clickBtn, 'none_click_btn':clickBtn}">상품</li>
      </ul>
    </h1>
    <div class="product_select">
      <p>
        <input id="allcheck" type="checkbox" @change="allCheck" v-model="allChecked" /><label for="allcheck">전체 선택</label>
      </p>
      <p @click="allCheckDelete">선택 삭제하기</p>
    </div>


    <article v-if="data.length > 0">
      <!-- 카테고리 찜 목록 컴포넌트 -->
      <div v-if="mode === 'categoryMode'" class="wish_product" v-for="product in data" :key="product.wishListCategoryDto.id">
        <input class="pro_check" type="checkbox" v-model="product.isChecked" />
        <div class="product_box">
          <div class="img_box">
            <img @click="moveDetail(product.wishListCategoryDto.id, product.wishListCategoryDto.name, product.brandName)"
              :src="`${GLOBAL_URL}/api/file/download/${product.wishListCategoryDto.mainImage}`"
              alt=""/>
          </div>
          <ul @click="moveDetail(product.wishListCategoryDto.id, product.wishListCategoryDto.name, product.brandName)" class="content_box">
            <li>{{ product.brandName }}</li>
            <li>상품명 : {{ product.wishListCategoryDto.name }}</li>
            <li>재고 : {{ product.productCount }}</li>
          </ul>
        </div>
        <div class="btn">
          <div class="delet_btn" @click="wishDelete(product.wishListCategoryDto.id, product.isChecked)">삭제</div>
        </div>
      </div>
  
      
      <!-- 상품 찜 목록 컴포넌트 -->
      <div v-if="mode === 'itemMode'" class="wish_product" v-for="product in data" :key="product.productId">
        <input class="pro_check" type="checkbox" v-model="product.isChecked" />
        <div class="product_box">
          <div class="img_box">
            <img @click="moveDetail(product.productId, product.productName, product.brandName)"
              :src="`${GLOBAL_URL}/api/file/download/${product.userSaleImages[0].filename}`"
              alt=""/>
          </div>
          <ul @click="moveDetail(product.productId, product.productName, product.brandName)" class="content_box">
            <li>{{ product.brandName }}</li>
            <li>상품명 : {{ product.productName }}</li>
            <li>옵션 : {{ product.size }}</li>
            <li>가격 : {{ product.price }}</li>
            <li>등급 : {{ product.gradeType }}</li>
          </ul>
        </div>
        <div class="btn">
          <div class="cart_btn" @click="addCart(product.productId, product.isChecked)">장바구니 담기</div>
          <div class="delet_btn" @click="wishDelete(product.productId, product.isChecked)">삭제</div>
        </div>
      </div>
    </article>
    
    <article v-else>
      <MypageEmptyComponent></MypageEmptyComponent>
    </article>

  </div>
</template>

<style scoped>
/* 전체설정 */
.wishlist_title {
  font-size: 3.2rem;
  font-weight: 400;
  border-bottom: solid 1px #000;
  padding: 20px 0;
  margin-top: 32px;
  position: relative;
}
.product_select {
  width: 100%;
  height: 57px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #000;
  padding: 0 7px;
}
.product_select p {
  cursor: pointer;
  display: flex;
  align-items: center;
  font-size: 1.4rem;
}
.product_select p input {
  margin: 0 5px;
}
.product_select p label {
  cursor: pointer;
}

.wishlist_mode{
  position: absolute;
  right: 0;
  bottom: 20px;
  display: flex; 
  background-color: antiquewhite;
  border-radius: 1.2rem;
}
.wishlist_mode li {
  cursor: pointer;
  font-size: 1.8rem;
  font-weight: 600;
  padding: 10px 16px;
}
.wishlist_mode li:nth-child(1){
  border-top-left-radius: 1.2rem;
  border-bottom-left-radius: 1.2rem;
}
.wishlist_mode li:nth-child(2){
  border-top-right-radius: 1.2rem;
  border-bottom-right-radius: 1.2rem;
}
.click_btn{
  background-color: #000;
  color: #fff;
}
.none_click_btn{
  background-color: rgb(245, 245, 245);
  color: #000;
}

/* 상품컴포넌트 설정 ############################ */
.wish_product {
  position: relative;
  width: 100%;
  height: 185px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 4px solid var(--color-main-gray);
}
.pro_check {
  position: absolute;
  top: 12px;
  left: 5px;
}
/* 상품설정 */
.product_box {
  display: flex;
  align-items: center;
  height: 100%;
}
.img_box {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 240px;
  height: 100%;
}
.img_box img {
  height: 80%;
  width: auto;
  background-color: var(--color-main-pink);
  border-radius: 1.5rem;
  cursor: pointer;
}
.content_box li:nth-child(1) {
  font-size: 2rem !important;
}
.content_box li {
  cursor: pointer;
  font-size: 1.6rem;
  margin: 10px 0;
}
/* 버튼설정 */
.btn > div {
  width: 150px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  text-align: center;
  font-size: 1.3rem;
  border: 1px solid var(--color-main-gray);
  border-radius: 0.7rem;
  margin: 5px 0;
}
.btn > div:hover {
  background-color: #000;
  color: #fff;
}
</style>
