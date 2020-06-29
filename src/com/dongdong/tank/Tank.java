package com.dongdong.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank extends GameObject {

	public static final int TANK_HEIGHT = ResourceMgr.tankD.getHeight();
	public static final int TANK_WIDTH = ResourceMgr.tankD.getWidth();
	int x = 200, y = 200;
	Dir dir = Dir.DOWN;
	private int SPEED = 5;
	boolean isMoving = true;
	boolean living = true;
	Random random = new Random();
	TankFram tf;
	Group group;
	Rectangle rectangle = new Rectangle();
	private int oldX,oldY;
	FireStrategy fs = null;;

	public Tank(int x, int y, Dir dir, Group g, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = g;
		this.SPEED = speed;
		rectangle.height = TANK_HEIGHT;
		rectangle.width = TANK_WIDTH;
		GameMoudel.INSTANCE.add(this);
	}

	
	public Rectangle getRectangle() {
		return rectangle;
	}


	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public int getSpeed() {
		return SPEED;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if (!living) {
			GameMoudel.INSTANCE.remove(this);
			return;
		}
		switch (dir) {
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;

		default:
			break;
		}
		move();

	}

	/**
	 * 移动坦克
	 */
	private void move() {
		oldX=x;
		oldY=y;
		if (!isMoving) {
			return;
		}
//		if (!gm.tanks.contains(this)) {
//			gm.tanks.add(this);
//		}
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		default:
			break;
		}

		if (this.group == Group.BAD && random.nextInt(100) > 95) {
			this.fire();
		}
		if (this.group == Group.BAD && random.nextInt(100) > 98) {
			randomDir();
		}
		boundsCheck();
		rectangle.x = this.x;
		rectangle.y = this.y;
	}
	public void back() {
		y=oldY;
		x=oldX;
	}
	

	// 边界检测 不能让坦克 开出去
	private void boundsCheck() {
		if (this.x < 2)
			x = 2;
		if (this.y < 28)
			y = 28;
		if (this.y > TankFram.GAME_HEIGHT - TANK_HEIGHT)
			y = TankFram.GAME_HEIGHT - TANK_HEIGHT;
		if (this.x > TankFram.GAME_WIDTH - TANK_WIDTH)
			x = TankFram.GAME_WIDTH - TANK_WIDTH;

	}

	/**
	 * 让地方坦克随机 换方向
	 */
	private void randomDir() {
		if (this.group != Group.GOOD) {
			this.dir = Dir.values()[random.nextInt(4)];
		}
	}
	/**
	 * 开火 发射子弹
	 */
	public void fire() {
		int bx = this.x + Tank.TANK_WIDTH / 2 - Bullet.BULLE_WIDTH / 2;
		int by = this.y + Tank.TANK_HEIGHT / 2 - Bullet.BULLE_HEIGHT / 2;
		new Bullet(bx, by, this.dir,  this.group);
		if (this.group == Group.GOOD) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					new Audio("audio/tank_fire.wav").play();
					;
				}
			}).start();
		}

	}

	/**
	 * 坦克被击毁
	 */
	public void die() {
		this.living = false;

	}

}
