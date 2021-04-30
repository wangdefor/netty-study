package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NioMappedTest
 * @Description
 * @Date 2020/6/30 15:32
 * @Author wangyong
 * @Version 1.0
 **/
public class NioMappedTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");
        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put("sdds".getBytes());
        map.flip();
        randomAccessFile.close();
    }
}
