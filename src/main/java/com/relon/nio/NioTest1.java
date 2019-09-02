package com.relon.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < 5; ++i) {
            int randomNum = new SecureRandom().nextInt(20);
            buffer.put(randomNum);
        }
        // 开始创建buffer时，limit = capacity
        System.out.println("before flip limit:" + buffer.limit());

        buffer.flip();

        // limit变到原来position的位置
        System.out.println("after flip limit:" + buffer.limit());

        while (buffer.hasRemaining()) {
            System.out.println("position:" + buffer.position());
            System.out.println("limit:" + buffer.limit());
            System.out.println("capacity");
            System.out.println(buffer.get());
        }
    }
}
