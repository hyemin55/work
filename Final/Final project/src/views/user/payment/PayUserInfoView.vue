<script setup>
import { GLOBAL_URL } from "@/api/util";
import { useUserStore } from "@/stores/Login";
import axios from "axios";
import { ref, watchEffect } from "vue";


const userStore = useUserStore();

const userInfo = ref([]);
const post = ref();
watchEffect(async () => {
  try {
    const res = await axios.get(`${GLOBAL_URL}/api/payment/buyer-info`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("token")}`,
      },
    });
    if (res.status == 200) {
      userInfo.value = res.data;
      post.value =
        userInfo.value.buyerAddress.addr + " " + userInfo.value.buyerAddress.postCode;
    }
  } catch (error) {
    console.error("Error:", error);
  }
});
</script>

<template>
  <section id="pay_user_info">
    <form action="#" method="post">
      <article>
        <div class="title_box">
          <h1>주문자 정보</h1>
        </div>

        <div class="info_box">
          <p><b>주문자</b> : <b>{{ userInfo.buyerName || `${userStore.nickName}` }}</b></p>
          <p>
            이메일 : {{ userInfo.buyerEmail }}
            <span>
              <input type="checkbox" name="emailcheck" id="emailcheck" />
              <label class="pointer" for="emailcheck">수신동의</label>
            </span>
          </p>
          <p for="phone">
            연락처 : {{ userInfo.buyerTel }}
            <span>
              <input type="checkbox" name="safePhone" id="safePhone" />
              <label class="pointer" for="safePhone">안심번호</label>
            </span>
          </p>
        </div>
      </article>

      <article>
        <div class="title_box">
          <h1>배송지 정보</h1>
        </div>
        <div class="info_box">
          <p for="address">
            <b>주소</b> : <b>{{ post }}</b>
            <span>
              <label class="pointer">배송지 변경</label>
            </span>
          </p>
          <p>
            <label for="request">배송 요청사항 : </label>
            <select id="request" name="request" class="styled-select">
              <option value="signature">부재시 문앞에 놔주세요.</option>
              <option value="none">부재시 경비실 앞에 놔주세요.</option>
              <option value="urgent">택배보관함에 보관해주세요.</option>
              <option value="fragile">배송전에 연락주세요.</option>
              <option value="other">직접입력</option>
            </select>

            <span>
              <input type="checkbox" name="nextorder" id="nextorder" />
              <label class="pointer" for="nextorder">다음 주문 시에 사용</label>
            </span>
          </p>
        </div>
      </article>
    </form>
  </section>
</template>

<style scoped>
/* 전체설정 */
#pay_user_info {
  width: 100%;
  height: 300px; /* 높이 임시 설정 */
  /* background-color: #ddd; */
}
#pay_user_info article{
  display: flex;
  border-bottom: 1px solid #dddddd;
}
#pay_user_info article:nth-child(1){
  height: 160px;
}
#pay_user_info article:nth-child(2){
  height: 140px;
}

/* 텍스트박스 설정 */
.title_box{
  width: 200px;
}
.title_box>h1{
  font-size: 2.2rem;
  letter-spacing: -0.034rem;
  padding: 25px 0 0 25px;
}

/* 인포박스 설정 */
.info_box{
  font-size: 1.5rem;
  /* letter-spacing: -0.034rem; */
  padding: 27px 0 0 20px;
  color: #121212;
}
.info_box>p{
  margin-bottom: 20px;
}


span{
  margin-left: 30px;
}
.pointer {
  cursor: pointer;
  color: var(--color-text-gray);
  margin-left: 3px;
  letter-spacing: -0.034rem;
  font-size: 1.5rem;
}
/* select_input */
.styled-select {
  width: 250px;
  padding: 0.7rem;
  font-size: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #f9f9f9;
  color: #333;
  appearance: none; /* 기본 화살표 제거 */
  background-image: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='18' height='18'><path fill='%23333' d='M7 10l5 5 5-5z'/></svg>");
  background-repeat: no-repeat;
  background-position: right 0.8rem center;
  background-size: 1rem;
  cursor: pointer;
}
.styled-select:focus {
  border-color: #007bff;
  outline: none;
}
.styled-select option {
  color: #333;
}
</style>
