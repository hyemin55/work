<script setup>

</script>

<template>
    <section id="pay_money">
        <div class="pay_money_product">
            <p class="title_text">상품 가격</p>
            <p class="price_text">230,000원</p>
        </div>
        <div>+</div>
        <div class="pay_money_delivery">
            <p class="title_text">배송비</p>
            <p class="price_text">3,000원</p>
        </div>
        <div>=</div>
        <div class="pay_money_mount">
            <p class="title_text">총 결제 금액</p>
            <p class="price_text">233,000원</p>
        </div>
    </section>
</template>

<style scoped>
#pay_money{
    height: 170px;/* 높이 임시 설정 */
    
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}
#pay_money>div{
    margin: 0 10px;
}
.pay_money_product{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.pay_money_delivery{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.pay_money_mount{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}


.title_text{
    font-size: 2rem;
}
.price_text{
    font-size: 1.8rem;
    font-weight: 600;
}
</style>