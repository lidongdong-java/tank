package com.dongdong.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		TankFram fm=new TankFram();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new Audio("audio/war1.wav").loop();
			}
		}).start();
		
		while (true) {
			Thread.sleep(50);
			fm.repaint();
		}
		
	}

}
