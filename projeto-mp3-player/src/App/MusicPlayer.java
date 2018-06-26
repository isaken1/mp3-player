package App;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
/**
 *
 * @author ian
 */
public class MusicPlayer {
    private File musica;
    private Player player;
    
    public MusicPlayer(File musica){
        this.musica = musica;
    }
    
    public void play(){
        try{
            FileInputStream stream = new FileInputStream(musica);
            BufferedInputStream buffer = new BufferedInputStream(stream);
            this.player = new Player(buffer);
            this.player.play();
        } catch (Exception e){
            System.out.println("Erro!");
            e.printStackTrace();
        }
    }
}
