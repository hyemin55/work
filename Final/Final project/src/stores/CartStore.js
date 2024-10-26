import  { defineStore } from 'pinia';

export const useCartStore = defineStore('cart', {
    state: ()=>({
        cartItems: [],
        deathNote: [],
    }),
    actions:{
        // 장바구니 담기
        addItem(item){
            const Duplicatecheck = this.cartItems.find(cartItem => cartItem.productId === item.productId);
            if(Duplicatecheck){
                Duplicatecheck.quantity++;
            }
            else{
                this.cartItems.push({ ...item, quantity: 1, isChecked: false });
            }
        },
        // 장바구니 삭제
        removeItem(){
            // this.cartItems = this.cartItems.filter(item => item.isChecked == false);
            this.cartItems = this.cartItems.filter(item => !item.isChecked);
        },

        // 수량변경
        upQuantity(quantity){
            console.log(quantity);
        },
        downQuantity(quantity){

        },

        // 토글 전체선택
        toggleAllCheck(isChecked) {
            this.cartItems.forEach(item => {
                item.isChecked = isChecked;
            })
            // return this.cartItems
            //     .filter(item => item.isChecked) 
            //     .map(item => item.productId);
        },

        // 장바구니 회원정보 불러오기
        updateCart(items) {
            // 장바구니 초기화
            this.cartItems = [];

            items.forEach(item => {
                const p = {
                    productId: item.productResDto.productId,
                    productName: item.productResDto.productName,
                    images: item.productResDto.images,
                    price: item.productResDto.price,
                    quantity: item.quantity || 1, // 기본값 1 설정
                    isChecked: false, // 기본 체크 여부
                };
                // 생성한 객체를 cartItems에 추가
                this.cartItems.push(p);
            });
        },

       
    }

})