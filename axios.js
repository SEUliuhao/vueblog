import axios from 'axios'
import Element from 'element-ui'
import router from './router'
import store from './store'


axios.defaults.baseURL='http://localhost:8081'

axios.interceptors.request.use(config =>{

    return config
})

axios.interceptors.response.use(response => {
        const res = response.data;

        console.log("=================")
        console.log(res)
        console.log("=================")

        if (res.code == 200) {
            return response
        } else {

            Element.Message({
                message: response.data.msg,
                type: 'error',
                duration: 2 * 1000
            })
            return Promise.reject(response.data.msg)
        }
    },
    error => {
        console.log('err'+error)
        if(error.response.data) {
            error.message = error.response.data.msg
        }

        if(error.response.status === 401) {
            store.commit("REMOVE_INFO")
            router.push({
                path: '/login'
            });
            error.message = '请重新登录';
        }
        if(error.response.status === 403){
            error.message = '权限不足，无法访问';
        }
        Element.Message({
                message: error.message,
                type: 'error',
                duration: 3*1000
            })
        return Promise.reject(error)
    }
)