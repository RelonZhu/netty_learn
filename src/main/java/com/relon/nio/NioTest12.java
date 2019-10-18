package com.relon.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: NioTest12
 * @Description: Selector相关
 * @Author: zhuruilong
 * @Date: 2019/9/3 16:17
 * @Version: v1.0 文件初始创建
 */

public class NioTest12 {
    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();
        for (int i = 0; i < ports.length; ++i) {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            ServerSocket socket = channel.socket();
            SocketAddress address = new InetSocketAddress(ports[i]);
            socket.bind(address);

            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口：" + ports[i]);
        }

        while (true) {
            int numbers = selector.select();
            System.out.println("numbers:" + numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectKeys:" + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);

                    accept.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("获取客户端连接：" + accept);
                } else if (next.isReadable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    int byteRead = 0;
                    while (true) {
                        ByteBuffer buffer = ByteBuffer.allocate(512);
                        buffer.clear();
                        int read = channel.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        buffer.flip();
                        channel.write(buffer);
                        byteRead += read;

                    }
                    System.out.println("读取：" + byteRead + ", 来自于：" + channel);
                    iterator.remove();
                }
            }

        }
    }
}
