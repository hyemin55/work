<script setup></script>

<template>
    <section id="pay_product">

        <article class="pay_product_set">
            <div class="pay_product_compnents">
                <div class="pay_product_img">
                    이미지
                </div>
                <div class="pay_product_content">
                    <ul>
                        <li>상품이름</li>
                        <li>세부설명 ~~~~ @@@@@</li>
                        <li>사이즈 / 색상</li>
                        <li>수량 : 1개</li>
                    </ul>
                    <p>가격 : 100,000원</p>
                </div>
                <div class="line"></div>
            </div>
        </article>

        <article class="pay_product_total_prcie">
            <p>총 상품 금액 : 202,000원</p>
        </article>

    </section>
</template>


<style scoped>
/* 전체 설정 */
#pay_product{
    height: auto;
    width: 100%;
    position: relative;
    background-color: rgb(236, 236, 236);
}
/* 상품 컴포넌트 설정 */
.pay_product_compnents{
    padding: 15px 30px;
    width: 100%;
    display: flex;
}
.pay_product_img{
    width: 145px;
    height: 145px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: antiquewhite;
}
.pay_product_content{
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: calc(100% - 145px);
    background-color: rgb(218, 218, 218);
}


.pay_product_content ul{
    margin-left: 30px;
}
.pay_product_content ul li{
    margin: 10px 0;
}

.pay_product_content p{
    margin-right: 30px;
}

/* 총가격 설정 */
.pay_product_total_prcie{
    font-size: 2rem;
    font-weight: 800;
    width: 100%;
    height: auto;
    text-align: end;
    padding: 1.5rem;
}
</style>