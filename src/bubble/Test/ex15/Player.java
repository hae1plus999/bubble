package bubble.Test.ex15;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//게임캐릭터추가
public class Player extends JLabel implements Moveable{
	
	private BubbleFrame mContext;
	//위치상태
	private int x;
	private int y;
	
	//플레이어의 방향
	private PlayerWay playerWay;

	//움직임상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	//벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	//플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2; 
	
	private ImageIcon playerR, playerL;

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 80;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		
		playerWay = PlayerWay.RIGHT;
		
		setIcon(playerR);
		setSize(50,50);
		setLocation(x,y);
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}
	
	@Override
	public void attack() {
		new Thread(()->{
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			if(playerWay == PlayerWay.LEFT) {
				bubble.left();
			} else {
				bubble.right();
			}
		}).start();
	}
	
	//이벤트핸들러
	@Override
	public void left() {
		playerWay = PlayerWay.LEFT;
		left =true;
		new Thread(()-> {
			while(left) {
				x = x - SPEED;
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
		playerWay = PlayerWay.RIGHT;
		right = true;
		new Thread(()-> {
			while(right) {
				x = x + SPEED;
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

	//left + up, right + up
	@Override
	public void up() {
		up = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x,y);
		
				try {
					Thread.sleep(5);
				}catch(Exception e){
					e.printStackTrace();
				}	
			}
			up = false;
			down();
			
		}).start();
	}

	@Override
	public void down() {
	
		down = true;
		new Thread(()->{
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x,y);
		
				try {
					Thread.sleep(3);
				}catch(Exception e){
					e.printStackTrace();
				}	
			}
			down = false;
		}).start();
	}
	
	public Player(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
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
	
	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}
	public PlayerWay getPlayerWay() {
		return playerWay;
	}

	public void setPlayerWay(PlayerWay playerWay) {
		this.playerWay = playerWay;
	}
}
