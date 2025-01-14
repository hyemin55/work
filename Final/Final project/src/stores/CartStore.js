import { addCartDatabase } from '@/api/cartApi';
import { loginCheck } from '@/api/KakaoLoginApi';
import { GLOBAL_URL } from '@/api/util';
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useCartStore = defineStore('cart', {
  state: () => ({
    cartItems: JSON.parse(localStorage.getItem('cartItems')) || [],
    // ↓ axios 통신으로 데이터베이스에서 통신할 id를 모으기 위해서 사용하는 배열이다.
    // 장바구니 체크된 상품들이 들어오는 배열
    cartCheckList: ref([]),
  }),
  actions: {
    // 장바구니 담기
    addItem(item) {
      console.log('store 들어온값', item);
      const Duplicatecheck = this.cartItems.find(cartItem => cartItem.usedProductId === item.usedProductId);
      if (Duplicatecheck) {
        alert('이미 장바구니에 있는 상품 입니다.');
      } else {
        this.cartItems.push({ ...item, quantity: 1, isChecked: true });
        alert('장바구니에 담았습니다.');
      }
      localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
      if (loginCheck) {
        const data = {
          usedProductId: item.usedProductId,
          quantity: 1,
        };
        addCartDatabase(data);
      }
    },

    // 장바구니 삭제(체크가 된것만 삭제하는 로직)
    removeItem() {
      this.cartItems = this.cartItems.filter(item => !item.isChecked);
      localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    },

    // 장바구니에 들어있는것 전체삭제
    logOutRemoveItem() {
      localStorage.removeItem('cartItems');
      this.cartItems = [];
    },

    // 토글 전체선택
    toggleAllCheck(isChecked) {
      this.cartItems.forEach(item => {
        item.isChecked = isChecked;
      });
      localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    },

    // 회원의 장바구니 정보 불러오기
    updateCart(items) {
      // 장바구니 초기화
      this.cartItems = [];
      items.forEach(item => {
        const generalDto = item.generalUsedProductResDto;
        if (!generalDto) return; // 데이터가 없으면 건너뜀

        const p = {
          usedProductId: item.generalUsedProductResDto.usedProductId,
          productName: item.generalUsedProductResDto.productName,
          userSaleImages: item.generalUsedProductResDto.userSaleImages || [],
          sellingPrice: item.generalUsedProductResDto.sellingPrice,
          productSize: item.generalUsedProductResDto.size,
          quantity: item.quantity || 1,
          isChecked: true, // 기본 체크 여부
        };
        // 생성한 객체를 cartItems에 추가
        this.cartItems.push(p);
      });
      localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    },
  },

  getters: {
    // 선택 상품 가격합계
    totalPrice: state => {
      return state.cartCheckList.reduce((sum, item) => sum + item.sellingPrice * 1, 0);
    },
  },
  
});
