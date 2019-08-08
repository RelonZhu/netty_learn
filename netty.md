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