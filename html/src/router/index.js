import VueRouter from 'vue-router'


const Home = () => import('@/components/Home')
const Login = () => import('@/components/Login')
const Register = () => import('@/components/Register')
const QuestionDetail = () => import('@/components/question/QuestionDetail')
const Submission = () => import('@/components/question/Submission')
const User = () => import('@/components/user/User')
const UserInfo = () => import('@/components/user/UserInfo')
const QuestionAdd = () => import('@/components/question/QuestionAdd')

const router = new VueRouter({
    routes: [{
        path: '/',
        component: Home,
        meta:{power:'noLogin',title:'首页'},
        children: [],
    },{
        path: '/login',
        component: Login,
        meta:{power:'noLogin',title:'用户登录'},
        children: [],
    },{
        path: '/register',
        component: Register,
        meta:{power:'noLogin',title:'用户注册'},
        children: [],
    },{
        path: '/question-detail/:id',
        component: QuestionDetail,
        meta:{power:'noLogin',title:'题目详情'},
        children: [],
    },{
        path: '/submit',
        component: Submission,
        meta:{power:'noLogin',title:'提交列表'},
        children: [],
    },{
        path: '/user',
        component: User,
        children: [{
            path: 'userInfo',
            component: UserInfo,
            meta:{power:'noLogin',title:'个人信息'},
            children: [],
        },{
            path: 'addQuestion',
            component: QuestionAdd,
            meta:{power:'noLogin',title:'添加题目'},
            children: [],
        }],
    }]
})


router.beforeEach((to, from, next) => {
    // console.log(to.meta);
    document.title = to.meta.title;
    to.params.from = from.fullPath;
    next();
})

/* 放在跳转到当前页面相同的路由报错 */
const VueRouterPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
export default router