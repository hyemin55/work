<script setup>
    // 장바구니 추가
    const addToCart=()=>{

    }
    // 찜목록 추가
    const addToWishlist=()=>{

    }
</script>

<template>
    <article class="products">
        <div class="product_img">
            <ul>
                <li class="cart_push" @click="addToCart">
                    <img class="icon" src="../img/icon/free-icon-font-shopping-cart.svg" alt="">
                </li>
                <li class="wish_push" @click="addToWishlist">
                    <img class="icon" src="../img/icon/free-icon-font-heart-line.svg" alt="">
                </li>
            </ul>
        </div>
        <div class="product_text">
            <ul>
                <li class="product_title">상품이름</li>
                <li class="product_content">상품설명</li>
            </ul>
            <ul>
                <li class="product_price">￦12,000</li>
                <li class="product_review">
                    <span>
                        <img class="star" src="../img/icon/free-icon-font-star.svg" alt="">
                        별점
                    </span>
                    (<span>리뷰수</span>)
                </li>
            </ul>
        </div>
    </article>
</template>

<style scoped>
/* 전체설정 */
.products{
    width: 305px;
    height: 390px;
    border: solid rgba(0, 0, 0, 0.1) 1px;
    border-radius: 6px;
    overflow: hidden;
}
/* 상단_이미지 설정 */
.product_img{
    position: relative;
    width: 100%;
    height: 305px;
    background-color: var(--color-main-Lgray);
}
.product_img>ul{
    display: flex;
    position: absolute;
    bottom: 0;
    right: 0;
}
.product_img>ul>li{
    width: 45px;
    height: 45px;
    border: 2px solid rgba(0, 0, 0, 0.05);
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
    margin: 15px;
    cursor: pointer;
    transition: background-color 0.2s;
}
.product_img>ul>li:hover{
    background-color: rgba(0, 0, 0, 0.5);
}

.product_img>ul>li:nth-child(1){
    margin-right: -10px;
}
.icon{
    width: 60%;
    height: auto;
}
.star{
    width: 11px;
    height: auto;
}



/* 하단_텍스트 설정 */
.product_text{
    width: 100%;
    height: 85px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 0 10px;
}
.product_text ul:nth-child(1){
    width: 100%;
    height: 45px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.product_text ul:nth-child(1)>li{
    margin-top: 4px;
}
.product_text ul:nth-child(2){
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 40px;
    width: 100%;
}
.product_title{
    font-weight: 400;
    font-size: 16px;
}
.product_content{
    font-size: 10px;
}
.product_price{
    font-weight: bold;
    font-size: 19px;
}
</style>