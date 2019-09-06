package com.relon.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
/**
 * @ClassName: NioTest9
 * @Description: 内存文件映射 MappedByteBuffer
 * @Author: zhuruilong
 * @Date: 2019/9/3 14:21
 * @Version: v1.0 文件初始创建
 */
public class NioTest9 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile rw = new RandomAccessFile("niotest9.txt", "rw");
        FileChannel channel = rw.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(0, (byte)'a');
        map.put(3, (byte)'b');


    }
}
