package com.cosmos.tank.net;

public abstract class Msg {
    public abstract void handler();
    public abstract byte[] toBytes();
}
