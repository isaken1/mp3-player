package UI;

import App.Usuario;

import IO.FileHandler;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainForm extends JFrame {

    Usuario logado;

//    private JMenuBar menuBar;
//    private JMenu fileMenu;
    private JPanel leftMainLayout;

    //Painel esquerdo
    private JPanel leftPanel;
    private JButton btnAddDirectory;
    private JButton btnAddFile;

    //Painel central
    private JPanel centerPanel;
    private JLabel lblMusicas;
    private JLabel lblPlaylist;
    private JScrollPane scrollPaneMusicas;
    private JScrollPane scrollPanePlaylist;

    //Painel inferior
    private JPanel lowerPanel;
    private JButton btnAnterior;
    private JButton btnPlay;
    private JButton btnProximo;
    private JProgressBar progressBarMusica;

    //Painel direito
    private JPanel rightPanel;
    private JLabel lblUsuario;
    private JLabel lblVip;
    private JLabel lblPlaylists;
    private JScrollPane scrollPanePlaylists;
    private JButton btnNovaPlaylist;

    public MainForm(Usuario u) {
        logado = u;
        initUI();
    }

    private void initUI() {
        this.setTitle("MP3 Player");
        this.setLayout(new GridBagLayout());

        leftMainLayout = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

//        menuBar = new JMenuBar();
//        fileMenu = new JMenu("Aplicação");
//        JMenuItem menuItem = new JMenuItem("Item");
//        fileMenu.add(menuItem);
//        menuBar.add(fileMenu);
//
//
//        this.setJMenuBar(menuBar);
        initLeftPane();
        initCenterPane();
        initLowerPane();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        this.add(leftMainLayout, c);

        initRightPane();

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 600);
        this.setResizable(false);
    }

    private void initLeftPane() {

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        btnAddDirectory = new JButton("Adicionar diretório");
        btnAddDirectory.addActionListener(new BtnAdDirec());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;

        leftPanel.add(btnAddDirectory, c);

        btnAddFile = new JButton("Adicionar música");
        btnAddFile.addActionListener(new BtnAdMusic());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(20, 0, 100, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = GridBagConstraints.REMAINDER;

        leftPanel.add(btnAddFile, c);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.9;
        c.weightx = 0.8;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        this.leftMainLayout.add(leftPanel, c);

    }

    private void initCenterPane() {
        centerPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        lblMusicas = new JLabel("Músicas");
        lblPlaylist = new JLabel("Playlist");
        scrollPaneMusicas = new JScrollPane();
        scrollPanePlaylists = new JScrollPane();

        lblMusicas.setVerticalAlignment(JLabel.CENTER);
        lblPlaylist.setVerticalAlignment(JLabel.CENTER);
        lblMusicas.setHorizontalAlignment(JLabel.CENTER);
        lblPlaylist.setHorizontalAlignment(JLabel.CENTER);
        lblMusicas.setHorizontalTextPosition(JLabel.CENTER);
        lblPlaylist.setHorizontalTextPosition(JLabel.CENTER);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 20, 5, 20);
        c.weighty = 0.1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;

        centerPanel.add(lblMusicas, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 20, 5, 20);
        c.weighty = 0.1;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;

        centerPanel.add(lblPlaylist, c);

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 20, 30, 20);
        c.weighty = 0.9;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;

        centerPanel.add(scrollPaneMusicas, c);

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 20, 30, 20);
        c.weighty = 0.9;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;

        centerPanel.add(scrollPanePlaylists, c);

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.weighty = 0.9;
        c.weightx = 0.8;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;

        this.leftMainLayout.add(centerPanel, c);
    }

    private void initLowerPane() {

        lowerPanel = new JPanel(new GridBagLayout());
        btnAnterior = new JButton("<<");
        btnPlay = new JButton("Play/Pause");
        btnProximo = new JButton(">>");
        progressBarMusica = new JProgressBar();

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 0.08;

        lowerPanel.add(btnAnterior, c);

        c.gridy = 0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 0.08;

        lowerPanel.add(btnPlay, c);

        c.gridy = 0;
        c.gridx = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 0.08;

        lowerPanel.add(btnProximo, c);

        c.gridy = 0;
        c.gridx = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.76;

        lowerPanel.add(progressBarMusica, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.2;
        c.weightx = 0.8;
        c.anchor = GridBagConstraints.LAST_LINE_START;

        this.leftMainLayout.add(lowerPanel, c);
    }

    private void initRightPane() {
        rightPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        lblUsuario = new JLabel("Usuário: " + logado.getNome());
        lblVip = new JLabel("Tipo de conta: " + ((logado.isVip()) ? "VIP" : "Comum"));
        lblPlaylists = new JLabel("Playlists");
        scrollPanePlaylists = new JScrollPane();
        btnNovaPlaylist = new JButton("Nova playlist");

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 10, 30, 10);

        rightPanel.add(lblUsuario, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 30, 10);
        c.anchor = GridBagConstraints.PAGE_START;

        rightPanel.add(lblVip, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 2;

        rightPanel.add(lblPlaylists, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 0.3;
        c.anchor = GridBagConstraints.CENTER;

        rightPanel.add(scrollPanePlaylists, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 4;

        rightPanel.add(btnNovaPlaylist, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.2;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        this.add(rightPanel, c);

    }

    public class BtnAdMusic implements ActionListener {

        private void inserir(File musica) {
            FileHandler f = new FileHandler();
            f.inserirMusica(musica);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooserArquivo = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Músicas .MP3", "mp3");
            chooserArquivo.setFileFilter(filter);
            int escolha = chooserArquivo.showOpenDialog(getParent());
            String arquivo = chooserArquivo.getSelectedFile().getAbsolutePath();
            File caminho = new File(arquivo);
            inserir(caminho);
        }
    }

    public class BtnAdDirec implements ActionListener {

        private void inserir(File musica) {
            FileHandler f = new FileHandler();
            f.inserirMusica(musica);
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser chooserDiretorio = new JFileChooser();
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("Músicas .MP3", "mp3");
            //chooserDiretorio.setFileFilter(filter);
            chooserDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //chooserDiretorio.setMultiSelectionEnabled(true);
            chooserDiretorio.showOpenDialog(getParent());
            File pasta = chooserDiretorio.getSelectedFile();
            File[] musicas = pasta.listFiles();
            JFileChooser auxiliar = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Músicas .MP3", "mp3");
            auxiliar.setFileFilter(filter);
            auxiliar.setSelectedFiles(musicas);
            File[] arq = auxiliar.getSelectedFiles();

            for (int i = 0; i < arq.length; i++) {
                inserir(arq[i]);
            }
        }
    }
}
