<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios from 'axios';
import { computed, ref } from 'vue';

const nickName = sessionStorage.getItem('nickName')

// 모달창 닫기(부모로 event 전달)
const emit = defineEmits();
const closeModal = () => {
  emit('closeModal');
};


// 신청 성공페이지
const complete = ref(true);


// 신청 데이터
const bankName = ref("");
const accountNumber = ref("");
const productName = ref("");
const categoryName = ref("");
const productSize = ref();
const expectedSellingPrice = ref();
const brandName = ref("");
const use = ref();
const productContent = ref("");
const uploadedFiles = ref([]);

// file 관리
const imageSrc = ref([]); // 이미지가 직접 보이는 배열
const files = ref([]) // DB로 업로드할 배열

const handleFileUpload = (event) => {
  files.value.push(...event.target.files);
  uploadedFiles.value = files.value;  
  // uploadedFiles.value = Array.from(files);

  for (const file of event.target.files) {
    if (file.type.startsWith("image/")) {
      const reader = new FileReader();
      reader.onload = (e) => {
        imageSrc.value.push(e.target.result);  // 이미지 파일의 데이터 URL을 imageSrc에 추가
      };
      reader.readAsDataURL(file);
    } else {
      console.error("이미지 파일만 업로드 가능 합니다.");
      imageSrc.value = null;  // 이미지가 아니면 null로 설정
    }
  }
};
const imgDelete = (index) => {
  files.value = files.value.filter((_, i) => i !== index);
  imageSrc.value = imageSrc.value.filter((_, i) => i !== index);
};

// 판매신청 보내기
const sucsess = async()=>{
    const pendingSaleCreateReqDto = {
      "seller": nickName,
      "bankName": bankName.value,
      "accountNumber": accountNumber.value,
      "productName": productName.value,
      "categoryName": categoryName.value,
      "productSize": Number(productSize.value), //number
      "expectedSellingPrice": Number(expectedSellingPrice.value), //number
      "brandName": brandName.value,
      "usedOrNot": use.value, //boolean
      "productContent": productContent.value,
      "quantity": 1,
      "userSaleReqImageDtos": uploadedFiles.value.map((file, index) => ({
          name: file.name,
          desc: `image-${index + 1}`,
        })
      ),
    }
    
    const formdata = new FormData();
    formdata.append('pendingSaleCreateReqDto',
      new Blob([JSON.stringify(pendingSaleCreateReqDto)], {type:'application/json'})
    )
    if(uploadedFiles.value.length>0){
      uploadedFiles.value.forEach(image => {
        formdata.append('files', image)
      })
    }else{
      formdata.append('files', 
      new Blob([], { type: 'application/octet-stream' })); // 빈 Blob 추가
    }

    try{
        const res = await axios.post(`${GLOBAL_URL}/api/pendingSale/create`, formdata,{
            headers:{
                'Content-type' : 'multipart/form-data',
                Authorization: `Bearer ${sessionStorage.getItem('token')}`,
            }
        })
        if (res.status === 201) {
          complete.value = false;
        }
    }catch(error){
        console.error("판매신청 실패", error)
        alert('실패ㅠㅠ')
    }
}

</script>

