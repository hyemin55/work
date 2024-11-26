<script setup>
import { GLOBAL_URL } from '@/api/util';
import axios, { HttpStatusCode } from 'axios';
import { ref, watchEffect, nextTick } from 'vue';
import { useRoute } from 'vue-router';
import Chart from 'chart.js/auto';

const route = useRoute();
const idx = ref(0);
const totalSalseList = ref([]);
const displayedList = ref([]);
const chartRef = ref(null); // chart element 참조
let chartInstance = null;
const showMore = ref(5);
const size = ref(route.query.size);
let sortTotalSaleTradeList = [];
// 데이터 로드 함수
const doLode = async () => {
  try {
    const response = await axios.get(`${GLOBAL_URL}/detail/chart/${idx.value}`);
    console.log(response);
    totalSalseList.value = response.data;
    console.log(totalSalseList.value);
    // console.log(response.data[0].tradeCompletedDate);
    if (totalSalseList.value.length > 0) {
      displayedList.value = totalSalseList.value.slice(0, showMore.value);

      // Call by Reference vs call by value
      //  sortTotalSaleTradeList.reverse();를 하면 totalSalseList과 같은 곳을 바라보기때문에
      // JSON.stringify로 문자열로 바꾼뒤 다시 JSON.parse로 객채를 만들어 sortTotalSaleTradeList가 다른곳을 바라보게 만든다.
      sortTotalSaleTradeList = JSON.parse(JSON.stringify(totalSalseList.value));
      sortTotalSaleTradeList.reverse();

      initializeChart();
    }
  } catch (error) {
    if (error.status === 404) {
      console.warn('No transaction history found.');
      return (totalSalseList.value.length = 0);
    } else {
      console.error('오류가 발생했습니다:', error);
    }
  }
};

// x축은 첫 거래일부터 현재까지의 날짜
const generateDateLabels = startDate => {
  const labels = [];
  let date = new Date(startDate);
  const today = new Date();

  // totalSalseList.value.forEach(item => {
  //   sortTotalSaleTradeList.push(item.tradeCompletedDate);
  // });
  // sortTotalSaleTradeList.sort();
  // sortTotalSaleTradeList.forEach(item => {
  //   console.log(item);
  // });
  sortTotalSaleTradeList.forEach(item => {
    labels.push(new Intl.DateTimeFormat('ko-KR', { month: 'numeric', day: 'numeric' }).format(Date.parse(item.tradeCompletedDate)));
  });

  // while (date <= today) {
  //   labels.push(new Intl.DateTimeFormat('ko-KR', { month: 'numeric', day: 'numeric' }).format(totalSalseList.value.tradeCompletedDate));
  //   date.setDate(date.getDate() + 30);
  // }
  return labels;
};

// 차트 초기화 함수
const initializeChart = async () => {
  await nextTick();
  // 기존 차트 인스턴스가 있으면 제거 (재설정)
  if (chartInstance) {
    chartInstance.destroy();
  }
  // 데이터가 존재할 때만 차트를 생성
  if (totalSalseList.value.length > 0) {
    const firstTradeDate = totalSalseList.value[totalSalseList.value.length - 1].tradeCompletedDate;
    const maxPrice = Math.ceil(Math.max(...totalSalseList.value.map(item => item.tradePrice)) * 1.1); // 최대 가격의 110%

    // console.log(totalSalseList.value);
    // console.log(
    //   'totalSalseList',
    //   sortTotalSaleTradeList.map(item => item.tradePrice),
    // );
    chartInstance = new Chart(chartRef.value, {
      type: 'line',
      data: {
        labels: generateDateLabels(firstTradeDate), // X축 라벨
        datasets: [
          {
            label: 'Sales Figures',
            data: sortTotalSaleTradeList.map(item => item.tradePrice),
            borderColor: 'orange',
            backgroundColor: 'orange',
            borderWidth: 1,
            pointRadius: 3,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            display: false,
            labels: {
              font: { size: 14 },
            },
          },
        },
        scales: {
          y: {
            beginAtZero: true,
            max: maxPrice,
            // grid: {
            //   color: 'rgba(200, 200, 200, 0.3)',
            // },
            title: {
              display: true,
              // text: 'Sales',
              text: '거래가격 (₩)',
            },
            ticks: {
              maxTicksLimit: 10, // X축 레이블을 4개로 제한하여 YY-MM 형식으로 표시
            },
          },
          x: {
            // grid: {
            //   color: 'rgba(200, 200, 200, 0.3)',
            // },
            title: {
              display: true,
              text: '거래날짜',
            },
            ticks: {
              maxTicksLimit: 5, // X축 레이블을 4개로 제한하여 YY-MM 형식으로 표시
            },
          },
        },
      },
    });
  }
};

const loadMore = () => {
  console.log(totalSalseList.value.length);
  if (totalSalseList.value.length > showMore.value) {
    showMore.value += 5;
    displayedList.value = totalSalseList.value.slice(0, showMore.value);
  }
};
const closeList = () => {
  showMore.value = 5;
  displayedList.value = totalSalseList.value.slice(0, showMore.value);
};
// onMounted(() => {
//   doLode();
// });

