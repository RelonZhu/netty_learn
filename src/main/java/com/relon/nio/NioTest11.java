package com.relon.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @ClassName: NioTest11
 * @Description: Buffer的Scattering和Gathering
 * @Author: zhuruilong
 * @Date: 2019/9/3 14:24
 * @Version: v1.0 文件初始创建
 */
public class NioTest11 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        SocketAddress address = new InetSocketAddress(8899);
        channel.bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffer = new ByteBuffer[3];
        byteBuffer[0] = ByteBuffer.allocate(2);
        byteBuffer[1] = ByteBuffer.allocate(3);
        byteBuffer[2] = ByteBuffer.allocate(4);
        SocketChannel accept = channel.accept();

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long r = accept.read(byteBuffer);
                byteRead += r;
                System.out.println("byteRead:" + byteRead);

                Arrays.asList(byteBuffer).stream().map(byteBuffer1 -> "position" + byteBuffer1.position() + ",limit:" + byteBuffer1.limit() + ",capacity:" + byteBuffer1.capacity())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffer).forEach(byteBuffer1 -> byteBuffer1.flip());

            long byteWritten = 0;
            while (byteWritten < messageLength) {
                long r = accept.write(byteBuffer);
                byteWritten += r;
            }

            System.out.println("byteRead:" + byteRead + ", byteWritten:" + byteWritten);

            Arrays.asList(byteBuffer).forEach(byteBuffer1 -> byteBuffer1.clear());
        }
    }
}
