package com.relon.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);

        buffer.flip();

        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.println("character:" + (char)b);
        }
    }
}
