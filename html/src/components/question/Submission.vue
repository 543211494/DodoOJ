<template>
    <div class="container">
        <Header></Header>
        <div class="content">
            <div class="submission">
                <div class="submission-list">
                    <div class="submission-list-nav">
                        <div>提交记录</div>
                    </div>
                    <div class="submission-list-title">
                        <div>#</div>
                        <div>题目标题</div>
                        <div>编程语言</div>
                        <div>结果</div>
                        <div>耗时</div>
                        <div>提交者</div>
                        <div>创建时间</div>
                    </div>
                    <div class="submissions" v-for="(submission,index) of submissions" :key="submission.id" @click="gotoQuestion(submission.questionId)">
                        <div>{{submission.id}}</div>
                        <div>{{submission.title}}</div>
                        <div>{{submission.language}}</div>
                        <div class="submission-message">
                            <p class="submission-message-ac" v-if="submission.judgeInfo.message=='Accepted'">{{submission.judgeInfo.message}}</p>
                            <p class="submission-message-wa" v-if="submission.judgeInfo.message!=='Accepted'">{{submission.judgeInfo.message}}</p>
                        </div>
                        <div>{{submission.judgeInfo.time}}ms</div>
                        <div>{{submission.userName}}</div>
                        <div>{{submission.createTime}}</div>
                    </div>
                </div>
                <div class="page">
                    <el-pagination
                        layout="prev, pager, next"
                        :current-page="currentPage"
                        :pager-count="5"
                        @current-change="handleCurrentChange"
                        :total="total"
                        class="custom-page"
                    >
                    </el-pagination>
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
            questionId:null,
            submissions:[
                {id:1,title: "A+B",questionId:1,language:"java",judgeInfo:{message:'Accepted',time:119},userName:"用户1",createTime:"2025-05-12 06:14:05"},
                {id:2,title: "A+B",questionId:1,language:"java",judgeInfo:{message:'Time Limit Exceeded',time:119},userName:"用户1",createTime:"2025-05-12 06:14:05"},
                {id:3,title: "A+B",questionId:1,language:"java",judgeInfo:{message:'Compile Error',time:119},userName:"用户1",createTime:"2025-05-12 06:14:05"},
                {id:4,title: "A+B",questionId:1,language:"java",judgeInfo:{message:'Accepted',time:119},userName:"用户1",createTime:"2025-05-12 06:14:05"},
            ],
            currentPage:2,
            total:100
        }
    },
    components:{
        Header:Header,
    },
    methods:{
        gotoQuestion(questionId){
            this.$router.push({
                path:'/question-detail/'+questionId,
            })
        
        },
        handleCurrentChange(val){
            console.log("页码被点击"+val)
        }
    },
    mounted(){
        this.questionId = this.$route.query.questionId;
        // console.log(this.questionId==null);
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
    flex: 1;
    display: flex;
    justify-content: center;
    padding-top: 40px;
    background: #ECECEC;
}
.submission{
    width: 90%;
}

.submission-list-nav{
    display: flex;
    height: 70px;
    background-color: #FFFFFF;
}
.submission-list-nav > div:nth-child(1){
    display: flex;
    padding-left: 15px;
    align-items: center;
    color: #495060;
    font-weight: bolder;
    font-size: 22px;
    width: 15%;
}

.submission-list-title{
    display: flex;
    height: 45px;
    background-color: #F8F8F9;
}
.submission-list-title > div{
    display: flex;
    justify-content: center;
    align-items: center;
    color: #495060;
    font-weight:600;
}

.submissions{
    display: flex;
    height: 45px;
    background-color: #FFFFFF;
    border-bottom: 1px solid #E9EAEC;
    cursor: pointer;
}
.submissions:hover{
    background-color: #F8F8F9;
}
.submissions>div{
    display: flex;
    justify-content: center;
    align-items: center;
}

.submission-message{
    display: flex;
    justify-content: center;
    align-items: center;
}
.submission-message p{
    padding: 3px 8px 3px 8px;
    border-radius: 3px;
    font-weight: bold;
    color: #FFFFFF;
}
.submission-message-ac{
    background-color: #19BE6B;
}
.submission-message-wa{
    background-color: #ED3F14;
}

.submission-list-title > div:first-child,
.submissions>div:first-child{
    width: 5%;
}

.submission-list-title > div:nth-child(7),
.submissions>div:nth-child(7){
    width: 15%;
}
.submission-list-title > div:nth-child(3),
.submissions>div:nth-child(3),
.submission-list-title > div:nth-child(5),
.submissions>div:nth-child(5){
    width: 10%;
}
.submission-list-title > div:nth-child(4),
.submissions>div:nth-child(4),
.submission-list-title > div:nth-child(6),
.submissions>div:nth-child(6),
.submission-list-title > div:nth-child(2),
.submissions>div:nth-child(2){
    width: 20%;
}

.page{
    margin-top: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.custom-page >>> .el-pager li,
.custom-page >>> .btn-prev,
.custom-page >>> .btn-next {
  margin: 0 5px;
  background: #FFFFFF;
}
@media screen and (max-width: 900px){
.submission{
    width: 96%;
}
.submission-list-nav{
    height: 50px;
}
.submission-list-nav > div:nth-child(1){
    padding-left: 5px;
    font-size: 15px;
    width: 30%;
}
.submission-list-title > div,.submissions{
    font-size: 10px;
}

.submission-message p{
    padding: 3px 3px 3px 3px;
}

.submission-list-title > div:nth-child(5),
.submissions>div:nth-child(5),
.submission-list-title > div:nth-child(7),
.submissions>div:nth-child(7){
    display: none;
}
.submission-list-title > div:nth-child(3),
.submissions>div:nth-child(3){
    width: 14%;
}
.submission-list-title > div:nth-child(4),
.submissions>div:nth-child(4){
    width: 31%;
}
.submission-list-title > div:nth-child(6),
.submissions>div:nth-child(6),
.submission-list-title > div:nth-child(2),
.submissions>div:nth-child(2){
    width: 25%;
}
}
</style>
  