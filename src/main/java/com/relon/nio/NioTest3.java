package com.relon.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        byte[] bytes = "hello world nio test2".getBytes();
        buffer.put(bytes);
        // 读写转换必须调用该方法
        buffer.flip();
        channel.write(buffer);

    }
}
