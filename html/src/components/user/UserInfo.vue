<template>
    <div class="userinfo-container">
        <div class="title">
            <p>个人信息设置</p>
        </div>
        <div class="info-inputs">
            <div class="info-input">
                <p>用户ID</p>
                <el-input
                    placeholder="请输入内容"
                    v-model="user.id"
                    :disabled="true"
                >
                </el-input>
            </div>
            <div class="info-input">
                <p>帐号</p>
                <el-input
                    placeholder="请输入内容"
                    v-model="user.account"
                    :disabled="true"
                >
                </el-input>
            </div>
            <div class="info-input">
                <p>用户权限</p>
                <el-input
                    placeholder="请输入内容"
                    v-model="user.role"
                    :disabled="true"
                >
                </el-input>
            </div>
            <div class="info-input">
                <p>用户名</p>
                <el-input
                    placeholder="请输入内容"
                    v-model="user.userName"
                >
                </el-input>
            </div>
            <div class="info-input">
                <p>用户邮箱</p>
                <el-input
                    placeholder="请输入内容"
                    v-model="user.email"
                >
                </el-input>
            </div>
        </div>
        <div class="submit-btn">
            <div @click="submit">提交修改</div>
        </div>
    </div>
</template>
  
<script>
export default {
    data(){
        return{
            user:{
                id:1,
                account:"",
                userName:"",
                role:"",
                email:""
            }
        }
    },
    methods:{
        goto(path){
            this.$router.push({
                path:path
            })
        },
        submit(){
            if(this.validate()){
                this.$axios({
                    url: '/api/updateInfo',
                    data: {
                        token:this.$store.state.token,
                        userName:this.user.userName,
                        email:this.user.email
                    },
                    method:'POST'
                }).then(res=>{
                    if(res.data.success){
                        this.$store.state.user = res.data.data;
                        localStorage.setItem("user", JSON.stringify(this.$store.state.user));
                        this.$message({message: '修改成功',type: 'success'});
                    }else{
                        if(res.data.code=="103"){
                            this.$store.state.user = null;
                            this.$store.state.token = null;
                            localStorage.clear();
                            this.$message.error(res.data.message);
                            this.goto("/login");
                        }else{
                            this.$message.error(res.data.message);
                        }
                    }
                });
            }
        },
        validate(){
            if(this.user.userName==""||this.user.email==""){
                this.$message.error('参数不能为空！');
                return false;
            }
            return true;
        }
    },
    mounted(){
        if(this.$store.state.user==null){
            this.$message.error('请先登录！');
            this.goto("/login");
        }
        if(this.$store.state.user!=null){
            this.user = this.$store.state.user;
        }
    }
}
</script>
  
<style scoped>
/* .userinfo-container,.userinfo-container *{
    border: 1px red solid;
} */
.title{
    display: flex;
    margin-left: 1%;
    margin-top: 10px;
    margin-bottom: 10px;
}
.title > p{
    display: flex;
    height: 100%;
    color: #495060;
    font-weight: bolder;
    font-size: 32px;
}

.info-inputs{
    display: flex;
    flex-wrap:wrap;
    width: 96%;
    margin-left: 2%;
}
.info-input{
    margin-top: 20px;
    padding-left: 5%;
    padding-right: 5%;
    width: 40%;
}
.info-input p{
    color: #495060;
    font-size: 20px;
    margin-bottom: 5px;
}


.submit-btn{
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 50px;
}
.submit-btn > div{
    padding:10px 80px 10px 80px;
    border-radius: 3px;
    color: #FFFFFF;
    background-color: #1E80FF;
    cursor: pointer;
    font-weight: bold;
}
.submit-btn > div:hover{
    background-color: #1171EE;
}

@media screen and (max-width: 900px){
.title > p{
    font-size: 18px
}
.submit-btn{
    margin-bottom: 50px;
}
.submit-btn > div{
    padding:10px 50px 10px 50px;
}

.info-input p{
    font-size: 12px;
}
.info-input /deep/ .el-input__inner,
.info-input /deep/ .el-input{
    height: 30px;
    line-height: 30px;
}
}
</style>
  