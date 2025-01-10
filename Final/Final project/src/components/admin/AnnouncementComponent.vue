<template>
  <article class="Announcement">
    <h1 class="AnnouncementTitle">공지사항</h1>
    <div class="AnnouncementText">
      <p>2024-11-19 오전 05:00~ 오전 06:00 관리자 버전 업데이트됩니다. 사용에 유의바랍니다.</p>
      <p>2025-01-26 ~ 2025-01-30 설연휴 배송 업체 휴무일 입니다. 배송 업무에 참고하시길 바랍니다.</p>
    </div>
  </article>
</template>

<script setup></script>

<style scoped>
.Announcement {
  background-color: white;
  width: 100%;
  height: auto;
  padding: 10px;
  margin-top: 10px;
  border-radius: 10px;
  font-size: 1.5rem;
  text-overflow: ellipsis;
  line-height: 1.5;
}
.AnnouncementTitle {
  font-size: 1.5rem;
  /* background-color: antiquewhite; */
  border-bottom: 1px solid var(--color-main-gray);
  line-height: 1.8;
}
.AnnouncementText {
  margin-top: 5px;
}
</style>
