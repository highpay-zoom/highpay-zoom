# web前端项目搭建说明

> Author: ChinSyun Pang
>
> Weibo: [arthinkign_plus](http://weibo.com/arthinkingplus)
>
> Posted in: [web前端项目搭建说明](http://www.itzhai.com/web-project-setup-introduction-html.html)
>
> 项目地址：[app-mis](https://github.com/highpay-zoom/app-mis)

## 安装webpack：

```bash
$ npm install webpack -g
```

> 关于[npm install](https://docs.npmjs.com/)

### 初始化项目：

```bash
$ npm init
```

执行完这步会在目录下生成`package.json`文件


### 为项目安装webpack：

```bash
$ npm install webpack --save-dev
```

或者安装特定的版本：

```bash
$ npm install webpack@1.2.x --save-dev
```

[webpack](https://webpack.github.io/)是一款模块加载器兼打包工具，它能把各种资源，例如JS（含JSX）、coffee、样式（含less/sass）、图片等都作为模块来使用和处理。

**--save-dev 与 --save的区别**
> --save-dev: Package will appear in your devDependencies.
> see[Grunt.js: What does -save-dev mean in npm install grunt --save-dev](http://stackoverflow.com/questions/19223051/grunt-js-what-does-save-dev-mean-in-npm-install-grunt-save-dev)

### 安装webpack调试工具：

> $ npm install webpack-dev-server --save-dev

#### 启动webpack-dev-server：

```bash
$ webpack-dev-server --inline --hot
```

[webpack-dev-server](https://webpack.github.io/docs/webpack-dev-server.html)webpack-dev-server是一个小型的node.js Express服务器,它使用webpack-dev-middleware中间件来为通过webpack打包生成的资源文件提供Web服务。它还有一个通过Socket.IO连接着webpack-dev-server服务器的小型运行时程序。webpack-dev-server发送关于编译状态的消息到客户端，客户端根据消息作出响应。

### webpack构建命令：

```bash
$ webpack --display-modules
$ webpack --display-modules --display-chunks
```

**--display-modules 和 --display-chunks的作用**

by default Webpack hides modules that are not yours. To see all the modules compiled by Webpack, we can pass the --display-modules flag.

run webpack with the --display-chunks flag to see what modules are in which chunks.

## 安装需要的模块：

### lodash

```bash
npm install --save lodash
```

[lodash](https://lodash.com/)是一个Javascript工具库。

### 安装babel：

```bash
$ npm install babel-core babel-loader babel-preset-es2015 babel-preset-react babel-preset-stage-2 --save-dev
```

然后在文件目录下创建 `.babelrc` 文件。详细配置参考：[babel](https://babeljs.io/)

* [babel](https://babeljs.io/)是一个Javascript代码转换器，可以把你写的符合 ECMAScript 6 标准的代码完美地转换为 ECMAScript 5 标准的代码，并且可以确保良好地运行在所有主流 JavaScript 引擎中。

### 安装eslint：

```bash
$ npm install eslint eslint-loader babel-eslint eslint-plugin-react --save-dev
```

在团队协作中，为避免低级 Bug、产出风格统一的代码，会预先制定编码规范。使用 Lint 工具和代码风格检测工具，则可以辅助编码规范执行，有效控制代码质量。在以前的项目中，我们选择 JSHint 和 JSCS 结合使用，使用 React JSX 语法时，却遇到了问题：JSHint 不支持 JSX 语法，以有了 ESLint。
[eslint](http://eslint.org/)

### 安装react：

```bash
npm install react react-dom react-router --save
```

### 安装antd

```bash
npm install antd --save
```

## 安装需要的webpack loader：

```bash
$ npm install css-loader postcss-loader less-loader style-loader html-loader sass-loader node-sass --save-dev
$ npm install url-loader file-loader --save-dev
$ npm install baggage-loader --save-dev
```

### loader配置说明：

```javascript
require("./loader!./dir/file.txt");
// => uses the file "loader.js" in the current directory to transform
//    "file.txt" in the folder "dir".

require("jade!./template.jade");
// => uses the "jade-loader" (that is installed from npm to "node_modules")
//    to transform the file "template.jade"
//    If configuration has some transforms bound to the file, they will still be applied.

require("!style!css!less!bootstrap/less/bootstrap.less");
// => the file "bootstrap.less" in the folder "less" in the "bootstrap"
//    module (that is installed from github to "node_modules") is
//    transformed by the "less-loader". The result is transformed by the
//    "css-loader" and then by the "style-loader".
//    If configuration has some transforms bound to the file, they will not be applied.
```


## 安装需要的webpack plugin：

```bash
$ npm install clean-webpack-plugin --save-dev
$ npm install extract-text-webpack-plugin --save-dev
$ npm install html-webpack-plugin
```

* [clean-webpack-plugin](https://github.com/johnagan/clean-webpack-plugin): A webpack plugin to remove/clean your build folder(s) before building.
* [extract-text-webpack-plugin](https://github.com/webpack/extract-text-webpack-plugin): It moves every require("style.css") in entry chunks into a separate css output file. So your styles are no longer inlined into the javascript, but separate in a css bundle file (styles.css).
* [html-webpack-plugin](https://github.com/ampedandwired/html-webpack-plugin): Simplifies creation of HTML files to serve your webpack bundles


## 参考：

* [webpack 入门指南](http://www.cnblogs.com/vajoy/p/4650467.html)
* [webpack-dev-server](https://webpack.github.io/docs/webpack-dev-server.html)
* [Webpack-dev-server结合后端分缶的热替换配置](http://www.jianshu.com/p/8adf4c2bfa51)
* [WEBPACK DEV SERVER](http://www.jianshu.com/p/941bfaf13be1)
* [webpack your bags](http://blog.madewithlove.be/post/webpack-your-bags/)
* [lodash](https://lodash.com/)
* [babel](https://babeljs.io/)
* [Babel-现在开始使用 ES6](http://www.cnblogs.com/whitewolf/p/4357916.html)
* [eslint](http://eslint.org/)
* [ESLint 使用入门](http://www.tuicool.com/articles/7JZZJzn)
