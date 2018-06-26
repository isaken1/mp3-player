package App;

import IO.FileHandler;

import UI.LoginForm;

import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Isaac Kennnedy
 * @version 1.0
 * Esta classe deve integrar a interface gráfica de login e a classe de gerenciamento de arquivos para facilitar as
 * operações de autenticação.
 */
public class Auth implements ActionListener {

    /**Gerenciador de arquivos que irá retirar a lista de usuários do XML*/
    private FileHandler handler;
    /**Lista de usuários.*/
    private ArrayList<Usuario> usuarios;
    /**Interface gráfica de login.*/
    private LoginForm form;
    /**Variável que irá guardar o usuário que será inserido.*/
    private Usuario logado = null;

    /**Construtor padrão.*/
    public Auth() {
        handler = new FileHandler();
        try {
            usuarios = handler.resgatarUsuarios();
        } catch (ParserConfigurationException parserEx) {
            parserEx.printStackTrace();
            System.out.println("Falha no processamento do arquivo");
        } catch (TransformerException transformerEx) {
            transformerEx.printStackTrace();
            System.out.println("Impossível converter o arquivo.");
        } catch (SAXException saxEx) {
            saxEx.printStackTrace();
            System.out.println("Não foi possível ler o arquivo");
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println("Falha na abertura no arquivo.");
        }

        initUI();
    }

    public void actionPerformed(ActionEvent e) {
        procurarUsuario(form.getUsername(), form.getPassword());
        if (!(logado == null)) {
            form.dispose();
            return;
        }
        JOptionPane.showMessageDialog(form,"Usuário ou senha inccorreto(s)", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void procurarUsuario(String nome, String senha) {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome)) {
                if (u.getSenha().equals(senha)) {
                    logado = u;
                }
            }
        }
    }

    public Usuario getLogado() {
        return this.logado;
    }

    private void initUI() {
        form = new LoginForm(this);
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JFrame getForm(){
        return form;
    }

    public void finish() {
        System.exit(0);
    }
}
