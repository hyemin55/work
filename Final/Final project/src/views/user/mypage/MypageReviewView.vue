<script setup>
import { GLOBAL_URL } from '@/api/util';
import PageNationComponent from '@/components/PageNationComponent.vue';
import axios from 'axios';
import { onMounted, ref, watch } from 'vue';

// 페이지네이션
const totalCount = ref(50);
const pageSize = ref(5);
const pageNum = ref(0);
const pageNationData = ref('');

// 리뷰 리스트 가져오기
const data = ref([]);
const getReview = async(pageNum, size)=>{
  try{
    const res = await axios.get(`${GLOBAL_URL}/myPage/reviewList`, {
      params:{
        pageNum : pageNum,
        size : size
      },
      headers:{
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
        'Content-Type':'application/json'
      },
    })
    totalCount.value = res.data[0].reviewCount;
    data.value = res.data.map(product => ({
      ...product,
      updateMode: true,
      starIcons: getStarIcon(product.star)
    }));
    console.log(data.value)
  }catch(error){
    console.error(error)
  }
}

const getStarIcon = (star)=>{
  switch(star){
    case 5:
      return '★★★★★';
    case 4:
      return '★★★★☆';
    case 3:
      return '★★★☆☆';
    case 2:
      return '★★☆☆☆';
    case 1:
      return '★☆☆☆☆';
    default:
      return '☆☆☆☆☆';
  }
}

// 리뷰 삭제 
const deleteReview = async(reviewId)=>{
  try{
    const res = await axios.delete(`${GLOBAL_URL}/myPage/reviewDelete/${reviewId}`, {
      headers:{
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
        'Content-Type':'application/json'
      }
    })
    await getReview();
  }catch(error){
    console.error(error);
  }
}

// 리뷰 업데이트
const setMode = (list)=>{
  list.updateMode = false;
}
const updateReview = async(list, reviewId, content)=>{
  list.updateMode = true;
  const updateData = (
    {
      reviewId: reviewId,
      content: content,
    }
  );
  try{
    await axios.post(`${GLOBAL_URL}/myPage/reviewUpdate`, updateData, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
        'Content-Type':'application/json'
      }
    })
    await getReview(pageNum.value, pageSize.value);
  }catch(error){
    console.error(error);
  } 
}

// 페이지 랜더링
watch([pageNum, totalCount], ([newPageNum, newTotalCount]) => {
  getReview(newPageNum, pageSize.value);
  pageNation(newTotalCount);
});
onMounted(()=>{
  getReview(pageNum.value, pageSize.value);
  pageNation();
})
const pageUpdate = pageNumer => {
  pageNum.value = pageNumer;
};

// 페이지컴포넌트 생성을 위해 보내는값(페이지수 생성)
const pageNation = () => {
  pageNationData.value = {
    totalCount: totalCount.value,
    pageSize: pageSize.value,
  };
};

</script>

<template>
  <section>
    <h1 class="review_manage_title">내 리뷰 관리</h1>
    
    <!-- 하위 컴포넌트 -->
    <article class="review_wrapper" v-for="list in data" :key="list.reviewId">
        <div class="review_title">
            <ul>
                <li class="title_star">{{ list.starIcons }} ( {{ list.star }} )</li>
                <li class="title_product">{{ list.productName }}</li>
                <li class="title_options">{{ list.size }}ml</li>
            </ul>
        </div>

        <div class="review_content">
          <div class="img_box">
            <img :src="`${GLOBAL_URL}/api/file/download/${list.reviewImage}`" alt="">
          </div>
          <div class="text_box">
            <p v-if="list.updateMode" class="text_content">{{ list.content }}</p>
            <textarea v-else class="text_content text_area" name="" id="" v-model="list.content"></textarea>

            <p class="text_date">{{ list.createDate }}</p>
          </div>
          <p class="delete_btn" @click="deleteReview(list.reviewId)">삭제</p>
        </div>
        
        <button v-if="list.updateMode" class="btn"  @click="setMode(list)">수정</button>
        <button v-else class="btn" @click="updateReview(list, list.reviewId, list.content)">저장</button>
    </article>

    <PageNationComponent :pageNationData="pageNationData" @currentPage="pageUpdate" />

    <!-- <article>
      <h1>리뷰가 없을때 article</h1>  
    </article> -->
  </section>
</template>

<style scoped>
/* 타이틀 텍스트 선택 */
.review_manage_title {
  font-size: 3.2rem;
  font-weight: 400;
  border-bottom: solid 1px #000;
  padding: 20px 0;
  margin-top: 32px;
}
/* 컴포넌트 설정 ######################## */
.review_wrapper{
  position: relative;
  height: 260px;
  width: 100%;
  border-bottom: 2px solid var(--color-main-gray);
}

/* 상단설정 */
.review_title{
  height: 90px;
  width: 100%;
  display: flex;
  align-items: center;
}
.review_title ul li{
  font-size: 1.8rem;
  margin: 5px 12px;
}
.title_options{
  font-size: 1.2rem !important;
}
.title_star{
  color: orange;
}

/* 하단설정 */
.review_content{
  position: relative;
  display: flex;
  width: 100%;
  height: 150px;
}
.img_box{
  width: 185px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.img_box img{
  height: 100%;
  width: auto;
  background-color: var(--color-main-Lgray);
  border-radius: 1.5rem;
}
.text_box{
  position: relative;
  width: calc(100% - 350px);
}
.text_content{
  font-size: 1.6rem;
  letter-spacing: -0.034rem;
}
.text_date{
  position: absolute;
  bottom: 0;
  left: 0;
  white-space: nowrap;
  font-size: 1.4rem;
  letter-spacing: -0.034rem;
  color: #696969;
}

/* 버튼 설정 */
.btn{
  width: 150px; 
  height: 36px;
  text-align: center;
  border: 1px solid var(--color-main-gray);
  border-radius: 0.7rem;
  margin: 5px 0;
  font-size: 1.3rem;
  position: absolute;
  top: 50%;
  right: 0;
  margin-top: -18px;
}
.btn:hover{
  background-color: black;
  color: #fff;
}

.delete_btn{
  position: absolute;
  right: 10px;
  bottom: 0;
  font-size: 1.34rem;
  color: #696969;
  cursor: pointer;
}
.delete_btn:hover{
  color: #000;
}


/* textarea 설정 */
textarea {
  width: 100%;
  height: 80%;
  font-size: 16px;
  line-height: 1.5; 
  border: 2px solid #ccc;
  border-radius: 5px; 
  resize: none;
  background-color: #f9f9f9;
}
textarea:focus {
  border-color: var(--color-main-bloode); 
  background-color: #ffffff; 
  outline: none; 
}
textarea:hover {
  border-color: #999; 
}

</style>
