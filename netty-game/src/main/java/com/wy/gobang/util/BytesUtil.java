package com.wy.gobang.util;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author wangyong
 * @Classname BytesUtil
 * @Description 字节工具类
 * @Date 2021/6/25 11:56
 */
public class BytesUtil {

    public static byte[] objectToBytes(Object object) throws IOException {
        Hessian2Output output = null;
        //序列化所用
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            output = new Hessian2Output(os);
            //写入object对象中
            output.writeObject(object);
            output.flush();
            //写入序列化后的数组
            byte[] bytes = os.toByteArray();
            return bytes;
        }finally {
            if(output != null){
                output.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(75 / 50);
    }

    public static Object bytesToObject(byte[] bytes) throws IOException {
        //序列化所用
        Hessian2Input input = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            input = new Hessian2Input(bis);
            return input.readObject();
        }finally {
            if(input != null){
                input.close();
            }
        }


    }
}
