package com.cosmos.tank.net;

import com.cosmos.tank.Dir;
import com.cosmos.tank.Group;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.util.UUID;


public class Client {
    private Channel channel = null;

    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();
        try {
            ChannelFuture f = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 9999);

            f.addListener(new ChannelFutureListener(){
                @Override
                public void operationComplete(ChannelFuture future) throws Exception{
                    if (!future.isSuccess()){
                        System.out.println("not connected");
                    } else {
                        System.out.println("connected");
                        // initialize the channel
                        channel = future.channel();
                    }
                }
            });
            f.sync();

            f.channel().closeFuture().sync();
            System.out.println("客户端退出");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
    public void send(String msg){
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.connect();
    }

    public void closeConnect(){
        this.send("_bye_");
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception{
        ch.pipeline() // 处理接收和发送的信息， encode decode
                .addLast(new TankJoinMsgEncoder())
                .addLast(new TankJoinMsgDecoder())
                .addLast(new ClientHandler());
    }
}

class ClientHandler extends SimpleChannelInboundHandler<TankJoinMsg>{
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception{
//        ByteBuf buf = null;
//        try {
//            buf = (ByteBuf) msg;
//            byte[] bytes = new byte[buf.readableBytes()];
//            buf.getBytes(buf.readerIndex(), buf);
//            String msgAccepted = new String(bytes);
////            ClientFrame.INSTANCE.updateText(msgAccepted);
//        }finally {
//            if (buf != null) ReferenceCountUtil.release(buf);
//        }
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        ctx.writeAndFlush(new TankJoinMsg(5, 8, Dir.DOWN, true, Group.GOOD, UUID.randomUUID()));
    }
}