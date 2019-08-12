### 为什么使用两个EventLoopGroup？
* bossXX负责从客户端接收
* workerXX负责进行逻辑处理

### channelRead0()
接收请求，然后向客户端返回相应

一个可以查看框架方法执行时机，可以采用重新实现方法的方式，在子类中加入
自己的代码，通过这种方式查看方法在什么时候调用

### netty可以做什么
* 作为http服务器
* 进行WebSocket编程

### WebSocket
客户端与服务器之间保持长连接，一旦连接建立，双方就可以直接互相传递内容本身

### handler与childHandler有什么区别？
childHandler会对workerLoopGroup起作用

### 贯穿始终的channel究竟是什么？作用是什么？
### ChannelInboundHandlerAdapter类中的方法分别是什么时候调用的？所代表的含义是什么？
### 飞行模式或者强制关机之后，handlerRemoved()方法不会被调用
### 不同的handler是使用了**责任链设计模式**
### WebSocket
#### 解决Http协议存在的一些问题，比如
* 无状态
* 无需像http一样传输内容为头信息和body信息，可以只传输内容，节省带宽
* 全双工网络数据传输

### RPC
* 多数RPC框架是跨语言的
    * 1. 定义一个接口说明文件，描述对象、对象成员、接口方法等一系列信息
    * 2. 通过RPC框架所提供的编译器，将接口文件编译成具体的语言文件
    * 3. 在客户端与服务端分别引入RPC编译器生成的文件，即可像调用本地方法一样调用远程方法