import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
/**
 * Décrivez votre classe Audio ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Audio
{
    private Clip aClip;
    
    public Audio(String pSon) {
        try {
            AudioInputStream vAudio = AudioSystem.getAudioInputStream(getClass().getResource(pSon));
            this.aClip = AudioSystem.getClip();
            this.aClip.open(vAudio);
            
        } catch (Exception e) {}
    }
    
    public Clip getClip() {
        return this.aClip;
    }
    
    public void play() {
        this.aClip.start();
    }
    
    public void stop() {
        this.aClip.stop();
    }
    
    public static void playSound(String pSon) {
        Audio vS = new Audio(pSon);
        vS.play();
    }
    
}
