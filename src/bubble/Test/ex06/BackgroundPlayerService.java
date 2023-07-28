package bubble.Test.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//백그라운드에서 계속 관찰
//메인스레드 바쁨 - 키보드 이벤트를 처리하기 바쁨
public class BackgroundPlayerService implements Runnable{
	
	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/test.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			//색상확인
			Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
