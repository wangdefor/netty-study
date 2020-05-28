package org.example.thrift.service;

import org.apache.thrift.TException;
import org.example.thrift.CaculatorService;
import org.example.thrift.MyException;
import org.example.thrift.NewException;
import org.example.thrift.PersonData;

/**
 * @ClassName MyService
 * @Description MyService
 * @Date 2020/5/28 11:34
 * @Author wangyong
 * @Version 1.0
 **/
public class MyService implements CaculatorService.Iface {

    @Override
    public String ping() throws TException {
        return "这是一个完美的thrift程序";
    }

    @Override
    public int add(int num1, int num2) throws TException {
        return num1 + num2;
    }

    @Override
    public long calculate(int num1, int num2, PersonData data) throws MyException, NewException, TException {
        return num1 + num2;
    }

    @Override
    public PersonData getPerson(int num, String name) throws TException {
        PersonData data = new PersonData();
        data.setComment("你好");
        data.setNum1(1);
        return data;
    }
}
