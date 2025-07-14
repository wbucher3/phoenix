package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundHandler {
    private Clip clip;
    private boolean isPlaying = false;

    public SoundHandler() {}

    public void stopSetPlayAndLoop(File musicFile) {
        if (this.isPlaying) {
            this.stop();
        }
        this.setFile(musicFile);
        this.play();
        this.loop();
    }

    public void setFile(File file) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void play() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(0.1));
        this.clip.start();
        this.isPlaying = true;
    }
    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        this.clip.stop();
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
