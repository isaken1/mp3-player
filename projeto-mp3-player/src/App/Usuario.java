package App;

/**
 * @author Isaac Kennedy
 * @version 1.0
 * Classe responsável por abstrair as informações do usuários.
 */
public class Usuario {
    private String nome, senha;
    private boolean vip;
    private int id;

    public Usuario(String nome, String senha, boolean vip, int id) {
        this.nome = nome;
        this.senha = senha;
        this.vip = vip;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
