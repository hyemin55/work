<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';

const FooterVisible = ref('');

const handleScroll = () => {
  const scrollPosition = window.innerHeight + window.scrollY; // 현재 스크롤 위치
  const documentHeight = document.documentElement.scrollHeight; // 전체 문서 높이

  // 스크롤이 끝에 도달하면 FooterVisible 값을 true로 변경
  FooterVisible.value = scrollPosition > documentHeight - 10; // 약간의 오차를 허용하기 위해 -1
};

const checkHeight = () => {
  const documentHeight = document.documentElement.scrollHeight;
  const windowHeight = window.innerHeight;

  // 문서의 높이가 화면 높이보다 작으면 바로 FooterVisible 값을 true로 변경
  if (documentHeight <= windowHeight) {
    FooterVisible.value = true;
  }
};

// 컴포넌트가 마운트될 때 스크롤 이벤트 추가 및 페이지 높이 체크
onMounted(() => {
  window.addEventListener('scroll', handleScroll);
  checkHeight(); // 페이지 높이를 체크하여 바로 푸터를 표시할지 결정
});

// 컴포넌트가 언마운트될 때 스크롤 이벤트 제거
onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll);
});
</script>

<template>
  <footer id="footer" :class="{ show: FooterVisible, noshow: !FooterVisible }">
    <div id="footer_text">
      <ul>
        <li>회사소개</li>
        <li>개인정보처리방침</li>
        <li>이용약관</li>
      </ul>
      <p>
        (주)CHERRISH | 대표이사 : 태영 김 | 도현 강 | 호창 송 | 혜민 조 | 사업자등록번호 : 123-45-67891 | 통신판매업 : 제 2024-대구반월D-0505호 대구광역시 달서구 반월로 67 그린컴퓨터아트학원 5층 |
        호스팅 서비스 : 네이버 클라우드 (주)
      </p>
      <p>신한은행 채무지급보증 안내 당사는 고객님의 현금 결제 금액에 대해 신한은행과 채무지급보증 계약을 체결하여 안전거래를 보장하고 있습니다. 서비스가입 사실 확인</p>
      <p>
        (주)CHERRISH는 통신판매 중개자로서 통신판매의 당사자가 아닙니다. 본 상품은 개별판매자가 등록한 상품으로 상품, 상품정보, 거래에 관한 의무와 책임은 각 판매자에게 있습니다. 단, 이용약관 및 정책,
        기타 거래 체결 과정에서 고지하는 내용 등에 따라 검수하고 보증하는 내용에 대한 책임은 (주)CHERRISH에 있습니다.
      </p>
      <p>출처: 카카오 이모티콘(아기양 메리메)</p>
    </div>
  </footer>
</template>

<style scoped>
#footer {
  width: 100%;
  /* height: 160px; */
  background-color: var(--color-main-gray);
  margin-top: 60px;
}
#footer_text {
  max-width: var(--main-max-width);
  color: rgb(36, 36, 36);
  margin: 0 auto;
  width: 100%;
  padding: 2rem 0;
  font-size: 1rem;
  font-weight: 400;
  letter-spacing: -0.34px;
}
#footer_text > p {
  margin-top: 0.7rem;
}
ul {
  display: flex;
  gap: 20px;
  margin-bottom: 1.2rem;
}
</style>
