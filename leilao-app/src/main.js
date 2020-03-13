import Vue from 'vue'
import Vuetify from 'vuetify'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios';
import VueAxios from 'vue-axios'
// import 'material-design-icons-iconfont/dist/material-design-icons.css' // Ensure you are using css-loader

// import Http from './Http'

Vue.config.productionTip = false

Vue.use(Vuetify)
// Vue.use(Vuetify, {
//     iconfont: 'md'
// })
Vue.use(VueAxios, axios)
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
