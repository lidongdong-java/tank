package com.dongdong.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankFram extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

	public TankFram() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setLocation(800, 0);
		setTitle("Main war");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new MyKeyListener());
		setVisible(true);
	}
	/**
	 * 相当于刷新  在主线程中每次间隔30毫秒做一次刷新
	 */

	@Override
	public void paint(Graphics g) {
		GameMoudel.INSTANCE.paint(g);
	}

	Image offScreenImage = null;

	/**
	 * 优化闪烁问题
	 */
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics graphics=offScreenImage.getGraphics();
		Color color=graphics.getColor();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		graphics.setColor(color);
		paint(graphics);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	class MyKeyListener extends KeyAdapter {

		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
		/**
		 * 获取键值
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			default:
				break;
			}
			setMainDir();

		}

		@Override
		public void keyReleased(KeyEvent e) {
		
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				GameMoudel.INSTANCE.getMaintank().fire();
				
				break;
			default:
				break;
			}
			setMainDir();
		}

		/**
		 * 根据键值 改变坦克方向 或者发射子弹
		 */
		private void setMainDir() {
			Tank myTank=GameMoudel.INSTANCE.getMaintank();
			if (!bD && !bL && !bR && !bU) {
				myTank.setMoving(false);
			} else {
				myTank.setMoving(true);
				if (bL)
					myTank.setDir(Dir.LEFT);
				if (bU)
					myTank.setDir(Dir.UP);
				if (bD)
					myTank.setDir(Dir.DOWN);
				if (bR)
					myTank.setDir(Dir.RIGHT);

			}

		}
	}
}
