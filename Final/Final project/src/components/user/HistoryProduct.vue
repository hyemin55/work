<script setup>
import { ref } from 'vue';

// 주문, 판매 텍스트 변경
const props = defineProps({
  propstext: {
    type: Object,
    required: false,
  },
  showBtn:{
    type: Boolean,
    required: true,
  }
});
const text01 = ref(props.propstext.text01)
const text02 = ref(props.propstext.text02)
// text03은 추후에 진행상태를 기입
const text03 = ref(props.propstext.text03)

const confirmed= ()=>{
  console.log('구매확정 함수')
}
const goreview= ()=>{
  console.log('리뷰작성 함수')
}


</script>

<template>
  <article class="history_box" v-if="props.propstext">

    <div class="top_box">
      <div>
        <p class="history_date">
          <span>{{ text01 }}날짜</span> 2024년 10월 12일
        </p>
        <p class="history_number">{{ text02 }}번호 123123132231321</p>
      </div>
      <p class="detail_more" v-if="props.showBtn">{{ text02 }}상세 ></p>
    </div>

    <div class="bottom_box">
      <p class="complete_date">{{ text03 }}완료 2024년 10월 13일</p>

      <div class="history_product">
        <div class="history_product_img">
          <img src="../assets/img/d003.png" alt="" />
        </div>
        <ul class="history_product_text">
          <li>상품명 : </li>
          <li>가격 : </li>
          <li>수량 : </li>
        </ul>

        <div class="history_product_btn" v-if="props.showBtn">
          <button @click="confirmed">구매확정</button>
          <button @click="goreview">구매후기 작성</button>
        </div>
      </div>
    </div>

  </article>
</template>

<style scoped>
/* 컴포넌트 설정 ########### */
.history_box {
  border-bottom: 4px solid var(--color-main-gray);
}

/* 윗 박스 */
.top_box {
  height: 70px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: solid 1px var(--color-main-gray);
  background-color: var(--color-main-pink);
  padding: 0 12px;
}
.history_date {
  font-size: 2rem;
  letter-spacing: -0.034rem;
}
.history_date span {
  color: rgb(226, 28, 28);
}
.history_number {
  font-size: 1.3rem;
  letter-spacing: -0.034rem;
}
.history_more {
  font-size: 1.5rem;
  letter-spacing: -0.034rem;
}
.detail_more {
  font-size: 1.5rem;
  cursor: pointer;
}

/* 아래 박스 */
.bottom_box {
  width: 100%;
  height: 230px;
}
.complete_date {
  height: 50px;
  width: 100%;
  font-size: 2rem;
  letter-spacing: -0.034rem;
  display: flex;
  align-items: center;
  padding-left: 12px;
}
.history_product {
  height: 180px;
  width: 100%;
  display: flex;
  align-items: center;
  position: relative;
}
/* product 이미지설정 */
.history_product_img{
  width: 180px;
  height: 180px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}
.history_product_img img{
  height: 80%;
  width: auto;
  background-color: var(--color-main-Lgray);
  border-radius: 1.5rem;
}
/* product 텍스트 설정 */
.history_product_text li{
  font-size: 1.7rem;
  margin: 7px 0px 7px 10px;
}
/* product 버튼 설정(v-if:구매) */
.history_product_btn{
  position: absolute;
  right: 0;
  top: 0;
  height: 100%; 
  width: 150px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: -40px;
}
.history_product_btn button{
  width: 100%; /* 150px */
  height: 36px;
  text-align: center;
  border: 1px solid var(--color-main-gray);
  border-radius: 0.7rem;
  margin: 5px 0;
  font-size: 1.3rem;
}
.history_product_btn button:hover{
  background-color: black;
  color: #fff;
}
</style>
