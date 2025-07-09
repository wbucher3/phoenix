package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundHandler {
    private Clip clip;
    File[] soundUrl = new File[10];

    public SoundHandler() {
        soundUrl[0] = new File("./assets/music/test-song.wav");
    }


    public void setFile(int index) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {

        }
    }
    public void play() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(0.1));
        this.clip.start();
    }
    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        this.clip.stop();
    }
}
