package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NioDreTest
 * @Description NioDreTest
 * @Date 2020/6/30 9:23
 * @Author wangyong
 * @Version 1.0
 **/
public class NioDreTest {


    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input2.txt");
        FileOutputStream outputStream = new FileOutputStream("output2.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
        while (true) {
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
    }


}
