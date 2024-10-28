<script setup>
import dayjs from 'dayjs';
import { ref, watch, watchEffect } from 'vue';
// import utc from 'dayjs/plugin/utc';
// import timezone from 'dayjs/plugin/timezone';
import { saveTodo, getTodos } from '@/api/monthApi';

// dayjs.extend(utc);
// dayjs.extend(timezone);

const isDisabled = ref(false);

const now = ref(dayjs());
const columns = ref([]);
const groupColumns = ref([]);

// selectDate 가 값이 null일때는 false 값이 date 로 바뀌면 true;
const selectDate = ref(null);
const title = ref('');
const content = ref('');
const todos = ref([]);
const toast = ref(false);

const setDate = e => {
  selectDate.value = e.target.value;
};

const doSave = async () => {
  isDisabled.value = true;
  await saveTodo(title.value, content.value, selectDate.value);
  await doGet();

  title.value = '';
  content.value = '';
  toast.value = true;

  setTimeout(() => {
    toast.value = false;
    isDisabled.value = false;
  }, 3000);
};

const doGet = async () => {
  const res = await getTodos();
  if (res.status == '200') {
    const newData = res.data;
    if (JSON.stringify(todos.value) !== JSON.stringify(newData))
      todos.value = res.data;
  }
};

const subMonth = () => {
  now.value = dayjs(now.value).subtract(1, 'month');
  // console.log(now.value.format('YYYY-MM-DD'));
};

const subYear = () => {
  now.value = dayjs(now.value).subtract(1, 'year');
  // console.log(now.value.format('YYYY-MM-DD'));
};
const addYear = () => {
  now.value = dayjs(now.value).add(1, 'year');
  // console.log(now.value.format('YYYY-MM-DD'));
};
const addMonth = () => {
  now.value = dayjs(now.value).add(1, 'month');
  // console.log(now.value.format('YYYY-MM-DD'));
};

const selectDateFn = date => {
  console.log('dateClick', dayjs(date).format('YYYY-MM-DD'));
  selectDate.value = dayjs(date).format('YYYY-MM-DD');
};

const dateClick = date => {
  selectDate.value = dayjs(date).format('YYYY-MM-DD');
  // console.log('dateClick', dayjs(date).format('YYYY-MM-DD'));
};
//처음 로딩할떄는 now로 현재달력을 보여준다.
watch(
  [now, todos],
  async () => {
    await doGet();
    columns.value = []; // 원래 있던 값 제거
    groupColumns.value = []; // 원래 있던 값 제거
    // 제일 처음 로딩 할때는 now는 현재 달력...
    const startday = dayjs(now.value).startOf('month');
    const lastday = dayjs(now.value).endOf('month');
    const startdayOfWeek = startday.get('day');
    const lastdayOfWeek = lastday.get('day');

    // 저번달에 날짜 추가
    for (let i = 1; i <= startdayOfWeek; i++) {
      columns.value.unshift(dayjs(startday).subtract(i, 'day'));
    }
    // 현재 달력에 날짜 추가
    for (let i = 0; i < lastday.get('date'); i++) {
      columns.value.push(dayjs(startday).add(i, 'day'));
    }
    // 다음달에 날짜 추가
    for (let i = 1; i <= 6 - lastdayOfWeek; i++) {
      columns.value.push(dayjs(lastday).add(i, 'day'));
    }

    groupColumns.value.push(columns.value.slice(0, 7));
    groupColumns.value.push(columns.value.slice(7, 14));
    groupColumns.value.push(columns.value.slice(14, 21));
    groupColumns.value.push(columns.value.slice(21, 28));
    groupColumns.value.push(columns.value.slice(28, 35));
  },
  {
    immediate: true, // 현재페이지 처음 로딩 될때 도 실행
    deep: true, // 안에 값이 객체이면 객체 안에 변수도 변경 될때 watch안에 있는 함수 실행
  },
);

// watchEffect(async () => {
//   await doGet();
// });
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
          @click="selectDateFn(column)"
          v-for="(column, index) in group"
          :key="column.format('YYYY-MM-DD')"
          class="text-center p-8"
          :class="{
            'text-red-600': index % 7 == 0,
            'text-blue-800': index % 7 == 6,
            'opacity-20': !column.isSame(now, 'month'),
          }"
        >
          <span class="cursor-pointer">
            {{ column.get('date') }}
            <template v-for="todo in todos" :key="todo">
              <div
                class="rounded"
                :class="{
                  'bg-red-500': todo.completed == '0',
                  'bg-blue-500': todo.completed == '1',
                }"
                v-if="todo.selectDate === column.format('YYYY-MM-DD')"
              >
                {{ todo.title }}
              </div>
            </template>
          </span>
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
            v-model="title"
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
            v-model="content"
            id="description"
            rows="4"
            placeholder="상세 설명을 입력하세요"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          ></textarea>
        </div>

        <div class="mb-6">
          <label
            for="due-date"
            class="block text-gray-700 text-sm font-bold mb-2"
            >마감일</label
          >
          <input
            @change="selectDate"
            v-model="selectDate"
            type="date"
            id="due-date"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </div>

        <div class="flex items-center justify-center">
          <button
            :disabled="isDisabled"
            type="submit"
            class="bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
          >
            등록하기
          </button>
        </div>
      </form>
    </div>
  </div>
  <template v-if="toast">
    <div class="toast">등록하였습니다.</div>
  </template>
</template>

<style></style>
