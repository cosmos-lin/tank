package com.cosmos.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            // 加载配置文件
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {

        if (props == null) return null;
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(props.get("initTankCount"));
    }
}