<template>
  <section class="sale_modal">
    <article class="modal_page" v-if="complete">
      <h2 class="modal_title">판매 등록 신청</h2>
      <div class="line"></div>
      <form @submit.prevent="sucsess">
        <div class="form_group">
          <label label for="user">판매 신청자 확인</label>
          <input type="text" id="user" :value="nickName" required readonly />
        </div>
        <div class="form_group">
          <label label for="account">계좌번호 입력 <small><span>*</span>필수사항</small></label>
          <select id="bank" name="bank" required v-model="bankName">
            <option value="" disabled selected>은행을 선택해 주세요</option>
            <option value="kb">국민은행</option>
            <option value="shinhan">신한은행</option>
            <option value="woori">우리은행</option>
            <option value="hana">하나은행</option>
            <option value="kakao">카카오뱅크</option>
            <option value="kb_savings">KB국민카드</option>
            <option value="ibk">기업은행</option>
            <option value="nh">농협은행</option>
            <option value="samsung">삼성증권</option>
            <option value="citibank">씨티은행</option>
            <option value="scbank">스탠다드차타드은행</option>
            <option value="busan">부산은행</option>
            <option value="daegu">대구은행</option>
            <option value="gwangju">광주은행</option>
            <option value="industrial">산업은행</option>
          </select>
          <input type="number" id="account" placeholder="계좌번호를 ' - ' 을 제외하고 입력해 주세요." v-model="accountNumber" min="0" max="999999999999999999" step="1" maxlength="18" required />
        </div>

        <div class="line02"></div>

        <div class="form_group">
          <label label for="">카테고리 <small><span>*</span>필수사항</small></label>
          <div class="radio_box radio_category">
            <label class="radio_label" for="Perfume">
                <input class="radio_input" type="radio" name="category" id="Perfume" value="Perfume" v-model="categoryName" required />
                <span class="radio_btn">향수(Perfume)</span>
            </label>
            <label class="radio_label" for="Diffuser">
                <input class="radio_input" type="radio" name="category" id="Diffuser" value="Diffuser" v-model="categoryName" required />
                <span class="radio_btn">디퓨저(Diffuser)</span>
            </label>
            <label class="radio_label" for="Candle">
                <input class="radio_input" type="radio" name="category" id="Candle" value="Candle" v-model="categoryName" required />
                <span class="radio_btn">향초(Candle)</span>
            </label>
          </div>
        </div>
        
        <div class="form_group">
          <label label for="brand">브랜드명 <small><span>*</span>필수사항</small></label>
          <input type="text" id="brand" placeholder="상품의 브랜드명을 입력해 주세요." maxlength="20" v-model="brandName" required />
        </div>
        <div class="form_group">
          <label label for="name">상품명 <small><span>*</span>필수사항</small></label>
          <input type="text" id="name" placeholder="상품의 이름을 입력해 주세요." maxlength="20" v-model="productName" required />
        </div>

        <div class="line02"></div>

        <div class="form_group">
          <label label for="">제품 사용 유무 <small><span>*</span>필수사항</small></label>
          <div class="radio_box radio_use">
            <label class="radio_label" for="notuse">
                <input class="radio_input" type="radio" name="use" id="notuse" :value="true" v-model="use" required />
                <span class="radio_btn">미사용 제품</span>
            </label>
            <label class="radio_label" for="use">
                <input class="radio_input" type="radio" name="use" id="use" :value="false" v-model="use" required />
                <span class="radio_btn">사용 제품</span>
            </label>
          </div>
        </div>

        <div class="form_group">
          <label label for="size">용량 <small><span>*</span>필수사항</small></label>
          <input type="number" id="size" placeholder="상품의 용량을 입력해 주세요. (ml)" min="10" max="99999" step="1" maxlength="11" v-model="productSize" required />
        </div>
        <div class="form_group">
          <label label for="price">희망 판매가격 <small><span>*</span>필수사항</small></label>
          <input type="number" id="price" placeholder="희망 판매 가격을 입력해 주세요. (￦)" min="50" max="9999999999" step="1" maxlength="11" v-model="expectedSellingPrice" required />
        </div>
        <div class="form_group">
          <label label for="content">상세 설명</label>
          <textarea maxlength="254" rows="10" id="content" placeholder="상품명의 상세한 설명을 입력해 주세요.(자세할수록 판매 등록에 도움이 됩니다.)" v-model="productContent"></textarea>
        </div>
        
        <div class="form_group">
          <label label for="photo">상품 사진 <small><span>*</span>필수사항</small></label>
          <input  type="file" id="photo" multiple accept="image/*" @change="handleFileUpload" required/>
          
          <div class="file_img_section">
            <div class="file_img_box" v-if="imageSrc" v-for="(img, index) in imageSrc" :key="index">
              <img :src="img" alt="Preview" id="preview"  />
              <div @click="imgDelete(index)" class="img_close">⨉</div>
            </div>
          </div>
        </div>
           
        <input type="submit" value="신청하기" />
      </form>

      <p @click.stop="closeModal" class="close_btn"><i class="fi fi-br-x"></i></p>
    </article>

    <article class="modal_page completePage" v-else="!complete">
      <span @click.stop="closeModal" class="close_btn"><i class="fi fi-br-x"></i></span>
      <!-- <img src="@/assets/img/partying-face.gif" alt=""> -->
      <img src="@/assets/img/smiling-face-hearts.gif" alt="">
      <h2>판매 신청 접수가 완료되었습니다!</h2>
      <p>신청현황은 판매내역 조회에서 확인하실 수 있습니다.</p>
    </article>
    
    <article @click.stop="closeModal" class="modal_background"></article>

  </section>
</template>

