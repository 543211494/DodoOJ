<template>
    <div class="container">
        <Header></Header>
        <div class="content">
            <div class="left-container">
                <div class="question-title-container">
                    <div class="question-title">
                        {{question.title}}
                    </div>
                    <div class="question-tags">
                        <p v-for="(tag,index) of question.tag">{{tag}}</p>
                    </div>
                </div>
                <div class="question-content">
                    <mavon-editor v-model="question.content" 
                            class="formContent" 
                            :editable="false" 
                            :subfield="false" 
                            defaultOpen="preview" 
                            :toolbarsFlag="false" >
                    </mavon-editor>
                </div>
                <div class="code-editor">
                    <div class="language-choice-container">
                        <div class="language-choice">
                            <p>编程语言：</p>
                            <el-select v-model="language" placeholder="请选择" class="language-select">
                                <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </div>
                        <div class="submit-btn">
                            <div @click="submit">提交代码</div>
                        </div>
                    </div>
                    <div class="code-editor-body">
                        <CodeEditor
                            v-model="code"
                            :language="language">
                        </CodeEditor>
                    </div>
                </div>
            </div>
            <div class="right-container">
                <div class="submit-log" @click="gotoSubmit">
                    <i class="el-icon-tickets">提交记录</i>
                </div>

                <div class="question-info">
                    <div class="question-info-title">
                        <i class="el-icon-s-promotion">题目信息</i>
                    </div>
                    <div class="question-info-content">
                        <p>ID</p>
                        <p>{{question.id}}</p>
                    </div>
                    <div class="question-info-content">
                        <p>Time Limit</p>
                        <p>{{question.judgeConfig.timeLimit}}ms</p>
                    </div>
                    <div class="question-info-content">
                        <p>Memory Limit</p>
                        <p>{{question.judgeConfig.memoryLimit}}KB</p>
                    </div>
                    <div class="question-info-content">
                        <p>IO Mode</p>
                        <p>Standard IO</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Header from '../Header.vue'
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import CodeEditor from "./MonacoEditor.vue"
export default {
    data(){
        return{
            code:"",
            language:"java",
            options:[
                {value: 'java',label: 'java'},
                {value: 'cpp',label: 'c++'},
                {value: 'python',label: 'python'}
            ],
            question:{
                id:0,
                title:"Simple A + B Problem",
                content:'## 题目描述\n请计算两个整数的和并输出结果。\n\n注意不要有不必要的输出，比如"请输入 a 和 b 的值: "。\n\n## 输入\n两个用空格分开的整数.\n```\n1 1\n```\n\n## 输出\n两数之和\n```\n2\n```',
                judgeConfig:{
                    memoryLimit:32768,
                    timeLimit:3000
                },
                tag:["简单","暴力"]
            }
        }
    },
    components:{
        Header:Header,
        mavonEditor:mavonEditor,
        CodeEditor:CodeEditor
    },
    methods:{
        gotoSubmit(){
            this.$router.push({
                path:"/submit?questionId="+this.question.id
            });
        },
        submit(){
            console.log(this.code);
        }
    },
    created(){
        this.question.id = this.$route.params.id;
        console.log("questionId:"+this.question.id);
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
    padding-top: 40px;
}

.left-container{
    width: 65%;
    margin-right: 5%;
}
.right-container{
    width: 15%;
}

.question-title-container{
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #FFFFFF;
    padding: 20px 10px 20px 10px;
}
.question-title{
    font-size: 26px;
    font-weight: bold;
}
.question-tags{
    display: flex;
    justify-content: center;
    align-items: center;
}
.question-tags > p{
    margin-left: 10px;
    padding: 3px 15px 3px 15px;
    border-radius: 3px;
    background-color: #19BE6B;
    color: #FFFFFF;
}

.code-editor{
    margin-top: 10px;
}
.code-editor-body{
    height: 300px;
    margin-bottom: 100px;
}

/* 编程语言选择部分 */
.language-choice-container{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    padding-left: 5px;
    padding-right: 5px;
}
.language-choice{
    display: flex;
    align-items: center;
}
.language-choice p{
    color: #495060;
}
.language-select /deep/ .el-input__inner{
    height: 30px;
    line-height: 30px;
}

.submit-btn{
    display: flex;
    align-items: center;
}
.submit-btn > div{
    padding:5px 80px 5px 80px;
    color: #FFFFFF;
    background-color: #1E80FF;
    cursor: pointer;
    font-weight: bold;
}
.submit-btn > div:hover{
    background-color: #1171EE;
}

/* 提交记录按钮 */
.submit-log{
    display: flex;
    height: 60px;
    background-color: #FFFFFF;
    cursor: pointer;
}
.submit-log:hover{
    background-color: #F8F8F9;
}
.submit-log > i{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    color: #495060;
    font-size: 22px;
}
.submit-log > i:hover{
    color: #5CADFF;
}


/* 题目信息 */
.question-info-title{
    display: flex;
    align-items: center;
    height: 50px;
    padding-left: 6%;
}
.question-info-title > i{
    color: #495060;
    font-size: 20px;
}
.question-info{
    margin-top: 20px;
    background-color: #FFFFFF;
    padding-bottom: 15px;
}
.question-info-content{
    margin-left: 6%;
    margin-right: 6%;
    padding-bottom: 5px;
    margin-top: 10px;
    border-bottom: 1px solid #E9EAEC;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.question-info-content > p{
    color: #495060;
    font-size: 16px;
}
@media screen and (max-width: 900px){
.content{
    padding-top: 20px;
}
.left-container{
    width: 90%;
}
.right-container{
    display: none;
}
.question-title-container{
    padding: 10px 10px 10px 10px;
}
.question-title{
    font-size: 18px;
}
.question-tags > p{
    margin-left: 3px;
    padding: 3px 5px 3px 5px;
    font-size: 12px;
}
.language-choice p{
    color: #495060;
    font-size: 12px;
}
.language-select /deep/ .el-input__inner{
    width: 120px;
}
.submit-btn > div{
    padding:8px 30px 8px 30px;
    font-size: 12px;
}
}
</style>