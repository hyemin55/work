import { defineStore } from "pinia";

export const useWishStore = defineStore('wish', {
    state: ()=>({
        wishList : JSON.parse(localStorage.getItem('wishList')) || [],
        itemWishList : JSON.parse(localStorage.getItem('itemWishList')) || [],
    }),
    actions:{
        // 찜목록에 추가(기본추가 / 이미있으면 삭제)

        // 카테고리 찜 목록
        makeWishList(product){
            // 들어오는 id값 하나라 객체가 아니다. 그렇기 때문에 뒤에 .productId가 안들어가되됨
            // product.productId 이렇게 찾아야할 값이 => product인 것이다.
            console.log('pinia에 들어온 값', product)
            const aready = this.wishList.find(item => item === product);

            if(aready){
                this.wishList = this.wishList.filter(item => item !== product);
                localStorage.setItem('wishList', JSON.stringify(this.wishList))
            }
            else{
                this.wishList.push(product)
                localStorage.setItem('wishList', JSON.stringify(this.wishList))
            }
        },
        removeWishList(){
            localStorage.removeItem('wishList');
            this.wishList = [];
        },



        // 상품 찜 목록
        itemMakeWishList(product){
            console.log('무엇이 온거야', product)
            const aready = this.itemWishList.find(item => item === product);
            if(aready){
                this.itemWishList = this.itemWishList.filter(item => item !== product);
                localStorage.setItem('itemWishList', JSON.stringify(this.itemWishList))
            }
            else{
                this.itemWishList.push(product)
                localStorage.setItem('itemWishList', JSON.stringify(this.itemWishList))
            }
            return aready;
        },
        itemRemoveWishList(){
            localStorage.removeItem('itemWishList');
            this.itemWishList = [];
        }
    },

    getter:{}

});
