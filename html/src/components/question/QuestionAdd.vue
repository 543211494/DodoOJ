<template>
    <div class="question-add-container">
        <div class="title">
            <p>添加题目</p>
        </div>
        <div class="question-title">
            <div class="question-title-input">
                <p>题目标题</p>
                <el-input
                    placeholder="请输入题目标题"
                    v-model="question.title"
                >
                </el-input>
            </div>
        </div>
        <div class="question-content">
            <div class="question-content-title">
                <p>题目描述</p>
            </div>
            <div>
                <mavon-editor v-model="question.content"
                            class="formclass" 
                            :editable="true"
                            :toolbars="toolbars"
                            >
                </mavon-editor>
            </div>
        </div>
        <div class="question-limit">
            <div class="question-limit-input">
                <p>时间限制（ms）</p>
                <el-input-number v-model="question.judgeConfig.timeLimit" :step="100" :min="0" :max="5000" label="时间限制（ms）"></el-input-number>
            </div>
            <div class="question-limit-input">
                <p>内存限制（MB）</p>
                <el-input-number v-model="question.judgeConfig.memoryLimit" :min="1" :max="512" label="内存限制（MB）"></el-input-number>
            </div>
        </div>
        <div class="question-tags">
            <div class="question-tags-title">
                <p>题目标签</p>
                <div @click="addTag">增加标签</div>
                <div @click="deleteTag">删除标签</div>
            </div>
            <div class="question-tags-input">
                <div v-for="(tag,index) in question.tags">
                    <el-input
                        placeholder="请输标签"
                        v-model="question.tags[index]"
                    >
                    </el-input>
                </div>
            </div>
        </div>
        <div class="judge-cases">
            <div class="judge-cases-title">
                <p>判题样例</p>
                <div @click="addCase">增加样例</div>
                <div @click="deleteCase">删除样例</div>
            </div>
            <div class="judge-case-input" v-for="(item,index) in question.judgeCase">
                <div class="judge-case-input-item">
                    <p>输入</p>
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 2}"
                        placeholder="样例输入"
                        v-model="question.judgeCase[index].input">
                    </el-input>
                </div>
                <div class="judge-case-input-item">
                    <p>输出</p>
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 2}"
                        placeholder="样例输出"
                        v-model="question.judgeCase[index].output">
                    </el-input>
                </div>
            </div>
        </div>
        <div class="submit">
            <div class="submit-btn">提交题目信息</div>
        </div>
    </div>
</template>
  
<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
    data(){
        return{
            question:{
                title:"",
                judgeConfig:{
                    memoryLimit:32,
                    timeLimit:3000
                },
                judgeCase:[
                    {input:"",output:""},
                ],
                tags:[""],
                content:""
            },
            toolbars:{
                bold: true,          // 加粗
                italic: true,        // 斜体
                header: true,        // 标题
                underline: true,     // 下划线
                strikethrough: true, // 删除线
                mark: true,          // 标记
                superscript: true,   // 上标
                subscript: true,     // 下标
                quote: true,         // 引用
                ol: true,            // 有序列表
                ul: true,            // 无序列表
                link: false,         // 禁用链接（防止插入图片链接）
                image: false,        // 禁用图片上传按钮
                uploadImage: false,  // 禁用图片上传功能
                code: true,          // 代码块
                table: true,         // 表格
                fullscreen: true,   // 全屏编辑
                readmodel: true,    // 阅读模式（预览）
                htmlcode: true,     // 查看HTML文本
                subfield: true,     // 单栏/双栏切换
                help: true,         // Markdown语法帮助
            }
        }
    },
    components:{
        mavonEditor:mavonEditor
    },
    methods:{
        addTag(){
            if(this.question.tags.length >= 5){
                this.$message({message: '标签最多只能有5条',type: 'warning'});
            }else{
                this.question.tags.push("")
            }
        },
        deleteTag(){
            if(this.question.tags.length > 1){
                this.question.tags.pop()
            }else{
                this.$message({message: '至少要有1个标签',type: 'warning'});
            }
        },
        addCase(){
            if(this.question.judgeCase.length >= 100){
                this.$message({message: '测试样例最多只能有100条',type: 'warning'});
            }else{
                this.question.judgeCase.push({input:"",output:""})
            }
        },
        deleteCase(){
            if(this.question.judgeCase.length > 1){
                this.question.judgeCase.pop()
            }else{
                this.$message({message: '至少要有1个标签',type: 'warning'});
            }
        }
    },
    mounted(){
    }
}
</script>
  
