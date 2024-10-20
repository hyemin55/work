import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import FirstChild from '@/components/FirstChild.vue'

const app = createApp(App)

app.use(router)

app.component('FirstChild', FirstChild)
app.mount('#app')
