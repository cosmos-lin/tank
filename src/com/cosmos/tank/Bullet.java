package com.cosmos.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
//    private static Dir dir; // 注意如果static修饰，子弹会随tank一起改变方向
    private Dir dir;
    private Group group = Group.BAD;
    private final static int SPEED = 10;
    GameModel gm;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    public boolean living = true;

    Rectangle rect = new Rectangle();

    // 构造方法
    Bullet(int x, int y, Dir dir, GameModel gm, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        //
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        // 将子弹添加到弹夹
        gm.bullets.add(this);
    }

    // 封装自动paint(画出自己的位置）
    public void paint(Graphics g){

        // living为false,删除子弹本身对象
        if (!living) gm.bullets.remove(this);

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
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();

            int eX = tank.getX() + Tank.WIDTH/2 - EXplode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - EXplode.HEIGHT/2;
            // 将碰撞爆炸加入到explodes队列
            gm.explodes.add(new EXplode(eX, eY, gm));
        }
    }

    private void die() {
        living = false;
    }
}
