<template>
  <article>
    <div class="chartBox">
      <ul class="handleStandardButton">
        <li @click="handleStandard('day')" :class="{ active: standard === 'day' }">일별</li>
        <p class="slash">|</p>
        <li @click="handleStandard('month')" :class="{ active: standard === 'month' }">주별</li>
        <p class="slash">|</p>
        <li @click="handleStandard('year')" :class="{ active: standard === 'year' }">연별</li>
      </ul>
    </div>
    <canvas id="stackedBarChart2"></canvas>
  </article>
</template>

<script setup>
import { getNetProfitStatisticsData } from '@/api/AdministratorModeApi';
import { ref, watch } from 'vue';
import { Chart, BarElement, CategoryScale, LinearScale, Title, Tooltip, Legend } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';

const netProfitData = ref([]);
const standard = ref('day');
const chartInstance = ref(null);
const chartData = ref({ labels: [], datasets: [] });

// Chart.js 등록
Chart.register(BarElement, CategoryScale, LinearScale, Title, Tooltip, Legend, ChartDataLabels);

const dolode = async () => {
  const netProfitStatisticsDataRes = await getNetProfitStatisticsData(standard.value);
  netProfitData.value = netProfitStatisticsDataRes;

  // 중복되지 않는 날짜 리스트 생성
  const labels = [...new Set(netProfitData.value.map(item => item.saleDate))];

  // 카테고리별 데이터 생성
  const categoryNames = [...new Set(netProfitData.value.map(item => item.categoryName))];
  const datasets = categoryNames.map(category => {
    return {
      label: category,
      data: labels.map(date =>
        netProfitData.value
          .filter(item => item.saleDate === date && item.categoryName === category)
          .reduce((sum, item) => sum + item.categoryNetProfit, 0),
      ),
      backgroundColor: getColor(category),
    };
  });

  chartData.value = { labels, datasets };
};

const renderChart = () => {
  if (chartInstance.value) {
    chartInstance.value.destroy();
    chartInstance.value = null;
  }

  const ctx = document.getElementById('stackedBarChart2').getContext('2d'); // getContext 추가
  chartInstance.value = new Chart(ctx, {
    type: 'bar',
    data: chartData.value,
    options: {
      responsive: true,
      plugins: {
        legend: { position: 'top', labels: { font: { size: 14 }, color: '#333' } },
        title: { display: true, text: '총 순수익', font: { size: 20 }, color: '#333' },
        datalabels: {
          anchor: 'center',
          align: 'center',
          color: '#444',
          font: { size: 12 },
          formatter: value => {
            return value > 0 ? `₩${value.toLocaleString()}` : '';
          }, // 데이터 레이블 표시 형식
        },
        tooltip: {
          backgroundColor: '#fff',
          titleColor: '#333',
          bodyColor: '#555',
          borderColor: '#ddd',
          borderWidth: 1,
          callbacks: { label: ctx => `₩${ctx.raw.toLocaleString()}` },
        },
      },
      scales: {
        x: {
          stacked: true,
          title: { display: true, text: '거래 날짜', font: { size: 14 }, color: '#666' },
          grid: { color: '#eaeaea' },
        },
        y: {
          stacked: true,
          title: { display: true, text: '순수익 (₩)', font: { size: 14 }, color: '#666' },
          grid: { color: '#eaeaea' },
        },
      },
    },
  });
};

// 랜덤 색상 생성 함수
const getColor = category => {
  const colorName = [
    { name: 'perfume', value: 'rgb(81, 150, 241)' },
    { name: 'Candle', value: 'rgb(241, 81, 81)' },
    { name: 'Diffuser', value: 'rgb(241, 214, 81)' },
  ];
  const colorObject = colorName.find(item => item.name.toLowerCase() === category.toLowerCase());
  return colorObject ? colorObject.value : 'rgb(241, 81, 177)';
};

const handleStandard = selectStandard => {
  standard.value = selectStandard;
  dolode();
};

// 데이터 변경 시 차트 업데이트
watch(chartData, renderChart);

dolode();
</script>

<style scoped>
* {
  font-size: 1.5rem;
}
.chartBox {
  margin-bottom: 1rem;
  display: flex;
  justify-content: flex-end;
}
.handleStandardButton {
  height: 40px;
  font-size: 1.4rem;
  display: flex;
  align-items: center;
  background-color: #f9f9f9;
  border-radius: 8px;
  padding: 5px 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.handleStandardButton li,
.slash {
  text-align: center;
  padding: 8px 16px;
  margin: 0 5px;
}
.handleStandardButton li {
  color: #555;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
}
.handleStandardButton li:hover {
  color: #000;
  text-decoration: underline;
  text-underline-position: under;
}
.active {
  color: #fff !important;
  background-color: #8f9d8d;
  border-radius: 4px;
  font-weight: bold;
}
#stackedBarChart2 {
  background-color: #fff;
  border-radius: 8px;
  padding: 1%;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>
