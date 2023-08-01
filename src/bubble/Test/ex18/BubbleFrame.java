package bubble.Test.ex18;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//게임맵
public class BubbleFrame extends JFrame{
	
	private BubbleFrame mContext = this;
	
	private JLabel backgroundMap;
	private Player player;
	private Enemy enemy;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player(mContext);
		add(player);
		enemy = new Enemy(mContext);
		add(enemy);
		new BGM();
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); //레이아웃 
		
		setLocationRelativeTo(null); //가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창끌때 같이 배치
	}
	
	// 리스너는 os 가 키보드를 지켜보는게 리스너(while)이다.->이벤트 루프(큐)->이벤트핸들러호출(스택)
	// 지금은 스레드가 하나밖에 없다. 오른쪽, 점프, 왼쪽, 하나만 전달이 된다.
	private void initListener() {
		addKeyListener(new KeyAdapter() {
		
			//키보드 클릭 핸들러
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if(!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}
						break;
					case KeyEvent.VK_RIGHT:
						if(!player.isRight() && !player.isRightWallCrash()) {
							player.right();
						}
						break;
					case KeyEvent.VK_UP:
						if(!player.isUp() && !player.isDown()) {
							player.up();
						}
						break;
					case KeyEvent.VK_SPACE:
						player.attack();
						break;
				}
			}
			//키보드 해제 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						player.setLeft(false);
						break;
					case KeyEvent.VK_RIGHT:
						player.setRight(false);
						break;
				}
			}
			
		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
}
