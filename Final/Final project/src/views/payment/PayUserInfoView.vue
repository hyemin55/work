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
    <form action="#" method="post">
      <div>
        <p class="info_text" for="recipient"><b>주문자</b> : 강도현</p>
      </div>
      
      <div style="display: flex; align-items: end;">
        <p class="info_text" for="phone"><b>연락처</b> : 010-1101-2130</p>
        <span class="safe_number">
          <input type="checkbox" name="safePhone" id="safePhone">
          <label class="pointer" for="safePhone">안심번호</label>
        </span>
      </div>
  
      <div style="display: flex; align-items: end;">
        <p class="info_text" for="address"><b>주소</b> : 그림 컴퓨터어학원</p>
        <p class="pointer chage_adress">
          배송지 변경
        </p>
      </div>

      <div>
        <label class="info_text" for="request"><b>택배 요청사항</b> : </label>
        <select id="request" name="request" class="styled-select">
          <option value="signature">부재시 문앞에 놔주세요.</option>
          <option value="none">부재시 경비실 앞에 놔주세요.</option>
          <option value="urgent">택배보관함에 보관해주세요.</option>
          <option value="fragile">배송전에 연락주세요.</option>
          <option value="other">직접입력</option>
        </select>
      </div>
    </form>
  </section>
</template>

<style scoped>
#pay_user_info{
  width: 100%;
  height: 180px; /* 높이 임시 설정 */
}
#pay_user_info form{
  /* margin-left: 30px; */
}
.info_text{
  font-size: 1.9rem;
}
form > div {
  margin-bottom: 20px;
  font-size: 15px;
}
.pointer{
  cursor: pointer;
}


.safe_number{
  font-size: 1rem;
  margin-left: 30px;
  display: flex;
  align-items: center;
}
.chage_adress{
  font-size: 1rem;
  margin-left: 30px;
}

/* select_input */
.styled-select {
  width: 250px;
  padding: 0.7rem;
  font-size: 1rem;
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
