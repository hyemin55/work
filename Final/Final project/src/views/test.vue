<template>
    <div class="container" @scroll="handleScroll" ref="scrollContainer">
      <div v-for="item in items" :key="item.id" class="item">
        <p>{{ item.name }}</p>
        <p>{{ item.description }}</p>
      </div>
  
      <!-- 로딩 중일 때 표시 -->
      <div v-if="loading" class="loading">로딩 중...</div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const items = ref([]);          // 아이템 리스트
  const loading = ref(false);     // 로딩 상태
  const page = ref(1);            // 페이지 번호
  
  // 스크롤 이벤트 핸들러
  const handleScroll = () => {
    const container = $refs.scrollContainer;
    // 스크롤이 컨테이너의 하단에 가까워지면 데이터를 로드합니다.
    if (container.scrollTop + container.clientHeight >= container.scrollHeight - 50 && !loading.value) {
      loadMoreData();
    }
  };
  
  // 데이터 로딩 함수
  const loadMoreData = async () => {
    loading.value = true;  // 로딩 시작
    try {
      const response = await axios.get(`https://api.example.com/items?page=${page.value}`);
      // 데이터가 있으면 기존 목록에 추가
      if (response.data && response.data.length > 0) {
        items.value = [...items.value, ...response.data];
        page.value += 1;  // 페이지 번호 증가
      }
    } catch (error) {
      console.error("데이터 로딩 실패:", error);
    } finally {
      loading.value = false;  // 로딩 완료
    }
  };
  
  // 컴포넌트가 마운트될 때 데이터 로드
  onMounted(() => {
    loadMoreData();
  });
  </script>
  
  <style scoped>
  .container {
    max-height: 500px;   /* 최대 높이 지정 */
    overflow-y: auto;    /* 스크롤 가능 */
  }
  
  .loading {
    text-align: center;
    padding: 10px;
    font-size: 16px;
    color: #777;
  }
  
  .item {
    padding: 15px;
    border-bottom: 1px solid #ccc;
  }
  </style>
  