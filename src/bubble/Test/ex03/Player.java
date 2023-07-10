package bubble.Test.ex03;

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
		x = x - 10;
		setLocation(x,y);
		setIcon(playerL);
	}

	@Override
	public void right() {
		
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		x = x + 10;
		setLocation(x,y);
		setIcon(playerR);
	}

	@Override
	public void up() {
		
	}

	@Override
	public void down() {
		
	}
}
