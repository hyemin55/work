<template>
  <section id="statisticsLayout">
    <!-- 총매출 -->
    <article>
      <TotalSalesStatisticsView />

    </article>
    <!-- 순수익 -->
    <article>
      <NetProfitStatisticsView />
    </article>

  </section>
</template>

<script setup>
import NetProfitStatisticsView from '@/views/admin/statistics/NetProfitStatisticsView.vue';
import TotalSalesStatisticsView from '@/views/admin/statistics/TotalSalesStatisticsView.vue';
</script>

<style scoped>
#statisticsLayout{
  display: grid;
  grid-template-columns: 50% 50%;
  gap: 20px;
  padding: 2% 2% 10% 1%;
}
@media (max-width: 1000px) {
  #statisticsLayout{
  display: grid;
  grid-template-columns: 100%;
  padding: 2% 2% 10% 2%;
}
}
</style>
