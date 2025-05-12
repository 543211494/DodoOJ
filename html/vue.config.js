const { defineConfig } = require('@vue/cli-service')
const MonacoWebpackPlugin = require('monaco-editor-webpack-plugin')
module.exports = defineConfig({
    transpileDependencies: true,
    // publicPath: './query/images/dist', // 基本路径
    publicPath: './', // 基本路径
    outputDir: './dist', // 构建时的输出目录
    assetsDir: './assets', // 放置静态资源的目录
    indexPath: 'index.html', // html 的输出路径
    filenameHashing: true, // 文件名哈希值
    lintOnSave: false, // 是否在保存的时候使用 `eslint-loader` 进行检查。
    //调整内部的 webpack 配置
    configureWebpack: {
        plugins: [
            new MonacoWebpackPlugin({languages:['java',"cpp","python"]})
        ]
    },
    chainWebpack: () => {},
    devServer: {
        port: 8000,
        proxy:{
            '/api':{
                target:'http://127.0.0.1:8080',
                pathRewrite:{'/api':'/api'},
                // ws: true, //用于支持websocket,默认值为true
                // changeOrigin: true //用于控制请求头中的host值,默认值为true,服务器收到的请求头中的host值为target中的值
            }
        },
        client: {
            overlay: {
              runtimeErrors: (error) => {
                // 忽略 ResizeObserver 相关警告
                if (error.message.includes('ResizeObserver')) {
                  return false;
                }
                return true;
              },
            },
        }
    },
})