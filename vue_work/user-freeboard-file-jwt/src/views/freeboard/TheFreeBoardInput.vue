<template>
  <div>
    <h1 class="h1-red">FreeBoardInput</h1>
    <div class="p-5">
      <!-- title:html   {{ title }}:변수명 -->
      <!-- title = {{ title }} content = {{ content }} -->
      <input
        type="text"
        v-model="title"
        placeholder="Enter your title here"
        class="m-4 w-11/12 p-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent text-gray-700 placeholder-gray-400 bg-white"
      />
      <textarea
        v-model="content"
        class="m-4 w-11/12 h-40 p-4 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-transparent resize-none text-gray-700 placeholder-gray-400 bg-white"
        placeholder="Enter Content here"
      ></textarea>
      <div class="my-3">
        <input type="file" name="" id="" @change="onFileChange" />
      </div>
      <button
        @click="save"
        class="px-4 py-2 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-300"
      >
        저장
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { saveFreeboard } from '@/api/freeboardApi'

const title = ref('')
const content = ref('')
const myfile = ref(null)
const router = useRouter()

const onFileChange = (e) => {
  myfile.value = e.target.files[0]
}

const save = async () => {
  const data = {
    title: title.value,
    content: content.value
  }

  const formData = new FormData()
  formData.append('data', new Blob([JSON.stringify(data)], { type: 'application/json' }))
  formData.append('file', myfile.value)

  const res = await saveFreeboard(formData)
  if(res.status==200){
    alert('저장하였습니다.')
    router.push({name:'freeboardlist'})
    return;
  }
  alert('에러'+res.respons.data.message);

  // axios
  //   .post('http://localhost:10000/freeboard', formData, {
  //     headers: {
  //       'Content-Type': 'multipart/form-data'
  //     }
  //   })
  //   .then((res) => {
  //     // console.log(res)
  //     alert('저장하였습니다.')
  //     router.push({ name: 'freeboardlist', params: { aa: 10, bb: '안녕하세요' } })
  //   })
  //   .catch((e) => {
  //     // console.log(e)
  //     alert('에러' + e.response.data.message)
  //   })
}
</script>

<style scoped>
.h1 {
  color: chocolate;
  margin: 10px 0 10px 30px;
  font-weight: 700;
}
</style>