<style scoped>
/* .question-add-container, .question-add-container *{
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
.question-limit,
.question-title{
    display: flex;
    flex-wrap:wrap;
    width: 96%;
    margin-left: 2%;
}
.question-title-input{
    margin-top: 20px;
    width: 39%;
}
.question-limit-input p,
.question-title-input p{
    color: #495060;
    font-size: 20px;
    margin-bottom: 5px;
}

.question-limit-input{
    margin-top: 20px;
    width: 19%;
}
/* 题目内容部分 */
.question-content-title{
    display: flex;
}
.question-content-title p{
    color: #495060;
    font-size: 20px;
    margin-bottom: 5px;
    margin-top: 20px;
}


/* 标签部分 */
.question-content,
.judge-cases,
.question-tags{
    width: 96%;
    margin-left: 2%;
}
.judge-cases-title,
.question-tags-title{
    display: flex;
    margin-bottom: 5px;
    margin-top: 20px;
}
.judge-cases-title p,
.question-tags-title p{
    padding-top: 5px;
    padding-bottom: 5px;
    color: #495060;
    font-size: 20px;
}
.judge-cases-title div,
.question-tags-title div{
    padding-top: 5px;
    padding-bottom: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100px;
    margin-left: 30px;
    border-radius: 3px;
    color: #FFFFFF;
    background-color: #1E80FF;
    cursor: pointer;
    font-weight: bold;
}
.judge-cases-title div:hover,
.question-tags-title div:hover{
    background-color: #1171EE;
}
.judge-cases-title div:last-child,
.question-tags-title div:last-child{
    background-color: #ED3F14;
}
.judge-cases-title div:last-child:hover,
.question-tags-title div:last-child:hover{
    background-color: #DA2A16;
}
.question-tags-input{
    display: flex;
    flex-wrap:wrap;
}
.question-tags-input > div{
    width: 20%;
    margin-right: 5%;
    margin-bottom: 10px;
}
/* 样例输入输出部分 */
.judge-case-input{
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
}
.judge-case-input-item{
    width: 46%;
    margin-right: 2%;
    margin-left: 2%;
}
.judge-case-input-item p{
    display: flex;
    justify-content: center;
    align-items: center;
    color: #495060;
}
/* 提交按钮 */
.submit{
    width: 96%;
    margin-left: 2%;
    display: flex;
    justify-content: center;
    margin-top: 30px;
    margin-bottom: 30px;
}
.submit-btn{
    padding:10px 80px 10px 80px;
    border-radius: 3px;
    color: #FFFFFF;
    background-color: #1E80FF;
    cursor: pointer;
    font-weight: bold;
}
.submit-btn:hover{
    background-color: #1171EE;
}
@media screen and (max-width: 900px){
.title > p{
    font-size: 18px
}
.question-content-title p,
.judge-cases-title p,
.question-limit-input p,
.question-title-input p,
.question-tags-title p{
    font-size: 12px;
}
.question-title-input{
    margin-top: 10px;
}
.question-title-input /deep/ .el-input__inner,
.question-title-input /deep/ .el-input{
    width: 150px;
    height: 30px;
    line-height: 30px;
}
.question-limit-input{
    margin-top: 10px;
    width: 95%;
}

.question-limit-input /deep/ .el-input-number,
.question-limit-input /deep/ .el-input__inner {
  height: 32px;
  line-height: 32px;
  width: 150px;
}

.question-limit-input /deep/ .el-input-number__decrease,
.question-limit-input /deep/ .el-input-number__increase {
  line-height: 30px;
  height: 30px;
  width: 30px;
}
/* 标签部分 */
.judge-case-input-item p,
.judge-cases-title,
.question-tags-title{
    margin-top: 10px;
    font-size: 12px;
}
.judge-cases-title div,
.question-tags-title div{
    width: 70px;
    margin-left: 20px;
}
.question-tags-input > div{
    width: 45%;
    margin-right: 5%;
}
.question-tags-input /deep/ .el-input__inner,
.question-tags-input /deep/ .el-input{
    height: 30px;
    line-height: 30px;
}
.question-content-title p{
    margin-top: 10px;
}
.submit-btn{
    padding:10px 30px 10px 30px;
}
}
</style>
  