<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios from 'axios';
import { ref } from 'vue';

// 모달창 닫기(부모로 event 전달)
const emit = defineEmits(['closeModal']);
const closeModal = () => {
  emit('closeModal');
};
// props
const props = defineProps({
    data : {
        type:Object,
        required: false
    }
})

// input.value 맵핑
const starPoint = ref();
const content = ref();
const reviewCreateReqDto = ref({
    id: props.data.purchaseProductId,
    content : content,
    starPoint : starPoint
})
// 파일 업로드
const file = ref([]);
const fileUpload = (event)=>{
    const selectedFile = event.target.files[0]; 
    if (selectedFile) {
        file.value = [selectedFile]; 
    } else {
        file.value = [];  
    } 
}

const formdata = new FormData();
formdata.append('reviewCreateReqDto', new Blob([JSON.stringify(reviewCreateReqDto)], {type:'application/json'}))
if(file.value.length>0){
    formdata.append('file', file.value[0]);
}else{
    formdata.append('file', new Blob([], {type:'application/octet-stream'}))
}

// 리뷰 생성
const createReview = async(event)=>{
    event.preventDefault();
    try{
        const res = await axios.post(`${GLOBAL_URL}/myPage/reviewCreate`, formdata, {
            headers : {
                'Contnet-type' : 'multpart/form-data',
                Authorization: `Bearer ${sessionStorage.getItem('token')}`,
            }
        })
        console.log("통신 성공", res.data);
    }catch(error){
        console.error(error)
    }
}

</script>

<template>
    <section class="create_review_wrapper">
        <article class="review_article">
            <h2 class="review_title">리뷰작성</h2>
            <form>
                <div class="star_rating">
                    <input type="radio" class="star" name="star" id="1" value="1" v-model="starPoint">
                    <input type="radio" class="star" name="star" id="2" value="2" v-model="starPoint">
                    <input type="radio" class="star" name="star" id="3" value="3" v-model="starPoint">
                    <input type="radio" class="star" name="star" id="4" value="4" v-model="starPoint">
                    <input type="radio" class="star" name="star" id="5" value="5" v-model="starPoint">
                </div>
                <div>
                    <input type="file" id="file-upload" @change="fileUpload($event)">
                    <label for="file-upload" class="file-upload-button">클릭해서 이미지를 추가해 주세요.</label>
                    <!-- <div v-if="fileName">선택된 파일: {{ fileName }}</div> -->
                </div>
                <textarea name="textarea" id="textarea" maxlength="200" required rows="5" cols="50" style="resize: none;" v-model="content"
                    placeholder="리뷰 내용을 입력하세요. (최대 200자)" ></textarea>
                <button class="btn" @click="createReview">리뷰등록</button>
            </form>
        </article>
        <article class="review_background" @click="closeModal"></article>
    </section>
</template>

<style scoped>
/* 전체설정 */
.create_review_wrapper{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100vh;
    z-index: 999;
}
.review_background{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 10;
}
.review_article{
    position: absolute;
    left: 50%;
    top: 50%;
    margin-top: -300px;
    margin-left: -200px;
    width: 400px;
    height: 600px;
    border-radius: 1.2rem;
    background-color: #fff;
    z-index: 11;
}
.review_title{
    width: 100%;
    height: 50px;
    margin-top: 15px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 2rem;
    /* border-bottom: 1px solid #000; */
}

/* 별점 설정 설정 */
.star_rating{
    width: 100%;
    height: 60px;
    display: flex;
    justify-content: center;
    border-bottom: 1px solid #d6d6d6;
}
.star{
    appearance: none;
    padding: 4px;
    cursor: pointer;
}
.star::after{
    content: '☆';
    color: orange;
    font-size: 3rem;
}
.star:hover::after,
.star:has(~ .star:hover)::after,
.star:checked::after,
.star:has(~ .star:checked)::after {
  content: '★';
}

#textarea{
    width: 90%;
    height: 200px;
    margin-left: 5%;
    font-size: 1.6rem;
}
textarea:focus{
    border-color: var(--color-main-bloode);
    outline: none;
    color: #000;
    /* background-color: #000; */
}


#file-upload {
  display: none;
}
/* 커스텀 스타일로 만든 버튼 */
.file-upload-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 90%;
  height: 170px;
  margin: 10px 5%;
  margin-top: 25px;
  background-color: rgb(240, 240, 240);
  border: 1px solid #cccccc;
  color: #cccccc;
  font-size: 1.5rem;
  font-weight: 400;
  border-radius: 5px;
  cursor: pointer;
}
.file-upload-button:hover {
  background-color: var(--color-main-bloode);
  color: #fff;
}


.btn{
    position: absolute;
    bottom: 20px;
    left: 50%;
    margin-left: -35px;
    width: 70px;
    padding: 10px 0;
    background-color: #000;
    border-radius: 0.5rem;
    text-align: center;
    color: #fff;
    font-size: 1.2rem;
}
.btn:hover{
    background-color: var(--color-main-bloode);
}
</style>