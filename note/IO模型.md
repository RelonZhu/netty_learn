# IO体系
## InputStream

## OutputStream

## Input Stream Chain
file -> FileInputStream(获取输入字节) -> BufferedInputStream(增加缓冲) -> DataInputStream(增加读取java基本数据类型功能) -> 数据

## Output Stream Chain
数据 -> DataOutputStream -> BufferedOutputStream -> FileOutputStream

## IO库的设计原则
装饰者设计模式（包装模式）
- 动态扩展一个对象的功能
装饰者角色
- 抽象构建角色：抽象接口，规范准备接收附加责任的对象
- 具体构件角色：定义一个将要接收附加责任的类
- 装饰角色：持有一个构件对象的引用，并定义一个与抽象构件接口一致的接口
- 具体装饰角色：负责给构件对象贴上附加的责任

# NIO体系


# java.io VS java.nio
## java.io
 - java.io中最核心的概念——流(Stream)，数据直接从流中读入程序
 - java中一个流要么是输入流，要么是输出流，不能共存
## java.nio
 - java.nio三个核心概念
    - Selector
    - Channel：指的是可以向其写入数据或是从中读取数据的对象，Channel是双向的，
    由于其是双向的，所以可以真实的反应操作系统的情况
    - Buffer:本身是一块内存，底层实现是数据，读写基于buffer实现的
        - 除了数组之外，Buffer还提供了对数据的结构化访问方式，并且可以追踪到系统的读写过程
        - java中各种原生数据类型都有各自对应的Buffer类型
        - 三个重要状态属性的含义：position、limit、capacity
            - capacity:Buffer包含的元素总数，永远不会为负数，也不会被修改
            - limit：第一个不能够被读取或写入的元素，不会为负数且不会大于capacity
            - position：将要允许被读取或写入的下一个元素，不会为负数且不会大于limit
        **调用flip()方法之后**
            - position：由原位置转到buffer开始的位置
            - limit：由原位置转到position所在位置的前一个位置
 - 数据来自于Channel，首先数据要先从channel中读入Buffer，然后再读入程序，不允许直接
    
 从Channel中将数据写入程序
 - 进行读写切换前要调用flip()方法，进行读写状态反转
 
 
 ## 通过NIO进行文件读写的三个步骤
 ### 读文件
 1. 通过文件创建FileInputStream对象，并通过该对象的getChannel方法获取channel对象
 2. 创建字节数组buffer对象用于存储文件字节内容
 3. 调用channel对象的read(buffer)方法把文件内容写入到buffer
 ### 写文件
 1. 创建FileOutputStream对象
 2. 调用buffer的flip()方法，切换状态
 3. 利用步骤1中所创建对象的getChannel()方法
 4. 调用channel对象的write()方法，读出buffer中的内容写入到文件
 
 ## 绝对方法与相对方法
 * 相对方法
    * limit和position会在操作时被考虑到
 * 绝对方法
    * 绝对方法会完全忽略掉limit与position
    
 ## directBuffer VS HeapByteBuffer
 * 使用HeapByteBuffer，buffer数据是使用的JVM的堆内存，当需要与外部IO设备交互时，需要将堆内存中的数据拷贝到
 操作系统中的一块内存空间(堆外内存)中，然后再与IO设备交互
 * 使用DirectByteBuffer，会使用堆内存保存对象，而且也会在堆外内存中维护一个备份，直接使用堆外内存，中间少了一次拷贝过程，此就称为“零拷贝”
 
 ## buffer中的Scattering和Gathering
 * Scattering：将一个channel中的数据读到多个buffer中
 * Gathering：将多个buffer(buffer数组)中的数据读到一个channel中
 
 ## Tips
 1. 使用Windows下Telnet功能时，发现是逐个字符发送数据，而在Unix系统下直到按下回车才会发送数据，此时如果需要按行发送数据，那么只需要在
 Telnet界面使用```ctl + ]```进入命令行格式，使用命令```send message-content```就可以发送整行数据
 
 ### zero-copy
 [零拷贝](./zero%20copy.md)