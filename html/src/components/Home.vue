<template>
    <div class="container">
        <Header></Header>
        <div class="content">
            <div class="question">
                <div class="question-list">
                    <div class="question-list-nav">
                        <div>题目列表</div>
                    </div>
                    <div class="question-list-title">
                        <div>#</div>
                        <div>题目标题</div>
                        <div>标签</div>
                        <div>通过率</div>
                    </div>
                    <div class="questions" v-for="(question,index) of questions" :key="question.id" @click="gotoQuestion(question.id)">
                        <div class="questions-id">{{question.id}}</div>
                        <div class="questions-title">{{question.title}}</div>
                        <div class="questions-tags">
                            <p v-for="(tag,index) of question.tag">{{tag}}</p>
                        </div>
                        <div class="questions-ac">{{question.submitCount==0?"-":((question.acceptedCount/question.submitCount)*100).toFixed(2)+'%'}}</div>
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
            <div class="tags">
                <div class="tags-titile">
                    <p>热门标签</p>
                </div>
                <div class="tags-container">
                    <div class="tag" v-for="tag in tags">{{tag}}</div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
import Header from './Header.vue'
export default {
    data(){
        return{
            questions:[
                {id: 1,title: "A+B",content: "计算两个数相加之和",tag:["简单","暴力"],submitCount: 0,acceptedCount: 0},
                {id: 2,title: "A+B",content: "计算两个数相加之和",tag:["简单","暴力"],submitCount: 3,acceptedCount: 1},
                {id: 3,title: "A+B",content: "计算两个数相加之和",tag:["简单","暴力"],submitCount: 0,acceptedCount: 0},
                {id: 4,title: "A+B",content: "计算两个数相加之和",tag:["简单","暴力"],submitCount: 0,acceptedCount: 0},
            ],
            currentPage:2,
            total:100,
            tags:["简单","字符串","中等","动态规划","暴力","困难","前缀和","二分","二叉树","暴力","图论","贪心","双指针","哈希","深度优先","堆"]

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
        console.log(this.$store.state)
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
.question{
    width: 75%;
}

.question-list-nav{
    display: flex;
    height: 70px;
    background-color: #FFFFFF;
}
.question-list-nav > div:nth-child(1){
    display: flex;
    padding-left: 15px;
    align-items: center;
    color: #495060;
    font-weight: bolder;
    font-size: 22px;
    width: 15%;
}

.question-list-title{
    display: flex;
    height: 45px;
    background-color: #F8F8F9;
}
.question-list-title > div{
    display: flex;
    justify-content: center;
    align-items: center;
    color: #495060;
    font-weight:600;
    width: 42%;
}

.questions{
    display: flex;
    height: 45px;
    background-color: #FFFFFF;
    border-bottom: 1px solid #E9EAEC;
    cursor: pointer;
}
.questions:hover{
    background-color: #F8F8F9;
}
.questions>div{
    display: flex;
    justify-content: center;
    align-items: center;
}
.questions-tags p{
    margin-left: 5px;
    margin-right: 5px;
    padding: 3px 15px 3px 15px;
    border-radius: 3px;
    background-color: #19BE6B;
    color: #FFFFFF;
}

.question-list-title > div,
.questions>div{
    width: 42%;
}
.question-list-title > div:first-child,
.question-list-title > div:last-child,
.questions>div:first-child,
.questions>div:last-child{
    width: 8%;
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

/* 右侧标签栏目 */
.tags{
    width: 15%;
    margin-left: 3%;
    background-color: #FFFFFF;
    padding: 8px;
    height: 90%;
}
.tags-titile{
    display: flex;
}
.tags-titile{
    padding-top: 10px;
    padding-bottom: 20px;
    color: #495060;
    font-weight: bolder;
    font-size: 22px;
}
.tags-container{
    display: flex;
    flex-wrap: wrap;
}
.tags-container > div{
    color: #495060;
    border: 1px #DDDEE1 solid;
    padding: 5px 15px 5px 15px;
    border-radius: 8px;
    margin-right: 12px;
    margin-bottom: 10px;
    cursor: pointer;
}
.tags-container > div:hover{
    color: #57A3F3;
    border: 1px #57A3F3 solid;
}
@media screen and (max-width: 900px){
.question{
    width: 96%;
}
.question-list-nav{
    height: 50px;
}
.question-list-nav > div:nth-child(1){
    padding-left: 5px;
    font-size: 15px;
    width: 30%;
}
.question-list-title > div,.questions{
    font-size: 12px;
}
.question-list-title > div,
.questions>div{
    width: 40%;
}
.question-list-title > div:first-child,
.questions>div:first-child{
    width: 5%;
}
.question-list-title > div:last-child,
.questions>div:last-child{
    width: 15%;
}
.questions-tags p{
    margin-left: 2px;
    margin-right: 2px;
    padding: 3px 5px 3px 5px;
}
.tags{
    display: none;
}
}
</style>
  