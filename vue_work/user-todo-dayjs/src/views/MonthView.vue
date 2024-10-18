<script setup>
import dayjs from 'dayjs';
import { ref, watch } from 'vue';
import utc from 'dayjs/plugin/utc';
import timezone from 'dayjs/plugin/timezone';

dayjs.extend(utc);
dayjs.extend(timezone);

const now = ref(dayjs());
const groupColumns = ref([]);
const selectDate = ref(null);

const subMonth = () => {
  now.value = dayjs(now.value).subtract(1, 'month');
  console.log(now.value.format('YYYY-MM-DD'));
};
const subYear = () => {
  now.value = dayjs(now.value).subtract(1, 'year');
  console.log(now.value.format('YYYY-MM-DD'));
};
const addYear = () => {
  now.value = dayjs(now.value).add(1, 'year');
  console.log(now.value.format('YYYY-MM-DD'));
};
const addMonth = () => {
  now.value = dayjs(now.value).add(1, 'month');
  console.log(now.value.format('YYYY-MM-DD'));
};

const dateClick = date => {
  console.log('dateClick', dayjs(date).format('YYYY-MM-DD'));
  selectDate.value = dayjs(date).format('YYYY-MM-DD');
};
//처음 로딩할떄는 now로 현재달력을 보여준다.
watch(
  now,
  newValue => {
    groupColumns.value = [];
    const columns = ref([]);
    const startday = dayjs(now.value).startOf('month');
    const lastday = dayjs(now.value).endOf('month');
    const startdayofweek = startday.get('day');
    const lastdayofweek = lastday.get('day');

    // 이전달의 일부 날짜 추가
    for (let i = 1; i <= startdayofweek; i++) {
      columns.value.unshift(dayjs(startday).subtract(i, 'day'));
    }
    //  현재 달력 날짜
    for (let i = 0; i < lastday.get('date'); i++) {
      columns.value.push(dayjs(startday).add(i, 'day'));
    }
    // 다음달의 일부 날짜 추가
    for (let i = 1; i <= 6 - lastdayofweek; i++) {
      columns.value.push(dayjs(lastday).add(i, 'day'));
    }
    groupColumns.value.push(columns.value.slice(0, 7));
    groupColumns.value.push(columns.value.slice(7, 14));
    groupColumns.value.push(columns.value.slice(14, 21));
    groupColumns.value.push(columns.value.slice(21, 28));
    groupColumns.value.push(columns.value.slice(28, 35));
  },
  {
    immediate: true, //현재페이지 처음 로딩 될때도 실행
    deep: true, //안에 값이 객체이면 객체 안에 변수도 변경 될때 watch안에 있는 함수 실행
  },
);
</script>

<template>
  <main>
    <div>
      <h1 class="text-7xl p-2 pt-20 text-yellow-500 text-center p-5">
        hello vue😢
      </h1>
      <div class="text-center text-3xl p-1">
        <button @click="subYear()" class="pe-10 text-orange-600">
          &lt;&lt;
        </button>
        <button @click="subMonth()" class="pe-3 text-orange-600">&lt;</button>
        {{ now.format('YYYY. MM') }}
        <button @click="addMonth()" class="pl-3 text-orange-600">&gt;</button>
        <button @click="addYear()" class="pl-10 text-orange-600">
          &gt;&gt;
        </button>
      </div>
    </div>
    <div class="p-9">
      <div class="grid grid-cols-7 gap-2">
        <div
          class="text-center p-2 border-b-2 border-b-yellow-900 py-3 text-2xl"
        >
          일
        </div>
        <div
          class="text-center p-2 border-b-2 border-b-yellow-900 py-3 text-2xl"
        >
          월
        </div>
        <div
          class="text-center p-2 border-b-2 border-b-yellow-900 py-3 text-2xl"
        >
          화
        </div>
        <div
          class="text-center p-2 border-b-2 border-b-yellow-900 py-3 text-2xl"
        >
          수
        </div>
        <div
          class="text-center p-2 border-b-2 border-b-yellow-900 py-3 text-2xl"
        >
          목
        </div>
        <div
          class="text-center p-2 border-b-2 border-b-yellow-900 py-3 text-2xl"
        >
          금
        </div>
        <div
          class="text-center p-2 border-b-2 border-b-yellow-900 py-3 text-2xl"
        >
          토
        </div>
      </div>
      <div
        class="grid grid-cols-7 gap-2"
        v-for="group in groupColumns"
        :key="group.length"
      >
        <div
          v-for="(column, index) in group"
          :key="column.format('YYYY-MM-DD')"
          class="text-center p-8"
          :class="{
            'text-red-600': index % 7 == 0,
            'text-blue-800': index % 7 == 6,
            'opacity-20': !column.isSame(now, 'month'),
          }"
        >
          <span
            @click="dateClick(column)"
            class="cursor-pointer p-5 hover:border"
            >{{ column.get('date') }}</span
          >
          <div class="bg-gray-900 w-10"></div>
        </div>
      </div>
      <!-- <div
        v-for="column in columns"
        :key="column.format('YYYY-MM-DD')"
        class=""
      >
        {{ column.get('date') }}
      </div> -->
    </div>
  </main>
  <div :class="{ hidden: !selectDate }" class="p-10 flex justify-center">
    <div class="bg-white shadow-lg rounded-lg p-6 max-w-lg w-full">
      <h2 class="text-2xl font-bold text-center mb-4">일정</h2>

      <form @submit.prevent="doSave">
        <div class="mb-4">
          <label for="task" class="block text-gray-700 text-sm font-bold mb-2"
            >할일 제목</label
          >
          <input
            type="text"
            id="task"
            placeholder="할일 제목을 입력하세요"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            required
          />
        </div>

        <div class="mb-4">
          <label
            for="description"
            class="block text-gray-700 text-sm font-bold mb-2"
            >상세 설명</label
          >
          <textarea
            id="description"
            rows="4"
            placeholder="상세 설명을 입력하세요"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          ></textarea>
        </div>

        <div class="mb-6">
          <input
            :value="selectDate"
            type="date"
            id="due-date"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </div>

        <div class="flex items-center justify-center">
          <button
            type="submit"
            class="bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
          >
            등록하기
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<style></style>
