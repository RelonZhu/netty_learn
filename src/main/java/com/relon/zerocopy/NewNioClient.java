package com.relon.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewNioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress(8899));
        socketChannel.configureBlocking(true);

        String fileName = "C:\\Users\\zrl13\\Desktop\\vc_redist.x64.rar";

        FileChannel channel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();

        long transferToCount = channel.transferTo(0, channel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferToCount);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
