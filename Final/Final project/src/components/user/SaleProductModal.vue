<script setup>
import { ref } from 'vue';

// 모달창 닫기(부모로 event 전달)
const emit = defineEmits()
const closeModal = ()=>{
    emit('closeModal')
}

// 신청 성공시 성공페이지
// const applicationForm = ref(false)
// const sucsess = ()=>{
//     applicationForm = applicationForm
// }


</script>

<template>
    <section class="sale_modal">
        <article @click.stop="closeModal" class="modal_background"></article>

        <article class="modal_page">
            <h2 class="modal_title">판매 등록 신청</h2>
            <div class="line"></div>
            <form>
                <div class="form-group">
                    <label label for="user">판매 신청자 확인</label>
                    <input type="text" id="user" value="강도현" required readonly/>
                </div>
                <div class="form-group">
                    <label label for="name">상품명 <small><span>*</span>필수사항</small></label>
                    <input type="text" id="name" placeholder="상품명을 입력해 주세요." maxlength="20" required/>
                </div>
                <div class="form-group">
                    <label label for="price">판매가격 <small><span>*</span>필수사항</small></label>
                    <input type="number" id="price" placeholder="￦ 희망 판매 가격을 입력해 주세요." min="50" max="9999999999" step="1" maxlength="11" required/>
                </div>
                <div class="form-group">
                    <label label for="content">상세 설명</label>
                    <textarea maxlength="300" rows="10" id="content" placeholder="상품명의 상세한 설명을 입력해 주세요.(자세할수록 판매 등록에 도움이 됩니다.)"></textarea>
                </div>
                <div class="form-group">
                    <label label for="photo">상품 사진 <small><span>*</span>필수사항</small></label>
                    <input type="file" id="photo" required/>
                </div>
                <input @submit="sucsess" type="submit" value="신청하기">
            </form>

            <p @click.stop="closeModal" class="close_btn"><i class="fi fi-br-x"></i></p>
        </article>
        
        <!-- <article class="modal_page" v-if="applicationForm">
            신청성공!
        </article> -->
    </section>
</template>

<style scoped>
@import url('https://cdn-uicons.flaticon.com/2.6.0/uicons-bold-rounded/css/uicons-bold-rounded.css');
/* 모달 전체 설정 */
.sale_modal{
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
.modal_background{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 10;
}
/* 모달창 설정 */
.modal_title{
    width: 100%;
    height: 100px;
    padding: 50px;
    font-size: 2.6rem;
}
.line{
    width: calc(100% - 100px);
    height: 2px;
    background-color: #333;
    margin-left: 50px;
}
.modal_page{
    position: relative;
    width: 600px;
    height: 800px;
    background-color: rgb(255, 255, 255);
    z-index: 11;
    border: 5px solid var(--color-main-bloode);
}
form{
    width: 100%;
    height: calc(100% - 100px);
    padding: 35px 50px;
    text-align: center;
}
.form-group {
    margin-bottom: 20px; /* 각 폼 그룹 사이에 여백 */
}
/* 라벨 스타일 */
.form-group label {
    float: left;
    display: block; 
    margin-bottom: 8px; 
    font-weight: bold;
    font-size: 1.5rem;
    color: #333;
}
.form-group label small{
    font-weight: 400;
    color: #9e9e9e;
    margin-left: 10px;
    font-size: 1.1rem;
}
.form-group label small span{
    color: red;
}

/* 텍스트 입력 필드 스타일 */
.form-group input[type="text"],
.form-group input[type="file"],
.form-group input[type="number"],
.form-group textarea {
    width: 100%; 
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px; 
    font-size: 1.4rem; 
    background-color: #f9f9f9;
}
.form-group textarea {
    height: 150px; 
    resize: none;
}
/* .form-group textarea::placeholder {
  white-space: pre-wrap;
} */

.form-group input[type="text"]:focus,
.form-group textarea:focus {
    border-color: var(--color-main-bloode); 
    outline: none;
}

/* 파일 입력 필드 스타일 */
.form-group input[type="file"] {
    padding: 10px;
    background-color: #f9f9f9;
}

/* 제출 버튼 */
input[type="submit"] {
    margin-top: 10px;
    padding: 12px 20px;
    background-color: var(--color-main-bloode);
    color: white;
    border: none;
    border-radius: 0.7rem;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.1s;
}
input[type="submit"]:hover {
    background-color: var(--color-main-red);
}

/* 닫기 버튼 */
.close_btn{
    position: absolute;
    right: 15px;
    top: 15px;
    font-size: 1.8rem;
    cursor: pointer;
}

</style>