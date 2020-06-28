package com.dongdong.tank.abstracfactory;

import com.dongdong.tank.Exlpode;
import com.dongdong.tank.Group;
import com.dongdong.tank.TankFram;

public class DefaultFactory  extends GameFactory{

	@Override
	public BaseTank createTank(int x, int y, Group group, TankFram tf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasetBullet creatBullet(int x, int y, TankFram tf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseExplode creatExplode(int x, int y, TankFram tf) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
