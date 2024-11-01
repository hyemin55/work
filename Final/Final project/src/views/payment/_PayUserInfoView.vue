<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios from 'axios';
import { watchEffect } from 'vue';

// watchEffect(async()=>{
//   try{
//     const res = await axios.post(`${GLOBAL_URL}`, {
//       header:{
//         'Content-Type': 'application/json',
//         Authorization: `Bearer ${sessionStorage.getItem('token')}`,
//       }
//     })
//     console.log("유저 정보", res)
//   }
//   catch(error){
//     console.error("Error:", error)
//   }
// })
</script>

<template>
  <section id="pay_user_info">
    <!-- # 데이터보내는 주소를 입력 -->
    <form action="#" method="post">
      <div>
        <p for="recipient">받는 분:</p>
        <p>강도현</p>
      </div>

      <div>
        <p for="phone">연락처:</p>
        <p>010-0101-0100</p>
        <input type="checkbox" name="safePhone" id="">
        <label for="safePhone">안심번호</label>
      </div>

      <div>
        <p for="address">주소:</p>
        <p>그림 컴퓨터어학원
            <span>배송지 변경</span>
        </p>
      </div>

      <div>
        <label for="request">택배 요청사항:</label>
        <select id="request" name="request">
          <option value="none">선택하세요</option>
          <option value="fragile">깨지기 쉬운 물품</option>
          <option value="urgent">긴급 배송</option>
          <option value="signature">서명 필요</option>
          <option value="other">기타</option>
        </select>
      </div>

    </form>
  </section>
</template>

<style scoped>
#pay_user_info{
    height: 170px; /* 높이 임시 설정 */
}

form > div {
  display: flex;
  align-items: center;
  margin: 10px 0;
  font-size: 15px;
}
</style>
