package test;

import com.cosmos.tank.Dir;
import com.cosmos.tank.Group;
import com.cosmos.tank.TankFrame;
import com.cosmos.tank.net.MsgEncoder;
import com.cosmos.tank.net.MsgType;
import com.cosmos.tank.net.TankJoinMsg;
import com.cosmos.tank.net.MsgDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TankJoinMsgCodecTest {
    @Test
    void testEncoder(){
        // 定义虚拟网络连接channel
        EmbeddedChannel ch = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(TankFrame.INSTANCE.getMainTank());
        // 往channel的 pipeline上添加Encode
        ch.pipeline().addLast(new MsgEncoder());
        // 往channel中写数据
        ch.writeOutbound(msg);
        // 将数据从channel中读出来
        ByteBuf buf = (ByteBuf) ch.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankJoin, msgType);

        int length = buf.readInt();
        assertEquals(33, length);

        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal]; // Dir.values() -> 数组
        boolean moving = buf.readBoolean();
        int groupOrdinal = buf.readInt();
        Group g = Group.values()[groupOrdinal];
        UUID uuid = new UUID(buf.readLong(), buf.readLong());

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Dir.DOWN, dir);
        assertEquals(true, moving);
        assertEquals(Group.BAD, g);
        assertEquals(id, uuid);
    }

    @Test
    void textDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(TankFrame.INSTANCE.getMainTank());
        ch.pipeline().addLast(new MsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankJoin.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());
        // 读
        TankJoinMsg msgR = (TankJoinMsg) ch.readInbound();

        assertEquals(5, msgR.x);
        assertEquals(10, msgR.y);
        assertEquals(Dir.DOWN, msgR.dir);
        assertEquals(true, msgR.moving);
        assertEquals(Group.BAD, msgR.group);
        assertEquals(id, msgR.id);
    }
}
