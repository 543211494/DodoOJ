<template>
    <div ref="CodeEditor" style="width: 100%; height: 100%;" />
</template>
  
<script>
import * as monaco from 'monaco-editor'
  
export default {
    name: 'CodeEditor',
    props: {
      editable: { type: Boolean, default: true },
      language: { type: String, default: 'json' },
      value: { type: String, required: true, default: '' }
    },
  
    data() {
      return {
        codeEditor: null
      }
    },
  
    watch: {
      value: function(val) {
        this.updateEditor(val)
      },
      language: function(val){
        // console.log(val)
        const model = this.codeEditor.getModel()
        const language = model.getModeId()
        if (language !== this.language) {
            monaco.editor.setModelLanguage(model, this.language)
        }
      }
    },
  
    mounted() {
      this.updateEditor(this.value)
      window.addEventListener('resize', this.handleResize)
    },
  
    beforeDestroy() {
      this.codeEditor.dispose()
    },
  
    methods: {
  
        updateEditor(content) {
            // 未创建则创建
            if (!this.codeEditor) {
                this.codeEditor = monaco.editor.create(this.$refs.CodeEditor, {
                    value: '',
                    language: this.language,
                    roundedSelection: false,
                    readOnly: !this.editable,
                    theme: 'vs-dark',
                    wordWrap: 'wordWrapColumn',
                    wordWrapColumn: 80,
        
                    tabSize: 2,
                    folding: true, // 是否折叠
                    foldingHighlight: true, // 折叠等高线
                    foldingStrategy: 'auto', // 折叠方式
                    showFoldingControls: 'always', // 是否一直显示折叠
                    disableLayerHinting: true, // 等宽优化
                    automaticLayout: true, // 自动布局
                    scrollBeyondLastLine: false, // 滚动完最后一行后再滚动一屏幕
                    colorDecorators: true, // 颜色装饰器
                    lineNumbers: 'on', // 行号 取值： "on" | "off" | "relative" | "interval" | function
                    lineNumbersMinChars: 5, // 行号最小字符   number
        
                    scrollbar: {
                        // Subtle shadows to the left & top. Defaults to true.
                        useShadows: false,
            
                        // Render vertical arrows. Defaults to false.
                        verticalHasArrows: false,
                        // Render horizontal arrows. Defaults to false.
                        horizontalHasArrows: false,
            
                        // Render vertical scrollbar.
                        // Accepted values: 'auto', 'visible', 'hidden'.
                        // Defaults to 'auto'
                        vertical: 'auto',
                        // Render horizontal scrollbar.
                        // Accepted values: 'auto', 'visible', 'hidden'.
                        // Defaults to 'auto'
                        horizontal: 'auto',
            
                        verticalScrollbarSize: 10,
                        horizontalScrollbarSize: 10,
                        arrowSize: 10
                    },
        
                    // 小地图
                    minimap: {
                        enabled: true
                    }
                })
                this.codeEditor.onDidChangeModelContent(() => {
                    this.$emit('input', this.codeEditor.getValue())
                })
            }
            // 修改后将value设置到编辑中
            if (content !== this.codeEditor.getValue()) { this.codeEditor.setValue(content) }
    
            this.codeEditor.updateOptions({
                readOnly: !this.editable
            })
            // 创建后再次修改language
            const model = this.codeEditor.getModel()
            const language = model.getModeId()
            if (language !== this.language) {
                monaco.editor.setModelLanguage(model, this.language)
            }
        }, 
        handleResize() {
            if (this.codeEditor) {
                this.codeEditor.layout()
            }
        }
    }
}
</script>