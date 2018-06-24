package UI;

import App.Auth;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Isaac Kennedy
 * @author Ian Honorato
 * @version 1.0;
 * Classe responsável por inicializar a tela de login.
 */
public class LoginForm extends JFrame {

    /** Campo de texto para o nome do usuário */
    private JTextField txtFieldNome;
    /** Campo de texto para a senha do usuário */
    private JPasswordField passFieldSenha;
    private JLabel lblNome;
    private JLabel lblSenha;
    /** Botão de login */
    private JButton btnLogin;
    /** Botão de cancelamento */
    private JButton btnCancel;

    /**
     * Construtuor padrão que inicializa a interface e todos os seus elementos
     * @param parent Um objeto do tipo Auth para que ele gerencie a conexão com o gerenciador de arquivos.
     */
    public LoginForm(Auth parent) {

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;

        lblNome = new JLabel("Usuário: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(lblNome, constraints);

        txtFieldNome = new JTextField(30);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(txtFieldNome, constraints);

        lblSenha = new JLabel("Senha: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(lblSenha, constraints);

        passFieldSenha = new JPasswordField(30);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(passFieldSenha, constraints);

        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Entrar");
        btnLogin.addActionListener(parent);

        btnCancel = new JButton("Cancelar");

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        txtFieldNome.requestFocus();
        KeyAdapter k = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        };
        txtFieldNome.addKeyListener(k);
        passFieldSenha.addKeyListener(k);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp,BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public String getUsername(){
        return txtFieldNome.getText().trim();
    }

    public String getPassword(){
        return (new String(passFieldSenha.getPassword()));
    }
}
