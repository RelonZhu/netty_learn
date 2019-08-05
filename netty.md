为什么使用两个EventLoopGroup？
bossXX负责从客户端接收
workerXX负责进行逻辑处理

channelRead0()
接收请求，然后向客户端返回相应