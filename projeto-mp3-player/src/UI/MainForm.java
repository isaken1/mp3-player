package UI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class MainForm extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JPanel leftMainLayout;

    private JPanel leftPanel;
    private JButton btnAddDirectory;
    private JButton btnAddFile;

    private JPanel centerPanel;
    private JLabel lblMusicas;
    private JLabel lblPlaylist;
    private JScrollPane scrollPaneMusicas;
    private JScrollPane scrollPanePlaylists;

    private JPanel lowerPanel;
    private JButton btnAnterior;
    private JButton btnPlay;
    private JButton btnProximo;
    private JProgressBar progressBarMusica;

    public MainForm() {
        initUI();
    }

    private void initUI() {
        this.setTitle("MP3 Player");
        this.setLayout(new GridBagLayout());

        leftMainLayout = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        fileMenu.setName("Aplicação");
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

        initLeftPane();
        initCenterPane();
        initLowerPane();

        this.add(leftMainLayout, c);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800,600);
        this.validate();
        //this.setResizable(false);

    }

    private void initLeftPane() {

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        btnAddDirectory = new JButton("Adicionar diretório");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(20, 5, 0, 0);
        c.gridx = 0;
        c.gridy = 0;

        leftPanel.add(btnAddDirectory, c);

        btnAddFile = new JButton("Adicionar música");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(20, 20, 100, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = GridBagConstraints.REMAINDER;

        leftPanel.add(btnAddFile, c);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.9;
        c.weightx = 0.9;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;

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
        c.weighty = 0.0;
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
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 1;
        c.gridy = 0;

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
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.76;

        lowerPanel.add(progressBarMusica, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.2;
        c.weightx = 0.9;
        c.anchor = GridBagConstraints.LAST_LINE_START;

        this.leftMainLayout.add(lowerPanel, c);
    }

    public void valueChanged(ListSelectionEvent e) {
        //TODO
    }


}
