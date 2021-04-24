package com.cosmos.tank.net;

import com.cosmos.tank.Dir;
import com.cosmos.tank.Group;

import java.io.*;
import java.util.UUID;

public class TankJoinMsg {
    public int x, y;
    public Dir dir;
    public boolean moving;
    public Group group;
    public UUID id;

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }

    public TankJoinMsg(){

    }

    public void parse(byte[] bytes){
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        // 先读TYPE消息， 根据TYPE消息处理不同的消息
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(), dis.readLong());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] toBytes(){
        // 定义一个字节数组输出流
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();  // 相当于在内存中开辟了一个字节数组
            dos = new DataOutputStream(baos); // DataOutputStream嵌套在ByteArrayOutputStream中，可以方便直接写数据，
            dos.writeInt(x); // 4个字节
            dos.writeInt(y);
            dos.writeInt(dir.ordinal()); // ordinal获取dir【enum】的下标
            dos.writeBoolean(moving);  // boolean 1bit ->  传输在网络占一个字节
            dos.writeInt(group.ordinal());
            // uuid 128 bit -> 16 个字节
            // Long -> 8个 字节
            dos.writeLong(id.getMostSignificantBits()); // getMostSignificantBits 获取高64 bit往外写
            dos.writeLong(id.getLeastSignificantBits());// 低64 bit 往外写
            dos.flush();
            bytes = baos.toByteArray(); // 将写入到内存的数组转换为 byte数组
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (dos != null){
                    dos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return bytes;
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                ", id=" + id +
                '}';
    }
}
