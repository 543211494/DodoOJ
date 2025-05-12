<template>
    <div class="container">
        <Header></Header>
        <div class="content">
            <div class="user-container">
                <div class="user-container-left">
                    <div class="user-container-left-img" >
                        <img :src="require('../../assets/avatar.jpg')"/>
                        <div>用户姓名</div>
                    </div>
                    <div class="line"></div>
                    <div class="user-container-left-choice">
                        <router-link to="/user/userInfo" active-class="active"><i class="el-icon-user"></i>个人信息</router-link>
                        <router-link to="/user/addQuestion" active-class="active"><i class="el-icon-paperclip"></i>添加题目</router-link>
                        <a @click="logout()"><i class="el-icon-refresh-left"></i>退出登录</a>
                    </div>
                </div>
                <div class="user-container-right">
                    <router-view></router-view>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
import Header from '../Header.vue'
export default {
    data(){
        return{
        }
    },
    components:{
        Header:Header,
    },
    methods:{
        logout(){

        }
    },
    mounted(){
    }
}
</script>
  
<style scoped>
/* .content *{
    border: 1px red solid;
} */
.container{
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}
.content{
    width: 100%;
    flex: 1;
    display: flex;
    justify-content: center;
    padding-top: 30px;
    background-color: #ECECEC;
}
.user-container{
    width: 90%;
    margin-bottom: 100px;
    display: flex;
    padding: 5px;
    border-radius: 3px;
    background-color: #FFFFFF;
}
.user-container-left{
    width: 20%;
    display: flex;
    flex-direction: column;
    align-items: center;
    border-right:1px #E6E6E6 solid;
    box-sizing:border-box;
}
.user-container-left-img {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 25px;
    padding-bottom: 10px;
}
.user-container-left-img > img{
    margin-bottom: 5px;
    height: 100px;
    width: 100px;
    border-radius: 50px;
}
.user-container-left-img > div{
    margin-bottom: 5px;
    color: #434343;
    text-align: center;
}
.line{
    margin-top: 10px;
    height: 1px;
    background-color: #E6E6E6;
    width: 100%;
}
.user-container-left-choice{
    width: 100%;
}
.user-container-left-choice > a{
    cursor: pointer;
    padding-top: 15px;
    padding-bottom: 15px;
    width: 100%;
    display: block;
    text-align: center;
    text-decoration:none;
    color: #333333;
}
.user-container-left-choice > a:hover,.active{
    color: #5183E1;
    background-color: #EAEFFB;
    border-right: 2px #2362D9 solid;
    box-sizing:border-box;
}
.user-container-right{
    width: 80%;
}
@media screen and (max-width: 900px){
/* .body{
    height: 520px;
} */
.user-container{
    padding: 4px;
}
.user-container{
    width: 94%;
    height: 94%;
}
/* .user-container-left{
    width: 2%;
} */
.user-container-left-img > img{
    margin-bottom: 2px;
    height: 40px;
    width: 40px;
    border-radius: 20px;
}
.user-container-left-img > div,.user-container-left-choice > a{
    font-size: 9px;
}
.user-container-left-choice > a{
    padding-top: 10px;
    padding-bottom: 10px;
}
}
</style>
  