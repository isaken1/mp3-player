package App;

import java.io.File;

public class Musica {
    private String nome;
    private String caminho;

    public Musica(File file) {
        this.caminho = file.getPath();
        this.nome = file.getName();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
