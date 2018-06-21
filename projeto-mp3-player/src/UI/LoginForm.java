package UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField txtFieldNome;
    private JPasswordField passFieldSenha;
    private JLabel lblNome;
    private JLabel lblSenha;
    private JButton btnLogin;
    private JButton btnCancel;

    public LoginForm(){

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
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Autenticacao
            }
        });

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

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp,BorderLayout.PAGE_END);

        pack();
        setResizable(false);
    }

    public String getUsername(){
        return txtFieldNome.getText().trim();
    }

    public String getPassword(){
        return (new String(passFieldSenha.getPassword()));
    }

    public boolean sucesso(){
        return true;
    }
}
