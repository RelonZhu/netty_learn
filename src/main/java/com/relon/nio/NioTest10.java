package com.relon.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NioTest10
 * @Description: FileLock
 * @Author: zhuruilong
 * @Date: 2019/9/3 14:23
 * @Version: v1.0 文件初始创建
 */

public class NioTest10 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile rw = new RandomAccessFile("niotest9.txt", "rw");
        FileChannel channel = rw.getChannel();

        channel.lock(3, 6, true);
    }
}
