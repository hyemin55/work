<template>
    <div class="modal-overlay" @click.self="close">
      <div class="modal-content">
          
          <img :src="image" alt="확대된 이미지" > <button class="close_button" @click="close">ｘ</button></img>
      </div>
    </div>
  </template>
  
  <script setup>
import { onMounted, onUnmounted } from 'vue';

  
  const props = defineProps({
    image: String,
  });
  const emit = defineEmits(['close']);
  
  function close() {
    emit('close');
  }

  onMounted(() => {
  document.body.style.overflow = 'hidden'; // 스크롤 비활성화
});

onUnmounted(() => {
  document.body.style.overflow = ''; // 스크롤 활성화
});
  </script>
  
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.7);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  .modal-content {
      position: relative;
      width: 450px;
      height: 450px;
      display: flex;
      justify-content: center;
      align-items: center;
      /* background-color: rgb(158, 85, 85); */
    }
    .modal-content img {
    max-width: 430px;
    max-height: 430px;
    background-color: white;
    border-radius: 20px;
}
.close_button {
    position: absolute;
    top: 15px;
    right: 40px;
    background: transparent;
    /* border: 0.5px solid #333;
    border-radius: 100%; */
    width: 10px;
    height: 10px;
  color: #333;
  font-size: 3rem;
  cursor: pointer;
  /* display: flex;
  justify-content: right;
  align-items: start; */
}
  </style>
  