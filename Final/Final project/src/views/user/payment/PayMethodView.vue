<script setup>
import { ref } from "vue";

const paymentMethod = ref("easyPayment");
const selectCheck = (method) => {
  paymentMethod.value = method;
};
</script>

<template>
  <section id="pay_method">

    <article class="method_check">
      <div @click="selectCheck('creditCard')">
        <label>신용카드</label>
        <input
          type="radio"
          name="paymentMethod"
          v-model="paymentMethod"
          value="creditCard"
          required
        />
      </div>
      <transition name="expand">
        <form v-if="paymentMethod === 'creditCard'" class="sub_div">
          <div class="horizontal">
            <p>카드선택 -</p>
            <select id="request" name="request">
              <option value="">신한카드</option>
              <option value="">하나카드</option>
              <option value="">국민카드</option>
              <option value="">대구카드</option>
              <option value="">기업카드</option>
            </select>
          </div>
          <div class="horizontal">
            <p>할부선택 :</p>
            <input type="text" disabled placeholder="일시불" />
          </div>
        </form>
      </transition>
    </article>

    <article class="method_check">
      <div @click="selectCheck('bankTransfer')">
        <label>계좌이체</label>
        <input
          type="radio"
          name="paymentMethod"
          value="bankTransfer"
          v-model="paymentMethod"
        />
      </div>
      <transition name="expand">
        <form v-if="paymentMethod === 'bankTransfer'" class="sub_div">
          <div class="horizontal">
            <label>은행선택 -</label>
            <select id="request" name="request">
              <option value="">신한은행</option>
              <option value="">하나은행</option>
              <option value="">국민은행</option>
              <option value="">대구은행</option>
              <option value="">기업은행</option>
            </select>
          </div>
          <div class="horizontal">
            <label>입금자명 :</label>
            <input type="text" />
          </div>
        </form>
      </transition>
    </article>

    <article class="method_check">
      <div @click="selectCheck('easyPayment')">
        <label>간편결제</label>
        <input
          type="radio"
          name="paymentMethod"
          value="easyPayment"
          v-model="paymentMethod"
        />
      </div>
      <transition name="expand">
        <div v-if="paymentMethod === 'easyPayment'" class="sub_div bank">
          <div>
            <img src="/src/assets/img/kakao.png" alt="" />
            카카오페이
          </div>
          <div>
            <img src="/src/assets/img/toss.png" alt="" />
            토스페이
          </div>
          <div>
            <img src="/src/assets/img/naver.png" alt="" />
            네이버페이
          </div>
          <div class="xxx">
            <img src="/src/assets/img/inicis.png" alt="" />
            이니시스
          </div>
        </div>
      </transition>
    </article>

  </section>
</template>

<style scoped>
#pay_method {
  /* 임시 높이 */
  height: 350px;
  width: 100%;
  position: relative;
}
.method_check {
  width: 100%;
  height: auto;
  background-color: #ebebeb;
}
.method_check > div:nth-child(1) {
  width: 100%;
  height: 65px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #F5F5F5;
  z-index: 100;
  cursor: pointer;
  font-size: 1.7rem;
  font-weight: 600;
  border-bottom: 1.5px solid #FFF;
}
.method_check > div:nth-child(1) label {margin-left: 50px;}
.method_check > div:nth-child(1) input {margin-right: 50px;}
.method_check > div:nth-child(2), form {z-index: 50;}
.sub_div {
  height: 155px;
  padding: 20px;
}
.horizontal {
  display: flex;
  align-items: center;
  justify-content: left;
  height: 50%;
  font-size: 1.7rem;
  padding-left: 30px;
}
select{
    width: 200px;
    padding: 5px;
    margin-left: 10px;
}
input[type="text"]{
    width: 200px;
    padding: 5px;
    margin-left: 10px;
}
/* 간편결제 설정 */
.bank {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  align-items: center;
  height: 200px;
}
.bank > div {
  display: flex;
  height: 60px;
  justify-content: center;
  align-items: center;
  font-size: 15px;
  margin: 10px;
  cursor: pointer;
  border-radius: 1.5rem;
  background-color: #fff;
}
.bank > div:hover{
  background-color: var(--color-main-bloode) !important;
  color: #fff;
}
.bank img {
  width: 50px;
  margin-right: 10px;
}

/* 트랜지션 효과 */
.expand-enter-from {
  max-height: 0;
  opacity: 0;
} /* 초기값 */
.expand-enter-active {
  transition: max-height 0s ease, opacity 0s ease;
}
.expand-enter-to {
  max-height: 500px;
  opacity: 1;
}
.expand-leave-active {
  transition: max-height 0s ease, opacity 0s ease;
}
.expand-leave-to {
  max-height: 0;
  opacity: 0;
}
.xxx{
  background-color: var(--color-main-bloode) !important;
  color: #fff;
}
</style>
