package com.dongdong.tank.core;

import java.util.LinkedList;
import java.util.List;

import com.dongdong.tank.GameObject;

public class ColliderChain  implements Collider{
	private List<Collider> colliders=new LinkedList<Collider>();
	
	
	public ColliderChain() {
		add(new BulletTankCollider());
		add(new TankTankCollider());
	}


	public void add(Collider c) {
		colliders.add(c);
		
	}


	public void collid(GameObject gameObject, GameObject gameObject2) {
		for (int i = 0; i < colliders.size(); i++) {
			colliders.get(i).collid(gameObject, gameObject2);
		}
		
	}



}
