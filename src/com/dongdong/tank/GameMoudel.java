package com.dongdong.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.dongdong.tank.core.BulletTankCollider;
import com.dongdong.tank.core.Collider;

public class GameMoudel {
	
	//创建我方坦克
	Tank myTank = new Tank(400, 800, Dir.DOWN,this,Group.GOOD,5);
	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
	Collider collider=new BulletTankCollider();
	//爆炸
//	List<Tank> tanks=new ArrayList<Tank>();
//	List<Exlpode> exlpodes=new ArrayList<>();
//	//子弹集合
//	List<Bullet> bullets=new ArrayList<Bullet>();
	List<GameObject> objects=new ArrayList<GameObject>();
	public GameMoudel() {
		
	int initCount=Integer.parseInt((String) PropertyMgr.get("tansNumber"));
		myTank.isMoving=false;
		for (int i = 1; i < initCount; i++) {
			add(new Tank(i*80, 200, Dir.DOWN, this,Group.BAD,1));
		}
		add(myTank);
	}
	public void add(GameObject go) {
		this.objects.add(go);
	}
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
	/**
	 * 画笔 间隔刷新
	 * @param g
	 */
	public void paint(Graphics g) {
		Color color=g.getColor();
		g.setColor(Color.WHITE);
//		g.drawString("子弹数量"+bullets.size(), 10, 60);
//		g.drawString("敌人数量"+tanks.size(), 10, 80);
		g.setColor(color);
		myTank.paint(g);
		for (int i=0;i<objects.size();i++) {
			objects.get(i).paint(g);
		}
//		for (int i=0;i<tanks.size();i++) {
//			tanks.get(i).paint(g);
//		}
		//子弹碰撞检测
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i+1; j < objects.size(); j++) {
				collider.collid(objects.get(i), objects.get(j));
			}
		}
//		for (int i=0;i<exlpodes.size();i++) {
//			exlpodes.get(i).paint(g);
//		}
	}
	public Tank getMaintank() {
		// TODO Auto-generated method stub
		return myTank;
	}
}
