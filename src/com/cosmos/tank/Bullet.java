package com.cosmos.tank;

import com.cosmos.tank.abstractfactory.BaseBullet;
import com.cosmos.tank.abstractfactory.BaseTank;

import java.awt.*;

public class Bullet extends BaseBullet {
    private int x,y;
//    private static Dir dir; // 注意如果static修饰，子弹会随tank一起改变方向
    private Dir dir;
    private Group group = Group.BAD;
    private final static int SPEED = 10;
    TankFrame tf = null;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    public boolean living = true;

    Rectangle rect = new Rectangle();

    // 构造方法
    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        //
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    // 封装自动paint(画出自己的位置）
    public void paint(Graphics g){

        // living为false,删除子弹本身对象
        if (!living) tf.bullets.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        moving();

    }

    // 封装子弹移动方法
    private void moving() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
        // 定义子弹存活条件
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) living = false;

        // update rect
        rect.x = x;
        rect.y = y;
    }

    // 定义碰撞检测方法
    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup()) return;
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();

            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            // 将碰撞爆炸加入到explodes队列
//            tf.explodes.add(new Explode(eX, eY, tf));
            tf.explodes.add(tf.gf.createExplode(eX, eY, tf));
        }
    }

    private void die() {
        living = false;
    }
}
