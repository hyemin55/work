import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'


import App from './App.vue'
import router from './router'

Kakao.init('a74d8c37f265d73b45045ad6a81d7f87'); // 사용하려는 앱의 JavaScript 키 입력

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
