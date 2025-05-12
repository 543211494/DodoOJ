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
                account:"admin",
                userName:"普通用户",
                role:"管理员",
                email:"124567@qq.com"
            }
        }
    },
    methods:{
        submit(){

        }
    },
    mounted(){
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
/* .info-input /deep/ .el-input__inner,
.info-input /deep/ .el-input{
    height: 30px;
    line-height: 30px;
} */
</style>
  