<style scoped>
@import url('https://cdn-uicons.flaticon.com/2.6.0/uicons-bold-rounded/css/uicons-bold-rounded.css');
/* 모달 전체 설정 ##################*/
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
/* 모달배경 설정 */
.modal_background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 10;
}
/* 모달창 설정 */
.modal_title {
  width: 100%;
  height: 100px;
  padding: 50px;
  font-size: 2.6rem;
}
.line {
  width: calc(100% - 100px);
  height: 2px;
  background-color: #333;
  margin-left: 50px;
}
.line02 {
  margin-top: 32px;
  margin-bottom: 32px;
  width: 100%;
  height: 1px;
  background-color: #e7e7e7;
}
.modal_page {
  position: relative;
  width: 600px;
  height: 800px;
  background-color: rgb(255, 255, 255);
  z-index: 11;
  border: 5px solid var(--color-main-bloode);
  overflow-y: scroll;
}
.modal_page::-webkit-scrollbar {width: 1px;}

form {
  width: 100%;
  height: calc(100% - 100px);
  padding: 35px 50px;
  text-align: center;
}
.form_group {
  margin-bottom: 24px; /* 각 폼 그룹 사이에 여백 */
  display: flex;
  flex-direction: column;
  align-items: start;
}

/* 라벨 스타일 ############################################## */
.form_group label {
  float: left;
  display: block;
  margin-bottom: 8px;
  font-weight: bold;
  font-size: 1.5rem;
  color: #333;
}
.form_group label small {
  font-weight: 400;
  color: #9e9e9e;
  margin-left: 10px;
  font-size: 1.1rem;
}
.form_group label small span {
  color: red;
}
/* 텍스트 입력 필드 스타일  */
.form_group input[type='text'],
.form_group input[type='file'],
.form_group input[type='number'],
.form_group textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1.4rem;
  background-color: #f9f9f9;
}
.form_group textarea {
  height: 150px;
  resize: none;
}
/* .form_group textarea::placeholder {
  white-space: pre-wrap;
} */
.form_group input[type='text']:focus,
.form_group textarea:focus {
  border-color: var(--color-main-bloode);
  outline: none;
}

/* 라디오버튼 설정 ############################################*/
.radio_box {
    width: 100%;
    display: grid;
}
.radio_category{grid-template-columns: repeat(3, 1fr);}
.radio_use{grid-template-columns: repeat(2, 1fr);}
.radio_input {display: none}
.radio_label {
    display: inline-block;
    cursor: pointer;
}
.radio_btn {
    display: inline-block;
    width: 100%;
    padding: 10px 0;
    background-color: #ccc; 
    color: #fff;
    border-radius: 0.2rem;
    text-align: center;
}
.radio_input:checked + .radio_btn {
    background-color: rgb(96, 0, 0);
    color: #fff;
}

/* select 설정 ############################################# */
select{
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1.4rem;
  background-color: #f9f9f9;
  margin-bottom: 10px;
}
select > option{
  font-size: 1.4rem;
}

/* 파일 입력 필드 스타일 #################################### */
.form_group input[type='file'] {
  padding: 10px;
  margin-bottom: 20px;
  background-color: #f9f9f9;
}
.file_img_section{
  width: 100%;
  /* background-color: #333; */
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;
}
.file_img_box{
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border: 1.2px solid #bebebe;
  border-radius: 1.2rem;
  background-color: #f9f9f9;  
}
.file_img_box > img{
  width: 100%;
}
.img_close{
  position: absolute;
  top: 5px;
  right: 5px;
  cursor: pointer;
  background-color: red;
  border-radius: 100%;
  width: 17px;
  height: 17px;
  font-weight: 800;

  display: flex;
  align-items: center;
  justify-content: center;
}

/* 제출 버튼 ############################################### */
input[type='submit'] {
  margin-top: 20px;
  margin-bottom: 40px;
  padding: 12px 20px;
  background-color: var(--color-main-bloode);
  color: white;
  border: none;
  border-radius: 0.7rem;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.1s;
}
input[type='submit']:hover {
  background-color: var(--color-main-red);
}

/* 닫기 버튼 ############################################# */
.close_btn {
  position: absolute;
  right: 15px;
  top: 15px;
  font-size: 1.8rem;
  cursor: pointer;
}

/* 신청 성공 페이지 ######################################### */
.completePage{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.completePage img{
  margin: 20px 0;
}
.completePage h2{
  font-size: 2.5rem;
  margin: 15px 0;
  color: #161616;
}
.completePage p{
  font-size: 1.3rem;
  color: #333;
  opacity: 1;
  animation: textop 5s infinite;
}
@keyframes textop{
  0%{
    opacity: 1;
  }
  20%{
    opacity: 0;
  }
  40%{
    opacity: 1;
  }
  100%{
    opacity: 1;
  }
}
</style>
