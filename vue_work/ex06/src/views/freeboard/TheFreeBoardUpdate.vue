<template>
  <div>
    <h1 class="h1-red">FreeBoardUpdate</h1>
    <div class="p-5">
      <!-- title:html   {{ title }}:변수명 -->
      title = {{ title }} content = {{ content }}
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
        수정
      </button>
    </div>
  </div>
</template>

<script setup>
import { getFreeBoardView, saveFreeboard } from '@/api/freeboardApi'
import { ref, watchEffect } from 'vue'
import { useRouter, useRoute } from 'vue-router'
// import { GLOBAL_URL } from '@/api/util'

const title = ref('')
const content = ref('')
const regdate = ref('')
const creAuthor = ref('')
const idx = ref(0)
const router = useRouter()
const route = useRoute()
const myfile = ref(null)

const onFileChange = (e) => {
  myfile.value = e.target.files[0]
}

// console.log(route.query);
watchEffect(async () => {
  const res = await getFreeBoardView(route.query.idx)
  if (res.status == 200) {
    title.value = res.data.title
    content.value = res.data.content
    regdate.value = res.data.regdate
    creAuthor.value = res.data.creAuthor
    idx.value = res.data.idx
  } else {
    alert(res.response.data.message)
    router.push({ name: 'freeboardlist' })
  }
})

const save = async () => {
  const data = {
    // 1번 글을 수정하겠다는 설정
    idx: route.query.idx,
    title: title.value,
    content: content.value
  }

  const formData = new FormData()
  formData.append('data', new Blob([JSON.stringify(data)], { type: 'application/json' }))
  formData.append('file', myfile.value)
  const res = await saveFreeboard(formData)
  if (res.status == 200) router.push({ name: 'freeboardlist' })
}
</script>

<style scoped>
.h1 {
  color: chocolate;
  margin: 10px 0 10px 30px;
  font-weight: 700;
}
</style>
