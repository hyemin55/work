<template>
  <div class="overlay" :class="{ isModal: isModal }"></div>
  <div class="modal p-5 rounded" :class="{ isView: isModal }">
    <h1 class="text-5x1">USER 수정</h1>
    <div class="p-5 w-80 bg-yellow-100 cursor-pointer m-5 rounded-xl">
      <h1>idx ={{ idx }}</h1>
      <h1>
        name =<input
          class="border border-yellow-500 rounded-lg p-1 focus:outline-none focus:ring-1 focus:ring-orange-500 focus:border-orange-500"
          type="text"
          v-model="name"
        />
      </h1>
      <h1>email ={{ email }}</h1>
      <h1>가입날짜 ={{ wdate }}</h1>
    </div>
    <div class="flex justify-center space-x-5">
      <button
        @click="modalUser"
        class="px-4 py-2 bg-yellow-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-300"
      >
        취소
      </button>
      <button
        @click="modalUser('save')"
        class="px-4 py-2 bg-yellow-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-300"
      >
        저장
      </button>
    </div>
  </div>
  <div class="pb-10">
    <h1 class="h1-navy">UserList</h1>
    <div class="my-flex">
      <div
        @click="modalUser(item)"
        v-for="item in arr"
        :key="item.idx"
        class="p-5 w-80 bg-yellow-100 cursor-pointer m-5 rounded-xl"
      >
        <h1>idx = {{ item.idx }}</h1>
        <h1>name = {{ item.name }}</h1>
        <h1>email = {{ item.email }}</h1>
        <h1>가입날짜 = {{ item.wdate }}</h1>
        <h1>작성한글 = {{ item.list.length }}</h1>
        <div class="flex justify-center ">
          <button
            class="px-4 py-2 bg-yellow-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-300"
            @click.stop="modalUser(item)"
          >
            수정
          </button>
          <button
            @click.stop="doDelete(item.idx)"
            class="px-4 py-2 bg-yellow-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-300"
          >
            삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { deleteUser, getUsers, saveUser } from '@/api/userApi'
import { ref, watchEffect } from 'vue'

const arr = ref([])

const idx = ref()
const name = ref()
const wdate = ref()
const email = ref()
const isModal = ref(false)

const doDelete = async (idx) => {
  await deleteUser(idx)
  const retValue = await getUsers()
  arr.value = retValue.data
}

const modalUser = async (item) => {
  isModal.value = !isModal.value
  if (item == 'save') {
    await saveUser({
      idx: idx.value,
      name: name.value,
      email: email.value,
      password: '마이패스워드'
    })
    // update를 해야함..
    alert('수정하였습니다.')
    const retValue = await getUsers()
    arr.value = retValue.data
    return
  }
  idx.value = item.idx
  name.value = item.name
  wdate.value = item.wdate
  email.value = item.email
}

//async(비동기함수, 시간이 걸리는걸 알려준다.),
// await 는 기다려라. 시간이 필요할 때 사용한다.
// 호출하지 않아도 함수 자동 실행
watchEffect(async () => {
  const retValue = await getUsers()
  // console.log("retValue"+JSON.stringify(retValue.data));
  arr.value = retValue.data
  // console.log(arr.value)
})
</script>

<style scoped>
.h1-navy {
  font-size: 3rem;
  color: navy;
}
.my-flex {
  display: flex;
  /* 요소들의 총 넓이가 부모넓이보다 크면 줄바꿈 */
  flex-wrap: wrap;
}
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1000;
  background-color: rgb(0, 0, 0, 0.5);
  display: none;
}
.isModal {
  display: block;
}
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 300px;
  background-color: white;
  z-index: 1001;
  display: none;
}
.isView {
  display: block;
}
</style>
