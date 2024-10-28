<template>
  <figure id="salseChart">
    <h1>시세</h1>
    <div class="chartCycle">
      <p>1개월</p>
      <p>6개월</p>
      <p>전체</p>
    </div>
    <figcaption>차트상자</figcaption>
    <h2 class="TransactionHistory">체결 거래</h2>
    <div class="TransactionHistoryPosition">
      <ul class="TransactionHistoryTitle">
        <li>옵션</li>
        <li>가격</li>
        <li>거래일</li>
      </ul>
      <ul class="TransactionHistoryContent">
        <li>30ml</li>
        <li>￦ 500,000</li>
        <li>2024-10-28</li>
        <li>30ml</li>
        <li>￦ 500,000</li>
        <li>2024-10-28</li>
        <li>30ml</li>
        <li>￦ 500,000</li>
        <li>2024-10-28</li>
      </ul>
    </div>
  </figure>
</template>

<script setup></script>

<style scoped>
#salseChart {
}
#salseChart h1 {
  font-size: 2rem;
  margin: 10px 0;
}
.chartCycle {
  display: flex;
  justify-content: space-between;
  width: 100%;
  /* height: 30px; */
  gap: 2%;
}
.chartCycle p:hover {
  background-color: var(--color-main-bloode);
  color: white;
  border: none;
}

.chartCycle p {
  width: 33%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 0.5px solid var(--color-main-gray);
  border-radius: 7px;
  font-size: 1.4rem;
  padding: 5px 0;
  cursor: pointer;
}
figcaption {
  height: 250px;
  margin: 10px 0;
  background-color: rgb(245, 206, 206);
}
.TransactionHistory {
  margin: 10px 0 0 10px;
  font-size: 1.6rem;
}
.TransactionHistoryPosition {
  margin: 15px 0;
  /* background-color: blueviolet; */
}
.TransactionHistoryTitle {
  position: relative;
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  /* background-color: pink; */
  margin: 10px 0 15px 0;
  padding: 0 3%;
  font-size: 1.4rem;
}
.TransactionHistoryTitle::after {
  position: absolute;
  content: '';
  border: 0.1px dashed var(--color-main-gray);
  width: 100%;
  bottom: -10px;
}
.TransactionHistoryContent {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  padding: 0 3%;
  font-size: 1.4rem;
}
.TransactionHistoryContent li {
  margin: 5px 0;
  background-color: aquamarine;
}
</style>
