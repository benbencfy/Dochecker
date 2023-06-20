import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

const init = () => {
  createApp(App).use(router).mount('#app');
};

if (process.env.NODE_ENV === 'development') {
  // 本地开发需要等待全局变量初始化
  setTimeout(() => {
    init();
  }, 2000);
} else {
  console.log('初始化app');
  init();
}
