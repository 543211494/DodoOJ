<template>
    <div class="container">
        <Header></Header>
        <div class="content">
            <div class="register">
                <h1>请填写注册信息！</h1>
                <el-input placeholder="请输入帐号" v-model="account" prefix-icon="el-icon-user-solid"></el-input>
                <el-input placeholder="请输入密码" v-model="password" show-password prefix-icon="el-icon-lock"></el-input>
                <el-input placeholder="请再次输入密码" v-model="passwordAgain" show-password prefix-icon="el-icon-lock"></el-input>
                <div class="register-btn" @click="register">注册</div>
                <p @click='goto("/login")'>登录</p>
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
            password:'',
            passwordAgain:''
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
        register(){
            if(this.validate()){
                this.$axios({
                url: '/api/register',
                data: {
                    account:this.account,
                    password:this.password,
                    checkPassword:this.passwordAgain
                },
                method:'POST'
            }).then(res => {
                // console.log(res);
                if(res.data.success){
                    this.$message({message: '注册成功',type: 'success'});
                    this.goto("/login");
                }else{
                    this.$message.error(res.data.message);
                }
            });
            }
        },
        validate(){
            if(this.account==""||this.password==""||this.passwordAgain==""){
                this.$message.error('参数不能为空！');
                return false;
            }
            if(this.password!=this.passwordAgain){
                this.$message.error('两次密码不一致！');
                return false;
            }
            return true;
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
.register{
    background-color: #FFFFFF;
    margin-top: 5%;
    height: 420px;
    width: 500px;
}
.register >h1{
    margin-top: 5%;
    margin-bottom: 5%;
    text-align: center;
}
.register >p{
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
.register > .el-input /deep/ {
    width: 70%;
    margin-left: 15%;
    margin-bottom: 20px;
}

.register-btn{
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
.register-btn:hover{
    background-color: #429DE4;
}
@media screen and (max-width: 900px){
.register{
    margin-top: 35%;
    width: 340px;
}
}
</style>
  