<script setup>
import { ref } from 'vue';

const showImgButton = ref(true);
const showImgclick = () => {
  showImgButton.value = !showImgButton.value;
};
</script>

<template>
  <article id="ProductDescription" :class="{ ProductDescription: showImgButton, showImg: !showImgButton }">
    <div class="img_box">
      <img src="@/assets/img/상세페이지_1.jpg" alt="" />
      <div class="img_btn" :class="{ close_img_btn: !showImgButton }">
        <button v-show="showImgButton" @click="showImgclick">이미지 더보기 ▽</button>
        <button v-show="!showImgButton" @click="showImgclick">이미지 닫기 △</button>
      </div>
    </div>

    <!-- <img :src="`${GLOBAL_URL}/api/file/download/${props.productId}`" alt="" /> -->
  </article>
</template>

<style scoped>
.ProductDescription {
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 2000px;
  /* height: auto; */
}
.showImg {
  /* position: relative; */
  width: 100%;
  height: auto;
}
.img_box {
  width: 60%;
  height: 100%;
  margin: auto;
}
img {
  width: 100%;
  height: auto;
}
/* {
} */
.img_btn {
  position: sticky;
  bottom: 0px;
  width: 100%;
  /* ▲ 이거는 설정이 바뀌면 안된다. */
  /* background-color: gray; */
  background: linear-gradient(rgba(255, 255, 255, 0) 0%, rgb(255, 255, 255) 70%, rgb(255, 255, 255) 100%);
  height: 150px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.img_btn button {
  background-color: white;
  height: 50px;
  width: 30%;
  margin-bottom: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 0.5px solid var(--color-main-gray);
  border-radius: 50px;
  font-size: 1.6rem;
}
.close_img_btn {
  position: static;
  background-color: white;
}
</style>
