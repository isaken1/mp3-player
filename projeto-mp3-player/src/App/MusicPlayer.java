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
 * Classe responsável por reproduzir as musicas
 */
public class MusicPlayer {
    private File musica;
    private Player player;
    
    /**
     * Construtor padrão
     */
    public MusicPlayer(){
    }
    
    /**
     * Esta função é responsável pela reprodução da musica.
     * @param musica Arquivo de musica a ser reproduzido.
     */
    public void play(File musica){
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
