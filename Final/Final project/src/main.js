import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

Kakao.init('9cd88a6b8990b47b53460dfbe08f9639') // 사용하려는 앱의 JavaScript 키 입력

const app = createApp(App)
const pinia = createPinia()

app.use(pinia)
app.use(router)

app.mount('#app')
