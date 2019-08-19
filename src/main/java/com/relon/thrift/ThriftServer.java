package com.relon.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

public class ThriftServer {
    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg1 = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        arg1.protocolFactory(new TCompactProtocol.Factory());
        arg1.transportFactory(new TFramedTransport.Factory());
        arg1.processorFactory(new TProcessorFactory(processor));

        THsHaServer tHsHaServer = new THsHaServer(arg1);
        System.out.println("Thrift Server Started!!!");

        tHsHaServer.serve();
    }
}
