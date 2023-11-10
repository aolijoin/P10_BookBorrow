import Vue from 'vue'
import App from './App.vue'
import router from './router'

// import element
//导入组件库
import ElementUI from 'element-ui'
//导入组件相关样式
import 'element-ui/lib/theme-chalk/index.css'
//配置Vue插件 将El安装到Vue上
Vue.use(ElementUI);

Vue.config.productionTip = false

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
