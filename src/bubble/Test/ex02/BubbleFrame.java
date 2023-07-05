package bubble.Test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//게임맵
public class BubbleFrame extends JFrame{
	
	private JLabel backgroundMap;
	private Player player;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true);
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player();
		add(player);
		
//		backgroundMap = new JLabel("Hello");
//		backgroundMap.setSize(1000,600);
//		add(backgroundMap);//제이프레임에 제이라벨이 그려진다
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); //레이아웃 
		
		setLocationRelativeTo(null); //가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창끌때 같이 배치
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BubbleFrame();
	}
}
