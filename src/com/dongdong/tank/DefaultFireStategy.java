package com.dongdong.tank;

public class DefaultFireStategy implements FireStrategy {

	@Override
	public void fire(Tank tank) {
		
		int bx = tank.x + Tank.TANK_WIDTH / 2 - Bullet.BULLE_WIDTH / 2;
		int by = tank.y + Tank.TANK_HEIGHT / 2 - Bullet.BULLE_HEIGHT / 2;
		new Bullet(bx, by, tank.dir, tank.gm, tank.group);
		if (tank.group==Group.GOOD) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					new Audio("audio/tank_fire.wav").play();;
				}
			}).start();	
		}
	}

}
