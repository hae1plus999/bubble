package bubble.Test.ex17;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Moveable{

	private BubbleFrame mContext;
	//위치상태
	private int x;
	private int y;
	
	//적군의 방향
	private EnemyWay enemyWay;

	//움직임상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	//0 살아있는 상태, 1 물방울에 갇힌상태
	private int state;
	
	//적군 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1; 
	
	private ImageIcon enemyR, enemyL;

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}
	
	private void initSetting() {
		x = 480;
		y = 178;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		state = 0;
		
		enemyWay = EnemyWay.RIGHT;
		
		setIcon(enemyR);
		setSize(50,50);
		setLocation(x,y);
	}

	private void initBackgroundEnemyService() {
//		new Thread(new BackgroundPlayerService(this)).start();
	}
	
	//이벤트핸들러
	@Override
	public void left() {
		enemyWay = EnemyWay.LEFT;
		left =true;
		new Thread(()-> {
			while(left) {
				x = x - SPEED;
				setLocation(x,y);
				setIcon(enemyL);
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
		enemyWay = EnemyWay.RIGHT;
		right = true;
		new Thread(()-> {
			while(right) {
				x = x + SPEED;
				setLocation(x,y);
				setIcon(enemyR);
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
	
	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
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

	public EnemyWay getEnemyWay() {
		return enemyWay;
	}

	public void setEnemyWay(EnemyWay enemyWay) {
		this.enemyWay = enemyWay;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
