package com.cosmos.tank.net;

import com.cosmos.tank.Dir;
import com.cosmos.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception{
        if (in.readableBytes() < 8) return; // TCP

        in.markReaderIndex();// 读指针初始位置；记录从哪个位置开始读
        MsgType msgType = MsgType.values()[in.readInt()]; // 类型
        int length = in.readInt(); // 消息体长度

        if (in.readableBytes() < length){ // 读取消息体
            in.resetReaderIndex(); // 读完消息体后，重置读指针回到初始位置
            return;
        }
        // 消息处理
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Msg msg = null;
        switch (msgType){
            case TankJoin:
                msg = new TankJoinMsg();
                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();
                break;
            default:
                break;
        }
        msg.parse(bytes);
        out.add(msg);

    }
}
