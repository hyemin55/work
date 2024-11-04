<script setup></script>

<template>
    <section id="pay_product">

        <article class="pay_product_set">
            <div class="pay_product_compnents">
                <div class="pay_product_img">
                    <img style="height: 85%;" src="../../assets/img/빵빵덕복숭아.png" alt="">
                </div>
                <div class="pay_product_content">
                    <ul>
                        <li><b>상품이름</b> : 아쿠아 오드퍼퓸</li>
                        <li><b>상품설명</b> : 프롤롤헌 하트노트, 이름 감싸주는...</li>
                        <li><b>옵션</b> : 100ml / <b>수량</b> : 3개</li>
                    </ul>
                    <p>가격 : 100,000원</p>
                </div>
                <div class="line"></div>
            </div>
        </article>
        <article class="pay_product_set">
            <div class="pay_product_compnents">
                <div class="pay_product_img">
                    <img style="height: 85%;" src="../../assets/img/빵빵덕세안.png" alt="">
                </div>
                <div class="pay_product_content">
                    <ul>
                        <li><b>상품이름</b> : 아쿠아 오드퍼퓸</li>
                        <li><b>상품설명</b> : 프롤롤헌 하트노트, 이름 감싸주는...</li>
                        <li><b>옵션</b> : 100ml / <b>수량</b> : 3개</li>
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