<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios from 'axios';
import { ref, watchEffect } from 'vue';

const props = defineProps({
  productInfo: {
    type: Object,
    required: true,
  },
})
const payProductInfo = ref(props.productInfo.purchaseProductDtos || '상품정보');
const payProductMoney = ref(props.productInfo.totalPrice || '상품총금액');
// const payDelivery = ref('0')
// const payTotal = ref( Number(payProductMoney+payDelivery) )

const list = ref()

watchEffect(async()=>{
    const data = payProductInfo.value.map(item => ({
        productId: item.productId,
        quantity: item.quantity,
    }))
    try{
        const res = await axios.post(`${GLOBAL_URL}/api/payment/cart-product-info`, data, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${sessionStorage.getItem('token')}`,
            }
        })
        if (res.status === 200) {
            list.value = res.data;
        } else {
            console.log("오류");
        }
        return res
    }
    catch(error){
        console.log(error);
    }
})

</script>

<template>
    <section id="pay_product">

        <article class="pay_product_set" v-for="(item) in list" :key="item.productId">
            <div class="pay_product_compnents">
                <div class="pay_product_img">
                    <!-- {{ mainImage }} -->
                    <img :src="`${GLOBAL_URL}/api/file/download/${item.mainImage}`" alt="" />
                </div>
                <div class="pay_product_content">
                    <ul>
                        <li><b>상품이름</b> : {{ item.productName }}</li>
                        <li class="pro_content"><b>상세</b> : {{ item.content }}</li>
                        <li><b>옵션</b> : {{ item.size }} / <b>수량</b> : {{ item.quantity }}개</li>
                    </ul>
                    <p>가격 : {{ item.price }}원</p>
                </div>
                <div class="line"></div>
            </div>
        </article>

        <article class="pay_product_total_prcie">
            <p>총 상품 금액 : {{payProductMoney}} 원</p>
        </article>
    </section>
</template>


<style scoped>
/* 전체 설정 */
#pay_product{
    padding: 15px 0;
    height: auto;
    width: 100%;
    position: relative;
    border-radius: 5px;
    background-color: var(--color-main-Lgray);
}
/* 상품 컴포넌트 설정 */
.pay_product_compnents{
    margin-left: 30px;
    padding: 15px 0px;
    width: calc(100% - 60px);
    display: flex;
    border-bottom: 1px solid rgb(155, 155, 155);
}
.pay_product_img{
    width: 145px;
    height: 145px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgb(255, 246, 242);
}
.pay_product_img img{
    height: 90%;
    width: auto;
}
.pay_product_content{
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: calc(100% - 145px);
    background-color: #fff;
}


.pay_product_content ul{
    margin-left: 30px;
}
.pay_product_content ul li{
    font-size: 1.2rem;
    letter-spacing: -0.034rem;
    margin: 10px 0;
    font-weight: 400;
}
.pay_product_content p{
    font-size: 1.6rem;
    letter-spacing: -0.034rem;
    font-weight: 600;
    margin-right: 30px;
}
.pro_content{
}

/* 총가격 설정 */
.pay_product_total_prcie{
    font-size: 2rem;
    font-weight: 800;
    width: 100%;
    height: auto;
    text-align: end;
    padding: 3rem;
}
</style>