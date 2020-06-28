package com.dongdong.tank.core;

import com.dongdong.tank.Bullet;
import com.dongdong.tank.GameObject;
import com.dongdong.tank.Tank;

public class BulletTankCollider implements Collider {

	@Override
	public void collid(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet&& o2 instanceof Tank) {
			((Bullet)o1).collideWith((Tank)o2);
		}else if(o1 instanceof Tank&& o2 instanceof Bullet) {
			collid(o2, o1);
		}
	}

}
