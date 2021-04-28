package test;

import com.cosmos.tank.Dir;
import com.cosmos.tank.net.MsgDecoder;
import com.cosmos.tank.net.MsgEncoder;
import com.cosmos.tank.net.MsgType;
import com.cosmos.tank.net.TankStartMovingMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TankStartMovingMsgCodecTest {
    @Test
    public void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(id, 5, 10, Dir.LEFT);
        ch.pipeline().addLast(new MsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankStartMoving, msgType);

        int length = buf.readInt();
        assertEquals(28, length);

        UUID uuid = new UUID(buf.readLong(), buf.readLong());
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal];

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Dir.LEFT, dir);
        assertEquals(id, uuid);
    }

    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(id, 5, 10, Dir.LEFT);
        ch.pipeline().addLast(new MsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankStartMoving.ordinal()); // MsgType消息类型下标
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length); // 消息长度
        buf.writeBytes(bytes); // 消息体

        ch.writeInbound(buf.duplicate());
        TankStartMovingMsg msgR = (TankStartMovingMsg) ch.readInbound();

        assertEquals(5, msgR.getX());
        assertEquals(10, msgR.getY());
        assertEquals(Dir.LEFT, msgR.getDir());
        assertEquals(id, msgR.getId());
    }
}
