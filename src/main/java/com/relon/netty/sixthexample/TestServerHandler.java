package com.relon.netty.sixthexample;

import com.relon.netty.sixthexample.domain.SixExample;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<SixExample.SixMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SixExample.SixMessage msg) throws Exception {
        SixExample.SixMessage.MessageType messageType = msg.getMessageType();
        if (messageType == SixExample.SixMessage.MessageType.PersonType) {
            SixExample.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        } else if (messageType == SixExample.SixMessage.MessageType.DogType) {
            SixExample.Dog dog = msg.getDog();
            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        } else {
            SixExample.Cat cat = msg.getCat();
            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }
    }
}
