import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

import VueRouter from 'vue-router'
import router from './router/index.js'
Vue.use(VueRouter)

import axios from 'axios'
axios.defaults.baseURL = "http://127.0.0.1:8080"
axios.defaults.timeout = 6000
Vue.prototype.$axios = axios

import ElementUI from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
Vue.use(ElementUI)

import store from './store/index.js'

new Vue({
  render: h => h(App),
  router: router,
  store: store,
}).$mount('#app')
