package org.example.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @ClassName ThriftClient
 * @Description Client
 * @Date 2020/5/28 14:09
 * @Author wangyong
 * @Version 1.0
 **/
public class ThriftClient {

    public static void main(String[] args) throws TException {
        TTransport transport = new TFastFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(transport);
        CaculatorService.Client client = new CaculatorService.Client(protocol);
        transport.open();
        PersonData name = client.getPerson(1, "name");
        System.out.println(name.getComment());
        int add = client.add(1, 2);
        transport.close();
    }

}
