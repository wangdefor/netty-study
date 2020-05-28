package org.example.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.example.thrift.service.MyService;

/**
 * @ClassName ThriftServer
 * @Description TODO
 * @Date 2020/5/28 11:48
 * @Author wangyong
 * @Version 1.0
 **/
public class ThriftServer {


    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);

        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        CaculatorService.Processor<MyService> processor = new CaculatorService.Processor<>(new MyService());


        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer ser = new THsHaServer(arg);
        System.out.println("Thrift server start");
        ser.serve();
    }
}
