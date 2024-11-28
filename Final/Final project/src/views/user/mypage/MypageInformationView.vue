<script setup>
import { ref } from 'vue';

const save = ()=>{
  console.log("작성저장")
}
const profileImage = sessionStorage.getItem('profileImage')
const inputFile = ref();
const change_profile_image = ()=>{
  inputFile.value.click();
}
const imageUpload = ()=>{
  console.log('사진업로드')
}

</script>


<template>
  <section>
    <h1 class="mypage_info_title">회원 정보 관리</h1>

    <article class="mypage_info_wrapper">
      <div class="profile_image">
        <div class="profile_img_box">
          <img :src="profileImage" alt="profile_image">
        </div>
        <p @click="change_profile_image">프로필 사진변경</p>

        <input @change="imageUpload" type="file" style="display: none;" ref="inputFile">
      </div>

      <div class="user_info">
        <form>
          <div class="form-group">
            <label for="nickname">닉네임</label>
            <input type="text" id="nickname" value="닉네임" />
          </div>
          <div class="form-group">
            <label for="address">주소</label>
            <input type="text" id="address" value="대구광역시 달서구 반월로 135 그린컴퓨터아트학원 5층 505호" />
          </div>
          <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" value="김영희" />
          </div>
          <div class="form-group">
            <label for="phone">핸드폰번호</label>
            <input type="tel" id="phone" value="010-1234-1234" />
          </div>
          <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" id="email" value="abcdefhijk@naver.com" readonly/>
            <!-- <small class="email_small">카카오 계정입니다.</small> -->
          </div>
          <div class="form-group">
            <label for="gender">성별</label>
            <input type="text" id="gender" value="여" readonly/>
          </div>

          <button @click.prevent="save" type="submit" class="info_save_btn">저정하기</button>
        </form>

        <p class="secession">
          <i class="fi fi-ss-delete-right"></i>
          회원탈퇴
        </p>
      </div>
    </article>

    <!-- <article></article> -->
  </section>
</template>

<style scoped>
@import url('https://cdn-uicons.flaticon.com/2.6.0/uicons-solid-straight/css/uicons-solid-straight.css');
.mypage_info_title {
  font-size: 3.2rem;
  font-weight: 400;
  border-bottom: solid 1px #000;
  padding: 20px 0;
  margin-top: 32px;
}
/* 전체설정 */
.mypage_info_wrapper {
  display: flex;
  width: 100%;
  height: 630px;
}
/* 프로필이미지 설정 */
.profile_image {
  width: 250px;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* background-color: #666; */
}
.profile_img_box{
  width: 160px;
  height: 160px;
  border-radius: 50%;
  overflow: hidden;
  background-color: var(--color-main-gray);
  margin-top: 60px;
}
.profile_img_box img{
  width: 100%;
  height: auto;
}
.profile_image p{
  margin-top: 20px;
  font-size: 1.2rem;
  font-weight: 600;
  cursor: pointer;
}
.profile_image p:hover{
  color: var(--color-main-bloode);
}

/* 유저정보 설정 */
.user_info {
  width: calc(100% - 250px);
  height: 100%;
  position: relative;
  background-color: var(--color-main-pink);
}
.user_info form{
  text-align: center;
  padding: 40px 80px 0 80px;
}
.form-group {
  margin: 25px 0;
  display: flex;
}
label {
  display: block;
  font-size: 14px;
  font-weight: bold;
  color: #333;
  width: 100px;
  height: 35px;
  display: flex;
  align-items: center;
  justify-content: start;
}
input[type="text"],
input[type="tel"],
input[type="email"] {
  width: 100%;
  min-width: 300px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
}
input[type="text"]:focus,
input[type="tel"]:focus,
input[type="email"]:focus {
  border-color: var(--color-main-bloode);
}
.email_small {
  position: absolute;
  font-size: 12px;
  color: #666;
}

/* save 버튼설정 */
.info_save_btn{
  margin-top: 50px;
  padding: 10px 20px;
  font-size: 16px;
  background-color: var(--color-main-bloode);
  color: rgb(255, 253, 253);
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}
.info_save_btn:active {
  transform: translateY(4px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); 
}

/* 탈퇴하기 */
.secession{
  position: absolute;
  bottom: 20px;
  right: 20px;
  font-size: 1.6rem;
  font-weight: 500;
  color: #919191;
  cursor: pointer;
  display: flex;
  align-items: center;
}
.secession i{
  font-size: 1.6rem;
  margin-right: 5px;
  display: flex;
  align-items: center;
}
.secession:hover{
  color: #000;
}
</style>
