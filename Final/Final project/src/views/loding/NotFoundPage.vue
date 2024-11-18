<template>
  <div id="NotFoundPage">
    <img src="@/assets/img/notFoundPage.png" alt="" />
    <div>
      <h1>Not Found Page</h1>
      <h1 @click="GoHomepage" class="GoHomepage">HOME으로 가기</h1>
    </div>
  </div>
</template>

<script setup>
import router from '@/router';

const GoHomepage = () => {
  router.push({ name: 'main' });
};
</script>

<style scoped>
#NotFoundPage {
  width: var(--main-max-width);
  height: auto;
  margin: 0 auto;
  padding: 5% 0;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: antiquewhite; */
}
h1 {
  color: var(--color-text-gray);
  font-size: 4rem;
}
.GoHomepage {
  font-size: 2rem;
  margin-top: 20px;
  cursor: pointer;
  color: var(--color-main-bloode);
  text-decoration: underline;
}
</style>
b3
