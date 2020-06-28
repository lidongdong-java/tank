package com.dongdong.tank;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Bullet extends GameObject {

	private static final int SPEED = 20;
	public static final int BULLE_HEIGHT=ResourceMgr.bulletD.getHeight();
	public static final int BULLE_WIDTH=ResourceMgr.bulletD.getWidth();
	private int x, y;
	private Dir dir;
	private boolean alive=true;
	private Group group=Group.BAD;
	Rectangle rectangle=new Rectangle();
	GameMoudel gm=new GameMoudel();

	
	public int getX() {
		return x;
	}

	public Bullet(int x, int y, Dir dir,GameMoudel gm,Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gm=gm;
		this.group=group;
		rectangle.height=BULLE_HEIGHT;
		rectangle.width=BULLE_WIDTH;
		gm.add(this);
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

	public static int getSpeed() {
		return SPEED;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {

		if (!alive) {
			gm.remove(this);
		}
		switch (dir) {
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;

		default:
			break;
		}
		move();

	}

	private void move() {

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
		rectangle.x=this.x;
		rectangle.y=this.y;
		if(x<0||y<0||x>TankFram.GAME_WIDTH||y>TankFram.GAME_HEIGHT) alive=false;
	}

	/**
	 *碰撞检测 当子弹碰到坦克时
	 * @param tank
	 */
	public void collideWith(Tank tank) {
		if (this.group==tank.getGroup()) {
			return;
		}
		if (this.rectangle.intersects(tank.rectangle)) {
			tank.die();
			this.die();
			int ex=tank.getX()+Tank.TANK_WIDTH/2-Exlpode.WIDTH/2;
			int ey=tank.getY()+Tank.TANK_HEIGHT/2-Exlpode.HEIGHT/2;
			gm.add(new Exlpode(ex, ey, gm));
			new Thread(new Runnable() {
				@Override
				public void run() {
					new Audio("audio/explode.wav").play();;
				}
			}).start();	
		}
	}

	private void die() {
		this.alive=false;
		
	}

}
