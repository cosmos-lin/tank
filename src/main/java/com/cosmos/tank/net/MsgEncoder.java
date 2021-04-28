package com.cosmos.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception{
        buf.writeInt(msg.getMsgType().ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length); // 先写bytes 长度  -> 消息格式： 消息类型+消息长度+消息体
        buf.writeBytes(bytes);
    }
}
