package com.relon.charset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @ClassName: CharSetTest1
 * @Description:
 * @Author: relon
 * @Date: 2019/9/24 16:24
 * @Version: v1.0 文件初始创建
 */

public class CharSetTest1 {
    public static void main(String[] args) throws IOException {
        String inputFile = "D:\\myproject\\netty_learning\\src\\main\\java\\com\\relon\\charset\\input.txt";
        String outputFile = "D:\\myproject\\netty_learning\\src\\main\\java\\com\\relon\\charset\\output.txt";

        RandomAccessFile input = new RandomAccessFile(inputFile, "r");
        RandomAccessFile output = new RandomAccessFile(outputFile, "rw");

        FileChannel inputChannel = input.getChannel();
        FileChannel outputChannel = output.getChannel();

        MappedByteBuffer inputData = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, new File(inputFile).length());

        Charset charset = Charset.forName("utf-8");

        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = decoder.decode(inputData);
        ByteBuffer byteBuffer = encoder.encode(charBuffer);

        outputChannel.write(byteBuffer);

        input.close();
        output.close();
    }
}
