# 第二章笔记  Java环境搭建和程序初体验

[TOC]

#### 安装任何软件应该注意的两点

1. 不要安装捆绑软件。

2. 为软件手动指定安装位置，搭好地基，方便计算机和软件的管理。

   ![](%E7%AC%AC%E4%BA%8C%E7%AB%A0%E7%AC%94%E8%AE%B0%20%20Java%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA%E5%92%8C%E7%A8%8B%E5%BA%8F%E5%88%9D%E4%BD%93%E9%AA%8C.assets/%E7%AC%94%E8%AE%B01.JPG)

​               `Java文件夹 （装JRE，JDK）；`

​               `JavaSoftware文件夹 （装与Java相关的软件，例如eclipse，IDEA）；`

​               `安装软件文件夹（装待安装的程序，压缩包，镜像文件等.exe或.zip文件）。`

​               `MarkDown文件夹(装与MarkDown相关的软件，例如Typora)`

#### 配置环境变量

```mermaid
graph LR
A(右键我的电脑) -->B(属性)
    B --> C(高级系统设置)
    C --> D(环境变量)
    D --> E(新建用户变量Path:值为JDK中bin目录位置)
    E --> F(完成)
```

#### IDEA常用快捷键

##### 文件(File)

| 快捷键                                                       | 说明             |
| ------------------------------------------------------------ | ---------------- |
| <kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>S</kbd>                  | 打开设置窗口     |
| <kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>Shift</kbd>+<kbd>S</kbd> | 打开项目结构窗口 |
| <kbd>Ctrl</kbd>+<kbd>S</kbd>                                 | 全部保存         |
| <kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>Y</kbd>                  | 从磁盘中全部加载 |

##### 编辑(Edit)

| 快捷键                                        | 说明                   |
| --------------------------------------------- | ---------------------- |
| <kbd>Ctrl</kbd>+<kbd>Z</kbd>                  | 撤销                   |
| <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>Z</kbd> | 重做撤销的操作         |
| <kbd>Ctrl</kbd>+<kbd>X</kbd>                  | 剪切                   |
| <kbd>Ctrl</kbd>+<kbd>C</kbd>                  | 粘贴                   |
| <kbd>Ctrl</kbd>+<kbd>A</kbd>                  | 全选                   |
| <kbd>Ctrl</kbd>+<kbd>F</kbd>                  | 在当前页面查找相关内容 |
| <kbd>Ctrl</kbd>+<kbd>W</kbd>                  | 扩展选区               |
| <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>W</kbd> | 收缩选区               |
| <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>U</kbd> | 切换大小写             |

##### 构建(Build)

| 快捷键                                         | 说明     |
| ---------------------------------------------- | -------- |
| <kbd>Ctrl</kbd>+<kbd>F9</kbd>                  | 构建项目 |
| <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>F9</kbd> | 重新编译 |

##### 运行(Run)

| 快捷键                                         | 说明         |
| ---------------------------------------------- | ------------ |
| <kbd>Shift</kbd>+<kbd>F10</kbd>                | 运行当前代码 |
| <kbd>Shift</kbd>+<kbd>F9</kbd>                 | 调试当前代码 |
| <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>F8</kbd> | 查看断点     |

