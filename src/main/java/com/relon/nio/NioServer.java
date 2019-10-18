package com.relon.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @ClassName: NioServer
 * @Description: 比较完善的NIO服务端
 * @Author: relon
 * @Date: 2019/9/16 15:43
 * @Version: v1.0 文件初始创建
 */

public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap();
    private static ByteBuffer writeBuffer;

    public static void main(String[] args) throws IOException {
        // 创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 配置成非阻塞
        serverSocketChannel.configureBlocking(false);
        // 获取socket对象绑定端口号
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                int select = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    try {
                        final SocketChannel client;
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            // 注册到selector上
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            // 保存客户端连接信息
                            String key = "[" + UUID.randomUUID().toString() + "]";
                            System.out.println("Get client connection:" + client);
                            clientMap.put(key, client);

                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel)selectionKey.channel();
                            int byteRead = 0;

                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int read = client.read(buffer);
                            if (read > 0) {
                                buffer.flip();
                                //Charset charset = Charset.forName("utf-8");
                                String receivedMessage = new String(buffer.array(), 0, read);
                                System.out.println(client + ": " + receivedMessage);

                                String senderKey = "";
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();
                                    writeBuffer = ByteBuffer.allocate(1024);
                                    writeBuffer.put((senderKey + ":" + receivedMessage).getBytes());
                                    value.write(writeBuffer);
                                }
                            }else {
                                System.out.println("客户端连接已关闭");
                                selectionKey.cancel();
                            }

                        }
                        selectionKeys.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                });



            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
