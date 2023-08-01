package bubble.Test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel implements Moveable{
	
	//의존성 콤포지션
	private BubbleFrame mContext;
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;
	
		//위치상태
		private int x;
		private int y;

		//움직임상태
		private boolean left;
		private boolean right;
		private boolean up;
		
		// 적군을 맞춘상태
		private int state; //0 물방울, 1 적을 가둔 물방울
		
		private ImageIcon bubble; //물방울 
		private ImageIcon bubbled; //적을 가둔 물방울 
		private ImageIcon bomb; //물방울이 터진 상태
		
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
//		initThread();
	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		
		state = 0;
	}
	
//	private void initThread() {
//		//버블은 스레드가 하나만 필요하다
//		new Thread(() -> {
//			if(player.getPlayerWay() == PlayerWay.LEFT) {
//				left();
//			} else {
//				right();
//			}
//		}).start();
//	}

	@Override
	public void left() {
		left = true;
		for(int i=0; i<400; i++) {
			x--;
			setLocation(x,y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0; i<400; i++) {
			x++;
			setLocation(x,y);
			
			if(backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(up) {
			y--;
			setLocation(x,y);
			
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//천장에 버블이 도착하고 나서 3초후에 메모리에서 소멸
		clearBubble();
	}
	
	//메인을 가진 클래스는 모든 객체의 정보를 가지고 있다.
	//행위 -> clear(동사) -> bubble(목적어)
	private void clearBubble() {
		try {
			Thread.sleep(2000);
			setIcon(bomb);
			Thread.sleep(500);
			//버블이 메모리에서 소멸
			mContext.remove(this);
			//버블프레임에서 다시 그린다. 메모리에 없는 건 그리지 않음 
			mContext.repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
