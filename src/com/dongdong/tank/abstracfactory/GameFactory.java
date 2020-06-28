package com.dongdong.tank.abstracfactory;

import com.dongdong.tank.Group;
import com.dongdong.tank.TankFram;

public abstract class GameFactory {

	public abstract BaseTank createTank(int x, int y,Group group,TankFram tf);
	public abstract BasetBullet creatBullet(int x, int y, TankFram tf);
	public abstract BaseExplode creatExplode(int x,int y,TankFram tf);
}
