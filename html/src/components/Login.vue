<template>
    <div class="container">
        <Header></Header>
        <div class="content">
            <div class="login">
                <h1>欢迎登录！</h1>
                <el-input placeholder="请输入帐号" v-model="account" prefix-icon="el-icon-user-solid"></el-input>
                <el-input placeholder="请输入密码" v-model="password" show-password prefix-icon="el-icon-lock"></el-input>
                <div class="login-btn" @click="login">登录</div>
                <p @click='goto("/register")'>注册</p>
                <div class="line"></div>
            </div>
        </div>
    </div>
</template>
  
<script>
import Header from './Header.vue'
export default {
    data(){
        return{
            account:'',
            password:''
        }
    },
    components:{
        Header:Header,
    },
    methods:{
        goto(path){
            this.$router.push({
                path:path
            })
        },
        login(){
            if(this.validate()){
                this.$axios({
                    url: '/api/login',
                    data: {
                        account:this.account,
                        password:this.password
                    },
                    method:'POST'
                }).then(res=>{
                    if(res.data.success){
                        this.$store.state.token = res.data.data.token;
                        this.$store.state.user = res.data.data.user;
                        localStorage.setItem("token", res.data.data.token);
                        localStorage.setItem("user", JSON.stringify(res.data.data.user));
                        this.$message({message: '登录成功',type: 'success'});
                        this.goto("/");
                    }else{
                        this.$message.error(res.data.message);
                    }
                });
            }
        },
        validate(){
            if(this.account==""||this.password==""){
                this.$message.error('参数不能为空！');
                return false;
            }
            return true;
        }
    },
    mounted(){
        // console.log(this.$store.state)
        if(this.$store.state.user!=null){
            this.goto("/user/userInfo")
        }
    }
}
</script>
  
<style scoped>
/* .content{
    border: 1px red solid;
} */
.container{
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}
.content{
    flex: 1;
    background: #ECECEC;
    display: flex;
    justify-content: center;
    /* align-items: center; */
    height: 520px;
}
.login{
    background-color: #FFFFFF;
    margin-top: 5%;
    height: 360px;
    width: 500px;
}
.login >h1{
    margin-top: 5%;
    margin-bottom: 5%;
    text-align: center;
}
.login >p{
    font-size: 14px;
    cursor:pointer;
    color: #1586DE;
    margin-left: 15%;
    margin-bottom: 15px;
}
.line{
    height: 1px;
    background-color: #E6E6E6;
    width: 70%;
    margin-left: 15%;
}
.login > .el-input /deep/ {
    width: 70%;
    margin-left: 15%;
    margin-bottom: 20px;
}

.login-btn{
    font-size: 20px;
    color: #FFFFFF;
    background-color: #1586DE;
    width: 70%;
    margin-left: 15%;
    margin-bottom: 15px;
    line-height: 30px;
    text-align: center;
    padding-top: 7px;
    padding-bottom: 7px;
    border-radius: 2px;
}
.login-btn:hover{
    background-color: #429DE4;
}
@media screen and (max-width: 900px){
.login{
    margin-top: 35%;
    width: 340px;
}
}
</style>
  