<template>
  <div>
    <h1 class="h1-red">TheFileUpload</h1>
    <div class="p-5">
      <input type="file" name="" id="" @change="onFileChange" />
    </div>
    <div class="p-5">
      <button
        @click="save"
        type="button"
        class="px-4 py-2 bg-yellow-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-300"
      >
        전송
      </button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'

const myfile = ref(null)

const save = () => {
  if (!myfile.value) {
    alert('파일을 선택하세요')
    return
  }

  const formData = new FormData()
  formData.append('file', myfile.value) // 'file'이 key여야 함
  formData.append(
    'fileDto',
    new Blob([JSON.stringify({ name: 'filename', desc: 'aValue' })], { type: 'application/json' })
  )

  axios
    .post('http://localhost:10000/file/upload', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
    .then((res) => {
      console.log(res)
    })
    .catch((e) => {
      console.log(e)
    })
}

// (e)는 이미 설정된 이벤트 객체이다.
const onFileChange = (e) => {
  myfile.value = e.target.files[0]
}
</script>
<style lang="scss" scoped></style>
