package bubble.Test.ex04;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//게임캐릭터추가
public class Player extends JLabel implements Moveable{
	//위치상태
	private int x;
	private int y;

	//움직임상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private ImageIcon playerR, playerL;
	
	public Player() {
		initObject();
		initSetting();
	}
	
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 55;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		setIcon(playerR);
		setSize(50,50);
		setLocation(x,y);
	}
	//이벤트핸들러
	@Override
	public void left() {
		left =true;
		new Thread(()-> {
			while(left) {
				x = x - 10;
				setLocation(x,y);
				setIcon(playerL);
				try {
					//0.01초
					Thread.sleep(10);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		
//		try {
//			Thread.sleep(2000);
//		}catch(InterruptedException e) {
//			e.printStackTrace();
//		}
		
		right = true;
		new Thread(()-> {
			while(right) {
				x = x + 10;
				setLocation(x,y);
				setIcon(playerR);
				try {
					//0.01초
					Thread.sleep(10);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		
	}

	@Override
	public void down() {
		
	}
}
