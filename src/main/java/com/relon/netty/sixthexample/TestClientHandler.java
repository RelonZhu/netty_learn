package com.relon.netty.sixthexample;

import com.relon.netty.sixthexample.domain.SixExample;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<SixExample.SixMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SixExample.SixMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int random = new Random().nextInt(3);
        SixExample.SixMessage.Builder message = null;
        if (random == 0) {
            message = SixExample.SixMessage.newBuilder();
            message.setMessageType(SixExample.SixMessage.MessageType.PersonType)
                    .setPerson(
                            SixExample.Person.newBuilder()
                                    .setName("张三")
                                    .setAge(20).
                                    setAddress("北京").build());
        } else if (random == 1) {
            message = SixExample.SixMessage.newBuilder();
            message.setMessageType(SixExample.SixMessage.MessageType.DogType)
                    .setDog(
                            SixExample.Dog.newBuilder()
                                    .setName("大黄狗")
                                    .setAge(2).build()).build();
        } else {
            message = SixExample.SixMessage.newBuilder();
            message.setMessageType(SixExample.SixMessage.MessageType.CatType)
                    .setCat(
                            SixExample.Cat.newBuilder()
                                    .setName("小奶猫")
                                    .setCity("上海").build()).build();
        }

        ctx.channel().writeAndFlush(message);
    }
}
