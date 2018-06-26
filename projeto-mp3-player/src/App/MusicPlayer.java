package App;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javazoom.jl.player.Player;
/**
 *
 * @author ian
 * Classe responsável por reproduzir as musicas
 */
public class MusicPlayer {
    private Player player;
    FileInputStream stream;
    BufferedInputStream buffer;
    private boolean playing = false;
    
    /**
     * Construtor padrão
     */
    public MusicPlayer(){
    }
    
    /**
     * Esta função é responsável pela reprodução da musica.
     * @param musica Arquivo de musica a ser reproduzido.
     */
    public boolean play(File musica){
        try {
            if (!isPlaying()) {
                stream = new FileInputStream(musica);
                buffer = new BufferedInputStream(stream);
                this.player = new Player(buffer);
                playing = true;
                System.out.println("Começou");
                this.player.play();
                System.out.println("Terminou");
                playing = false;
            }
        } catch (Exception e){
            System.out.println("Erro!");
            e.printStackTrace();
        }

        return false;
    }

    public void stop() {
        if (playing) {
            try {
                this.stream.close();
                this.buffer.close();
                this.player.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Não foi possível fechar a Stream");
            }
            playing = false;
        }
    }

    public boolean isPlaying() {
        return playing;
    }
}