watchEffect(() => {
  idx.value = route.params.idx;
  doLode();
});
</script>

<template>
  <figure id="salseChart" v-if="totalSalseList.length > 0">
    <h1>시세</h1>
    <div class="chartCycle">
      <p>1개월</p>
      <p>6개월</p>
      <p class="chartCycleFix">전체</p>
    </div>
    <figcaption>
      <canvas ref="chartRef" style="height: 250px; width: 100%"></canvas>
    </figcaption>

    <div class="TransactionHistory">
      <h1>체결 내역</h1>
      <p>(최근거래순)</p>
    </div>
    <div class="TransactionHistoryPosition">
      <ul class="TransactionHistoryTitle">
        <li>옵션 ({{ size }} ml)</li>
        <li>거래가격</li>
        <li>거래날짜</li>
      </ul>
      <ul class="TransactionHistoryContent" v-for="(list, index) in displayedList" :key="index">
        <li>{{ list.size }} ml</li>
        <li>￦ {{ list.tradePrice.toLocaleString() }}</li>
        <li>{{ list.tradeCompletedDate }}</li>
      </ul>
      <div class="showButtonBox">
        <button v-if="showMore && totalSalseList.length > showMore" @click="loadMore" class="showButton">체결 내역 더보기 ▽</button>
        <button v-if="showMore && totalSalseList.length <= showMore" @click="closeList" class="showButton">닫기 △</button>
      </div>
    </div>
  </figure>
  <figure id="salseChart" v-else>
    <h1>시세</h1>
    <div class="chartCycle">
      <p>1개월</p>
      <p>6개월</p>
      <p class="chartCycleFix">전체</p>
    </div>
    <div class="elseChartImg">
      <img src="@/assets/img/chartImg.png" alt="" />
      <p class="overlay_text">거래 내역이 없어요</p>
      <!-- <canvas ref="chartRef" style="height: 250px; width: 100%"></canvas> -->
    </div>

    <div class="TransactionHistory">
      <h2>체결 내역</h2>
      <p>(최근거래순)</p>
    </div>
    <div class="TransactionHistoryPosition">
      <ul class="TransactionHistoryTitle">
        <li>옵션</li>
        <li>가격</li>
        <li>거래날짜</li>
      </ul>
      <ul class="NoTransactionHistory">
        <li>거래 내역이 없어요</li>
      </ul>
    </div>
  </figure>
</template>

<style scoped>
#salseChart {
  width: 100%;
  /* 스타일 수정 */
}
#salseChart > h1 {
  font-size: 1.7rem;
  margin: 40px 0 10px 0;
}
.chartCycle {
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 30px;
  gap: 3%;
}
.chartCycleFix {
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
  width: 100%;
  height: 250px;
  margin: 10px 0;
  border-radius: 5px;
  background-color: var(--color-main-pink);
  padding: 1%;
  /* background-color: rgb(245, 206, 206); */
}
.elseChartImg {
  width: 100%;
  height: 250px;
  margin: 10px 0;
  border-radius: 5px;
  background-color: var(--color-main-pink);
  padding: 1%;
  position: relative;
}
.elseChartImg > img {
  width: 100%;
  height: auto;
  filter: blur(3px);
}
.overlay_text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 1.5rem;
  /* background-color: var(--color-main-gray); */
  padding: 1%;
  border-radius: 10px;
}
.TransactionHistory {
  margin: 20px 0 -10px 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.TransactionHistory > h1 {
  font-size: 1.6rem;
}
.TransactionHistory > p {
  margin: 5px 7px 0 0;
  font-size: 1.2rem;
  color: var(--color-text-gray);
}
.TransactionHistoryPosition {
  margin: 15px 0;
  line-height: 30px;
  padding: 1%;
  text-align: center;
}
.TransactionHistoryContent::after {
  content: '';
  width: 300%;
  border: 0.5px dotted var(--color-main-gray);
  height: 1px;
  display: flex;
}
.TransactionHistoryTitle::after {
  /* position: absolute; */
  content: '';
  width: 300%;
  border: 0.5px solid var(--color-main-gray);
  height: 1px;
  /* width: var(--main-max-width); */
  display: flex;
  /* margin-top: -20px; */
}
.TransactionHistoryTitle,
.TransactionHistoryContent {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  /* padding: 0 1%; */
  font-size: 1.4rem;
  /* background-color: antiquewhite; */
}
.NoTransactionHistory {
  padding: 1%;
  font-size: 1.4rem;
}
.showButtonBox {
  position: relative;
  margin: 10px auto;
  background-color: white;
  width: 35%;
  height: 45px;
  border: 0.5px solid var(--color-main-gray);
  border-radius: 50px;
}
.showButton {
  position: static;
  font-size: 1.4rem;
  margin: 5px 0;
  padding: 1%;
  /* color: var(--color-text-gray); */
}
</style>
