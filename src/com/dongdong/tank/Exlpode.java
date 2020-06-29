package com.dongdong.tank;

import java.awt.Graphics;


public class Exlpode extends GameObject {

	public static final int HEIGHT=ResourceMgr.explodes[0].getHeight();
	public static final int WIDTH=ResourceMgr.explodes[0].getWidth();
	private int x, y;
	private int step=0;

	public int getX() {
		return x;
	}

	public Exlpode(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		GameMoudel.INSTANCE.add(this);
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
	/**
	 * 播放爆炸动画
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if (step>=ResourceMgr.explodes.length) {
			step=0;
			GameMoudel.INSTANCE.remove(this);
		}
	}
	

}
