import { defineStore } from "pinia";

export const useProductStore = defineStore('product', {
    state: () => ({
        // products: [], // 모든 상품 데이터
        // selectedProduct: null, // 현재 선택된 상품
    }),
    actions: {
        setSelectedProduct(productId){
            // this.selectedProduct = this.products.find(p => p.id === productId);
        },
        fetchProducts(){
      // API를 통해 상품 데이터를 가져오는 로직
      // this.products = fetchedProducts;
        }
    }
})