<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios from 'axios';
import { ref } from 'vue';


// 모달창 닫기(부모로 event 전달)
const emit = defineEmits(['closeModal', 'Rendering']);
const closeModal = () => {
  emit('closeModal');
  emit('Rendering');
};

// 데이터
const props = defineProps({
    data : {
        type: Object,
        required: false,
    }
})
console.log(props.data)


// 판매 승인
const check = ref(false);
const salesPrice = ref("");
const saleApprove = async(pendingSaleId) => {
    const data = {
        pendingSaleId : pendingSaleId,
        salesPrice : salesPrice.value,
    }
    if(check.value && salesPrice.value){
        try{
          const res = await axios.post(`${GLOBAL_URL}/myPage/verified-sale/approve`, data ,{
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${sessionStorage.getItem('token')}`,
            },
          })
          closeModal();
          alert('상품 등록이 완료되었습니다.')
        }
        catch(error){
          console.error(error)
        }
    }
    else{ 
        if(!salesPrice.value){
            alert('판매가격을 입력하지 않았습니다.') 
        }
        else if(!check.value){
            alert('판매등록에 동의하지 않았습니다.') 
        }
    }
}

</script>


<template>
    <section class="sale_modal">
        <article class="modal_page">

            <div class="modal_title">
                <h1>- 상품 판매 등록 확인 -</h1>
                <ul>
                    <li><span style="color: green;">✔</span> 판매자 계좌 확인</li>
                    <li>예금주 : 강도현</li>
                    <li>은행 : 하나은행 | 계좌번호 : 123123-123123-123123</li>
                </ul>
            </div>

            <table>
              <thead>
                <tr>
                  <th></th>
                  <th>검수 전<br><span class="medium_text">(신청시 기입 내용)</span></th>
                  <th>검수 후<br><span class="medium_text">(판매시 등록 내용)</span></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>대표사진</td>
                  <td>
                    <img :src="`${GLOBAL_URL}/api/file/download/${data.image}`" alt="" />
                  </td>
                  <td>
                    <span class="arrow-icon"><i class="fi fi-ss-left"></i></span>
                    <img :src="`${GLOBAL_URL}/api/file/download/${data.verifiedImages}`" alt="" />
                  </td>
                </tr>
                <tr>
                  <td>카테고리</td>
                  <td>{{ props.data.category }}</td>
                  <td><span class="arrow-icon"><i class="fi fi-ss-left"></i></span>{{ props.data.verifiedCategory }}</td>
                </tr>
                <tr>
                  <td>브랜드</td>
                  <td>{{ props.data.brand }}</td>
                  <td><span class="arrow-icon"><i class="fi fi-ss-left"></i></span>{{ props.data.verifiedBrand }}</td>
                </tr>
                <tr>
                  <td>상품명</td>
                  <td>{{ props.data.name }}</td>
                  <td><span class="arrow-icon"><i class="fi fi-ss-left"></i></span>{{ props.data.verifiedProductName }}</td>
                </tr>
                <tr>
                  <td>가격</td>
                  <td>₩{{ props.data.price.toLocaleString() }}</td>
                  <td><span class="arrow-icon"><i class="fi fi-ss-left"></i>
                    </span><input v-model="salesPrice" type="number" min="0" placeholder="원하시는 판매가격을 입력해주세요."><br>
                    <span class="small_text averPrice">평균 시세가 : ₩ {{ props.data.verifiedPrice.toLocaleString() }}</span>
                  </td>
                </tr>
                <tr>
                  <td>용량</td>
                  <td>{{ props.data.size }}ml</td>
                  <td><span class="arrow-icon"><i class="fi fi-ss-left"></i></span>{{ props.data.verifiedSize }}ml</td>
                </tr>
                <tr>
                  <td>등급<br><span class="small_text">(사용유무)</span></td>
                  <td> {{ props.data.verifiedUsedOrNot ? '미사용 제품' : '사용 제품' }} </td>
                  <td><span class="arrow-icon"><i class="fi fi-ss-left"></i></span>{{ props.data.grade }} 등급</td>
                </tr>
              </tbody>
            </table>

            <div class="modal_btn">
                <div class="agreement">
                    <input v-model="check" type="checkbox" name="" id="agree">
                    <label for="agree">위 검수 사항을 확인하였으며, 판매 등록에 동의합니다.</label>
                </div>
                <!-- <button>닫기</button> -->
                <button @click="saleApprove(props.data.id)">판매 등록 확정</button>
            </div>
        </article>

        <article @click.stop="closeModal()" class="modal_background"></article>
    </section>
</template>


<style scoped>
@import url('https://cdn-uicons.flaticon.com/2.6.0/uicons-solid-rounded/css/uicons-solid-rounded.css');
/* 테이블 설정 ################################################### */
table{
  width: 100%;
  text-align: center;
  /* border-collapse: collapse; */
  font-size: 1.5rem;
}
thead th {
  background-color: #f4f4f4; 
  color: #333;
  font-weight: bold;
  padding: 10px; 
  border: 1px solid #ddd;
}
tbody td {
  padding: 10px 0;
  border: 1px solid #ddd; 
  text-align: center; 
  vertical-align: middle;
  position: relative;
}
tbody td img{
    width: auto;
    height: 180px;
}
tbody tr td:nth-child(1) {
  background-color: #f4f4f4; 
  padding: 20px 5px;
}
.arrow-icon {
  position: absolute; 
  left: -5px;
  top: 50%; 
  margin-top: -9px;
  font-size: 14px;
  color: #333; 
  z-index: 10;
  color: red;
  transform: rotate(180deg);
}
.medium_text{
    font-size: 1.3rem;
}
.small_text{
    font-size: 1.2rem;
}
.averPrice{
  color: var(--color-main-bloode);
}

tbody tr:hover td:nth-child(1){
    background-color: rgb(173, 173, 173);
}
tbody tr:hover td {
    background-color: #d6d6d6;
    font-weight: 600;
}
tbody tr:hover .arrow-icon{
    animation: ani1 2s infinite;
}
@keyframes ani1{
    0%{
        transform: translateX(0) rotate(180deg);
    }
    50%{
        transform: translateX(10px) rotate(180deg);
    }
    100%{
        transform: translateX(0) rotate(180deg);
    }
}

/* 모달 전체 설정 ################################################ */
.sale_modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  z-index: 950;
  display: flex;
  align-items: center;
  justify-content: center;
}
.modal_page {
  position: relative;
  width: 1000px;
  height: 800px;
  padding: 30px;
  background-color: rgb(255, 255, 255);
  z-index: 11;
  border: 5px solid var(--color-main-bloode);
  overflow-y: scroll;
}
/* 모달배경 설정 */
.modal_background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.2);
  z-index: 10;
}
/* 모달 타이틀 설정 ################################################ */
.modal_title{
    width: 100%;
    /* height: 140px; */
}
.modal_title h1{
    text-align: center;
    font-size: 2.5rem;
    padding: 20px;
    margin-top: 20px;
    /* margin: 20px 0; */
}
.modal_title ul{
    /* background-color: #f0f0f0; */
    border-bottom: 1px solid #c0c0c0;
    padding: 10px 20px;
    margin-bottom: 24px;
    font-size: 1.5rem;
}
.modal_title ul li{
    margin: 7px 0;
} 
.modal_title ul li:nth-child(1){
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 15px;
}
/* 모달 번튼 설정 ################################################ */
.modal_btn{
    text-align: center;
    /* background-color: antiquewhite; */
    width: 100%;
}
.modal_btn button{
    padding: 15px;
    background-color: #a5a5a5;
    margin: 20px 10px;
    border-radius: 1.2rem;
}
.modal_btn button:hover{
    background-color: var(--color-main-bloode);
    color: #e9e9e9;
}
.agreement{
    margin-top: 60px;
    margin-bottom: 10px;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.4rem;
}
.agreement label{
    margin-left: 5px;
    cursor: pointer;
}
input[type="number"]{
  padding: 2px;
  width: 60%;
  text-align: center;
}
</style>