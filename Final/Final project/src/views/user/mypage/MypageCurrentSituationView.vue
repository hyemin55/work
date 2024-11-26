<script setup>
import { ref } from 'vue';
const arrowIcon = ref('fi fi-rr-angle-right')

</script>

<template>
  <article class="progress_order">
    <h2>진행중인 주문</h2>
    <ul>
      <li>
        <p>결제완료</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>배송 준비중</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>배송중</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>배송 완료</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>구매 결정</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>후기 작성</p>
        <p>0</p>
      </li>
    </ul>
  </article>

  <article class="progress_sale">
    <h2>진행중인 판매</h2>
    <ul>
      <li>
        <p>수령 대기</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>수령 완료</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>감정 진행중</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>등록 대기중</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>판매중</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>판매 완료</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>정산 대기중</p>
        <p>0</p>
      </li>
      <li><i :class="arrowIcon"></i></li>
      <li>
        <p>정산 완료</p>
        <p>0</p>
      </li>
    </ul>
  </article>
</template>

<style scoped>
@import url('https://cdn-uicons.flaticon.com/2.6.0/uicons-regular-rounded/css/uicons-regular-rounded.css');
/* 전체설정 */
article{
  height: 150px;
  width: 100%;
  border-bottom: solid 1px var(--color-main-gray);
}
article h2{
  display: flex;
  align-items: end;
  height: 40px;
  width: 100%;
  font-size: 2rem;
  font-weight: 400;
  margin-left: 4px;
}
article ul{
  height: 110px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 50px;
}
article ul li{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 1.6rem;
  line-height: 24px;
}
article ul li i{
  color: var(--color-main-gray);
}
/* 주문설정 */
.progress_order{

}

/* 판매설정 */
.progress_sale{

}
</style>
