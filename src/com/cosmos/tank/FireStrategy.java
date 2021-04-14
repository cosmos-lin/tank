package com.cosmos.tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
