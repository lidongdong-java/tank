package com.dongdong.tank;

public class FourDirFireStategy implements FireStrategy {

	@Override
	public void fire(Tank tank) {
		// TODO Auto-generated method stub
		
		int bx = tank.x + Tank.TANK_WIDTH / 2 - Bullet.BULLE_WIDTH / 2;
		int by = tank.y + Tank.TANK_HEIGHT / 2 - Bullet.BULLE_HEIGHT / 2;
		Dir[] dirs=Dir.values();
		for (int i = 0; i < dirs.length; i++) {
			new Bullet(bx, by, dirs[i], tank.gm, tank.group);
		}
		
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
