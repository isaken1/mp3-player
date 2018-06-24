package App;

import IO.FileHandler;

import UI.LoginForm;
import org.xml.sax.SAXException;
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

    private FileHandler handler;
    private ArrayList<Usuario> usuarios;
    private LoginForm form;
    private Usuario logado;

    public Auth() {
        handler = new FileHandler();
        form = new LoginForm(this);
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
        form.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
       if (procurarUsuario(form.getUsername(), form.getPassword()) != null) {

       }
    }

    private Usuario procurarUsuario(String nome, String senha) {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome)) {
                if (u.getSenha().equals(senha)) {
                    return u;
                }

            }
        }
        return null;
    }

}
