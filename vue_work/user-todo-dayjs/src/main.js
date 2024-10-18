import './assets/main.css';

import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';

const app = createApp(App);

app.use(createPinia());
app.use(router);

window.Kakao.init('bcef26e55e7a978acbbbcc598faf1886'); // 사용하려는 앱의 JavaScript 키 입력

app.mount('#app');
