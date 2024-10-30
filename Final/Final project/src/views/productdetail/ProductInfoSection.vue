<template>
  <article id="productInfoSection">
    <ul id="productInfo">
      <li>브랜드명</li>
      <li>상품명상품명상품명상품명상품명상품명상품명상품명상품명상품명</li>
      <li>1,222찜 수 ★별점</li>
      <li>￦ 580,000원</li>
    </ul>

    <p class="OptionSelect">옵션선택</p>
    <ul id="productOption">
      <li>30ml</li>
      <li>50ml</li>
      <li>100ml</li>
    </ul>

    <div class="addButtonGroub">
      <button class="addToCart BuyNow">바로 구매하기</button>
      <button class="addToCart">
        <img src="../../img/icon/free-icon-font-shopping-cart.svg" alt="" />
        장바구니 추가
      </button>
      <button class="addwish">
        <img src="../../img/icon/wish.png" alt="" />
      </button>
      <button class="addwish">
        <img src="../../img/icon/share.png" alt="" />
      </button>
    </div>

    <SalseChart />
  </article>
</template>

<script setup>
import SalseChart from './SalseChart.vue'
</script>

<style scoped>
/* 오른쪽 상품정보 구역 */
#productInfoSection {
  /* background-color: aquamarine; */
  width: 50%;
  margin: 25px 0 25px 0;
  /* background-color: yellow; */
}
#productInfo {
  text-align: left;
}
#productInfo li:nth-child(1) {
  font-size: 1.4rem;
}
#productInfo li:nth-child(2) {
  font-size: 3.4rem;
  margin-top: 20px;
}
#productInfo li:nth-child(3) {
  font-size: 1.4rem;
  margin-top: 20px;
}
#productInfo li:nth-child(4) {
  font-size: 2rem;
  margin-top: 20px;
}
.OptionSelect {
  font-size: 1.6rem;
  margin: 25px 0 10px 0;
}
#productOption {
  display: flex;
  align-items: center;
  gap: 25px;
  /* width: 70%; */
  font-size: 1.4rem;
  /* background-color: aquamarine; */
}
#productOption li {
  border: 0.5px solid var(--color-main-gray);
  padding: 10px 7%;
  border-radius: 10px;
  cursor: pointer;
}
#productOption li:hover {
  background-color: var(--color-main-bloode);
  color: white;
  border: 0.5px solid var(--color-main-bloode);
}
.addButtonGroub {
  display: flex;
  justify-content: space-between;
  margin: 25px 0;
  height: 45px;
  width: 80%;
  /* background-color: brown; */
}
.addToCart {
  width: 35%;
  height: 100%;
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--color-main-gray);
  font-size: 1.6rem;
}
.BuyNow {
  background-color: var(--color-main-bloode);
  color: white;
  font-family: 'Pretendard-SemiBold';
}
.addToCart > img {
  width: 15%;
  margin-right: 5px;
  /* background-color: antiquewhite; */
}
.addwish {
  height: 100%;
}
</style>
