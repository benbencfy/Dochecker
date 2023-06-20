const IS_PRO = ['production', 'test'].includes(process.env.NODE_ENV);
const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,
  // 开启或者关闭eslint
  lintOnSave: true,
  productionSourceMap: false, // 不生成map
  publicPath: IS_PRO ? '${resourceUrl}' : '/',
  css: {
    extract: IS_PRO, // 是否使用css分离插件 ExtractTextPlugin
    sourceMap: false // 打包时不生成.map文件
  },
  chainWebpack: (config) => {
    if (IS_PRO) {
      config.plugin('html').tap((args) => {
        return [{ filename: 'main.jsp', template: './public/main.html' }];
      });
    }
  },
  devServer: {
    port: 8000,
    proxy: {
      '/': {
        target: 'http://127.0.0.1:8080/', // 设置你调用的接口域名和端口号 别忘了加http
        changeOrigin: true, // 这里设置是否跨域
        ws: false
      }
    }
  }
});
