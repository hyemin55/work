<script setup>
import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';
import timezone from 'dayjs/plugin/timezone';

dayjs.extend(utc);
dayjs.extend(timezone);

const date = new Date();
// console.log(date)

const now = dayjs();
// console.log(now)

// console.log(now.format('YYYY년MM월DD일 HH시mm분ss초'))
// console.log(now.format('YYYY-MM-DD HH:mm:ss'))
// console.log(now.format('MM-DD HH:mm:ss'))
// console.log(now.format('MM-DD'))
// console.log(now.format('DD'))

// console.log(dayjs().startOf('year').format('YYYY/MM/DD'))
// console.log(dayjs().startOf('month').format('YYYY/MM/DD'))
// console.log(dayjs().endOf('month').format('YYYY/MM/DD'))

// const firstday = dayjs().startOf('month')
// const lastday = dayjs().endOf('month')
// console.log(dayjs().day() + '일')
// console.log(firstday.day())
// console.log(lastday.day())

// 이전달
const beforeMonth = dayjs().subtract(1, 'month').format('YYYY/MM/DD');
// 이전달의 마지막날
const sepLastday = dayjs(beforeMonth).endOf('month');
// console.log("sepLastday : "+sepLastday.format('YYYY/MM/DD'))

// 10월 17일 -> 9월 17일
const afterMonth = dayjs().add(1, 'month').format('YYYY/MM/DD');
// 9월 마지막날
const novLastday = dayjs(afterMonth).endOf('month');
// console.log("sepLastday = "+novLastday.format('YYYY/MM/DD'));

// 이전달과 현재달을 비교
// console.log("이전달과 같은지? : "+dayjs().isSame(sepLastday,'month'))
// 11월달과 현재달의 달을 비교
// console.log("다음달과 같은지? : "+dayjs().isSame(novLastday,'month'));
// 현재달의 1일 월 isSame 현재달의 17일 월을 비교
// console.log(dayjs().isSame(dayjs().startOf('month'),'month'))
// 현재달의 31일 월 isSame 현재달의 17일 월을 비교
// console.log(dayjs().isSame(dayjs().endOf('month'),'month'))
</script>

<template>
  <main>
    <h1 class="text-7xl p-2 text-yellow-500">hello vue😒</h1>
    <div class="text 5xl font-bold p-2">
      <h1>오늘 : {{ now.format('YYYY/MM/DD') }}</h1>
      <br />
      <div>
        <h1>어제 : {{ now.subtract(1, 'day').format('YYYY/MM/DD') }}</h1>
        <h1>한달전 : {{ now.subtract(1, 'month').format('YYYY/MM/DD') }}</h1>
        <h1>일년전 : {{ now.subtract(1, 'year').format('YYYY/MM/DD') }}</h1>
      </div>
      <br />
      <div>
        <h1>내일 : {{ now.add(1, 'day').format('YYYY/MM/DD') }}</h1>
        <h1>한달후 : {{ now.add(1, 'month').format('YYYY/MM/DD') }}</h1>
        <h1>일년후 : {{ now.add(1, 'year').format('YYYY/MM/DD') }}</h1>
      </div>

      <br />
      <div>
        <h1 class="text-3xl">
          첫 날 {{ now.tz('Asia/Seoul').startOf('month').format('YYYY-MM-DD') }}
        </h1>
        <h1>첫날 : {{ now.startOf('month').format('YY/MM/DD') }}</h1>
        <h1>첫날 : {{ now.endOf('month').format('YY/MM/DD') }}</h1>
      </div>
    </div>
  </main>
</template>
