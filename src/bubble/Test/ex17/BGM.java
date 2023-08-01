package bubble.Test.ex17;


import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGM {
	public BGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			
			// 소리설정
			FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			//볼륨조정
			gainControl.setValue(-20);
			
			clip.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
