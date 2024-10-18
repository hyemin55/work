<script setup></script>

<template>
  <section id="header">
    <RouterLink to="/">Logo</RouterLink>

    <ul id="headernav">
      <li><RouterLink to="/perfume">Perfume</RouterLink></li>
      <li><RouterLink to="/diffuser">Diffuser</RouterLink></li>
      <li><RouterLink to="/candle">Candle</RouterLink></li>
    </ul>
    <ul>
      <li><RouterLink to="/search">검색</RouterLink></li>
    </ul>
  </section>
</template>

<style scoped>
#header {
  width: 100vw;
  height: 135px;
  display: flex;
  justify-content: space-between;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(255, 255, 255, 0.5);
}
#headernav {
  display: flex;
  justify-content: space-between;
}
</style>
