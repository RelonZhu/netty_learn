### Thrift传输格式
* TBinaryProtocol - 二进制格式
* TCompactProtocol - 压缩格式
* TJSONProtocol - JSON格式
* TSimpleJSONProtocol - 提供JSON只写协议，生成的文件很容易通过脚本解析，
缺少元数据信息
* TDebugProtocol - 使用易懂的可读的文本格式，以便debug

### Thrift数据传输方式
* TSocket - 阻塞式Socket
* TFrameTransport - 以Frame为单位进行传输，非阻塞式服务中使用
* TFileTransport - 以文件形式进行传输
* TMemoryTransport - 将内存用于I/O，java实现时内部实际使用了简单的ByteArrayOutputStream
* TZlibTransport - 使用zlib进行压缩，与其他传输方式联合使用，当前无java实现

？frame是什么

###Thrift支持的服务模型
* TSimpleServer - 简单的单线程服务模型，常用于测试
* TThreadPoolServer - 多线程服务模型，使用标准的阻塞式IO
* TNonblockingServer - 多线程服务模型，使用非阻塞是IO（需使用TFrameTransport数据传输方式）
* THsHaServer - THsHaServer引入线程池处理，其模型把读写任务放到线程池去处理；Half-sync/Half-async
的处理模式，Half-async是在处理IO事件上（accept/read/read/write io）, Half-sync用于handler
对rpc的同步处理


### python启动服务端时，报出以下错误
```
OSError: [WinError 10013] 以一种访问权限不允许的方式做了一个访问套接字的尝试。
```
错误原因是因为端口已经占用，因为java服务端已经启动了一个绑定端口为8899的服务

### 启动Python服务端之后，启动java客户端失败，提示连接拒绝
修改服务端中
```socket = TSocket.TServerSocket(port=8899)```

改为

```socket = TSocket.TServerSocket(host='127.0.0.1', port=8899)```

就可以正常访问，有资料说是因为Python为TCPV6，但java使用TCP，不甚理解，有待了解


### 内网中的服务调用首先最好考虑RPC方式调用