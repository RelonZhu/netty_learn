package com.relon.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer
 * 可以随时调用asReadOnlyBuffer()返回只读buffer，但是不能将只读buffer转换为读写方法
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

    }
}
