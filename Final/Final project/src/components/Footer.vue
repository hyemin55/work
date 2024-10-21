<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

// 컴포넌트에서 사용할 reactive 상태 선언
const FooterVisible = ref(false)

// 스크롤 이벤트 핸들러
const handleScroll = () => {
  const scrollHeight = window.innerHeight + window.scrollY
  const documentHeight = document.documentElement.scrollHeight
  FooterVisible.value = scrollHeight >= documentHeight
}
console.log(`scrollHeight: ${scrollHeight}, documentHeight: ${documentHeight}`)
// 컴포넌트가 마운트되었을 때 스크롤 이벤트 리스너 추가
onMounted(() => {
  window.addEventListener('scroll', handleScroll)
})

// 컴포넌트가 언마운트되었을 때 스크롤 이벤트 리스너 제거
onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll)
})
</script>

<template>
  <section id="footer" v-if="FooterVisible">
    <div id="footer_text">
      <ul>
        <li>회사소개</li>
        <li>개인정보처리방침</li>
        <li>이용약관</li>
      </ul>
      <br />
      <p>
        (주)CHERRISH | 대표이사 : 태영 김 | 사업자등록번호 : 123-45-67891 |
        통신판매업 : 제 2024-대구반월D-0505호 대구광역시 달서구 반월로 67
        그린컴퓨터아트학원 5층 | 호스팅 서비스 : 네이버 클라우드 (주)
      </p>
      <br />
      <p>
        신한은행 채무지급보증 안내 당사는 고객님의 현금 결제 금액에 대해
        신한은행과 채무지급보증 계약을 체결하여 안전거래를 보장하고 있습니다.
        서비스가입 사실 확인
      </p>
      <br />
      <p>
        (주)CHERRISH는 통신판매 중개자로서 통신판매의 당사자가 아닙니다. 본
        상품은 개별판매자가 등록한 상품으로 상품, 상품정보, 거래에 관한 의무와
        책임은 각 판매자에게 있습니다. 단, 이용약관 및 정책, 기타 거래 체결
        과정에서 고지하는 내용 등에 따라 검수하고 보증하는 내용에 대한 책임은
        (주)CHERRISH에 있습니다.
      </p>
    </div>
  </section>
</template>

<style scoped>
#footer {
  background-color: var(--color-main-gray);
  width: 100%;
  bottom: 0;
  left: 0;
  z-index: 999;
  position: fixed;
  /* display: none; */
}
#footer_text {
  max-width: var(--main-max-width);
  width: 100%;
  margin: 0 auto;
  padding: 20px 0px;
}
ul {
  display: flex;
}
li {
  margin-right: 20px;
}
</style>
