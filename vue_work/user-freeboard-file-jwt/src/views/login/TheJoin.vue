<template>
  <div class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-md w-96">
      <h2 class="text-2xl font-bold mb-6 text-center">회원가입</h2>

      <form @submit="doSubmit">
        <div class="mb-4">
          <label for="username" class="block text-sm font-medium text-gray-700">사용자 이름</label>
          <input
            type="text"
            id="username"
            name="username"
            value="장길동"
            required
            class="mt-1 p-2 border border-gray-300 rounded w-full"
            placeholder="사용자 이름을 입력하세요"
          />
        </div>
        <div class="mb-4">
          <label for="email" class="block text-sm font-medium text-gray-700">이메일</label>
          <input
            type="email"
            id="email"
            name="email"
            value="aaa@naver.com"
            required
            class="mt-1 p-2 border border-gray-300 rounded w-full"
            placeholder="이메일을 입력하세요"
          />
        </div>
        <div class="mb-4">
          <label for="password" class="block text-sm font-medium text-gray-700">비밀번호</label>
          <input
            type="password"
            id="password"
            name="password"
            value="123456"
            required
            class="mt-1 p-2 border border-gray-300 rounded w-full"
            placeholder="비밀번호를 입력하세요"
          />
        </div>
        <div class="mb-4">
          <label for="confirm-password" class="block text-sm font-medium text-gray-700"
            >비밀번호 확인</label
          >
          <input
            type="password"
            id="confirm-password"
            name="confirm-password"
            value="123456"
            required
            class="mt-1 p-2 border border-gray-300 rounded w-full"
            placeholder="비밀번호를 다시 입력하세요"
          />
        </div>
        <button type="submin" class="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700">
          회원가입
        </button>
      </form>

      <p class="mt-4 text-center text-sm text-gray-600">
        이미 계정이 있으신가요? <a href="#" class="text-blue-600 hover:underline">로그인</a>
      </p>
    </div>
  </div>
</template>

<script setup>
import { doJoin } from '@/api/loginApi';

const doSubmit = async(event) => {
    // event.stopPropagation(); //이벤트 전파 막기
    event.preventDefault(); //기본적으로 새로고침 막기
    // const res = console.log("연결됐나?"+event);
    const res = await doJoin({
        "name": "장길동",
        "password": "1234",
        "age": "20",
        "email": "aaa@naver.com"
    }); // join API 호출
    console.log(res);
}
</script>
<style lang="scss" scoped></style>
