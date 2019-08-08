package com.relon.netty.thirdExample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg + "\n");
            } else {
                ch.writeAndFlush("[自己]" + "发送的消息:" + msg + "\n" );
            }
        });
    }

    /**
     * 该方法就表示客户端与服务端的连接已经建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 获取当前与服务器端的通道（channel）
        Channel channel = ctx.channel();
        // 对channelGroup中所有的channel进行消息广播
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "已加入");

        // 将每个已经建立连接的channel放入channelGroup
        channelGroup.add(channel);
    }

    /**
     * 连接断开时，会调用该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 在channelGroup中把当前channel移除
        // channelGroup.remove(channel); 如果连接断掉，就无须手动移除，会自动从channelgroup中移除
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "已离开");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "已上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "已下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
