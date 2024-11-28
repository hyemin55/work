<script setup>
const correction =()=>{
  console.log("수정하기")
}
</script>

<template>
  <section>
    <h1 class="review_manage_title">내 리뷰 관리</h1>
    
    <!-- 하위 컴포넌트 -->
    <article class="review_wrapper">
        <div class="review_title">
            <ul>
                <li class="title_star">★★★★★</li>
                <li class="title_product">상품명</li>
                <li class="title_options">수량 / 사이즈</li>
            </ul>
        </div>

        <div class="review_content">
          <div class="img_box">
            <img src="@/assets/img/c008.png" alt="">
          </div>
          <div class="text_box">
            <p class="text_content">리뷰의 내용</p>
            <p class="text_date">2024.07.05 03:28PM</p>
          </div>
        </div>
 
        <button class="btn" @click="correction">수정</button>
    </article>

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
  display: flex;
  width: 100%;
  height: 150px;
  /* background-color: antiquewhite; */
}
.img_box{
  width: 185px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.img_box img{
  height: 100%;
  width: auto;
  background-color: var(--color-main-Lgray);
  border-radius: 1.5rem;
}
.text_box{
  position: relative;
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

</style>
