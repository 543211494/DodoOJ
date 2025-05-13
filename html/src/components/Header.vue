<template>
    <div class="nav">
        <div class="logo">
            <p>DodoOJ</p>
        </div>
        <div class="choice">
            <div @click="goto('/')">
                <i class="el-icon-edit"></i>
                <p>题库</p>
            </div>
            <div @click="goto('/submit')">
                <i class="el-icon-tickets"></i>
                <p>提交记录</p>
            </div>
            <div @click="goto('/user')">
                <i class="el-icon-user"></i>
                <p>个人中心</p>
            </div>
        </div>
        <div class="user-choice">
            <div class="user-btn" v-show="!isLogin" @click='goto("/login")'>登录</div>
            <div class="user-btn" v-show="!isLogin" @click='goto("/register")'>注册</div>
            <div class="user-avatar" v-show="isLogin" @click="goto('/user')">
                <img :src="require('../assets/avatar.jpg')"/>
                <p>{{userName}}</p>
            </div>
        </div>
    </div>
</template>
  
<script>
  
export default {
    data(){
        return{
            isLogin:false,
            userName:"测试用户",
        }
    },
    methods:{
        goto(path){
            this.$router.push({
                path:path
            })
        },
    },
    mounted(){
        if(this.$store.state.user!=null){
            this.isLogin=true;
            this.userName = this.$store.state.user.userName;
        }
    }
}
</script>
  
<style scoped>
/* *{
    border: 1px red solid;
} */
.nav{
    display: flex;
    height: 60px;
    background-color: #FDFDFD;
}
.logo{
    display: flex;
    width: 10%;
}
.logo > p{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    color: #495060;
    font-weight: bolder;
    font-size: 32px;
}
.choice{
    display: flex;
    width: 75%;
}
.choice > div{
    padding-left: 20px;
    padding-right: 20px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    color: #495060;
}
.choice > div:hover{
    background-color: #E8E7E3;
}
.choice i,.choice p{
    display: flex;
    font-size: 18px;
    justify-content: center;
    align-items: center;
}

.user-choice{
    display: flex;
    justify-content: space-around;
    align-items: center;
}
.user-btn{
    height: 40px;
    padding-left: 20px;
    padding-right: 20px;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #737373;
    cursor: pointer;
}
.user-btn:hover{
    color: #409EFF;
}

.user-avatar{
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}
.user-avatar > img{
    height: 40px;
    width: 40px;
    border-radius: 20px;
}
.user-avatar > p{
    padding-left: 12px;
    color: #737373;
}
.user-avatar:hover{
    color: #409EFF;
}
@media screen and (max-width: 900px){
.nav{
    height: 40px;
}
.logo{
    display: none;
}
.choice > div{
    padding-left: 2px;
    padding-right: 2px;
}
.choice i,.choice p{
    font-size: 15px;
}

.user-choice{
    width: 25%;
}
.user-btn{
    height: 20px;
    padding-left: 2px;
    padding-right: 2px;
}

.user-avatar{
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}
.user-avatar > img{
    height: 30px;
    width: 30px;
    border-radius: 15px;
}
.user-avatar > p{
    padding-left: 2px;
    font-size: 10px;
}
}
</style>
  